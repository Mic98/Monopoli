package caselle;

public class Stazione extends Casella {

	
	private int valore;
	
	public Stazione(String nome, int numero, int valore) {
		super(nome, numero);
		this.valore = valore;
	}

	public int getValore() {
		return valore;
	}

	public void setPrezzo(int valore) {
		this.valore = valore;
	}
	
	

}
