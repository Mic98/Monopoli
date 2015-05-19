/**
 * 
 */
package main;

import java.util.Vector;

/**
 * @author mrmoddom
 *
 */
public class Tabellone {

	private Vector<Casella> caselle;
	private Vector<Giocatore> giocatori;
	private int turni_attuali; //Variabile che tiene conto dei turni 
	
	public Tabellone(){
		//Inizializzazione dei dati delle caselle
		caselle = new Vector<Casella>();
		initCaselle();
		
		//Creazione del vettore del giocatore
		giocatori = new Vector<Giocatore>();
		
		turni_attuali = 0;
	}
	
	public void addGiocatore(Vector<Giocatore> plrs){
		giocatori = plrs;
	}
	
	private void initCaselle(){
		String[] nomiCaselle = Data.getNomiCaselle();
		int i=0;
		
		for(String str : nomiCaselle){
			caselle.add(new Casella(str, i));
			i++;
		}
	}
	
	public void movePlayer(Giocatore g, int step){		
		int dest = g.getPedina().getPosizione() + step;
		
		if(dest >= 40)
			dest = dest - 40;
		
		g.getPedina().setPosizione(dest);
		
	}
	
	public void teleportPlayer(Giocatore g, int casella){
		g.getPedina().setPosizione(casella);
	}
	
//	public Casella aggiungiCasella(Casella in){
//		caselle.add(in);
//		return in;
//	}
}
