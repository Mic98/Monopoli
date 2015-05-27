/**
 * 
 */
package Caselle;

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
	private int tipo;
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
		tipo = 0;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}