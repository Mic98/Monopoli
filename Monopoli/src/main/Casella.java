/**
 * 
 */
package main;

/**
 * @author Carlo
 *
 */
public class Casella {
	
	private String nome;
	private int numero;
	
	public Casella(String nome, int numero){
		this.nome=nome;
		this.numero=numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
