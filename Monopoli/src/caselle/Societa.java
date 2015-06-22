package caselle;

import main.Dado;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Societa extends Acquistabile {


	private static final double MOLTIPLICATORE = 4;
	private static final double MOLTIPLICATORE_DOPPIO = 10;
	private boolean acquistabile;
	

    /** 
     * Costruttore della classe Societa'
	 * 
	 * @param nome Nome della societa'
	 * @param numero Posizione della societa' sul tabellone
	 * @param valore Valore della societa'
     */
	public Societa(String nome, int numero, int valore) {
		super(nome, numero, valore);
		super.setTipo(SOCIETA);
		acquistabile = true;
	}

	
	/**
	 * 
	 * @param dado I dadi lanciati dal giocatore
	 * @return Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore
	 *         e non le possiede entrambe
	 */
	public double getCosto(Dado dado){
		
		
		return MOLTIPLICATORE * dado.risultato();
	}
	
	/**
	 * 
	 * @param dado I dadi lanciati dal giocatore
	 * @return Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore 
	 *         e le possiede entrambe
	 */
	public double costoDoppio(Dado dado){
		
		
		return MOLTIPLICATORE_DOPPIO * dado.risultato();
	}
	
	public boolean isAcquistabile() {
		return acquistabile;
	}

	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}

	
	

}
