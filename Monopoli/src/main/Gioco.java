/**
 * 
 */
package main;

import java.io.File;
import java.util.Vector;

import carte.*;
import caselle.*;
import utilities.BelleStringhe;
import utilities.MyMenu;
import utilities.MyUtil;
import utilities.ServizioFile;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Gioco {

	private final static String MESSAGGIO_FINE_TURNO = "Il tuo turno e' concluso";
	private final static String MESSAGGIO_TROPPI_LANCI = "Hai ottenuto tre volte di seguito lo stesso punteggio per entrambi i dadi, andrai in prigione";
	private final static String MESSAGGIO_POSIZIONE = "Il tuo lancio ha dato come risultato: %d \nOra sei nella casella n: %d %s\n";
	private final static String IN_BANCA_ROTTA = "\n\nHai finito i soldi! Sei in bancarotta, la tua partita e' finita.\n";
	private final static String UN_SOLO_GIOCATORE = "\n\nE' rimasto un solo giocatore in partita\n";
	private final static String VINCITORE = "VINCITORE";
	private final static String RIMANI_IN_PRIGIONE = "Il tuo tiro non ha avuto esito positivo rimani in prigione";
	private final static String VINCITORI = "VINCITORI";
	private final static String MESSAGGIO_TASSA_PRIGIONE = "\nPrima di lanciare i dadi dovrai pagare una tassa di %.2f euro\n";
	

	private final static String TITOLO_TURNO01 = "Turno n: ";
	private final static String TITOLO_TURNO02 = "\tTurno di: ";
	private final static String TITOLO_TURNO03 = "\tCapitale posseduto: ";
	private final static String VOCE_TURNO01 = "Lancio dadi";
	private final static String LANCIO_DOPPIO = "Hai fatto un lancio con due numeri uguali, hai diritto ad un altro tiro ";
	private final static String USCITO_DI_PRIGIONE = "Hai tirato doppio, sei uscito di prigione e puoi lanciare ancora";
	private final static String VOCE_TURNO02 = "Visualizza le tue proprieta'";
	private final static String VOCE_TURNO03 = "Visualizza situazione partita";
	private final static String VOCE_TURNO04 = "Passa il turno";
	private final static String VOCE_TURNO05 = "Salva partita";
	private final static String[] VOCI_MENU_GIOCO = { VOCE_TURNO01,
			VOCE_TURNO02, VOCE_TURNO03, VOCE_TURNO04, VOCE_TURNO05 };

	// ----------NUOVA PARTITA--------------------
	private final static String RICHIESTA_NOME_GIOCATORE = "Nome giocatore: ";
	private final static String ERR_ESISTE_GIA = "Il nome inserito e' gia' esistente, inserirne un altro";
	private final static String FINE_INSERIMENTO_GIOCATORI = "Ci sono altri giocatori?";
	private final static String ERRORE_POCHI_GIOCATORI = "ATTENZIONE!!! Minimo 2 giocatori!";
	private final static String ERRORE_TROPPI_GIOCATORI = "ATTENZIONE!!! Massimo 6 giocatori! La partita inziera'";

	// ----------SALVATAGGIO E CARICAMENTI DATI-------------------
	private final static String PARTITA_FILE = "Partita.dat";
	private final static String MESS_NO_CAST = "ATTENZIONE! problemi con il cast";
	private final static String FILE_CARICATI = "\nI file sono stati caricati con successo";
	private final static String FILE_SALVATI = "I file sono stati salvati\n\n";
	private final static String NESSUNA_PARTITA_SALVATA = "\nNon c'e' nessuna partita preesistente";
	private final static String PARTITA_SALVATA = "La partita e' stata salvata, vuoi continuare a giocare?";
	private final static String NIENTE_DA_SALVARE = "Non esistono dati da salvare";

		
	private final static File filePartita = new File(PARTITA_FILE);

	public static Tabellone tabellone = new Tabellone();
	public static Dado dado;

	
	
	

	/**
	 * Costruttore della classe Gioco
	 */
	public Gioco() {
		tabellone = Data.creaTabellone();
	}

	/**
	 * Crea una nuova partita
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
		
		while (inGame && tabellone.getElencoGiocatori().size()>1) {
			// Seleziona il giocatore attuale per una migliore gestione
			Giocatore giocatoreAttuale = tabellone.getElencoGiocatori().get(
					tabellone.getTurnoGiocatore());
						
			MyMenu menuGioco = new MyMenu(TITOLO_TURNO01
					+ tabellone.getTurniAttuali() + TITOLO_TURNO02
					+ giocatoreAttuale.getNome() + TITOLO_TURNO03 + giocatoreAttuale.getCapitale() + " euro"
					, VOCI_MENU_GIOCO);

			giocatoreAttuale.setToken(true);
			int scelta;
			boolean passa;
			
			do {
				System.out.print("\n\n");
				scelta = menuGioco.scegli();
				System.out.print("\n\n");
				passa = false;
				switch (scelta) {
				// Lancio dei dadi
				case 1:
					if(giocatoreAttuale.hasToken())
						gestioneTurno(giocatoreAttuale);
					else 
						System.out.print("Hai gia' lanciato!");
						
					break;

				case 2: 
					System.out.printf(giocatoreAttuale.toString());
					break;
								

				case 3:
					System.out.printf(tabellone.toString());
					break;
					
				case 4:
					if(giocatoreAttuale.hasToken())
						System.out.printf("Devi ancora tirare!");
					else
						passa = true;
						
					break;
					
				case 5:
					salvaPartita();
					if(!MyUtil.yesOrNo(PARTITA_SALVATA)){ 
						giocatoreAttuale.setToken(false);
						passa = true;
						inGame = false;
						
					}
					break;
					
					
				case 0:
					giocatoreAttuale.setToken(false);
					passa = true;
					inGame = false;
					break;

				}
				
				menuGioco.setTitolo(TITOLO_TURNO01
					+ tabellone.getTurniAttuali() + TITOLO_TURNO02
					+ giocatoreAttuale.getNome() + TITOLO_TURNO03 + giocatoreAttuale.getCapitale() + " euro");
			} while (!passa || giocatoreAttuale.hasToken());

			if(scelta == 4)
				System.out.println("\n"+MESSAGGIO_FINE_TURNO);
			
			tabellone.incrementaTurnoGiocatore();

			// Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
			if (tabellone.getTurniAttuali() > Data.NUMERO_TURNI) {
				System.out.println(dichiaraVincitori());
				System.out.println("\n\n");
				inGame = false;
				
			}
		  }
			if(tabellone.getElencoGiocatori().size()==1){
				tabellone.unGiocatoreRimasto();
				inGame= false;
			}
			
		
	}// fine metodo partita

	
	
	
	/**
	 * Gestisce il turno di un giocatore
	 * 
	 * @param giocatoreAttuale
	 *            Il giocatore che puo' giocare in questo turno
	 */
	public void gestioneTurno(Giocatore giocatoreAttuale) {
		
		dado.lancioDadi();
		
		if (!giocatoreAttuale.isInPrigione()) {
			giocatoreAttuale.controlloFineTiro(dado.risultato());
			
		  if(!giocatoreAttuale.inBancaRotta()){
			//esegue di nuovo il controllo sulla prigione perche' dopo il tiro dei dadi potrebbe essere in prigione
			if (!dado.sonoUguali() || giocatoreAttuale.isInPrigione()) 
				gestioneDadiDiversi(giocatoreAttuale);
			else 
				gestioneTiroDoppio(giocatoreAttuale);	  
		  }	
		
		} else  //Se e' in prigione
			gestionePrigione(giocatoreAttuale);
		
		if(giocatoreAttuale.inBancaRotta())
			gestioneBancaRotta(giocatoreAttuale);
			
		
	}// fine gestioneTurno
	
	/**
	 * Le azioni da eseguire nel caso il tiro dei dadi abbia dato due risultati diversi
	 * @param giocatoreAttuale Il giocatore che ha tirato il dado
	 */
	public void gestioneDadiDiversi(Giocatore giocatoreAttuale){
		stampaPosizione(giocatoreAttuale);
		giocatoreAttuale.setNumeroLanci(0); //Se non ha tirato doppio, resetta il contatore
		giocatoreAttuale.setToken(false);
	}
	
	/**
	 * Le azioni da eseguire nel caso il tiro dei dadi abbia dato due risultati uguali
	 * @param giocatoreAttuale
	 */
	public void gestioneTiroDoppio(Giocatore giocatoreAttuale){
		giocatoreAttuale.setNumeroLanci(giocatoreAttuale.getNumeroLanci() + 1); //Se invece ha tirato doppio, aumenta il contatore
		giocatoreAttuale.setToken(true);
		
		if (giocatoreAttuale.getNumeroLanci() >= 3) { //Se ha tirato per 3 volte di seguito doppio
			System.out.println(MESSAGGIO_TROPPI_LANCI); //Lo avvisa
			giocatoreAttuale.vaiInPrigione();
			}
		else { //Se ha tirato doppio, ma non e' ancora finito in prigione, lo avvisa di avere a disposizione un altro lancio
			System.out.println(LANCIO_DOPPIO);
			stampaPosizione(giocatoreAttuale);
		}
		
	}
	
	/**
	 * Gestisce la permanenza e l'uscita del giocatore nella prigione
	 * 
	 * @param giocatoreAttuale Giocatore in prigione
	 */
	public void gestionePrigione(Giocatore giocatoreAttuale){
		System.out.printf(MESSAGGIO_TASSA_PRIGIONE, Data.TASSA_PRIGIONE);
		
		if(giocatoreAttuale.puoPermetterselo(Data.TASSA_PRIGIONE)){
			giocatoreAttuale.prelevaCapitale(Data.TASSA_PRIGIONE);
			
		if (dado.sonoUguali()) { //Se sono uguali
			giocatoreAttuale.esceDiPrigione();
			System.out.println(USCITO_DI_PRIGIONE);
		}	
		else{
			System.out.println(RIMANI_IN_PRIGIONE);
			giocatoreAttuale.setToken(false);
			giocatoreAttuale.setNumeroLanci(giocatoreAttuale.getNumeroLanci() + 1);
		}
	   }
			
	}

	
	/**
	 * Gestisce l'uscita del giocatore dalla partita a causa di banca rotta
	 * @param giocatoreAttuale il giocatore perdente
	 */
	public void gestioneBancaRotta(Giocatore giocatoreAttuale){
		System.out.println(IN_BANCA_ROTTA);
		giocatoreAttuale.finitoCapitale();
		tabellone.getClassificaFinale().add(giocatoreAttuale);
		tabellone.getElencoGiocatori().remove(giocatoreAttuale);
	}
	
	/**
	 * Stampa la posizione attuale del giocatore
	 * 
	 * @param giocatoreAttuale Giocatore di cui si vuole sapere la posizione
	 */
	public void stampaPosizione(Giocatore giocatoreAttuale){
		//Aggiorna il giocatore sulla sua posizione attuale
				System.out.printf(MESSAGGIO_POSIZIONE, dado.risultato(),
					giocatoreAttuale.getPosizione(), tabellone.getCaselle()
							.get(giocatoreAttuale.getPosizione()).getNome());
	}
	
	
	
    
	/**
	 * 
	 * @return Messaggio a schermo contenente l'elenco dei vincitori della partita
	 */
	public String dichiaraVincitori(){
		StringBuilder visualizza = new StringBuilder();
	
		Vector<Giocatore> vincenti = tabellone.trovaVincitori();
		if(vincenti.size() > 1)
		   visualizza.append(BelleStringhe.incornicia(VINCITORI));
		if(vincenti.size() ==1)
		   visualizza.append(BelleStringhe.incornicia(VINCITORE));
		for(int i=0; i<vincenti.size(); i++)
			visualizza.append("\t" + vincenti.get(i).getNome());
		
		
		
		return visualizza.toString();
	}
	
	
	/**
	 * Carica una partita salvata precedentemente e
	 * la riprende da dove interrotta
	 */
	public void riprendiPartita() {
		if (ServizioFile.esistenza(filePartita))
			try {
				tabellone = (Tabellone) ServizioFile.caricaSingoloOggetto(filePartita);
			}
		    catch (ClassCastException e) {
				System.out.println(MESS_NO_CAST);
			}
		
		if (!tabellone.getElencoGiocatori().isEmpty() && !tabellone.getCaselle().isEmpty()){	
			
			System.out.println(FILE_CARICATI);
			dado = new Dado();
			partita();
		
		} 
		else
			System.out.println(NESSUNA_PARTITA_SALVATA);
		
	}
	
	/**
	 * Salva la partita in corso
	 */
	public void salvaPartita() {
		if (!tabellone.getElencoGiocatori().isEmpty()) {
			ServizioFile.salvaSingoloOggetto(filePartita, tabellone);
			System.out.println(FILE_SALVATI);
		} else
			System.out.printf(NIENTE_DA_SALVARE);

	}// fine salvaPartita

}
