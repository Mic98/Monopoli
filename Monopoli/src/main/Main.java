/**
 * 
 */
package main;

import java.io.*;
import java.util.Vector;

import utilities.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Main {

	// ------------VOCI SISTEMA-----------------
	private final static String BENVENUTI = "Benvenuti nel gioco del Monopoleh!";
	private final static String ARRIVEDERCI = "GRAZIE PER AVER GIOCATO";
	private final static String FINE_PARTITA = "La partita e' finita! Un'altra? ";

	// ------------MENU PRINCIPALE---------------
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Riprendi partita";
	private final static String[] VOCI_MENU_INIZIALE = { VOCE_INIZIALE01,
			VOCE_INIZIALE02 };

	private static final MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE,
			VOCI_MENU_INIZIALE);
	private static Gioco gioco;

	public static void main(String[] args) {
		System.out.println(BENVENUTI);

		int scelta;

		do {
			scelta = menuIniziale.scegli();

			switch (scelta) {
			case 1:
				gioco = new Gioco();
				gioco.nuovaPartita();
				scelta = finePartita();
				break;
			case 2:
				gioco.riprendiPartita();
				break;

			case 0:
				break;

			}
		} while (scelta != 0);

		System.out.println(ARRIVEDERCI);
	}

	/**
	 * metodo per far scegliere all'utente se giocare ancora o uscire
	 * 
	 * @return 1 se il giocatore sceglie di fare un'altra partita 0 altrimenti
	 */
	private static int finePartita() {
		// Metodo che gestisce la fine di una partita
		return MyUtil.yesOrNo(FINE_PARTITA) ? 1 : 0;
	}

}
