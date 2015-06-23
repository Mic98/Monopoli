/**
 * 
 */
package main;

import java.io.*;
import java.util.Vector;

import utilities.BelleStringhe;
import caselle.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */

public class Giocatore implements Serializable {

	private final static String GIOCATORE = "%n%n GIOCATORE: %s %n";
	private final static String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai %.2f euro di bonus\n";



	private String nome;
	private int posizione;
	private double capitale;
	private int numeroLanci;
	private boolean inPrigione;
	private boolean token; 
	private Vector<Acquistabile> proprieta;
	


	/**
	 * Costruttore della classe Giocatore
	 * 
	 * @param nome nome del giocatore
	 */
	public Giocatore(String nome) {
		this.nome = nome;
		
		posizione = Data.POSIZIONE_DEFAULT;
		capitale = Data.CAPITALE_DEFAULT;
		
		numeroLanci = 0;
		inPrigione = false;
		token = false;
		
		proprieta = new Vector<Acquistabile>();
		
	}
	
	/**
	 * Sposta il giocatore lungo il tabellone, verificando l'avvenuto passaggio dal via
	 * 
	 * @param g giocatore da spostare
	 * @param step risultato dato dal tiro dei dadi
	 */
	public void muoviGiocatore(int step) {
		int dest = getPosizione() + step;

		if (dest >= 40){
			dest = dest - 40;
			System.out.printf(MESSAGGIO_VIA, Data.BONUS_VIA);
			aggiungiCapitale(Data.BONUS_VIA);
		}

		setPosizione(dest);

	}

	/**
	 * Aggiunge una proprieta' al vettore di giocatore
	 * @param casella La casella da aggiungere al vettore
	 */
	public void aggiungiProprieta(Acquistabile casella) {
		proprieta.add(casella);
	}

	/**
	 * Sottrae dal capitale del giocatore la spesa specificata
	 * @param spesa Spesa da sostenere
	 */
	public void prelevaCapitale (double spesa){
		capitale =  capitale - spesa;
	}
	
	/**
	 * Aggiunge al capitale del giocatore il guadagno specificato
	 * @param guadagno Guadagno del giocatore
	 */
	public void aggiungiCapitale(double guadagno){
		capitale = capitale + guadagno;
	}

	/**
	 * 
	 * @return true se il giocatore ha finito i soldi
	 */
	public boolean inBancaRotta(){
		if(capitale<=0)
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param altroGiocatore Giocatore da confrontare
	 * @return true se il primo giocatore e' piu' povero del secondo
	 */
	public boolean piuPovero(Giocatore altroGiocatore){
		if(this.capitale <= altroGiocatore.capitale)
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param altroGiocatore Giocatore da confrontare
	 * @return true se i due giocatori hanno lo stesso capitale
	 */
	public boolean riccoUguale(Giocatore altroGiocatore){
		if(this.capitale == altroGiocatore.capitale)
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param costoDaSostenere Spesa da sostenere
	 * @return true se il giocatore puo' sostenere la spesa
	 */
	public boolean puoPermetterselo(double costoDaSostenere){
		if(this.capitale <= costoDaSostenere)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param colore Colore della casella su cui l'avversario che deve pagare si trova
	 * @return true se il proprietario possiede tutti i terreni di un dato colore
	 */
	public boolean possiedeTuttiTerreni(String colore){
		int contatore = 0;
			for(int i=0; i<proprieta.size(); i++){
				if(proprieta.get(i) instanceof Terreno){
				Terreno terreno = (Terreno) proprieta.get(i);
				if(colore.equalsIgnoreCase(terreno.getColore()))
					contatore++;
				}
			}
			
		if(colore.equalsIgnoreCase(Data.VIOLA) || colore.equalsIgnoreCase(Data.VIOLA_SCURO)){
			if(contatore==2)
			    return true;
		}
		else
			if(contatore==3)
				return true;
		
		return false;
			
	}
	
	/**
	 * 
	 * @return true se il giocatore possiede entrambe le societa'
	 */
	public boolean possiedeTutteSocieta(){
		int contatore = 0;
		
		for(int i = 0; i<proprieta.size(); i++)
		    if(proprieta.get(i) instanceof Societa)
				   contatore++;
		   
	    if(contatore==2)
	    	return true;
	    
	    return false;
			   
	}
	
	/**
	 * Mette di nuovo in vendita le proprieta' di un giocatore finito in bancarotta 
	 */
	public void sfratta(){
		for(Acquistabile casella: proprieta){
			casella.setAcquistabile(true);
		}
	}
	
	/**
	 * @param acquistabile La casella di cui si deve trovare il proprietario
	 * @return true se il giocatore possiede il territorio
	 */
	
	public boolean possiede(Acquistabile casella) {
		for(int i = 0; i<proprieta.size(); i++)
			if(proprieta.get(i).getNome().equalsIgnoreCase(casella.getNome()))
				return true;
		
		return false;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	public double getCapitale(){
		return capitale;
	}
	
	public void setCapitale(double capitale){
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

	
	/**
	 * restituisce il nome del giocatore e l'elenco delle sue proprieta'
	 */
	public String toString() {
		
		StringBuilder visualizza = new StringBuilder();
		visualizza.append(String.format(GIOCATORE, getNome()));
		visualizza.append("\n");
		
		for(int i=0; i<proprieta.size(); i++){
		    if((i+1)%4 == 0)
			    visualizza.append("\n\n");	
			
			visualizza.append(String.format(BelleStringhe.incolonna(("\t\t" + proprieta.get(i).getNumero() + "  " + proprieta.get(i).getNome() + "\t\t"),35)));
		}
		

		return visualizza.toString();
	}


}
