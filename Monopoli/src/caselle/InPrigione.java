/**
 * 
 */
package caselle;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class InPrigione extends Casella {

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

}
