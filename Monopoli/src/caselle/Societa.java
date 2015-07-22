package caselle;

import java.util.Vector;

import main.Dado;
import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Societa extends Acquistabile {


	private static final double MOLTIPLICATORE = 4;
	private static final double MOLTIPLICATORE_DOPPIO = 10;
	private boolean acquistabile;
	

    /** 
     * Costruttore della classe Societa'
	 * 
	 * @param nome Nome della societa'
	 * @param numero Posizione della societa' sul tabellone
	 * @param valore Valore della societa'
     */
	public Societa(String nome, int numero, double valore, String colore) {
		super(nome, numero, valore, colore);
		acquistabile = true;
	}

	
	/**
	 * 
	 * @param dado I dadi lanciati dal giocatore
	 * @return Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore
	 *         e non le possiede entrambe
	 */
	@Override
	public double getCosto(){
		
		
		return MOLTIPLICATORE * Gioco.dado.risultato();
	}
	
	
	/**
	 * Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore 
	 * e quest'ultimo le possiede entrambe
	 */
	@Override
	public double getCostoDoppio() {
		
		return MOLTIPLICATORE_DOPPIO * Gioco.dado.risultato();
	}
	
	


	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Societa'
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
