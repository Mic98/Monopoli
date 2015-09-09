/**
 * 
 */
package carte;

import main.Giocatore;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class CartaConcreta extends Carta {

private String descrizione;
private Decorator decoratore;
	
	
	
	/**
	 * Costruttore della classe Carta
	 * @param descrizione La descrizione dell'effetto della carta
	 */
	public CartaConcreta(String descrizione, Decorator decoratore){
		this.descrizione = descrizione;
		this.decoratore = decoratore;
		
	}
	
	public void stampaDescrizione(){
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
	}

	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Viene eseguita l'istruzione riportata sulla descrizione della carta mediante il decoratore
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		stampaDescrizione();
		decoratore.effetto(giocatoreAttuale);
		
	}
	

}
