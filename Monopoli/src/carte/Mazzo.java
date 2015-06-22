package carte;

import java.io.Serializable;
import java.util.Vector;

import main.Giocatore;

public class Mazzo implements Serializable {
	
	Vector <Carta> carte;
	
	public Mazzo(){
		carte = new Vector<Carta>();
	}

	public void aggiungiCarta(Carta carta) {
		carte.add(carta);
		
	}
	
	public void pescaCarta(Giocatore giocatoreAttuale){
		Carta pescata = carte.get(0);
		pescata.effetto(giocatoreAttuale);
		carte.remove(0);
		carte.add(pescata);
	}
	
	public  Vector<Carta> getCarte(){
		return carte;
	}

}
