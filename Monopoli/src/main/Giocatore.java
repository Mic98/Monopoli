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
	
	public Giocatore(String nome){
		this.nome=nome;
		this.pedina = new Pedina(0);
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

}
