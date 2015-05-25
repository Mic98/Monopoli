/**
 * 
 */
package main;

import java.io.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */

public class Giocatore implements Serializable {

	private static final String GIOCATORE = "%n%n GIOCATORE: %s %n";
	private static final int POSIZIONE_DEFAULT = 0;


	private String nome;
	private int posizione;
	private int numeroLanci;
	private boolean inPrigione;


	public Giocatore(String nome) {
		this.nome = nome;
		posizione = POSIZIONE_DEFAULT;
		numeroLanci = 0;
		inPrigione = false;
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

	public boolean isInPrigione() {
		return inPrigione;
	}

	public void setInPrigione(boolean inPrigione) {
		this.inPrigione = inPrigione;
	}

	public int getNumeroLanci() {
		return numeroLanci;
	}

	public void setNumeroLanci(int numeroLanci) {
		this.numeroLanci = numeroLanci;
	}

	public String toString() {
		StringBuilder visualizza = new StringBuilder();

		visualizza.append(String.format(GIOCATORE, getNome()));

		return visualizza.toString();
	}

}
