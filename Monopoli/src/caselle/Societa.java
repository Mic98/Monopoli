package caselle;

import main.Dado;
import main.Giocatore;
import main.Gioco;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Societa extends Acquistabile {

	private final static String ACQUISTATO = "\nComplimenti! Hai acquistato %s al costo di %.2f euro\n\n";
	private static final String NON_ACQUISTATO = "\nSpiacente! Non sei riuscito a comprare %s perche' non possiedi sufficiente capitale\n\n";
	private static final String TERRITORIO_NEMICO = "\nTi trovi sul territorio di %s e gli devi un totale di %.2f euro\n\n";
	private final static String CAPITALE_INSUFFICIENTE = "Non hai sufficiente capitale per pagare l'affitto a %s per questo tutti i tuoi soldi rimasti saranno dati a lui";
	

	private static final double MOLTIPLICATORE = 4;
	private static final double MOLTIPLICATORE_DOPPIO = 10;
	private boolean acquistabile;
	

    /** 
     * Costruttore della classe Societa'
	 * 
	 * @param nome Nome della societa'
	 * @param numero Posizione della societa' sul tabellone
	 * @param valore Valore della societa'
     */
	public Societa(String nome, int numero, double valore) {
		super(nome, numero, valore);
		acquistabile = true;
	}

	
	/**
	 * 
	 * @param dado I dadi lanciati dal giocatore
	 * @return Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore
	 *         e non le possiede entrambe
	 */
	public double getCosto(Dado dado){
		
		
		return MOLTIPLICATORE * dado.risultato();
	}
	
	/**
	 * 
	 * @param dado I dadi lanciati dal giocatore
	 * @return Il prezzo da pagare se un giocatore dopo il tiro dei dadi finisce su una casella societa' di un'altro giocatore 
	 *         e le possiede entrambe
	 */
	public double costoDoppio(Dado dado){
		
		
		return MOLTIPLICATORE_DOPPIO * dado.risultato();
	}
	
	public boolean isAcquistabile() {
		return acquistabile;
	}

	public void setAcquistabile(boolean acquistabile) {
		this.acquistabile = acquistabile;
	}


	/**
	 * Gestisce il passaggio di un giocatore su una casella di tipo Societa'
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
        	 System.out.printf(NON_ACQUISTATO,this.getNome() );	
		}
		else{
			double prezzoDaPagare;
			Giocatore proprietario = this.trovaProprietario(Gioco.tabellone.getElencoGiocatori());
			
			if(!giocatoreAttuale.getNome().equalsIgnoreCase(proprietario.getNome())){
			if(proprietario.possiedeTutteSocieta())
				prezzoDaPagare = this.costoDoppio(Gioco.dado);
			else
				prezzoDaPagare = this.getCosto(Gioco.dado);
			
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
