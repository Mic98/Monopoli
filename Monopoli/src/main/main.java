package main;

import java.util.Vector;

import utilities.MyMenu;

public class main {
	
	private Vector<Giocatore> giocatore;
	private Tabellone tabellone;
	private MyMenu menu;
	
	public main(){		
		inizializzazione();
		
		//Fase di creazione giocatori
		
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
