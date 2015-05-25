/**
 * 
 */
package main;

import java.io.File;
import java.util.Vector;

import utilities.MyMenu;
import utilities.MyRandom;
import utilities.MyUtil;
import utilities.ServizioFile;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Gioco {

	private static final String MESSAGGIO_FINE_TURNO = "Il tuo turno � concluso";
	private static final String MESSAGGIO_TROPPI_LANCI = "Hai ottenuto tre volte di seguito lo stesso punteggio per entrambi i dadi, andrai in prigione";
	private static final String MESSAGGIO_POSIZIONE = "Il tuo lancio ha dato come risultato: %d%nOra sei nella casella n�: %d %s%n";

	private final static String TITOLO_TURNO01 = "Turno n�: ";
	private final static String TITOLO_TURNO02 = " Turno di: ";
	private final static String VOCE_TURNO01 = "Lancio dadi";
	private static final String LANCIO_DOPPIO = "Hai fatto un lancio con due numeri uguali, hai diritto ad un altro tiro ";
	private final static String VOCE_TURNO02 = "Salva partita";
	private final static String VOCE_TURNO03 = "Mostra elenco giocatori";
	private final static String[] VOCI_MENU_GIOCO = { VOCE_TURNO01,
			VOCE_TURNO02, VOCE_TURNO03 };

	// ----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ERRORE_POCHI_GIOCATORI = "ATTENZIONE!!! Minimo 2 giocatori!";
	private final static String ERRORE_TROPPI_GIOCATORI = "ATTENZIONE!!! Massimo 6 giocatori! La partita inzier�";

	// ----------SALVATAGGIO E CARICAMENTI DATI-------------------
	private static final String PARTITA_FILE = "partita.dat";
	private static final String MESS_NO_CAST = "ATTENZIONE! problemi con il cast";
	private static final String FILE_CARICATI = "I file sono stati caricati con successo";
	private static final String FILE_SALVATI = "I file sono stati salvati";
	private static final String NIENTE_DA_SALVARE = "Non esistono dati da salvare";
	private static final String NESSUNA_PARTITA_SALVATA = "Non c'� nessuna partita preesistente";

	private static final File filePartita = new File(PARTITA_FILE);
	private static final String PARTITA_SALVATA = null;

	public static Tabellone tabellone;
	public static Dado dado;

	public Gioco() {
		tabellone = new Tabellone();
	}

	/**
	 * metodo che si occupa di creare una nuova partita
	 */
	public void nuovaPartita() {
		Vector<Giocatore> nuoviGiocatori = new Vector<Giocatore>();
		boolean ok = false;

		while (!ok) {
			String nome = MyUtil.riceviString(RICHIESTA_NOME_GIOCATORE);
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

		tabellone.addGiocatori(nuoviGiocatori);
		dado = new Dado(1, 1);
		partita();
	}

	/**
	 * Metodo di gioco. Qui e' presente il ciclo infinito del gioco
	 */
	public void partita() {
		boolean partitaInterrotta = false;
		boolean inGame = true;
		
		while (inGame && !partitaInterrotta) {
			// Seleziona il giocatore attuale per una migliore gestione
			Giocatore giocatoreAttuale = tabellone.getElencoGiocatori().get(
					tabellone.turnoGiocatore);
			
			MyMenu menuGioco = new MyMenu(TITOLO_TURNO01
					+ tabellone.getTurniAttuali() + TITOLO_TURNO02
					+ giocatoreAttuale.getNome(), VOCI_MENU_GIOCO);

			int scelta;
			
			do {
				scelta = menuGioco.scegli();

				switch (scelta) {
				// Lancio dei dadi
				case 1:
					gestioneTurno(giocatoreAttuale);
					break;

				// notare che al momento del salvataggio un giocatore possiede
				// il token, nel caso del caricamento della partita in futuro si
				// sapr� da quale giocatore riprendere
				case 2:
					salvaPartita();
					if(!MyUtil.yesOrNo(PARTITA_SALVATA)){
						scelta = 0; 
						partitaInterrotta = true;
					}
					break;

				case 3:
					System.out.printf(tabellone.toString());
					break;

				case 0:
					partitaInterrotta = true;
					break;

				}
			} while (scelta != 0);

			// Assegna il turno di gioco al prossimo giocatore
			tabellone.turnoGiocatore++;
			if (tabellone.turnoGiocatore > tabellone.getElencoGiocatori().size() - 1)
				tabellone.turnoGiocatore = 0;

			// Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
			if (tabellone.getTurniAttuali() >= 20) {
				inGame = false;
			}
		}
	}// fine metodo partita

	/**
	 * metodo per gestire il turno di un giocatore
	 * 
	 * @param giocatoreAttuale
	 *            il giocatore che pu� giocare in questo turno
	 */
	public void gestioneTurno(Giocatore giocatoreAttuale) {

		if (!giocatoreAttuale.isInPrigione()) {
			lancioDadi();
			tabellone.movePlayer(giocatoreAttuale, dado.risultato());

			if (!dado.sonoUguali()) {
				System.out.println(MESSAGGIO_FINE_TURNO);
				giocatoreAttuale.setNumeroLanci(0);
			} else {
				System.out.println(LANCIO_DOPPIO);
				giocatoreAttuale.setNumeroLanci(giocatoreAttuale
						.getNumeroLanci() + 1);
			}
			if (giocatoreAttuale.getNumeroLanci() >= 3) {
				System.out.println(MESSAGGIO_TROPPI_LANCI);
				tabellone.teleportPlayer(giocatoreAttuale, Data.getPrigione()); // AH-AH-AH
				giocatoreAttuale.setInPrigione(true);
				giocatoreAttuale.setNumeroLanci(0);
			}
			
			System.out.printf(MESSAGGIO_POSIZIONE, dado.risultato(),
					giocatoreAttuale.getPosizione(), tabellone.getCaselle()
							.get(giocatoreAttuale.getPosizione()).getNome());
			
		} else {
			lancioDadi();
			if (dado.sonoUguali()) {
				giocatoreAttuale.setInPrigione(false);
			}

		}
	}// fine gestioneTurno


	/**
	 * metodo per lanciare i dadi
	 */
	public void lancioDadi() {

		dado.setLancio1(MyRandom.estraiIntero(Dado.getDadoMIN(),
				Dado.getDadoMAX()));
		dado.setLancio2(MyRandom.estraiIntero(Dado.getDadoMIN(),
				Dado.getDadoMAX()));
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
			dado = new Dado(1, 1);
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
