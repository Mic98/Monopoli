package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class PagaDebito extends Decorator{
	
	private double daPagare;
	
	/**
	 * Costruttore della classe PagaDebito
	 * @param descrizione La descrizione della carta
	 * @param daPagare La quantita' di denaro da pagare
	 */
	public PagaDebito(double daPagare){
		
	}

	/**
	 * Preleva dal giocatore la quantita' di denaro da pagare prevista dalla carta e la versa alla Banca
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {		
		if(giocatoreAttuale.puoPermetterselo(daPagare))
		    giocatoreAttuale.prelevaCapitale(daPagare);
		else
			giocatoreAttuale.finitoCapitale();
		
	}

}
