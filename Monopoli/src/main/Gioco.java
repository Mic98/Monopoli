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
	private final static String CASELLA_TASSA = "\nSei finito sulla casella %s, devi %.2f euro alla banca \n\n";
	private final static String UN_SOLO_GIOCATORE = "\n\nE' rimasto un solo giocatore in partita\n";
	private final static String VINCITORE = "VINCITORE";
	private final static String RIMANI_IN_PRIGIONE = "Il tuo tiro non ha avuto esito positivo rimani in prigione";
	private final static String VINCITORI = "VINCITORI";
	
	private final static String ACQUISTATO = "\nComplimenti! Hai acquistato %s al costo di %.2f euro\n\n";
	private static final String NON_ACQUISTATO = "\nSpiacente! Non sei riuscito a comprare %s perche' non possiedi sufficiente capitale\n\n";
	private static final String TERRITORIO_NEMICO = "\nTi trovi sul territorio di %s e gli devi un totale di %.2f euro\n\n";
	private final static String CAPITALE_INSUFFICIENTE = "Non hai sufficiente capitale per pagare l'affitto a %s per questo tutti i tuoi soldi rimasti saranno dati a lui";


	private final static String TITOLO_TURNO01 = "Turno n: ";
	private final static String TITOLO_TURNO02 = "\tTurno di: ";
	private final static String TITOLO_TURNO03 = "\tCapitale posseduto: ";
	private final static String VOCE_TURNO01 = "Lancio dadi";
	private final static String LANCIO_DOPPIO = "Hai fatto un lancio con due numeri uguali, hai diritto ad un altro tiro ";
	private final static String USCITO_DI_PRIGIONE = "Hai tirato doppio, sei uscito di prigione e puoi lanciare ancora";
	private final static String MESSAGGIO_IN_PRIGIONE = "\nSei finito nella casella \"IN PRIGIONE!\" per questo motivo verrai spostato in prigione";
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
			
			// Assegna il turno di gioco al prossimo giocatore
			tabellone.setTurnoGiocatore(tabellone.getTurnoGiocatore() + 1);;
			if (tabellone.getTurnoGiocatore() > tabellone.getElencoGiocatori().size() - 1)
				tabellone.setTurnoGiocatore(0);

			// Cotrolla se abbiamo raggiunto il massimo dei turni disponibili
			tabellone.setTurniAttuali(tabellone.getTurniAttuali() + 1);
			if (tabellone.getTurniAttuali() > Data.NUMERO_TURNI) {
				System.out.println(dichiaraVincitori());
				System.out.println("\n\n");
				inGame = false;
				
			}
		  }
			if(tabellone.getElencoGiocatori().size()==1){
				System.out.println(UN_SOLO_GIOCATORE);
				System.out.println(BelleStringhe.incornicia(VINCITORE) + "\n\t" + tabellone.getElencoGiocatori().get(0).getNome());
				tabellone.getClassificaFinale().add(tabellone.getElencoGiocatori().get(0));
				tabellone.getElencoGiocatori().remove(0);
				inGame= false;
			}
			
		
	}// fine metodo partita

	/**
	 * Gestisce il turno di un giocatore
	 * 
	 * @param giocatoreAttuale
	 *            il giocatore che puo' giocare in questo turno
	 */
	public void gestioneTurno(Giocatore giocatoreAttuale) {
		
		dado.lancioDadi(); //Lancia i dadi
		
		//Se il giocatore non e' in prigione
		if (!giocatoreAttuale.isInPrigione()) {
			giocatoreAttuale.muoviGiocatore(dado.risultato()); //Muove il giocatore
			controlloDopoTiro(giocatoreAttuale);
            
			if(!giocatoreAttuale.inBancaRotta()){
				//esegue di nuovo il controllo sulla prigione perche' dopo il tiro dei dadi potrebbe essere in prigione
				if (!dado.sonoUguali() || giocatoreAttuale.isInPrigione()) {
					stampaPosizione(giocatoreAttuale);
					giocatoreAttuale.setNumeroLanci(0); //Se non ha tirato doppio, resetta il contatore
					giocatoreAttuale.setToken(false);
			} else {
				giocatoreAttuale.setNumeroLanci(giocatoreAttuale
						.getNumeroLanci() + 1); //Se invece ha tirato doppi, aumenta il contatore
				giocatoreAttuale.setToken(true);
				if (giocatoreAttuale.getNumeroLanci() >= 3) { //Se ha tirato per 3 volte di seguito doppio
					System.out.println(MESSAGGIO_TROPPI_LANCI); //Lo avvisa
					giocatoreAttuale.setPosizione(Data.PRIGIONE); // AH-AH-AH (finisce in prigione)
					giocatoreAttuale.setInPrigione(true); 
					giocatoreAttuale.setNumeroLanci(0);
					giocatoreAttuale.setToken(false);
				} else { //Se ha tirato doppio, ma non e' ancora finito in prigione, lo avvisa di avere a disposizione un altro lancio
					System.out.println(LANCIO_DOPPIO);
					stampaPosizione(giocatoreAttuale);
				}
			  }
			}
			else{//se il giocatore e' in bancarotta esce dalla partita
				System.out.println(IN_BANCA_ROTTA);
				giocatoreAttuale.setToken(false);
				giocatoreAttuale.setCapitale(0);
				tabellone.getClassificaFinale().add(giocatoreAttuale);
				tabellone.getElencoGiocatori().remove(giocatoreAttuale);
				
			}
		} else { //Se e' in prigione
			gestionePrigione(giocatoreAttuale);
		}	
		
	}// fine gestioneTurno
	
	/**
	 * Gestisce l'uscita del giocatore dalla prigione
	 * 
	 * @param giocatoreAttuale giocatore in prigione
	 */
	public void gestionePrigione(Giocatore giocatoreAttuale){
		if (dado.sonoUguali()) { //Se sono uguali 
			giocatoreAttuale.setInPrigione(false); //Esce di prigione
			giocatoreAttuale.setToken(true); //E ha a disposizione un altro lancio
			System.out.println(USCITO_DI_PRIGIONE);
		}	
		else{
			System.out.println(RIMANI_IN_PRIGIONE);
			giocatoreAttuale.setToken(false);
		}
	}

	/**
	 * Stampa la posizione attuale del giocatore
	 * 
	 * @param giocatoreAttuale giocatore di cui si vuole sapere la posizione
	 */
	public void stampaPosizione(Giocatore giocatoreAttuale){
		//Aggiorna il giocatore sulla sua posizione attuale
				System.out.printf(MESSAGGIO_POSIZIONE, dado.risultato(),
					giocatoreAttuale.getPosizione(), tabellone.getCaselle()
							.get(giocatoreAttuale.getPosizione()).getNome());
	}
	
	
	/**
	 * Metodo di controllo della posizione del giocatore dopo il tiro dei dadi
	 * 
	 * @param giocatoreAttuale giocatore che ha appena tirato il dado
	 */
	public void controlloDopoTiro(Giocatore giocatoreAttuale){
		//Controlla dove si trova il giocatore
		
			Casella casellaAttuale = tabellone.getCaselle().get(giocatoreAttuale.getPosizione());
			switch (casellaAttuale.getTipo()) {
				case Casella.TASSE:
					Tassa t = (Tassa) casellaAttuale;
					giocatoreAttuale.prelevaCapitale(t.getMalus());
					System.out.printf(CASELLA_TASSA, t.getNome(), t.getMalus());
				break;
				
				
				case Casella.VAI_IN_PRIGIONE: 
					giocatoreAttuale.setPosizione(Data.PRIGIONE);
					System.out.println(MESSAGGIO_IN_PRIGIONE);
					giocatoreAttuale.setInPrigione(true);
				break;
				
				
				case Casella.ACQUISTABILE:
                    Acquistabile acquistabile = (Acquistabile) casellaAttuale;
                    if(acquistabile.isAcquistabile()){
                        if(giocatoreAttuale.puoPermetterselo(acquistabile.getValore())){
                          System.out.printf(ACQUISTATO, acquistabile.getNome(), acquistabile.getValore());	 
                          giocatoreAttuale.prelevaCapitale(acquistabile.getValore());
				          acquistabile.setAcquistabile(false);
                          giocatoreAttuale.aggiungiProprieta(acquistabile);
                       }
                        else
                    	 System.out.printf(NON_ACQUISTATO, acquistabile.getNome() );
                    }
                    else{
                    	
                    	 double prezzoDaPagare;
                    	 Giocatore proprietario = acquistabile.trovaProprietario(tabellone.getElencoGiocatori());
                    	 
                    if(!giocatoreAttuale.getNome().equalsIgnoreCase(proprietario.getNome())){
                    	 if(acquistabile instanceof Terreno){
                    		 Terreno terreno = (Terreno) acquistabile;
                    	 if(proprietario.possiedeTuttiTerreni(terreno.getColore()))
                    		 prezzoDaPagare = 2* terreno.getCosto();
                    	 else
                    		 prezzoDaPagare = terreno.getCosto();
                    	 }
                    	 else 
                    		 prezzoDaPagare = acquistabile.getCosto();
                    	 
                    	 System.out.printf(TERRITORIO_NEMICO, proprietario.getNome(), prezzoDaPagare);
                    	 
                    	 if(giocatoreAttuale.puoPermetterselo(prezzoDaPagare)){
                    	    proprietario.aggiungiCapitale(prezzoDaPagare);
                    	    giocatoreAttuale.prelevaCapitale(prezzoDaPagare);
                    	 }
                    	 else{
                    		 System.out.printf(CAPITALE_INSUFFICIENTE, proprietario.getNome());
                    		 proprietario.aggiungiCapitale(giocatoreAttuale.getCapitale());
                    		 giocatoreAttuale.setCapitale(0);
                    	 } 
                      }
                    }
				       
				break;
                    
				
				case Casella.SOCIETA:
					Societa societa = (Societa) casellaAttuale;
					if(societa.isAcquistabile()){
                        if(giocatoreAttuale.puoPermetterselo(societa.getValore())){
                          System.out.printf(ACQUISTATO, societa.getNome(), societa.getValore());
                          giocatoreAttuale.prelevaCapitale(societa.getValore());
				          societa.setAcquistabile(false);
                          giocatoreAttuale.aggiungiProprieta(societa);
                         }
                        else
                    	 System.out.printf(NON_ACQUISTATO,societa.getNome() );	
					}
					else{
						double prezzoDaPagare;
						Giocatore proprietario = societa.trovaProprietario(tabellone.getElencoGiocatori());
						
						if(!giocatoreAttuale.getNome().equalsIgnoreCase(proprietario.getNome())){
						if(proprietario.possiedeTutteSocieta())
							prezzoDaPagare = societa.costoDoppio(dado);
						else
							prezzoDaPagare = societa.getCosto(dado);
						
                   	    System.out.printf(TERRITORIO_NEMICO, proprietario.getNome(), prezzoDaPagare);

						if(giocatoreAttuale.puoPermetterselo(prezzoDaPagare)){
	                    	 proprietario.aggiungiCapitale(prezzoDaPagare);
	                    	 giocatoreAttuale.prelevaCapitale(prezzoDaPagare);
	                    	 }
	                    	 else{
	                    		 System.out.printf(CAPITALE_INSUFFICIENTE, proprietario.getNome());
	                    		 proprietario.aggiungiCapitale(giocatoreAttuale.getCapitale());
	                    		 giocatoreAttuale.setCapitale(0);
	                    	 }
						}
					}
					break;
					
				case Casella.PROBABILITA:
				     tabellone.getProbabilita().pescaCarta(giocatoreAttuale);
				     break;
				     
				     
				case Casella.IMPREVISTI:
					 tabellone.getImprevisti().pescaCarta(giocatoreAttuale);
					 break;
                    
				default:
				break;
			}
			
			
	}	
	
    
	/**
	 * 
	 * @return messaggio a schermo che dichiara i vincitori della partita
	 */
	public String dichiaraVincitori(){
		StringBuilder visualizza = new StringBuilder();
	
		tabellone.classifica();
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
