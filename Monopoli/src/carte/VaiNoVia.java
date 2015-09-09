package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class VaiNoVia extends Decorator {
	
	private int destinazione;
	
	/**
	 * Costruttore della classe VaiNoVIa
	 * @param destinazione La posizione della casella di destinazione
	 */
	public VaiNoVia(int destinazione) {
		this.destinazione = destinazione;
	}


    /**
     * Sposta il giocatore senza passare dal "Via!"
     */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		giocatoreAttuale.setPosizione(destinazione);
		
	}
	
	

}
