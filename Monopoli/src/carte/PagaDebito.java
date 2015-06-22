package carte;

import main.Giocatore;

public class PagaDebito extends Carta{
	
	private double daPagare;
	
	/**
	 * Costruttore della classe PagaDebito
	 * @param descrizione La descrizione della carta
	 * @param daPagare La quantita' di denaro da pagare
	 */
	public PagaDebito(String descrizione, double daPagare){
		super(descrizione);
	}

	/**
	 * Preleva dal giocatore la quantita' di denaro da pagare prevista dalla carta e la versa alla Banca
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
		if(giocatoreAttuale.puoPermetterselo(daPagare))
		    giocatoreAttuale.prelevaCapitale(daPagare);
		else
			giocatoreAttuale.setCapitale(0);
		
	}

}
