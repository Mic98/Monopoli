/**
 * 
 */
package main;

import java.util.Vector;

/**
 * @author mrmoddom
 *
 */
public class Tabellone {

	private Vector<Casella> caselle;
	
	public Tabellone(){
		caselle = new Vector<Casella>();
	}
	
	public Casella aggiungiCasella(Casella in){
		caselle.add(in);
		return in;
	}

}
