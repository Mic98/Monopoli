package carte;

import main.Data;
import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class VaiInPrigione extends Decorator {
	
	/**
	 * Costruttore della classe VaiInPrigione
	 * @param descrizione La descrizione della carta
	 */
	public VaiInPrigione(){
	}

	/**
	 * Sposta il giocatore in prigione
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
        giocatoreAttuale.vaiInPrigione();
	}
	
	

}
