/**
 * 
 */
package main;

import java.io.File;
import java.util.Vector;

import caselle.*;
import utilities.MyMenu;
import utilities.MyUtil;
import utilities.ServizioFile;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Gioco {

	private static final String MESSAGGIO_FINE_TURNO = "Il tuo turno e' concluso";
	private static final String MESSAGGIO_TROPPI_LANCI = "Hai ottenuto tre volte di seguito lo stesso punteggio per entrambi i dadi, andrai in prigione";
	private static final String MESSAGGIO_POSIZIONE = "Il tuo lancio ha dato come risultato: %d \nOra sei nella casella n: %d %s\n";
	private static final String IN_BANCA_ROTTA = "\n\n Hai finito i soldi! Sei in bancarotta, la tua partita è finita.%n";
	private static final String CASELLA_TASSA = "\nSei finito sulla casella %s, devi %d € alla banca %n";

	private final static String TITOLO_TURNO01 = "Turno n: ";
	private final static String TITOLO_TURNO02 = "\tTurno di: ";
	private final static String TITOLO_TURNO03 = "\tCapitale posseduto: ";
	private final static String VOCE_TURNO01 = "Lancio dadi";
	private final static String LANCIO_DOPPIO = "Hai fatto un lancio con due numeri uguali, hai diritto ad un altro tiro ";
	private final static String USCITO_DI_PRIGIONE = "Hai tirato doppio, sei uscito di prigione e puoi lanciare ancora";
	private static final String MESSAGGIO_IN_PRIGIONE = "\nSei finito nella casella \"IN PRIGIONE!\" per questo motivo verrai spostato in prigione";
	private final static String VOCE_TURNO02 = "Salva partita";
	private final static String VOCE_TURNO03 = "Mostra situazione partita";
	private final static String[] VOCI_MENU_GIOCO = { VOCE_TURNO01,
			VOCE_TURNO02, VOCE_TURNO03 };

	// ----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private static final String ERR_ESISTE_GIA = "Il nome inserito e' gia' esistente, inserirne un altro";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ERRORE_POCHI_GIOCATORI = "ATTENZIONE!!! Minimo 2 giocatori!";
	private final static String ERRORE_TROPPI_GIOCATORI = "ATTENZIONE!!! Massimo 6 giocatori! La partita inziera'";

	// ----------SALVATAGGIO E CARICAMENTI DATI-------------------
	private final static String PARTITA_FILE = "partita.dat";
	private final static String MESS_NO_CAST = "ATTENZIONE! problemi con il cast";
	private final static String FILE_CARICATI = "I file sono stati caricati con successo";
	private final static String FILE_SALVATI = "I file sono stati salvati";
	private final static String NIENTE_DA_SALVARE = "Non esistono dati da salvare";
	private final static String NESSUNA_PARTITA_SALVATA = "Non c'e' nessuna partita preesistente";
	private final static String PARTITA_SALVATA = "La partita e' stata salvata, vuoi continuare a giocare?";


	private static final File filePartita = new File(PARTITA_FILE);

	
	
	
	
	public static Tabellone tabellone;
	public static Dado dado;

	public Gioco() {
		tabellone = new Tabellone();
		tabellone = Data.creaTabellone();
	}

	/**
	 * metodo che si occupa di creare una nuova partita
	 */
	public void nuovaPartita() {
		Vector<Giocatore> nuoviGiocatori = new Vector<Giocatore>();
		boolean ok = false;

		while (!ok) {
			String nome;
			do{
			nome = MyUtil.riceviString("\n\n"+RICHIESTA_NOME_GIOCATORE);
			if(tabellone.esisteGia(nome, nuoviGiocatori))
				System.out.printf(ERR_ESISTE_GIA + "%n");
			}
			while(tabellone.esisteGia(nome,nuoviGiocatori));
			
			nuoviGiocatori.add(new Giocatore(nome));

			if (MyUtil.yesOrNo(FINE_INSERIMENTO_GIOCATORI)) {
				if (nuoviGiocatori.size() >= 6) {
					System.out.println(ERRORE_TROPPI_GIOCATORI);
					ok = true;
				}
			} else {
				if (nuoviGiocatori.size() < 2)
					System.out.println(ERRORE_POCHI_GIOCATORI);
				else
					ok = true;
			}

		}

		tabellone.mescolaGiocatori(nuoviGiocatori);
		dado = new Dado();
		partita();
	}

	/**
	 * Metodo di gioco. Qui e' presente il ciclo infinito del gioco
	 */
	public void partita() {
		boolean inGame = true;
		
		while (inGame) {
			// Seleziona il giocatore attuale per una migliore gestione
			Giocatore giocatoreAttuale = tabellone.getElencoGiocatori().get(
					tabellone.getTurnoGiocatore());
						
			MyMenu menuGioco = new MyMenu(TITOLO_TURNO01
					+ tabellone.getTurniAttuali() + TITOLO_TURNO02
					+ giocatoreAttuale.getNome() + TITOLO_TURNO03 + giocatoreAttuale.getCapitale() + " €"
					, VOCI_MENU_GIOCO);

			giocatoreAttuale.setToken(true);
			int scelta;
			
			do {
				System.out.print("\n\n");
				scelta = menuGioco.scegli();
				System.out.print("\n\n");
				switch (scelta) {
				// Lancio dei dadi
				case 1:
					gestioneTurno(giocatoreAttuale);
					break;

				case 2:
					salvaPartita();
					if(!MyUtil.yesOrNo(PARTITA_SALVATA)){ 
						inGame = false;
						giocatoreAttuale.setToken(false);
					}
					break;

				case 3:
					System.out.printf(tabellone.toString());
					break;

				case 0:
					inGame = false;
					giocatoreAttuale.setToken(false);
					break;

				}
			} while (giocatoreAttuale.hasToken());

			if(scelta == 1)
				System.out.println("\n"+MESSAGGIO_FINE_TURNO);
			
			// Assegna il turno di gioco al prossimo giocatore
			tabellone.setTurnoGiocatore(tabellone.getTurnoGiocatore() + 1);;
			if (tabellone.getTurnoGiocatore() > tabellone.getElencoGiocatori().size() - 1)
				tabellone.setTurnoGiocatore(0);

			// Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
			if (tabellone.getTurniAttuali() > 20) {
				inGame = false;
			}
		}
	}// fine metodo partita
	

	/**
	 * metodo per gestire il turno di un giocatore
	 * 
	 * @param giocatoreAttuale
	 *            il giocatore che puo' giocare in questo turno
	 */
	public void gestioneTurno(Giocatore giocatoreAttuale) {
		
		//Se il giocatore non e' in prigione
		if (!giocatoreAttuale.isInPrigione()) {
			dado.lancioDadi(); //Lancia i dadi
			tabellone.muoviGiocatore(giocatoreAttuale, dado.risultato()); //Muove il giocatore
			controlloDopoTiro(giocatoreAttuale);
            
				
			//esegue di nuovo il controllo sulla prigione perchè dopo il tiro dei dadi potrebbe essere in prigione
			if (!dado.sonoUguali() || giocatoreAttuale.isInPrigione()) {
				giocatoreAttuale.setNumeroLanci(0); //Se non ha tirato doppio, resetta il contatore
				giocatoreAttuale.setToken(false);
			} else {
				giocatoreAttuale.setNumeroLanci(giocatoreAttuale
						.getNumeroLanci() + 1); //Se invece ha tirato doppi, aumenta il contatore
				giocatoreAttuale.setToken(true);
				if (giocatoreAttuale.getNumeroLanci() >= 3) { //Se ha tirato per 3 volte di seguito doppio
					System.out.println(MESSAGGIO_TROPPI_LANCI); //Lo avvisa
					tabellone.teleportGiocatore(giocatoreAttuale, Data.getPrigione()); // AH-AH-AH (finisce in prigione)
					giocatoreAttuale.setInPrigione(true); 
					giocatoreAttuale.setNumeroLanci(0);
					giocatoreAttuale.setToken(false);
				} else { //Se ha tirato doppio, ma non e' ancora finito in prigione, lo avvisa di avere a disposizione un altro lancio
					System.out.println(LANCIO_DOPPIO);
				}
			}
		} else { //Se e' in prigione
			dado.lancioDadi(); //Cerca di uscire lanciando i dadi
			if (dado.sonoUguali()) { //Se sono uguali 
				giocatoreAttuale.setInPrigione(false); //Esce di prigione
				giocatoreAttuale.setToken(true); //E ha a disposizione un altro lancio
				System.out.println(USCITO_DI_PRIGIONE);
			}
		}
		
		//Aggiorna il giocatore sulla sua posizione attuale
		System.out.printf(MESSAGGIO_POSIZIONE, dado.risultato(),
			giocatoreAttuale.getPosizione(), tabellone.getCaselle()
					.get(giocatoreAttuale.getPosizione()).getNome());
		
	}// fine gestioneTurno

	
	public void controlloDopoTiro(Giocatore giocatoreAttuale){
		//Controlla dove si trova il giocatore
		
			Casella casellaAttuale = tabellone.getCaselle().get(giocatoreAttuale.getPosizione());
			switch (casellaAttuale.getTipo()) {
			case 1:
				Tassa t = (Tassa) casellaAttuale;
				giocatoreAttuale.setCapitale(giocatoreAttuale.getCapitale() - t.getMalus());
				System.out.printf(CASELLA_TASSA, t.getNome(), t.getMalus());
			break;
			
			case 2: 
				tabellone.teleportGiocatore(giocatoreAttuale, Data.getPrigione());
				System.out.println(MESSAGGIO_IN_PRIGIONE);
				giocatoreAttuale.setInPrigione(true);
			break;
			

			default:
				break;
			}
			
	}	
	


	/**
	 * metodo che si occupa di caricare una partita salvata precedenntemente e
	 * riprenderla da dove interrotta
	 */
	public void riprendiPartita() {

		if (ServizioFile.esistenza(filePartita))
			try {
				tabellone = (Tabellone) ServizioFile
						.caricaSingoloOggetto(filePartita);
			} catch (ClassCastException e) {
				System.out.println(MESS_NO_CAST);
			}
		if (!tabellone.getElencoGiocatori().isEmpty()) {
			System.out.println(FILE_CARICATI);
			dado = new Dado();
			partita();
		} else
			System.out.println(NESSUNA_PARTITA_SALVATA);

	}

	/**
	 * metodo per salvare una partita in corso
	 */
	public void salvaPartita() {
		if (!tabellone.getElencoGiocatori().isEmpty()) {
			ServizioFile.salvaSingoloOggetto(filePartita, tabellone);
			System.out.println(FILE_SALVATI);
		} else
			System.out.printf(NIENTE_DA_SALVARE);

	}// fine salvaPartita

}
