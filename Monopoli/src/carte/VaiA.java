package carte;

import main.Data;
import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class VaiA extends Decorator{
	
	private final static String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai %.2f euro di bonus\n";

	private int destinazione;
	
	public VaiA(int destinazione) {

	    this.destinazione = destinazione;	
	}

	/**
	 * Sposta il giocatore contando dell'eventualita' del passaggio dal "VIA!"
	 */
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
