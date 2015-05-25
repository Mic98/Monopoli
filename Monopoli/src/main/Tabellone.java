/**
 * 
 */
package main;

import java.util.Collections;
import java.util.Vector;
import java.io.*;

import utilities.BelleStringhe;
import utilities.MyUtil;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tabellone implements Serializable {

	private static final String MESS_VUOTO = "\n La lista giocatori e' vuota \n\n";
	private static final int BONUS_VIA = 500;
	private static final String MESSAGGIO_VIA = "\n Sei passato dal via! Riceverai 500Û di bonus \n";
	private Vector<Casella> caselle;
	private Vector<Giocatore> elencoGiocatori;
	private int turnoGiocatore;
	private int turniAttuali; 

	public Tabellone() {
	
		caselle = new Vector<Casella>();
		initCaselle();
		
		elencoGiocatori = new Vector<Giocatore>();
		turniAttuali = 0;
		turnoGiocatore = 0;
	}

	/**
	 * metodo che mescola l'ordine del vettore elencoGiocatori
	 * @param plrs vettore contentente la lista dei giocatori
	 */
	public void addGiocatori(Vector<Giocatore> plrs) {
		elencoGiocatori = plrs;
		Collections.shuffle(plrs);
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
			System.out.println(MESSAGGIO_VIA);
			g.setCapitale(g.getCapitale() + BONUS_VIA);
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
			visualizza.append(BelleStringhe.incornicia("SITUAZIONE ATTUALE DI GIOCO"));
			for (Giocatore g : elencoGiocatori){
				visualizza.append(String.format(g.toString()));
				visualizza.append("\n\t Posizione: "+caselle.get(g.getPosizione()).getNome()+"%n");
			}

		return visualizza.toString();

	}

}
