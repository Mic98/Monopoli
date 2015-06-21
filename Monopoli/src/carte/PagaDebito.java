package carte;

import main.Giocatore;

public class PagaDebito extends Carta{
	
	private double daPagare;
	
	public PagaDebito(String descrizione, double daPagare){
		super(descrizione);
	}

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
