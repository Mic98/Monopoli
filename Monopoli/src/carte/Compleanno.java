package carte;

import main.Giocatore;
import main.Gioco;

public class Compleanno extends Carta {
	
	private double regalo;
	
	public Compleanno(String descrizione, double regalo){
		super(descrizione);
		this.regalo = regalo;
	}
	
	
	@Override
	public void effetto(Giocatore giocatoreAttuale){
		for(Giocatore giocatore: Gioco.tabellone.getElencoGiocatori()){
			if(!giocatore.getNome().equalsIgnoreCase(giocatoreAttuale.getNome()))
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
