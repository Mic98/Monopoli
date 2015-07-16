package caselle;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */

public class Neutra extends Casella {

	public Neutra(String nome, int numero) {
		super(nome, numero);
		
	}

	/**
	 * Nessun effetto da gestire su una casella di tipo Neutro
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		
	}


	


}
