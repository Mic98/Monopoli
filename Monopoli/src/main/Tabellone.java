/**
 * 
 */
package main;

import java.util.Collections;
import java.util.Vector;
import java.io.*;

import caselle.*;
import utilities.BelleStringhe;
import utilities.MyUtil;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tabellone implements Serializable {

	private final static String MESS_VUOTO = "\nLa lista giocatori e' vuota \n\n";
	private final static int BONUS_VIA = 500;
	private final static String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai 500 euro di bonus \n";
	
	
	private Vector<Casella> caselle;
	private Vector<Giocatore> elencoGiocatori;
	private Vector<Giocatore> classificaFinale;
	private int turnoGiocatore;
	private int turniAttuali; 

	public Tabellone() {
	
		caselle = new Vector<Casella>();
		elencoGiocatori = new Vector<Giocatore>();
		classificaFinale = new Vector<Giocatore>();
		turniAttuali = 1;
		turnoGiocatore = 0;
	}

	/**
	 * Mescola l'ordine del vettore elencoGiocatori
	 * 
	 * @param plrs vettore contentente la lista dei giocatori
	 */
	public void mescolaGiocatori(Vector<Giocatore> plrs) {
		elencoGiocatori = plrs;
		Collections.shuffle(plrs);
	}
	
	/**
	 * Sposta il giocatore lungo il tabellone, verificando l'avvenuto passaggio dal via
	 * 
	 * @param g giocatore da spostare
	 * @param step risultato dato dal tiro dei dadi
	 */
	public void muoviGiocatore(Giocatore g, int step) {
		int dest = g.getPosizione() + step;

		if (dest >= 40){
			dest = dest - 40;
			System.out.println(MESSAGGIO_VIA);
			g.setCapitale(g.getCapitale() + BONUS_VIA);
		}

		g.setPosizione(dest);

	}

	/**
	 * Sposta il giocatore senza farlo passare dal via
	 * 
	 * @param g giocatore da spostare
	 * @param casella destinazione del giocatore
	 */
	public void teleportGiocatore(Giocatore g, int casella) {
		g.setPosizione(casella);
	}
	

	public void aggiungiCasella(Casella c) {
		caselle.add(c);
		
	}
	
	/**
	 * Verifica che l'input dell'utente non sia gia' stato inserito
	 *  
	 * @param daControllare input dell'utente da controllare
	 * @param nuoviGiocatori vettore di nomi gia“ inseriti
	 * @return true se il nome e' gia' stato inserito
	 */
	public boolean esisteGia(String daControllare, Vector<Giocatore> nuoviGiocatori){
		for(Giocatore g: nuoviGiocatori)
		      if(g.getNome().equalsIgnoreCase(daControllare))
		               return true;
		           
		return false;
	}
	
	
	/**
	 * Mette in ordine di capitale i giocatori una volta finita la partita
	 */
	public void classifica(){
		int i = elencoGiocatori.size();
		while (i>0){
			classificaFinale.addElement(elencoGiocatori.get(ilPiuPovero()));
			elencoGiocatori.remove(elencoGiocatori.get(ilPiuPovero()));
			
			i--;
		}	
	}
	
	/**
	 * 
	 * @return il vettore dei giocatori vincenti
	 */
	public Vector<Giocatore> trovaVincitori(){
		Vector<Giocatore> vincenti = new Vector <Giocatore>();
		
		for(int i=classificaFinale.size(); i>0; i--){
			if(classificaFinale.lastElement().riccoUguale(classificaFinale.get(i-1)))
				vincenti.add(classificaFinale.get(i-1));
			
		}
		
		return vincenti;
	}
	
	/**
	 * Restituisce la posizione del giocatore con meno capitale nel vettore elencoGiocatori
	 * 
	 * @return posizione del giocatore piu' povero
	 */
	public int ilPiuPovero(){
		int posizione = 0;
		
		for(int i=0; i<elencoGiocatori.size();i++)
			for(int j=0; j<elencoGiocatori.size();j++)
				if(elencoGiocatori.get(i).piuPovero(elencoGiocatori.get(j)))
					posizione = i;
		
		
		
			return posizione;
				
			
	}
	
	public int getTurnoGiocatore() {
		return turnoGiocatore;
	}

	public void setTurnoGiocatore(int turnoGiocatore) {
		this.turnoGiocatore = turnoGiocatore;
	}

	public int getTurniAttuali() {
		return turniAttuali;
	}

	public void setTurniAttuali(int turniAttuali) {
		this.turniAttuali = turniAttuali;
	}

	public Vector<Giocatore> getElencoGiocatori() {
		return elencoGiocatori;
	}
	
	public Vector<Giocatore> getClassificaFinale(){
		return classificaFinale;
	}


	public Vector<Casella> getCaselle() {
		return caselle;
	}



	/**
	 * restituisce informazioni essenziali di tutti i giocatori in partita
	 */
	public String toString() {
		StringBuilder visualizza = new StringBuilder();

		if (elencoGiocatori.isEmpty())
			visualizza.append(String.format(MESS_VUOTO));
		else
			visualizza.append(BelleStringhe.incornicia("SITUAZIONE ATTUALE DI GIOCO"));
			for (Giocatore g : elencoGiocatori){
				visualizza.append(String.format(g.toString()));
				visualizza.append("\n\t Posizione: "+ caselle.get(g.getPosizione()).getNome()+"%n");
				visualizza.append("\n\t Capitale: " + g.getCapitale() + " euro\n");
			}

		return visualizza.toString();

	}
	
}
