/**
 * 
 */
package caselle;

import main.Data;
import main.Giocatore;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class InPrigione extends Casella {
	
	private final static String MESSAGGIO_IN_PRIGIONE = "\nSei finito nella casella \"IN PRIGIONE!\" per questo motivo verrai spostato in prigione";

	/**
	 * Costruttore della classe InPrigione
	 * 
	 * @param nome Nome della casella
	 * @param numero Posizione della casella sul tabellone
	 */
	public InPrigione(String nome, int numero) {
		super(nome, numero);
		super.setTipo(Casella.VAI_IN_PRIGIONE);
	}

	/**
	 * Gestisce l'arrivo del giocatore su una casella di tipo InPrigione
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		giocatoreAttuale.setPosizione(Data.PRIGIONE);
		System.out.println(MESSAGGIO_IN_PRIGIONE);
		giocatoreAttuale.setInPrigione(true);
		
	}

}
