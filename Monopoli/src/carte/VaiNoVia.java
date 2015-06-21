package carte;

import main.Giocatore;

public class VaiNoVia extends Carta {
	
	private int destinazione;
	
	public VaiNoVia(String descrizione, int destinazione) {
		super(descrizione);
		this.destinazione = destinazione;
	}



	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		giocatoreAttuale.setPosizione(destinazione);
		
	}
	
	

}
