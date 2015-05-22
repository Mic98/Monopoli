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
	
	public static Tabellone tabellone=new Tabellone();
	public static Dado dado;
	
	//------------VOCI SISTEMA-----------------
	private final static String BENVENUTI = "Benvenuti nel gioco del Monopoleh!";
	private final static String ARRIVEDERCI = "GRAZIE PER AVER GIOCATO";
	private final static String FINE_PARTITA = "La partita e' finita! Un'altra? ";
	private static final String MESSAGGIO_FINE_TURNO = "Il tuo turno � concluso";
	private static final String MESSAGGIO_TROPPI_LANCI = "Hai ottenuto tre volte di seguito lo stesso punteggio per entrambi i dadi, andrai in prigione";
	private static final String MESSAGGIO_POSIZIONE = "Il tuo lancio ha dato come risultato: %d%nOra sei nella casella n�: %d %s%n";
    
	//------------MENU PRINCIPALE---------------
	private final static String TITOLO_INIZIALE = "MONOPOLI";
	private final static String VOCE_INIZIALE01 = "Nuova partita";
	private final static String VOCE_INIZIALE02 = "Riprendi partita";
	private final static String [] VOCI_MENU_INIZIALE = {VOCE_INIZIALE01, VOCE_INIZIALE02};
	
	//-------------MENU GIOCATORE---------------
	private final static String TITOLO_TURNO01 = "Turno n�: ";
	private final static String TITOLO_TURNO02 = " Turno di: ";
	private final static String VOCE_TURNO01 = "Lancio dadi";
	private static final String LANCIO_DOPPIO = "Hai fatto un lancio con due numeri uguali, hai diritto ad un altro tiro ";
	private final static String VOCE_TURNO02 = "Salva partita";
	private final static String VOCE_TURNO03 = "Mostra elenco giocatori";
	private final static String [] VOCI_MENU_GIOCO = {VOCE_TURNO01, VOCE_TURNO02, VOCE_TURNO03};
	
	//----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ERRORE_POCHI_GIOCATORI = "ATTENZIONE!!! Minimo 2 giocatori!";
	private final static String ERRORE_TROPPI_GIOCATORI = "ATTENZIONE!!! Massimo 6 giocatori! La partita inzier�";
	
	private static final MyMenu menuIniziale = new MyMenu(TITOLO_INIZIALE, VOCI_MENU_INIZIALE);
	
	//----------SALVATAGGIO E CARICAMENTI DATI-------------------
	private static final String PARTITA_FILE = "partita.dat";
	private static final String MESS_NO_CAST = "ATTENZIONE! problemi con il cast";
	private static final String FILE_CARICATI = "I file sono stati caricati con successo" ;
	private static final String CARICAMENTO_FALLITO = "Non esiste alcun file caricabile";
	private static final String FILE_SALVATI = "I file sono stati salvati";
	private static final String NIENTE_DA_SALVARE = "Non esistono dati da salvare";
	private static final String NESSUNA_PARTITA_SALVATA = "Non c'� nessuna partita preesistente";
	
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
			
			case 0:  break;
			
			}
		}
		while(scelta!=0);
		
		System.out.println(ARRIVEDERCI);
    }	

/**
 * metodo che si occupa di creare una nuova partita	
 */
	private static void nuovaPartita(){
		Vector<Giocatore> nuoviGiocatori = new Vector<Giocatore>();
		boolean ok = false;
		
		while(!ok){
			String nome = MyUtil.riceviString(RICHIESTA_NOME_GIOCATORE);
			nuoviGiocatori.add(new Giocatore(nome));
			
			if(MyUtil.yesOrNo(FINE_INSERIMENTO_GIOCATORI)){
				if(nuoviGiocatori.size()>=6){
						System.out.println(ERRORE_TROPPI_GIOCATORI);
						ok = true;
					}
			}
					else{
						if(nuoviGiocatori.size()<2)
								System.out.println(ERRORE_POCHI_GIOCATORI);
						else 
							ok = true;
					}
						
				}
		
		
		
		tabellone.addGiocatori(nuoviGiocatori);
		dado = new Dado(1,1);
		partita();
	}
  

	
/**
 * Metodo di gioco. Qui e' presente il ciclo infinito del gioco
 */
		private static void partita(){
			boolean partitaSalvata = false ;
			int turnoGiocatore;
			boolean inGame = true;
		
//questo if serve nel caso si riprenda da una partita salvata per non far ricominciare il giro dal primo giocatore
			if(giocatoreHaToken())
			   turnoGiocatore = giocatoreConToken();
			else
			   turnoGiocatore = 0;
			
			while(inGame && !partitaSalvata){
				//Seleziona il giocatore attuale per una migliore gestione
				Giocatore giocatoreAttuale = tabellone.getElencoGiocatori().get(turnoGiocatore);
				
				
				MyMenu menuGioco = new MyMenu(TITOLO_TURNO01 + tabellone.getTurniAttuali() + TITOLO_TURNO02 + giocatoreAttuale.getNome(), VOCI_MENU_GIOCO);
				
				
				int scelta;
				//Stabilisce se un giocatore ha concluso il proprio turno
				giocatoreAttuale.setToken(true);
				
				do{
					scelta = menuGioco.scegli();
					
					switch (scelta) {
						//Lancio dei dadi
						case 1:gestioneTurno(giocatoreAttuale); break;
						
// notare che al momento del salvataggio un giocatore possiede il token, nel caso del caricamento della partita in futuro si sapr� da quale giocatore riprendere
						case 2: salvaPartita();
						        giocatoreAttuale.setToken(false); 
						        scelta=0; partitaSalvata = true; 
		                        break;
		                        
						case 3:System.out.printf(tabellone.toString()); break;
		                        
					}		
				}while (giocatoreAttuale.hasToken() && scelta!=0);
				
				//Assegna il turno di gioco al prossimo giocatore 
				turnoGiocatore ++;
				if(turnoGiocatore > tabellone.getElencoGiocatori().size() - 1)
					turnoGiocatore = 0;
				
				//Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
				tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
				if(tabellone.getTurniAttuali() >= 20){
					inGame = false;	
				}
			}
		}//fine metodo partita
		
		
