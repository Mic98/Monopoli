package caselle;

public class Tassa extends Casella{
	
	private int malus;
	
	/**
	 * Costruttore della classe Tassa
	 * 
	 * @param nome nome della casella
	 * @param numero posizione della casella
	 * @param malus costo della tassa
	 */
	public Tassa (String nome, int numero, int malus){
		super(nome, numero);
		super.setTipo(Casella.TASSE);
		this.malus = malus;
	}

	public int getMalus() {
		return malus;
	}
}
