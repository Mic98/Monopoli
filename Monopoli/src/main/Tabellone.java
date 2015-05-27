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

	private static final String MESS_VUOTO = "\n La lista giocatori e' vuota \n\n";
	private static final int BONUS_VIA = 500;
	private static final String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai 500Û di bonus \n";
	
	
	private Vector<Casella> caselle;
	private Vector<Giocatore> elencoGiocatori;
	private int turnoGiocatore;
	private int turniAttuali; 

	public Tabellone() {
	
		caselle = new Vector<Casella>();
		elencoGiocatori = new Vector<Giocatore>();
		turniAttuali = 1;
		turnoGiocatore = 0;
	}

	/**
	 * metodo che mescola l'ordine del vettore elencoGiocatori
	 * 
	 * @param plrs vettore contentente la lista dei giocatori
	 */
	public void mescolaGiocatori(Vector<Giocatore> plrs) {
		elencoGiocatori = plrs;
		Collections.shuffle(plrs);
	}
	
	/**
	 * metodo che sposta il giocatore lungo il tabellone, verificando l'avvenuto passaggio dal via
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
	 * metodo che sposta il giocatore senza farlo passare dal via
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
	
	public boolean esisteGia(String daControllare, Vector<Giocatore> nuoviGiocatori){
		for(Giocatore g: nuoviGiocatori)
		      if(g.getNome().equalsIgnoreCase(daControllare))
		               return true;
		           
		return false;
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


	public Vector<Casella> getCaselle() {
		return caselle;
	}



	public String toString() {
		StringBuilder visualizza = new StringBuilder();

		if (elencoGiocatori.isEmpty())
			visualizza.append(String.format(MESS_VUOTO));
		else
			visualizza.append(BelleStringhe.incornicia("SITUAZIONE ATTUALE DI GIOCO"));
			for (Giocatore g : elencoGiocatori){
				visualizza.append(String.format(g.toString()));
				visualizza.append("\n\t Posizione: "+ caselle.get(g.getPosizione()).getNome()+"%n");
				visualizza.append("\n\t Capitale: " + g.getCapitale() + " Û\n");
			}

		return visualizza.toString();

	}
	
}
