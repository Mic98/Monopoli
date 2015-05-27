package main;

import utilities.MyRandom;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Dado {

	/**
	 * Valore minimo del dado
	 */
	public static final int DADO_MIN = 1;
	/**
	 * Valore massimo del dado
	 */
	public static final int DADO_MAX = 6;

	private int lancio1;
	private int lancio2;
	
	/**
	 * Costruttore della classe dado
	 * 
	 * @param lancio1
	 *            risultato del tiro del primo dado
	 * @param lancio2
	 *            risultato del tiro del secondo dado
	 */
	public Dado() {
		this.lancio1 = DADO_MIN;
		this.lancio2 = DADO_MIN;
	}
	
	/**
	 * metodo per lanciare i dadi
	 */
	public void lancioDadi() {

		setLancio1(MyRandom.estraiIntero(Dado.getDadoMIN(),
				Dado.getDadoMAX()));
		setLancio2(MyRandom.estraiIntero(Dado.getDadoMIN(),
				Dado.getDadoMAX()));
	}

	/**
	 * 
	 * @return il risultato del lancio dei due dadi
	 */
	public int risultato() {
		return lancio1 + lancio2;
	}

	/**
	 * 
	 * @return true se i lanci hanno risultato uguale
	 */
	public boolean sonoUguali() {
		return lancio1 == lancio2;
	}

	/**
	 * 
	 * @return il risultato del primo lancio
	 */
	public int getLancio1() {
		return lancio1;
	}

	/**
	 * 
	 * @return il risultato del secondo lancio
	 */
	public int getLancio2() {
		return lancio2;
	}

	public void setLancio1(int lancio1) {
		this.lancio1 = lancio1;
	}

	public void setLancio2(int lancio2) {
		this.lancio2 = lancio2;
	}

	public static int getDadoMIN() {
		return DADO_MIN;
	}

	public static int getDadoMAX() {
		return DADO_MAX;
	}

}
