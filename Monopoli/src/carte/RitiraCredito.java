package carte;

import main.Giocatore;

public class RitiraCredito extends Carta {

	private double daRitirare;
	
	public RitiraCredito(String descrizione, double daRitirare) {
		super(descrizione);
		this.daRitirare = daRitirare;
			}
	
	

	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
		giocatoreAttuale.aggiungiCapitale(daRitirare);
		
	}
	
	

}