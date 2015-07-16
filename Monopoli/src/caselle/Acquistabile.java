package caselle;

import java.util.Vector;

import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public abstract class Acquistabile extends Casella {

	
	private static final double DIECI_PER_CENTO = 0.1;
	private double valore;
	private boolean acquistabile;
	
	/**
	 * Costruttore della classe Acquistabile
	 * 
	 * @param nome Nome della stazione
	 * @param numero Posizione della stazione sul tabellone
	 * @param valore Valore della stazione
	 */
	public Acquistabile(String nome, int numero, double valore) {
		super(nome, numero);
		this.valore = valore;
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
	 * @return Il prezzo da pagare se un giocatore finisce su una stazione o su un terreno avversario
	 */
	public double getCosto(){
		return valore * DIECI_PER_CENTO;
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


	@Override
	public abstract void effetto(Giocatore giocatoreAttuale);
	
	
	

}
