package carte;

import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Compleanno extends Carta {
	
	private double regalo;
	
	/**
	 * 
	 * @param descrizione
	 * @param regalo
	 */
	public Compleanno(String descrizione, double regalo){
		super(descrizione);
		this.regalo = regalo;
	}
	

	/**
	 * Preleva da tutti i giocatori in gioco, tranne quello che ha pescato la carta,
	 * una quantita' di denaro pari a quella dell'attributo "regalo" 
	 * e versa l'equivalente al giocatore che ha estratto la carta
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale){
		System.out.println();
		System.out.println(getDescrizione());
		System.out.println();
		
		for(Giocatore giocatore: Gioco.tabellone.getElencoGiocatori()){
			if(!giocatore.getNome().equalsIgnoreCase(giocatoreAttuale.getNome())){
			  if(giocatore.puoPermetterselo(regalo)){
				giocatore.prelevaCapitale(regalo);
				giocatoreAttuale.aggiungiCapitale(regalo);
			  }
			else{
				giocatoreAttuale.aggiungiCapitale(giocatore.getCapitale());
				giocatore.setCapitale(0);
			}
		  }	
		}
	  }
	

}
