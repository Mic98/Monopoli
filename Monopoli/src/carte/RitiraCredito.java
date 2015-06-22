package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class RitiraCredito extends Carta {

	private double daRitirare;
	
	/**
	 * Costruttore della classe RitiraCredito
	 * @param descrizione La descrizione della carta
	 * @param daRitirare La quantita' di denaro da ritirare
	 */
	public RitiraCredito(String descrizione, double daRitirare) {
		super(descrizione);
		this.daRitirare = daRitirare;
			}
	
	

	/**
	 * Preleva dalla Banca la quantita' di denaro prevista dalla carta e la versa al giocatore
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
		giocatoreAttuale.aggiungiCapitale(daRitirare);
		
	}
	
	

}
