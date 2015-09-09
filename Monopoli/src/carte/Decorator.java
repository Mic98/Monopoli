/**
 * 
 */
package carte;

import main.Giocatore;

/**
 * @author Daniele Baratieri Carlo Giannini Alessandro Grazioli
 *
 */
public abstract class Decorator extends Carta{
	
	public abstract void effetto(Giocatore giocatoreAttuale);

}
