/**
 * 
 */
package carte;

import java.io.Serializable;

import main.Giocatore;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public abstract class Carta implements Serializable {
	
	

	/**
	 * Viene eseguita l'istruzione riportata sulla descrizione della carta
	 * @param giocatoreAttuale Il giocatore che ha pescato la carta
	 */
	public abstract void effetto(Giocatore giocatoreAttuale);

	

}


