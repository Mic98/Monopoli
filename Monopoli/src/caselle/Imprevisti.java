package caselle;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Imprevisti extends Casella{
	
	
	/**
	 * Costruttore della classe Imprevisti
	 * @param nome Il nome della casella
	 * @param numero La posizione della casella
	 */
	public Imprevisti(String nome, int numero){
		super(nome, numero);
		super.setTipo(IMPREVISTI);
	}
	

}
