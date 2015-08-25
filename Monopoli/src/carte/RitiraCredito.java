package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class RitiraCredito extends Decorator {

	private double daRitirare;
	
	/**
	 * Costruttore della classe RitiraCredito
	 * @param descrizione La descrizione della carta
	 * @param daRitirare La quantita' di denaro da ritirare
	 */
	public RitiraCredito(double daRitirare) {
		this.daRitirare = daRitirare;
			}
	
	

	/**
	 * Preleva dalla Banca la quantita' di denaro prevista dalla carta e la versa al giocatore
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {

		giocatoreAttuale.aggiungiCapitale(daRitirare);
		
	}
	
	

}
