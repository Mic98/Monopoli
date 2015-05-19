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
	public static Dado dado;
	
	//------------VOCI SISTEMA-----------------
	private final static String BENVENUTI = "Benvenuti nel gioco del Monopoleh!";
	private final static String ARRIVEDERCI = "GRAZIE PER AVER GIOCATO";
	private final static String FINE_PARTITA = "La partita e' finita! Un'altra? ";
	private static final String MESSAGGIO_FINE_TURNO = "Il tuo turno è concluso premi 0 per passarlo al giocatore successivo";
	private static final String MESSAGGIO_TROPPI_LANCI = "Hai ottenuto tre volte di seguito lo stesso punteggio per entrambi i dadi, andrai in prigione";
    
	//------------MENU PRINCIPALE---------------
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Carica partita";
	private final static String [] VOCI_MENU_INIZIALE = {VOCE_INIZIALE01, VOCE_INIZIALE02};
	
	//-------------MENU GIOCATORE---------------
	private final static String TITOLO_TURNO = "Turno di ";
	private final static String VOCE_LANCIO_DADI = "Lancio dadi";
	private final static String [] VOCI_MENU_GIOCO = {VOCE_LANCIO_DADI};
	
	//----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ERRORE_NUM_GIOCATORI = "ATTENZIONE!!! Minimo 2 massimo 6 giocatori!";
	
	private static final MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE, VOCI_MENU_INIZIALE);
	
	
	public static void main(String[] args){
		System.out.println(BENVENUTI);
		
		int scelta;
		
		do{
			scelta = menuIniziale.scegli();
			
			switch(scelta){
			case 1: 
				nuovaPartita();
				scelta = finePartita();
			break;
			case 2: caricaPartita(); break;
				
			case 0:  break;
			
			}
		}
		while(scelta!=0);
		
		System.out.println(ARRIVEDERCI);
    }	

	private static void nuovaPartita(){
		Vector<Giocatore> nuoviGiocatori = new Vector<Giocatore>();
		boolean ok = false;
		
		while(!ok){
			String nome = MyUtil.riceviString(RICHIESTA_NOME_GIOCATORE);
			nuoviGiocatori.add(new Giocatore(nome));
			
			if(!MyUtil.yesOrNo(FINE_INSERIMENTO_GIOCATORI)){
				if(nuoviGiocatori.size() >= 2 && nuoviGiocatori.size() <= 6)
					ok = true;
				else
					System.out.println(ERRORE_NUM_GIOCATORI);
			}
		}
		
		tabellone = new Tabellone();
		tabellone.addGiocatori(nuoviGiocatori);
		dado = new Dado(1,1);
		
		
		partita();
	}
	
	private static void caricaPartita() {
		System.out.println("FUNZIONALITA' NON ANCORA IMPLEMENTATA!!!");
	}
	
	private static int finePartita(){
		//Metodo che gestisce la fine di una partita			
		return MyUtil.yesOrNo(FINE_PARTITA) ? 1 : 0;
	}
	
	
	//Metodo di gioco. Qui e' presente il ciclo infinito del gioco
	private static void partita(){
		boolean inGame = true;
		int turnoGiocatore = 0;
		
		while(inGame){
			//Seleziona il giocatore attuale per una migliore gestione
			Giocatore giocatoreAttuale = tabellone.getGiocatori().get(turnoGiocatore);
			
			gestioneTurno(giocatoreAttuale);
			
			//Assegna il turno di gioco al prossimo giocatore 
			turnoGiocatore ++;
			if(turnoGiocatore > tabellone.getGiocatori().size() - 1)
				turnoGiocatore = 0;
			
			//Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurni_attuali(tabellone.getTurni_attuali() + 1);
			if(tabellone.getTurni_attuali() >= 20){
				inGame = false;	
			}
		}
	}//fine metodo partita
	
	private static void lancioDadi(){
		
		dado.setLancio1(MyRandom.estraiIntero(Dado.getDadoMIN(), Dado.getDadoMAX()));
		dado.setLancio2(MyRandom.estraiIntero(Dado.getDadoMIN(), Dado.getDadoMAX()));
	}
	
	
	//Metodo che gestisce il turno del singolo giocatore
	private static void gestioneTurno(Giocatore giocatore){
		MyMenu menuGioco = new MyMenu(TITOLO_TURNO + giocatore.getNome(), VOCI_MENU_GIOCO);
		int scelta;
		
		//Stabilisce se un giocatore ha concluso il proprio turno
		giocatore.setToken(true);
		
		int numLanci = 0 ;
		do{
			scelta = menuGioco.scegli();
			
			switch (scelta) {
				//Lancio dei dadi
				case 1:{ 
					if(giocatore.hasToken() && !giocatore.isInPrigione()){
					    lancioDadi();
					    tabellone.movePlayer(giocatore, dado.risultato());
					    numLanci++;
					
				    if(!dado.sonoUguali()){
						giocatore.setToken(false);
						System.out.println(MESSAGGIO_FINE_TURNO);
				    }
					if(numLanci >= 3 ){
						giocatore.setToken(false);
						System.out.println(MESSAGGIO_TROPPI_LANCI);
						tabellone.teleportPlayer(giocatore, 10); //AH-AH-AH 
						giocatore.setInPrigione(true);
					}
				}
					else{
						lancioDadi();
						if(dado.sonoUguali()){
							giocatore.setInPrigione(false);
						}
					}
				}break;

			}		
		}while (giocatore.hasToken() && scelta!=0);
		
		
	}
}

