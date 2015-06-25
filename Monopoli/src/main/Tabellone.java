/**
 * 
 */
package main;

import java.util.Collections;
import java.util.Vector;
import java.io.*;

import carte.Mazzo;
import caselle.*;
import utilities.BelleStringhe;
import utilities.MyUtil;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tabellone implements Serializable {

	private final static String GIOCATORE = "%n%n GIOCATORE: %s %n";
	private final static String MESS_VUOTO = "\nLa lista giocatori e' vuota \n\n";
	

	
	private Vector<Casella> caselle;
	private Vector<Giocatore> elencoGiocatori;
	private Vector<Giocatore> classificaFinale;
	private Mazzo probabilita;
	public  Mazzo imprevisti;
	private int turnoGiocatore;
	private int turniAttuali; 

	public Tabellone() {
	
		caselle = new Vector<Casella>();
		elencoGiocatori = new Vector<Giocatore>();
		classificaFinale = new Vector<Giocatore>();
		probabilita = new Mazzo();
		imprevisti = new Mazzo();
		turniAttuali = 1;
		turnoGiocatore = 0;
	}


	/**
	 * Mescola l'ordine del vettore elencoGiocatori
	 * 
	 * @param plrs Vettore contentente la lista dei giocatori
	 */
	public void mescolaGiocatori(Vector<Giocatore> plrs) {
		elencoGiocatori = plrs;
		Collections.shuffle(plrs);
	}
	
	

	public void aggiungiCasella(Casella c) {
		caselle.add(c);
		
	}
	
	/**
	 * Verifica che l'input dell'utente non sia gia' stato inserito
	 *  
	 * @param daControllare Input dell'utente da controllare
	 * @param nuoviGiocatori Vettore di nomi gia' inseriti
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
	private void classifica(){
		int i = elencoGiocatori.size();
		while (i>0){
			classificaFinale.addElement(elencoGiocatori.get(ilPiuPovero()));
			elencoGiocatori.remove(elencoGiocatori.get(ilPiuPovero()));
			
			i--;
		}	
	}
	
	/**
	 * 
	 * @return Il vettore dei giocatori vincenti
	 */
	public Vector<Giocatore> trovaVincitori(){
		classifica();
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
	 * @return La posizione nel vettore del giocatore piu' povero
	 */
	private int ilPiuPovero(){
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
	
	public Mazzo getProbabilita() {
		return probabilita;
	}

	public Mazzo getImprevisti() {
		return imprevisti;
	}
	
	public void mescolaProbabilita(Mazzo probabilita) {
		this.probabilita = probabilita;
	}


	public void mescolaImprevisti(Mazzo imprevisti) {
		this.imprevisti = imprevisti;
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
			for (Giocatore giocatore : elencoGiocatori){
				visualizza.append(String.format(GIOCATORE, giocatore.getNome()));
				visualizza.append("\n\t Posizione: "+ caselle.get(giocatore.getPosizione()).getNome()+"\n");
				visualizza.append("\n\t Capitale: " + giocatore.getCapitale() + " euro\n");
			}

		return visualizza.toString();

	}
	
}
