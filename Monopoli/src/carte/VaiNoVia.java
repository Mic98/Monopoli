package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class VaiNoVia extends Carta {
	
	private int destinazione;
	
	/**
	 * Costruttore della classe VaiNoVIa
	 * @param descrizione La descrizione della carta
	 * @param destinazione La posizione della casella di destinazione
	 */
	public VaiNoVia(String descrizione, int destinazione) {
		super(descrizione);
		this.destinazione = destinazione;
	}


    /**
     * Sposta il giocatore senza passare dal "Via!"
     */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		stampaDescrizione();
		
		giocatoreAttuale.setPosizione(destinazione);
		
	}
	
	

}
