package carte;

import java.io.Serializable;
import java.util.Vector;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Mazzo implements Serializable {
	
	Vector <Carta> carte;
	
	/**
	 * Costruttore della classe Mazzo
	 */
	public Mazzo(){
		carte = new Vector<Carta>();
	}

	public void aggiungiCarta(Carta carta) {
		carte.add(carta);
		
	}
	
	/**
	 * Questo metodo si occupa di estrarre la prima carta dal mazzo e di reinserirla nel fondo del mazzo
	 * @param giocatoreAttuale Il giocatore che ha pescato la carta
	 */
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
