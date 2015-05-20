/**
 * 
 */
package main;

import java.io.*;
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
	private final static String VOCE_INIZIALE02 = "Riprendi partita";
	private final static String VOCE_INIZIALE03 = "Carica partita";
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
	
	//----------SALVATAGGIO E CARICAMENTI DATI-------------------
	private static final String PARTITA_FILE = "partita.dat";
	private static final String MESS_NO_CAST = "ATTENZIONE! problemi con il cast";
	private static final String FILE_CARICATI = "I file sono stati caricati con successo" ;
	private static final String CARICAMENTO_FALLITO = "Non esiste alcun file caricabile";
	private static final String FILE_SALVATI = "I file sono stati salvati";
	private static final String NIENTE_DA_SALVARE = "Non esistono dati da salvare";
	private static final String NESSUNA_PARTITA_SALVATA = "Non c'è nessuna partita preesistente";
	
	private static final File filePartita = new File (PARTITA_FILE);
	
	
	
	
	public static void main(String[] args){
		System.out.println(BENVENUTI);
		
		int scelta;
		
		do{
			scelta = menuIniziale.scegli();
			
			switch(scelta){
			case 1: nuovaPartita(); 
			        scelta = finePartita();
			        break;
			case 2: riprendiPartita();break;      
			case 3: caricaPartita(); break;
				
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
	
	private static void riprendiPartita(){
		if(!tabellone.getGiocatori().isEmpty()){
			dado = new Dado(1,1);
			partita();
		}
		else 
			System.out.println(NESSUNA_PARTITA_SALVATA);
		
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
				tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
				if(tabellone.getTurniAttuali() >= 20){
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
							tabellone.teleportPlayer(giocatore, Data.getPrigione()); //AH-AH-AH 
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
					
					case 2: salvaPartita(); giocatore.setToken(false); scelta=0; break;

				}		
			}while (giocatore.hasToken() && scelta!=0);
			
			
		}
	
	
	
	private static int finePartita(){
		//Metodo che gestisce la fine di una partita			
		return MyUtil.yesOrNo(FINE_PARTITA) ? 1 : 0;
	}
	
	
	private static void salvaPartita() {
		if(!tabellone.getGiocatori().isEmpty()){
			  ServizioFile.salvaSingoloOggetto(filePartita, tabellone);
	           System.out.printf(FILE_SALVATI);
			}
			else
				System.out.printf(NIENTE_DA_SALVARE);
		
	}
	
	private static void caricaPartita() {
		if(ServizioFile.esistenza(filePartita))
			try{
				tabellone = (Tabellone)ServizioFile.caricaSingoloOggetto(filePartita);
			}
		catch(ClassCastException e){
			System.out.println(MESS_NO_CAST); 
		  }
		if(!tabellone.getGiocatori().isEmpty() )
			System.out.println(FILE_CARICATI);
	    else
				  System.out.println(CARICAMENTO_FALLITO);
	}
	
	
	
	

	
}

