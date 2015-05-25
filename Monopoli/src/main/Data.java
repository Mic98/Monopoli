/**
 * 
 */
package main;

import java.util.Vector;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Data {

	private static final int PRIGIONE = 10;
	private static final int MALUS_PATRIMONIALE = 250;
	private static final int MALUS_LUSSO = 10;
	

	private static final String CASELLA_0 = "VIA";
	private static final String CASELLA_1 = "VICOLO CORTO";
	private static final String CASELLA_2 = "PROBABILITA";
	private static final String CASELLA_3 = "VICOLO STRETTO";
	private static final String CASELLA_4 = "TASSA PATRIMONILAE";
	private static final String CASELLA_5 = "STAZIONE SUD";
	private static final String CASELLA_6 = "BASTIONI GRAN SASSO";
	private static final String CASELLA_7 = "IMPREVISTI";
	private static final String CASELLA_8 = "VIALE MONTEROSA";
	private static final String CASELLA_9 = "VIALE VESUVIO";
	private static final String CASELLA_10 = "PRIGIONE/TRANSITO";
	private static final String CASELLA_11 = "VIA ACCADEMIA";
	private static final String CASELLA_12 = "SOCIETA ELETTRICA";
	private static final String CASELLA_13 = "CORSO ATENEO";
	private static final String CASELLA_14 = "PIAZZA UNIVERSITA";
	private static final String CASELLA_15 = "STAVIONE OVEST";
	private static final String CASELLA_16 = "VIALE VERDI";
	private static final String CASELLA_17 = "PROBABILITA";
	private static final String CASELLA_18 = "CORSO RAFFAELLO";
	private static final String CASELLA_19 = "PIAZZA DANTE";
	private static final String CASELLA_20 = "PARCHEGGIO GRATUITO";
	private static final String CASELLA_21 = "VIA MARCO POLO";
	private static final String CASELLA_22 = "IMPREVISTI";
	private static final String CASELLA_23 = "CORSO MAGELLANO";
	private static final String CASELLA_24 = "LARGO COLOMBO";
	private static final String CASELLA_25 = "STAZIONE NORD";
	private static final String CASELLA_26 = "VIALE COSTANTINO";
	private static final String CASELLA_27 = "VIALE TRAIANO";
	private static final String CASELLA_28 = "SOCIETA ACQUA POTABILE";
	private static final String CASELLA_29 = "PIAZZA GIULIO CESARE";
	private static final String CASELLA_30 = "IN PRIGIONE!";
	private static final String CASELLA_31 = "VIA ROMA";
	private static final String CASELLA_32 = "CORSO IMPERO";
	private static final String CASELLA_33 = "PROBABILITA";
	private static final String CASELLA_34 = "LARGO AUGUSTO";
	private static final String CASELLA_35 = "STAZIONE EST";
	private static final String CASELLA_36 = "IMPREVISTI";
	private static final String CASELLA_37 = "VIALE DEI GIARDINI";
	private static final String CASELLA_38 = "TASSA DEL LUSSO";
	private static final String CASELLA_39 = "PARCO DELLA VITTORIA";

	private static final String[] NOMI_CASELLE = { CASELLA_0, CASELLA_1,
			CASELLA_2, CASELLA_3, CASELLA_4, CASELLA_5, CASELLA_6, CASELLA_7,
			CASELLA_8, CASELLA_9, CASELLA_10, CASELLA_11, CASELLA_12,
			CASELLA_13, CASELLA_14, CASELLA_15, CASELLA_16, CASELLA_17,
			CASELLA_18, CASELLA_19, CASELLA_20, CASELLA_21, CASELLA_22,
			CASELLA_23, CASELLA_24, CASELLA_25, CASELLA_26, CASELLA_27,
			CASELLA_28, CASELLA_29, CASELLA_30, CASELLA_31, CASELLA_32,
			CASELLA_33, CASELLA_34, CASELLA_35, CASELLA_36, CASELLA_37,
			CASELLA_38, CASELLA_39 };
	

	

	public static String[] getNomiCaselle() {
		return NOMI_CASELLE;
	}

	public static int getPrigione() {
		return PRIGIONE;
	}
	
	public Tabellone creaTabellone(){
		Tabellone temporaneo = new Tabellone();
		
		temporaneo.aggiungiCasella(new Casella(CASELLA_0, 0));
		temporaneo.aggiungiCasella(new Casella(CASELLA_1, 1));
		temporaneo.aggiungiCasella(new Casella(CASELLA_2, 2));
		temporaneo.aggiungiCasella(new Casella(CASELLA_3, 3));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_4, 4, MALUS_PATRIMONIALE));
		temporaneo.aggiungiCasella(new Casella(CASELLA_5, 5));
		temporaneo.aggiungiCasella(new Casella(CASELLA_6, 6));
		temporaneo.aggiungiCasella(new Casella(CASELLA_7, 7));
		temporaneo.aggiungiCasella(new Casella(CASELLA_8, 8));
		temporaneo.aggiungiCasella(new Casella(CASELLA_9, 9));
		temporaneo.aggiungiCasella(new Casella(CASELLA_10, 10));
		temporaneo.aggiungiCasella(new Casella(CASELLA_11, 11));
		temporaneo.aggiungiCasella(new Casella(CASELLA_12, 12));
		temporaneo.aggiungiCasella(new Casella(CASELLA_13, 13));
		temporaneo.aggiungiCasella(new Casella(CASELLA_14, 14));
		temporaneo.aggiungiCasella(new Casella(CASELLA_15, 15));
		temporaneo.aggiungiCasella(new Casella(CASELLA_16, 16));
		temporaneo.aggiungiCasella(new Casella(CASELLA_17, 17));
		temporaneo.aggiungiCasella(new Casella(CASELLA_18, 18));
		temporaneo.aggiungiCasella(new Casella(CASELLA_19, 19));
		temporaneo.aggiungiCasella(new Casella(CASELLA_20, 20));
		temporaneo.aggiungiCasella(new Casella(CASELLA_21, 21));
		temporaneo.aggiungiCasella(new Casella(CASELLA_22, 22));
		temporaneo.aggiungiCasella(new Casella(CASELLA_23, 23));
		temporaneo.aggiungiCasella(new Casella(CASELLA_24, 24));
		temporaneo.aggiungiCasella(new Casella(CASELLA_25, 25));
		temporaneo.aggiungiCasella(new Casella(CASELLA_26, 26));
		temporaneo.aggiungiCasella(new Casella(CASELLA_27, 27));
		temporaneo.aggiungiCasella(new Casella(CASELLA_28, 28));
		temporaneo.aggiungiCasella(new Casella(CASELLA_29, 29));
		temporaneo.aggiungiCasella(new Casella(CASELLA_30, 30));
		temporaneo.aggiungiCasella(new Casella(CASELLA_31, 31));
		temporaneo.aggiungiCasella(new Casella(CASELLA_32, 32));
		temporaneo.aggiungiCasella(new Casella(CASELLA_33, 33));
		temporaneo.aggiungiCasella(new Casella(CASELLA_34, 34));
		temporaneo.aggiungiCasella(new Casella(CASELLA_35, 35));
		temporaneo.aggiungiCasella(new Casella(CASELLA_36, 36));
		temporaneo.aggiungiCasella(new Casella(CASELLA_37, 37));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_38, 38, MALUS_LUSSO));
		temporaneo.aggiungiCasella(new Casella(CASELLA_39, 39));
		
		
		return temporaneo;
	}

}
