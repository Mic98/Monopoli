package caselle;

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
		super.setTipo(PROBABILITA);
		
	}

}
