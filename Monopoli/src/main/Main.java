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
	
	//------------VOCI SISTEMA-----------------
	private final static String BENVENUTI = "Benvenuti nel gioco del Monopoleh!";
	private final static String ARRIVEDERCI = "GRAZIE PER AVER GIOCATO";
    
	//------------MENU PRINCIPALE---------------
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Carica partita";
	private final static String [] VOCI_MENU_INIZIALE = {VOCE_INIZIALE01, VOCE_INIZIALE02};
	private final static String TITOLO_CREAZIONE = "";
	private final static String VOCE_CREAZIONE01 = "Aggiungi Giocatore";
	private final static String VOCE_CREAZIONE02 = "Inizia partita";
	private final static String [] VOCI_CREAZIONE = {VOCE_CREAZIONE01, VOCE_CREAZIONE02};
	
	//-------------MENU GIOCATORE---------------
	private final static String TITOLO_TURNO = "Turno di ";
	private final static String VOCE_LANCIO_DADI = "Lancio dadi";
	private final static String [] VOCI_MENU_GIOCO = {VOCE_LANCIO_DADI};
	
	//----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	
	private static final MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE, VOCI_MENU_INIZIALE);
	private static final MyMenu menuCreazionePartita= new MyMenu(TITOLO_CREAZIONE, VOCI_CREAZIONE);
	
	
	public static void main(String[] args){
		System.out.println(BENVENUTI);
		
		int scelta;
		
		do{
			scelta = menuIniziale.scegli();
			
			switch(scelta){
			case 1: nuovaPartita(); break;
			case 2: caricaPartita(); break;
				
			case 0:  break;
			
			}
		}
		while(scelta!=0);
		
		System.out.println(ARRIVEDERCI);
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
		tabellone.addGiocatori(g);
		
		partita();
	}
	
	private static void caricaPartita() {
		System.out.println("FUNZIONALITA' NON ANCORA IMPLEMENTATA!!!");
	}
	
	
	//Metodo di gioco. Qui e' presente il ciclo infinito del gioco
	private static void partita(){
		boolean inGame = true;
		int turnGiocatore = 0;
		
		while(inGame){
			//Seleziona il giocatore attuale per una migliore gestione
			Giocatore G_Attuale = tabellone.getGiocatori().get(turnGiocatore);
			
			MyMenu menuGioco = new MyMenu(TITOLO_TURNO + G_Attuale.getNome(), VOCI_MENU_GIOCO);
			
			//Stabilisce se un giocatore ha concluso il proprio turno
			boolean turno = true;
			do{
				scelta = menuGioco.scegli();
				
				int numLanci = 0 ;
				switch (scelta) {
					//Lancio dei dadi
					case 1:
						numLanci++;
						if(numLanci >= 3){
							turno = false;
							tabellone.teleportPlayer(G_Attuale, 10); //AH-AH-AH 
						}
						
					break;
	
					default:
					break;
				}
				
				
				
			}while (turno);
			
			
			
			
			
			//Assegna il turno di gioco al prossimo giocatore 
			turnGiocatore ++;
			if(turnGiocatore >= tabellone.getGiocatori().size() - 1)
				turnGiocatore = 0;
			
			//Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurni_attuali(tabellone.getTurni_attuali() + 1);
			if(tabellone.getTurni_attuali() >= 20){
				inGame = false;
				
			}
		}
	}
}

