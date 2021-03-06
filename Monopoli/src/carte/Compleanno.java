package carte;

import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Compleanno extends Decorator {
	
	private double regalo;
	
	/**
	 * 
	 * @param regalo La quantita' di denaro che ogni giocatore deve a chi ha pescato la carta
	 */
	public Compleanno(double regalo){
		this.regalo = regalo;
	}
	

	/**
	 * Preleva da tutti i giocatori in gioco, tranne quello che ha pescato la carta,
	 * una quantita' di denaro pari a quella dell'attributo "regalo" 
	 * e versa l'equivalente al giocatore che ha estratto la carta
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale){
		
		for(Giocatore giocatore: Gioco.tabellone.getElencoGiocatori()){
			if(!giocatore.getNome().equalsIgnoreCase(giocatoreAttuale.getNome())){
			  if(giocatore.puoPermetterselo(regalo)){
				giocatore.prelevaCapitale(regalo);
				giocatoreAttuale.aggiungiCapitale(regalo);
			  }
			else{
				giocatoreAttuale.aggiungiCapitale(giocatore.getCapitale());
				giocatore.finitoCapitale();
			}
		  }	
		}
	  }
	

}
