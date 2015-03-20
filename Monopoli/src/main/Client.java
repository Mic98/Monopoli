package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    String ipServer, nick;
    int serverPort;
    private Socket socket;
    private ObjectInputStream sIn;
    private ObjectOutputStream sOut;
    private Listener orecchio;
    boolean active;
    
    Messaggio msg;
    
    public Client(String _nick, String _ipServer, int _serverPort){
        nick=_nick;
        ipServer=_ipServer;
        serverPort=_serverPort;
        
        //Apertura connessione e streams
        try{
            socket=new Socket(ipServer, serverPort);
            sOut= new ObjectOutputStream(socket.getOutputStream());
            sIn= new ObjectInputStream(socket.getInputStream());
            display("Connesso!\n");
        }catch( IOException ex){
            display("Errore connessione: "+ex.toString());
        }
        
        //Login
        try{
            sOut.writeObject(nick);
            sOut.flush();
            String welcome= (String) sIn.readObject();
            display(welcome+"\n");
        } catch (IOException ex){
            display("Errore login: "+ ex.toString());
        } catch (ClassNotFoundException cex){
            display("Errore improbabile");
        }
        
        //Start listener
        orecchio= new Listener();
        orecchio.start();
        
        active=true;
    }
    
    public void inizio(){
        
        Scanner console=new Scanner(System.in);
        display(">");
        while(active){
            try{
                String input=console.nextLine();
                if(input.equalsIgnoreCase("LOGOUT")){
                    sOut.writeObject(new Messaggio(nick,"LOGOUT", Messaggio.LOGOUT));
                    active=false;
                } else if(input.equalsIgnoreCase("WHOISIN")){
                    sOut.writeObject(new Messaggio(nick,"WHOISIN", Messaggio.WHOISIN));
                } else {
                    sOut.writeObject(new Messaggio(nick,input,Messaggio.MESSAGE));
                }
                sOut.flush();
            } catch (IOException ex){
                display(("Errore invio messaggio: "+ex.toString()));
            }
        }
        
        orecchio.stop();
        close();
    }
    
    void close(){
        try{
            if( sOut != null) sOut.close();
            if( sIn != null) sIn.close();
            if( socket !=null) socket.close();
            this.active=false;
            System.exit(-1);
        }catch(IOException ex){
            display("Errore chiusura connessione: "+ex.toString());
        }
    }
    
    class Listener extends Thread{
        public void run(){
            while (active){
                try{
                    Messaggio input=(Messaggio) sIn.readObject();
                    display("\r");
                    display(input.getNick()+": "+input.getMsg());
                    display("\n>");
                } catch (IOException ex){
                    display("Errore ricezione messaggio: "+ex.toString());
                    display("Il server ha interrotto la connessione!!!");
                    close();
                    active=false;
                } catch (ClassNotFoundException cex){
                    display("Errore ricezione oggetto: "+cex.toString());
                }
            }
        }
    }
            
    void display(String msg){
        System.out.print(msg);
    }
    
    public static void main(String args[]){
        
        int porta = 1500;
	String serverIP = "127.0.0.1";
	String username = "Anonymous";
	switch(args.length) {
		case 3:
			serverIP = args[2];
		case 2:
			try {
				porta = Integer.parseInt(args[1]);
			}
			catch(Exception e) {
				System.out.println("Porta non valida.");
				System.out.println("Per usare: > java Client [username] [portNumber] [serverAddress]");
				return;
			}
		case 1: 
			username = args[0];
		case 0:
			break;
		default:
			System.out.println("Per usare: > java Client [username] [portNumber] {serverAddress]");
		return;
	}
       
        Client client = new Client(username, serverIP, porta);
        client.inizio();
        System.out.println("\n");
        System.out.println("-----------SESSIONE CLIENT TERMINTA-----------");
    }
}
