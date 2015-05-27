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

	private final static String GIOCATORE = "%n%n GIOCATORE: %s %n";
	private final static int POSIZIONE_DEFAULT = 0;
	private final static int CAPITALE_DEFAULT = 5000;


	private String nome;
	private int posizione;
	private int capitale;
	private int numeroLanci;
	private boolean inPrigione;
	private boolean token;


	public Giocatore(String nome) {
		this.nome = nome;
		posizione = POSIZIONE_DEFAULT;
		capitale = CAPITALE_DEFAULT;
		numeroLanci = 0;
		inPrigione = false;
		token = false;
	}

	public boolean inBancaRotta(){
		if(capitale<=0)
			return true;
		
		return false;
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
	
	public int getCapitale(){
		return capitale;
	}
	
	public void setCapitale(int capitale){
		this.capitale = capitale;
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
	
	public boolean hasToken() {
		return token;
	}

	public void setToken(boolean token) {
		this.token = token;
	}

	public String toString() {
		StringBuilder visualizza = new StringBuilder();

		visualizza.append(String.format(GIOCATORE, getNome()));

		return visualizza.toString();
	}

}
