package caselle;

import main.Dado;

public class Societa extends Acquistabile {


	private static final double MOLTIPLICATORE = 4;
	private boolean acquistabile;
	

    /** 
     * Costruttore della classe Societa'
	 * 
	 * @param nome nome della societa'
	 * @param numero posizione della societa' sul tabellone
	 * @param valore valore della societa'
     */
	public Societa(String nome, int numero, int valore) {
		super(nome, numero, valore);
		super.setTipo(SOCIETA);
		acquistabile = true;
	}

	
	public double getCosto(Dado dado){
		
		
		return MOLTIPLICATORE * dado.risultato();
	}
	
	public boolean isAcquistabile() {
		return acquistabile;
	}

	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}

	
	

}
