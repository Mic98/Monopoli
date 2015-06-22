package carte;

import main.Data;
import main.Giocatore;

public class VaiInPrigione extends Carta {
	
	/**
	 * Costruttore della classe VaiInPrigione
	 * @param descrizione La descrizione della carta
	 */
	public VaiInPrigione(String descrizione){
		super(descrizione);
	}

	/**
	 * Sposta il giocatore in prigione
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
        giocatoreAttuale.setInPrigione(true);	
        giocatoreAttuale.setToken(false);
        giocatoreAttuale.setPosizione(Data.PRIGIONE);
	}
	
	

}
