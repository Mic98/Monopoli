package carte;

import main.Giocatore;

/**
 * 
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class TornaIndietro extends Carta {
	
	private int passiIndietro;

	/**
	 * Costruttore della classe TornaIndietro
	 * @param descrizione La descrizione della Carta
	 * @param passiIndietro Numero di passi da fare indietro
	 */
	public TornaIndietro(String descrizione, int passiIndietro) {
		super(descrizione);
		this.passiIndietro = passiIndietro;
	}

	/**
	 * Sposta il giocatore indietro dei numeri di passi riportati sulla carta
	 */
	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		stampaDescrizione();
		
		int nuovaPosizione = giocatoreAttuale.getPosizione() - passiIndietro;
		
		if(nuovaPosizione < 0){
			nuovaPosizione = 40 + nuovaPosizione;
			
		}
			
		giocatoreAttuale.setPosizione(nuovaPosizione);
			
			
		}
			
			
		
	}
	
	


