package carte;

import main.Data;
import main.Giocatore;

public class VaiInPrigione extends Carta {
	
	public VaiInPrigione(String descrizione){
		super(descrizione);
	}

	@Override
	public void effetto(Giocatore giocatoreAttuale) {
          giocatoreAttuale.setInPrigione(true);	
          giocatoreAttuale.setPosizione(Data.PRIGIONE);
	}
	
	

}
