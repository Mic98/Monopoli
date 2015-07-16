package carte;

import main.Data;
import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
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
		
        giocatoreAttuale.vaiInPrigione();
	}
	
	

}
