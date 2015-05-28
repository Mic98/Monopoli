/**
 * 
 */
package caselle;

/**
 * @author Carlo
 *
 */
public class InPrigione extends Casella {

	public InPrigione(String nome, int numero) {
		super(nome, numero);
		super.setTipo(Casella.VAI_IN_PRIGIONE);
	}

}
