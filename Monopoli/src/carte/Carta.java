/**
 * 
 */
package carte;

import main.Giocatore;

/**
 * @author Daniele BarattieriCarlo Giannini Alessandro Grazioli
 *
 */
public abstract class Carta {
	
	private String descrizione;
	
	
	public Carta(String descrizione){
		this.descrizione = descrizione;
	}

	
	public abstract void effetto(Giocatore giocatoreAttuale);


	public String getDescrizione() {
		return descrizione;
	}

}


