/**
 * 
 */
package caselle;

/**
 * @author Carlo
 *
 */
public class Terreno extends Casella{
	
	private int prezzo;
	private String colore;

	public Terreno(String nome, int numero, int prezzo, String colore) {
		super(nome, numero);
		super.setTipo(TERRENI);
		this.prezzo = prezzo;
		this.colore = colore;
	}

	
	
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
	

}
