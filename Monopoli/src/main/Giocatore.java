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
	private Pedina pedina;
	private boolean inPrigione;
	private boolean token;
	

	public Giocatore(String nome){
		this.nome=nome;
		this.pedina = new Pedina(0);
		token=false;
		inPrigione=false;
	}
	
	public String getNome() {
		return nome;
	}
	public void setName(String nome) {
		this.nome = nome;
	}
	public Pedina getPedina() {
		return pedina;
	}
	public void setPedina(Pedina pedina) {
		this.pedina = pedina;
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
