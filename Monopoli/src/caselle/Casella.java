/**
 * 
 */
package caselle;

import java.io.*;

import main.Giocatore;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public abstract class Casella implements Serializable {
	
	
	        

	private String nome;
	private int numero;
	
	/**
	 * Costruttore della classe Casella
	 * 
	 * @param nome
	 *            Il nome della casella
	 * @param numero
	 *            La posizione della casella
	 */
	public Casella(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public abstract void effetto(Giocatore giocatoreAttuale);


	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}

}
