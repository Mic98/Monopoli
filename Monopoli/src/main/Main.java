/**
 * 
 */
package main;

import utilities.*;





/**
 * @author Carlo
 *
 */
public class Main {
    
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Carica partita";
	private final static String [] VOCI_MENU_INIZIALE = {VOCE_INIZIALE01, VOCE_INIZIALE02};
	private final static String TITOLO_CREAZIONE = "";
	private final static String VOCE_CREAZIONE01 = "Aggiungi Giocatore";
	private final static String VOCE_CREAZIONE02 = "Inizia partita";
	private final static String [] VOCI_CREAZIONE = {VOCE_CREAZIONE01, VOCE_CREAZIONE02};
	
	
	
	MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE, VOCI_MENU_INIZIALE);
	MyMenu menuCreazionePartita= new MyMenu(TITOLO_CREAZIONE, VOCI_CREAZIONE);
	
	
	public static void main(String[] args){
	
  }
	
}

