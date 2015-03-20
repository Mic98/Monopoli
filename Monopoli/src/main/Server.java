package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Vector;

public class Server {
    
    Vector<ClientThread> clients= new Vector<ClientThread>();
    ServerSocket server;
    boolean active;
    
    public Server(int _port){
        //Creazione servizio server
        try{
            server=new ServerSocket(_port);
            display("Server attivo sulla porta "+_port);
            active=true;
        } catch (IOException ex){
            display("Errore creazione servizio!!! Controllare che la porta non sia gia' in uso!");
        }
    }
    
    public void inizio(){
        while(active){
            try{
                addThread(server.accept());
            }catch (IOException ex){
                display("Errore aggiunta connessione!"+ex.getMessage());
            }
        }
    }
    
    private synchronized void addThread(Socket accept) {
        clients.add(new ClientThread(this,accept));
        clients.lastElement().start();
    }
    
    public synchronized void removeThread(String nick){
        int pos=findThread(nick);
        if(pos==-1) display("Errore:utente non trovato");
        else{
            clients.get(pos).close();
            clients.remove(pos);
        }
    }
    
    public void retriveList(String nick){
        int pos=findThread(nick);
        
        if(pos==-1) display("Errore: utente non trovato");
        else{
            StringBuffer list= new StringBuffer();
        
            for(int i=0; i<clients.size(); i++)
                list.append((i+1)+") "+clients.get(i).getNick()+"\n");
            
            Messaggio lista=new Messaggio("Server","Lista utenti connessi:\n"+list.toString(), Messaggio.MESSAGE);
        
            clients.get(pos).send(lista);
        }
    }
    
    public synchronized void broadcast(Messaggio msg){
        for(int i=0; i<clients.size(); i++)
            clients.get(i).send(msg);
    }
    private int findThread(String nick){
        int pos;
        for(pos=0; pos<clients.size(); pos++){
            if(clients.get(pos).getNick().equalsIgnoreCase(nick))
                return pos;
        }
        return -1;
    }
    
    class ClientThread extends Thread{
        
        Socket socket;
        Server server;
        private ObjectOutputStream sOut;
        private ObjectInputStream sIn;
        String nick;
        boolean active=true;
        
        public ClientThread (Server _server, Socket _socket){
            socket=_socket;
            server=_server;
            
            //Apertura stream & login
            try{
                sOut= new ObjectOutputStream(socket.getOutputStream());
                sIn= new ObjectInputStream(socket.getInputStream());
                nick=(String) sIn.readObject();
                sOut.writeObject("Welcome!");
                sOut.flush();                
            } catch (IOException ex){
                display("Errore connessione");
            } catch (ClassNotFoundException ex){
                display("Errore login!");
            }
            
            display(nick+" si e' connesso!");
            active=true;
        }
        
        public void run(){
            while(active){
                try{
                    Messaggio input=(Messaggio) sIn.readObject();
                    
                    switch(input.getType()){
                        case (Messaggio.LOGOUT):{
                            server.removeThread(this.nick);
                            display(nick+ " si e' disconnesso tramite comando LOGOUT");
                            active=false;
                            break;
                        }
                        case (Messaggio.WHOISIN):{
                            server.retriveList(this.nick);
                            break;
                        }
                        case (Messaggio.MESSAGE):{
                            server.broadcast(input);
                            display(this.nick+": "+input.getMsg());
                            break;
                        }
                    }
                } catch (IOException ex){
                    display("Errore ricezione messaggio da "+nick);
                    removeThread(this.nick);
                } catch (ClassNotFoundException cex){
                    display("Errore oggetto ricevuto da "+nick);
                }
            }
            close();
            display(nick+" disconnesso");
        }
        
        public void send(Messaggio msg){
            try{
                sOut.writeObject(msg);
                sOut.flush();
            } catch (IOException ex){
                display("Errore invio messaggio a "+nick);
            }
        }
        
        public String getNick(){
            return nick;
        }
        
        void close(){
            try{
                if(sOut != null) sOut.close();
                if(sIn != null) sIn.close();
                if(socket != null) socket.close();
                this.active=false;
            } catch (IOException ex){
                display("Errore chiusura connessione "+nick);
            }
        }
    }
    
    void display(String msg){
        System.out.println(msg);
    }
    
    public static void main(String args[]){
        int portNumber = 1500;
		switch(args.length) {
			case 1:
				try {
					portNumber = Integer.parseInt(args[0]);
				}
				catch(Exception e) {
					System.out.println("Porta non valida");
					System.out.println("Per usare: > java Server [portNumber]");
					return;
				}
			case 0:
				break;
			default:
				System.out.println("Per usare: > java Server [portNumber]");
				return;
				
		}
        Server server1=new Server(portNumber);
        server1.inizio();
        System.out.println("Server chat terminato.");
    }
}