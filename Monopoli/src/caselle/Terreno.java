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
	 * @return true se il giocatore possiede tutti i terreni dello stesso colore
	 */
	@Override
	public boolean possiedeTutti(Giocatore proprietario, Acquistabile casella) {
		int contatore = 0;
		Vector<Acquistabile> proprieta = proprietario.getProprieta();
		
		for(int i=0; i<proprieta.size(); i++)
			if(casella.getColore().equalsIgnoreCase(proprieta.get(i).getColore()))
				contatore++;
			
		
		
	if(casella.getColore().equalsIgnoreCase(Data.NERO))
		    return false;
				
	if(casella.getColore().equalsIgnoreCase(Data.VIOLA) || this.getColore().equalsIgnoreCase(Data.ROSA)){
		if(contatore==2)
		    return true;
	}
	else
		if(contatore==3)
			return true;
	
	return false;
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
