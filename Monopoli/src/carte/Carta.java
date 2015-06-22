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
	
	private String descrizione;
	
	
	/**
	 * Costruttore della classe Carta
	 * @param descrizione La descrizione dell'effetto della carta
	 */
	public Carta(String descrizione){
		this.descrizione = descrizione;
	}

	/**
	 * Viene eseguita l'istruzione riportata sulla descrizione della carta
	 * @param giocatoreAttuale Il giocatore che ha pescato la carta
	 */
	public abstract void effetto(Giocatore giocatoreAttuale);


	public String getDescrizione() {
		return descrizione;
	}

}


