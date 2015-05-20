/**
 * 
 */
package main;

/**
 * @author mrmoddom
 *
 */

public class Giocatore {
	
	private String nome;
	private int posizione;
	private boolean inPrigione;
	private boolean token;
	

	public Giocatore(String nome){
		this.nome=nome;
		this.posizione = 0;
		token=false;
		inPrigione=false;
	}
	
	public String getNome() {
		return nome;
	}
	public void setName(String nome) {
		this.nome = nome;
	}
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	public boolean hasToken() {
		return token;
	}

	public void setToken(boolean token) {
		this.token = token;
	}

	public boolean isInPrigione() {
		return inPrigione;
	}

	public void setInPrigione(boolean inPrigione) {
		this.inPrigione = inPrigione;
	}
	
	

}
