/**
 * 
 */
package caselle;

import java.util.Vector;

import main.Data;
import main.Giocatore;
import main.Gioco;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Terreno extends Acquistabile{
	
	
	
	private static final double DIECI_PER_CENTO = 0.1;
	
	
	private boolean acquistabile; 

	/**
	 * Costruttore della classe Terreno
	 * 
	 * @param nome Nome del terreno
	 * @param numero Posizione del terreno sul tabellone
	 * @param prezzo Prezzo del terreno
	 * @param colore Colore della proprieta'
	 */
	public Terreno(String nome, int numero, double valore, String colore) {
		super(nome, numero, valore, colore);
		acquistabile = true;
	}
	
	/**
	 * @return Il prezzo da pagare se un giocatore finisce su una stazione o su un terreno avversario
	 */
	@Override
	public double getCosto(){
		return getValore() * DIECI_PER_CENTO;
	}
	
	/**
	 *Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella Terreno di un'altro giocatore 
	 * e quest'ultimo possiede tutti i terreni dello stesso colore
	 */
	@Override
	public double getCostoDoppio() {
	
		return 2 * this.getCosto();
	}
	
	
	
	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Terreno
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		
        if(this.isAcquistabile())
            giocatoreAttuale.acquistaProprieta(this);
        
        else{
        	Giocatore proprietario = this.trovaProprietario(Gioco.tabellone.getElencoGiocatori());
            double prezzoDaPagare = checkCosto(giocatoreAttuale, proprietario);
             
        	giocatoreAttuale.transazione(proprietario, prezzoDaPagare);
          }
        }
	
	
	




}
