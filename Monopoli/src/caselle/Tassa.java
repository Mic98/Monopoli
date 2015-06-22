package caselle;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tassa extends Casella{
	
	private double malus;
	
	/**
	 * Costruttore della classe Tassa
	 * 
	 * @param nome nome della casella
	 * @param numero posizione della casella
	 * @param malus costo della tassa
	 */
	public Tassa (String nome, int numero, double malus){
		super(nome, numero);
		super.setTipo(Casella.TASSE);
		this.malus = malus;
	}

	public double getMalus() {
		return malus;
	}
}
