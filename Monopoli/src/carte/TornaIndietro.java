package carte;

import main.Giocatore;

public class TornaIndietro extends Carta {
	
	private int passiIndietro;

	public TornaIndietro(String descrizione, int passiIndietro) {
		super(descrizione);
		this.passiIndietro = passiIndietro;
	}

	@Override
	public void effetto(Giocatore giocatoreAttuale) {
		int nuovaPosizione = giocatoreAttuale.getPosizione() - passiIndietro;
		
		if(nuovaPosizione < 0){
			nuovaPosizione = 40 + nuovaPosizione;
			
		}
			
		giocatoreAttuale.setPosizione(nuovaPosizione);
			
			
		}
			
			
		
	}
	
	


