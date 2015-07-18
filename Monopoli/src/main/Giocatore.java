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

	private final static String GIOCATORE = "\n\n GIOCATORE: %s \n";
	private final static String MESSAGGIO_VIA = "\nSei passato dal via! Riceverai %.2f euro di bonus\n";
	private final static String ACQUISTATO = "\nComplimenti! Hai acquistato %s al costo di %.2f euro\n\n";
	private final static String NON_ACQUISTATO = "\nSpiacente! Non sei riuscito a comprare %s perche' non possiedi sufficiente capitale\n\n";
	private final static String TERRITORIO_NEMICO = "\nTi trovi sul territorio di %s e gli devi un totale di %.2f euro\n\n";
	private final static String CAPITALE_INSUFFICIENTE = "Non hai sufficiente denaro per pagare l'affitto a %s. Tutti i tuoi soldi rimasti saranno dati al proprietario della societa'";



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
	 * Manda il giocatore in prigione
	 */
    public void vaiInPrigione(){
    	this.setPosizione(Data.PRIGIONE);
		this.setInPrigione(true);
		this.setToken(false);
		this.setNumeroLanci(0);
    }
	
    /**
     * Controlla lo stato del giocatore dopo il lancio dei dadi
     * @param risultatoLancio Risultato del tiro dei dadi
     */
    public void controlloFineTiro(int risultatoLancio){
    	this.muoviGiocatore(risultatoLancio); //Muove il giocatore
		Casella casellaAttuale = Gioco.tabellone.getCaselle().get(this.getPosizione());
		casellaAttuale.effetto(this);
    }
    
	/**
	 * Libera il giocatore dalla prigione
	 */
	public void esceDiPrigione(){
		this.setInPrigione(false); //Esce di prigione
		this.setToken(true); //E ha a disposizione un altro lancio
	    this.setNumeroLanci(0);
	}

	
	/**
	 * Aggiunge una proprieta' al vettore di giocatore
	 * @param casella La casella da aggiungere al vettore
	 */
	private void aggiungiProprieta(Acquistabile casella) {
		proprieta.add(casella);
	}
	
	/**
	 * Gestisce l'acquisto di una casella
	 * @param casella Terreno o Societa' da acquistare
	 */
	public void acquistaProprieta(Acquistabile casella){
		
		 if(casella.isAcquistabile()){
	            if(this.puoPermetterselo(casella.getValore())){
	              System.out.printf(ACQUISTATO, casella.getNome(), casella.getValore());	 
	              this.prelevaCapitale(casella.getValore());
	              casella.setAcquistabile(false);
	              this.aggiungiProprieta(casella);
	           }
		 }
	            else
	        	 System.out.printf(NON_ACQUISTATO, casella.getNome() );
	}
	
	/**
	 * Gestisce una transazione tra il proprietario di un terreno e il giocatore che ci è capitato sopra
	 * @param proprietario Il proprietario del terreno
	 * @param prezzoDaPagare L'importo dovuto
	 */
	public void transazione(Giocatore proprietario, double prezzoDaPagare){
		
	    if(!(this.getNome().equalsIgnoreCase(proprietario.getNome()))){
	       System.out.printf(TERRITORIO_NEMICO, proprietario.getNome(), prezzoDaPagare);
	    	
		   if(this.puoPermetterselo(prezzoDaPagare)){
       	      proprietario.aggiungiCapitale(prezzoDaPagare);
       	      this.prelevaCapitale(prezzoDaPagare);
       	     }
       	   else{
       		  System.out.printf(CAPITALE_INSUFFICIENTE, proprietario.getNome());
       		  proprietario.aggiungiCapitale(this.getCapitale());
       		  this.finitoCapitale();
       	     }
	     }
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
		if(this.capitale < costoDaSostenere)
			return false;
		
		return true;
	}
	
	public void finitoCapitale(){
		this.setToken(false);
		this.setCapitale(0);
		this.sfratta();
	}
	
	/**
	 * Mette di nuovo in vendita le proprieta' di un giocatore finito in bancarotta 
	 */
	private void sfratta(){
		for(Acquistabile casella: proprieta){
			casella.setAcquistabile(true);
		}
		
		proprieta.removeAllElements();
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
	
	public Vector<Acquistabile> getProprieta(){
		return proprieta;
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
