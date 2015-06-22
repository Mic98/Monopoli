/**
 * 
 */
package carte;

import java.io.Serializable;

import main.Giocatore;

/**
 * @author Daniele BarattieriCarlo Giannini Alessandro Grazioli
 *
 */
public abstract class Carta implements Serializable {
	
	private String descrizione;
	
	
	public Carta(String descrizione){
		this.descrizione = descrizione;
	}

	
	public abstract void effetto(Giocatore giocatoreAttuale);


	public String getDescrizione() {
		return descrizione;
	}

}