/**
 * metodo che serve ad individuare il giocatore con il token
 * @return la posizione nel vettore Giocatori del giocatore in possesso del token
 */
		private static int giocatoreConToken() {
						
			int posizioneGiocatore = 0;
			for(int i=0; i<tabellone.getElencoGiocatori().size();i++)
			     if(tabellone.getElencoGiocatori().get(i).hasToken())
                              posizioneGiocatore = i;
			
			return posizioneGiocatore;
			
		}

/**
 * metodo che serve per sapere se un giocatore possiede il token
 * @return true se il giocatore possiede il token
 */
		private static boolean giocatoreHaToken() {
			
			for(int i=0; i<tabellone.getElencoGiocatori().size();i++)
			     if(tabellone.getElencoGiocatori().get(i).hasToken())
			    	 return true;
			
			return false;
			
		}

/**
 * metodo per lanciare i dadi 		  
*/
		private static void lancioDadi(){
			
			dado.setLancio1(MyRandom.estraiIntero(Dado.getDadoMIN(), Dado.getDadoMAX()));
			dado.setLancio2(MyRandom.estraiIntero(Dado.getDadoMIN(), Dado.getDadoMAX()));
		}
		

/**	
 * metodo per gestire il turno di un giocatore
 * @param giocatoreAttuale il giocatore che pu� giocare in questo turno
 */
	private static void gestioneTurno(Giocatore giocatoreAttuale){
		
		if(giocatoreAttuale.hasToken() && !giocatoreAttuale.isInPrigione()){
		    lancioDadi();
		    tabellone.movePlayer(giocatoreAttuale, dado.risultato());
		    System.out.printf(MESSAGGIO_POSIZIONE, dado.risultato(), giocatoreAttuale.getPosizione(), tabellone.getCaselle().get(giocatoreAttuale.getPosizione()).getNome());
		    
		
	    if(!dado.sonoUguali()){
			giocatoreAttuale.setToken(false);
			System.out.println(MESSAGGIO_FINE_TURNO);
			giocatoreAttuale.setNumeroLanci(0);
	    }
	    else{
	    	System.out.println(LANCIO_DOPPIO);
	    	giocatoreAttuale.setNumeroLanci(giocatoreAttuale.getNumeroLanci()+1);
	    }
		if(giocatoreAttuale.getNumeroLanci() >= 3 ){
			giocatoreAttuale.setToken(false);
			System.out.println(MESSAGGIO_TROPPI_LANCI);
			tabellone.teleportPlayer(giocatoreAttuale, Data.getPrigione()); //AH-AH-AH 
			giocatoreAttuale.setInPrigione(true);
			giocatoreAttuale.setNumeroLanci(0);
		    }
	    }
		else{
			lancioDadi();
			if(dado.sonoUguali()){
				giocatoreAttuale.setInPrigione(false);
				giocatoreAttuale.setToken(false);
	        }
		
	    }
	 }//fine gestioneTurno
	
/**
* 	metodo per far scegliere all'utente se giocare ancora o uscire
* @return 1 se il giocatore sceglie di fare un'altra partita 0 altrimenti
*/
		private static int finePartita(){
			//Metodo che gestisce la fine di una partita			
			return MyUtil.yesOrNo(FINE_PARTITA) ? 1 : 0;
		}
			
	
/**
 * metodo che si occupa di caricare una partita salvata precedenntemente e riprenderla da dove interrotta	
*/
		private static void riprendiPartita(){
			
			caricaPartita();
			if(!tabellone.getElencoGiocatori().isEmpty()){
				dado = new Dado(1,1);
				partita();
			}
			else 
				System.out.println(NESSUNA_PARTITA_SALVATA);
			
		}
		
	
/**
* metodo per salvare una partita in corso	
*/
			private static void salvaPartita() {
				if(!tabellone.getElencoGiocatori().isEmpty()){
					  ServizioFile.salvaSingoloOggetto(filePartita, tabellone);
			           System.out.println(FILE_SALVATI);
					}
					else
						System.out.printf(NIENTE_DA_SALVARE);
				
			}// fine salvaPartita

/**
* metodo per caricare una partita salvata in precedenza	
*/
			private static void caricaPartita() {
				if(ServizioFile.esistenza(filePartita))
					try{
						tabellone = (Tabellone)ServizioFile.caricaSingoloOggetto(filePartita);
					}
				catch(ClassCastException e){
					System.out.println(MESS_NO_CAST); 
				  }
				if(!tabellone.getElencoGiocatori().isEmpty() )
					System.out.println(FILE_CARICATI);
			    else
						  System.out.println(CARICAMENTO_FALLITO);
			}//fine caricaPartita
				
	
}
	
	

	


