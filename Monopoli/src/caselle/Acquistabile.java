package caselle;

import java.util.Vector;

import main.Data;
import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public abstract class Acquistabile extends Casella {

	
	private double valore;
	private boolean acquistabile;
	private String colore;
	
	/**
	 * Costruttore della classe Acquistabile
	 * 
	 * @param nome Nome della stazione
	 * @param numero Posizione della stazione sul tabellone
	 * @param valore Valore della stazione
	 */
	public Acquistabile(String nome, int numero, double valore, String colore) {
		super(nome, numero);
		this.valore = valore;
		this.colore = colore;
		acquistabile = true;
	
	}


   /**
    * 
    * @param giocatori Il vettore dei giocatori in partita
    * @return Il proprietario della casella
    */
   public Giocatore trovaProprietario(Vector <Giocatore> giocatori){
	   Giocatore proprietario = null;
	   
	   for(int i = 0; i< giocatori.size(); i++){
		   if(giocatori.get(i).possiede(this))
			   proprietario = giocatori.get(i);
	   }
	   
	   return proprietario;
   }
   
   /**
    * 
    * @param giocatoreAttuale Il giocatore che deve pagare
    * @param proprietario Il proprietario del terreno 
    * @return L'importo dovuto
    */
   public double checkCosto(Giocatore giocatoreAttuale, Giocatore proprietario){
	   double prezzoDaPagare = 0;
	        	 
  		 if(possiedeTutti(proprietario,this))
      		 prezzoDaPagare = this.getCostoDoppio();
  		 else
  			 prezzoDaPagare = this.getCosto();
   
	   
	   return prezzoDaPagare;
   }
   

	@Override
	public abstract void effetto(Giocatore giocatoreAttuale);
	
	public abstract double getCosto();
	
	public abstract double getCostoDoppio();
	

	/**
	 * @return true se il giocatore possiede tutti i terreni dello stesso colore o tutte e due le societa'
	 */
	public boolean possiedeTutti(Giocatore proprietario, Acquistabile casella) {
		int contatore = 0;
		Vector<Acquistabile> proprieta = proprietario.getProprieta();
		
		for(int i=0; i<proprieta.size(); i++)
			if(casella.getColore().equalsIgnoreCase(proprieta.get(i).getColore()))
				contatore++;
			
		System.out.println(contatore);
		
	if(casella.getColore().equalsIgnoreCase(Data.NERO))
		    return false;
				
	if(casella.getColore().equalsIgnoreCase(Data.VIOLA) || this.getColore().equalsIgnoreCase(Data.ROSA) || this.getColore().equalsIgnoreCase(Data.BIANCO)){
		if(contatore==2)
		    return true;
	}
	else
		if(contatore==3)
			return true;
	
	return false;
	}
	
	public double getValore() {
		return valore;
	}

	public void setPrezzo(double valore) {
		this.valore = valore;
	}

	public boolean isAcquistabile() {
		return acquistabile;
	}
	
	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}
	
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}


	
	
	

}
