package main;

import java.util.Vector;

import utilities.MyMenu;

public class main {
	
	private Vector<Giocatore> giocatore;
	private Tabellone tabellone;
	private MyMenu menuMonopoli;
	
	private final static String TITOLO = "MONOPOLI";
	private final static String VOCE_01 ="Crea nuova partita";
	private final static String[] VOCI_MENU={VOCE_01};
	public main(){		
		inizializzazione();
		
		int scelta;
		menuMonopoli = new MyMenu(TITOLO, VOCI_MENU);
		
		do{
			scelta = menuMonopoli.scegli();
			
			switch(scelta){
			case 1:break;//Fase di creazione giocatori
			default:break;

			}
		}
			while(scelta != 0);
		
				
	}
	
	
	//Crea e instanzia tutte le caselle del tabellone
	private void inizializzazione(){
		tabellone = new Tabellone();
		
		String [] nomi_caselle = Data.getNomiCaselle();
		
		int i;
		for(i=0; i<40; i++){
			tabellone.aggiungiCasella(new Casella(nomi_caselle[i], i));
		}
	}
}
