/**
 * 
 */
package main;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Casella {
	
	private String nome;
	private int numero;
	
/**
 * Costruttore della classe Casella
 * 	
 * @param nome Il nome della casella
 * @param numero Il numero della casella
 */
	public Casella(String nome, int numero){
		this.nome=nome;
		this.numero=numero;
	}

/**
 * 
 * @return nome della casella
 */
	public String getNome() {
		return nome;
	}

/**
 * 
 * @return il numero della casella
 */
	public int getNumero() {
		return numero;
	}

}
