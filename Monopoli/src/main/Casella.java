/**
 * 
 */
package main;

import java.io.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Casella implements Serializable {
	
	public static final int NEUTRA = 0,
							TASSE = 1;

	private String nome;
	private int numero;
	private int type = 0;
	/**
	 * Costruttore della classe Casella
	 * 
	 * @param nome
	 *            Il nome della casella
	 * @param numero
	 *            Il numero della casella
	 */
	public Casella(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
