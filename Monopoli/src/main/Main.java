/**
 * 
 */
package main;

import java.util.Vector;

import utilities.*;

/**
 * @author Carlo
 *
 */
public class Main {
	
	public static Tabellone tabellone;
    
	//------------MENU PRINCIPALE---------------
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Carica partita";
	private final static String [] VOCI_MENU_INIZIALE = {VOCE_INIZIALE01, VOCE_INIZIALE02};
	private final static String TITOLO_CREAZIONE = "";
	private final static String VOCE_CREAZIONE01 = "Aggiungi Giocatore";
	private final static String VOCE_CREAZIONE02 = "Inizia partita";
	private final static String [] VOCI_CREAZIONE = {VOCE_CREAZIONE01, VOCE_CREAZIONE02};
	
	//----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ARRIVEDERCI = "GRAZIE PER AVER GIOCATO";
	
	private static final MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE, VOCI_MENU_INIZIALE);
	private static final MyMenu menuCreazionePartita= new MyMenu(TITOLO_CREAZIONE, VOCI_CREAZIONE);
	
	
	public static void main(String[] args){
		
		int scelta;
		
		do{
			scelta = menuIniziale.scegli();
			
			switch(scelta){
			case 1: nuovaPartita(); break;
			case 2: caricaPartita(); break;
				
			case 0: System.out.println(ARRIVEDERCI); break;
			
			}
		}
		while(scelta!=0);
    }
	
	

	private static void nuovaPartita(){
		Vector<Giocatore> g = new Vector<Giocatore>();
		boolean ok = false;
		while(!ok){
			String name = MyUtil.riceviString(RICHIESTA_NOME_GIOCATORE);
			g.add(new Giocatore(name));
			
			if(MyUtil.yesOrNo(FINE_INSERIMENTO_GIOCATORI)){
				if(g.size() >= 2 && g.size() <= 6)
					ok = true;
			}
		}
		
		tabellone = new Tabellone();
		tabellone.addGiocatore(g);
	}
	
	private static void caricaPartita() {
		
		
	}
}

