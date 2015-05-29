/**
 * 
 */
package caselle;

/**
 * @author Carlo
 *
 */
public class Terreno extends Acquistabile{
	
	private String colore;
	private boolean acquistabile; 

	/**
	 * Costruttore della classe Terreno
	 * 
	 * @param nome nome del terreno
	 * @param numero posizione del terreno sul tabellone
	 * @param prezzo prezzo del terreno
	 * @param colore colore della proprieta'
	 */
	public Terreno(String nome, int numero, int valore, String colore) {
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
