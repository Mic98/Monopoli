/**
 * 
 */
package main;

import java.util.Collections;
import java.util.Vector;
import java.io.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tabellone implements Serializable {

	private static final String MESS_VUOTO = "%n La lista giocatori � vuota %n%n";
	private Vector<Casella> caselle;
	private Vector<Giocatore> elencoGiocatori;
	int turnoGiocatore;
	private int turniAttuali; // Variabile che tiene conto dei turni

	public Tabellone() {
		// Inizializzazione dei dati delle caselle
		caselle = new Vector<Casella>();
		initCaselle();

		// Creazione del vettore del giocatore
		elencoGiocatori = new Vector<Giocatore>();

		turniAttuali = 0;
		turnoGiocatore = 0;
	}

	public void addGiocatori(Vector<Giocatore> plrs) {
		elencoGiocatori = plrs;

		// Scambia casualmente la posizione dei turni dei giocatori
		Collections.shuffle(plrs);
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

	private void initCaselle() {
		String[] nomiCaselle = Data.getNomiCaselle();
		int i = 0;

		for (String str : nomiCaselle) {
			caselle.add(new Casella(str, i));
			i++;
		}
	}

	public Vector<Casella> getCaselle() {
		return caselle;
	}

	public void movePlayer(Giocatore g, int step) {
		int dest = g.getPosizione() + step;

		if (dest >= 40){
			dest = dest - 40;
		}

		g.setPosizione(dest);

	}

	public void teleportPlayer(Giocatore g, int casella) {
		g.setPosizione(casella);
	}

	public String toString() {
		StringBuilder visualizza = new StringBuilder();

		if (elencoGiocatori.isEmpty())
			visualizza.append(String.format(MESS_VUOTO));
		else
			for (Giocatore g : elencoGiocatori)
				visualizza.append(String.format(g.toString()));

		return visualizza.toString();

	}

	// public Casella aggiungiCasella(Casella in){
	// caselle.add(in);
	// return in;
	// }
}
