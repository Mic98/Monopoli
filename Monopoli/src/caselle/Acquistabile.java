package caselle;

import java.util.Vector;

import main.Giocatore;

public class Acquistabile extends Casella {

	
	private static final double DIECI_PER_CENTO = 0.1;
	private double valore;
	private boolean acquistabile;
	
	/**
	 * Costruttore della classe Acquistabile
	 * 
	 * @param nome nome della stazione
	 * @param numero posizione della stazione sul tabellone
	 * @param valore valore della stazione
	 */
	public Acquistabile(String nome, int numero, int valore) {
		super(nome, numero);
		super.setTipo(ACQUISTABILE);
		this.valore = valore;
		acquistabile = true;
	
	}


   /**
    * 
    * @param giocatori il vettore dei giocatori in partita
    * @return il proprietario della casella
    */
   public Giocatore trovaProprietario(Vector <Giocatore> giocatori){
	   Giocatore proprietario = null;
	   
	   for(int i = 0; i< giocatori.size(); i++){
		   if(giocatori.get(i).possiede(this))
			   proprietario = giocatori.get(i);
	   }
	   
	   
	   
	   return proprietario;
   }
   
   //public double

	public double getValore() {
		return valore;
	}

	public void setPrezzo(double valore) {
		this.valore = valore;
	}
	
	/**
	 * 
	 * @return il prezzo da pagare se un giocatore finisce su una stazione o su un terreno avversario
	 */
	public double getCosto(){
		return valore * DIECI_PER_CENTO;
	}

	public boolean isAcquistabile() {
		return acquistabile;
	}
	
	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}
	
	
	

}
