package carte;

import main.Data;
import main.Giocatore;

public class VaiInPrigione extends Carta {
	
	public VaiInPrigione(String descrizione){
		super(descrizione);
	}

	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
        giocatoreAttuale.setInPrigione(true);	
        giocatoreAttuale.setToken(false);
        giocatoreAttuale.setPosizione(Data.PRIGIONE);
	}
	
	

}
