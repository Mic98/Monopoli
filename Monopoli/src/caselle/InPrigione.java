/**
 * 
 */
package caselle;

/**
 * @author Carlo
 *
 */
public class InPrigione extends Casella {

	/**
	 * Costruttore della classe InPrigione
	 * 
	 * @param nome nome della casella
	 * @param numero posizione della casella
	 */
	public InPrigione(String nome, int numero) {
		super(nome, numero);
		super.setTipo(Casella.VAI_IN_PRIGIONE);
	}

}
