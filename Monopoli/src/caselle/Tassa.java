package caselle;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Tassa extends Casella{
	
	private final static String CASELLA_TASSA = "\nSei finito sulla casella %s, devi %.2f euro alla banca \n\n";
	
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
		this.malus = malus;
	}

	public double getMalus() {
		return malus;
	}

	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Tassa
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		giocatoreAttuale.prelevaCapitale(this.getMalus());
		System.out.printf(CASELLA_TASSA, this.getNome(), this.getMalus());
		
	}
}
