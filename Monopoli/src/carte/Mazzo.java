package carte;

import java.util.Vector;

public class Mazzo {
	
	Vector <Carta> carte;
	
	public Mazzo(){
		carte = new Vector<Carta>();
	}

	public void aggiungiCarta(Carta carta) {
		carte.add(carta);
		
	}
	
	public  Vector<Carta> getCarte(){
		return carte;
	}

}
