/**
 * 
 */
package caselle;

import main.Data;
import main.Giocatore;
import main.Gioco;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Terreno extends Acquistabile{
	
	
	private final static String ACQUISTATO = "\nComplimenti! Hai acquistato %s al costo di %.2f euro\n\n";
	private static final String NON_ACQUISTATO = "\nSpiacente! Non sei riuscito a comprare %s perche' non possiedi sufficiente capitale\n\n";
	private static final String TERRITORIO_NEMICO = "\nTi trovi sul territorio di %s e gli devi un totale di %.2f euro\n\n";
	private final static String CAPITALE_INSUFFICIENTE = "Non hai sufficiente capitale per pagare l'affitto a %s per questo tutti i tuoi soldi rimasti saranno dati a lui";
	
	private String colore;
	private boolean acquistabile; 

	/**
	 * Costruttore della classe Terreno
	 * 
	 * @param nome Nome del terreno
	 * @param numero Posizione del terreno sul tabellone
	 * @param prezzo Prezzo del terreno
	 * @param colore Colore della proprieta'
	 */
	public Terreno(String nome, int numero, double valore, String colore) {
		super(nome, numero, valore);
		super.setTipo(ACQUISTABILE);
		this.colore = colore;
		acquistabile = true;
	}
	
	
	
	
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public boolean isAcquistabile() {
		return acquistabile;
	}

	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}




	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Terreno
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		
        if(this.isAcquistabile()){
            if(giocatoreAttuale.puoPermetterselo(this.getValore())){
              System.out.printf(ACQUISTATO, this.getNome(), this.getValore());	 
              giocatoreAttuale.prelevaCapitale(this.getValore());
	          this.setAcquistabile(false);
              giocatoreAttuale.aggiungiProprieta(this);
           }
            else
        	 System.out.printf(NON_ACQUISTATO, this.getNome() );
        }
        else{
        	
        	 double prezzoDaPagare;
        	 Giocatore proprietario = this.trovaProprietario(Gioco.tabellone.getElencoGiocatori());
        	 
        if(!giocatoreAttuale.getNome().equalsIgnoreCase(proprietario.getNome())){       
        		 prezzoDaPagare = this.getCosto();
        		 
        		 if(proprietario.possiedeTuttiTerreni(this.getColore()))
            		 prezzoDaPagare = 2* this.getCosto();
        	 
        	 System.out.printf(TERRITORIO_NEMICO, proprietario.getNome(), prezzoDaPagare);
        	 
        	 if(giocatoreAttuale.puoPermetterselo(prezzoDaPagare)){
        	    proprietario.aggiungiCapitale(prezzoDaPagare);
        	    giocatoreAttuale.prelevaCapitale(prezzoDaPagare);
        	 }
        	 else{
        		 System.out.printf(CAPITALE_INSUFFICIENTE, proprietario.getNome());
        		 proprietario.aggiungiCapitale(giocatoreAttuale.getCapitale());
        		 giocatoreAttuale.setCapitale(0);
        	 } 
          }
        }
	}

}
