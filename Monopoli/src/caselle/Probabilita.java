package caselle;

import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Probabilita extends Casella{

	/**
	 * costruttore della classe Probabilita'
	 * @param nome Il nome della casella
	 * @param numero La posizione della casella
	 */
	public Probabilita(String nome, int numero) {
		super(nome, numero);
		
	}

	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Probabilita'
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		Gioco.tabellone.getProbabilita().pescaCarta(giocatoreAttuale);
		
	}

}
