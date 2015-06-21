package carte;

import main.Data;
import main.Giocatore;

public class VaiA extends Carta{
	
	private final static String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai %.2f euro di bonus \n ";

	private int destinazione;
	
	public VaiA(String descrizione, int destinazione) {
		super(descrizione);
	    this.destinazione = destinazione;	
	}

	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		int passi = destinazione - giocatoreAttuale.getPosizione();
		if(passi>0)
			giocatoreAttuale.muoviGiocatore(passi);
		else{
			giocatoreAttuale.setPosizione(destinazione);
			System.out.printf(MESSAGGIO_VIA, Data.BONUS_VIA);
			giocatoreAttuale.aggiungiCapitale(Data.BONUS_VIA);
		}
		
	}

}
