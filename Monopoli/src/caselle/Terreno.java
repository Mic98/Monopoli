/**
 * 
 */
package caselle;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Terreno extends Acquistabile{
	
	private String colore;
	private boolean acquistabile; 

	/**
	 * Costruttore della classe Terreno
	 * 
	 * @param nome Nome del terreno
	 * @param numero Posizione del terreno sul tabellone
	 * @param prezzo Prezzo del terreno
	 * @param colore Colore della proprieta'
	 */
	public Terreno(String nome, int numero, double valore, String colore) {
		super(nome, numero, valore);
		super.setTipo(ACQUISTABILE);
		this.colore = colore;
		acquistabile = true;
	}
	
	
	
	
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public boolean isAcquistabile() {
		return acquistabile;
	}

	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}

}
