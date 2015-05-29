/**
 * 
 */
package main;

import java.util.Vector;

import caselle.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Data {

	private final static int PRIGIONE = 10;
	private final static int MALUS_PATRIMONIALE = 250;
	private final static int MALUS_LUSSO = 10;
	
	public final static String VIOLA = "viola";
	public final static String AZZURRO = "azzurro";
	public final static String ARANCIONE = "arancione";
	public final static String MARRONE = "marrone";
	public final static String ROSSO = "rosso";
	public final static String GIALLO = "giallo";
	public final static String VERDE = "verde";
	
	
	private final static int prezzoVicoloCorto = 60;
	private final static int prezzoVicoloStretto = 60;
	private final static int prezzoBastioniGranSasso = 100;
	private final static int prezzoVialeMonterosa = 100;
	private final static int prezzoVialeVesuvio = 120;
	private final static int prezzoViaAccademia = 140;
	private final static int prezzoCorsoAteneo = 140;
	private final static int prezzoPiazzaUniversita = 160;
	private final static int prezzoViaVerdi = 180;
	private final static int prezzoCorsoRaffaello = 180;
	private final static int prezzoPiazzaDante = 200;
	private final static int prezzoViaMarcoPolo = 220;
	private final static int prezzoCorsoMagellano = 220;
	private final static int prezzoLargoColombo = 240;
	private final static int prezzoVialeCostantino = 260;
	private final static int prezzoVialeTraiano = 260;
	private final static int prezzoPiazzaGiulioCesare = 280;
	private final static int prezzoViaRoma = 300;
	private final static int prezzoCorsoImpero = 300;
	private final static int prezzoLargoAugusto = 320;
	private final static int prezzoVialeDeiGiardini = 350;
	private final static int prezzoParcoDellaVittoria = 400;
	
	private final static int valoreSocietaElettrica = 150;
	private final static int valoreSocietaAcquaPotabile = 150;
	
	private final static int valoreStazioneNord = 20;
	private final static int valoreStazioneSud = 20;
	private final static int valoreStazioneEst = 20;
	private final static int valoreStazioneOvest = 20;

	private final static String CASELLA_0 = "VIA";
	private final static String CASELLA_1 = "VICOLO CORTO";
	private final static String CASELLA_2 = "PROBABILITA'";
	private final static String CASELLA_3 = "VICOLO STRETTO";
	private final static String CASELLA_4 = "TASSA PATRIMONIALE";
	private final static String CASELLA_5 = "STAZIONE SUD";
	private final static String CASELLA_6 = "BASTIONI GRAN SASSO";
	private final static String CASELLA_7 = "IMPREVISTI";
	private final static String CASELLA_8 = "VIALE MONTEROSA";
	private final static String CASELLA_9 = "VIALE VESUVIO";
	private final static String CASELLA_10 = "PRIGIONE/TRANSITO";
	private final static String CASELLA_11 = "VIA ACCADEMIA";
	private final static String CASELLA_12 = "SOCIETA' ELETTRICA";
	private final static String CASELLA_13 = "CORSO ATENEO";
	private final static String CASELLA_14 = "PIAZZA UNIVERSITA";
	private final static String CASELLA_15 = "STAVIONE OVEST";
	private final static String CASELLA_16 = "VIA VERDI";
	private final static String CASELLA_17 = "PROBABILITA";
	private final static String CASELLA_18 = "CORSO RAFFAELLO";
	private final static String CASELLA_19 = "PIAZZA DANTE";
	private final static String CASELLA_20 = "PARCHEGGIO GRATUITO";
	private final static String CASELLA_21 = "VIA MARCO POLO";
	private final static String CASELLA_22 = "IMPREVISTI";
	private final static String CASELLA_23 = "CORSO MAGELLANO";
	private final static String CASELLA_24 = "LARGO COLOMBO";
	private final static String CASELLA_25 = "STAZIONE NORD";
	private final static String CASELLA_26 = "VIALE COSTANTINO";
	private final static String CASELLA_27 = "VIALE TRAIANO";
	private final static String CASELLA_28 = "SOCIETA' ACQUA POTABILE";
	private final static String CASELLA_29 = "PIAZZA GIULIO CESARE";
	private final static String CASELLA_30 = "IN PRIGIONE!";
	private final static String CASELLA_31 = "VIA ROMA";
	private final static String CASELLA_32 = "CORSO IMPERO";
	private final static String CASELLA_33 = "PROBABILITA'";
	private final static String CASELLA_34 = "LARGO AUGUSTO";
	private final static String CASELLA_35 = "STAZIONE EST";
	private final static String CASELLA_36 = "IMPREVISTI";
	private final static String CASELLA_37 = "VIALE DEI GIARDINI";
	private final static String CASELLA_38 = "TASSA DEL LUSSO";
	private final static String CASELLA_39 = "PARCO DELLA VITTORIA";

	private final static String[] NOMI_CASELLE = { CASELLA_0, CASELLA_1,
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
	
	
	/**
	 * inizializza il tabellone inserendo tutte le caselle in ordine
	 * @return tabellone completo
	 */
	public static Tabellone creaTabellone(){
		
		Tabellone temporaneo = new Tabellone();
		
		temporaneo.aggiungiCasella(new Casella(CASELLA_0, 0));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_1, 1, prezzoVicoloCorto, VIOLA));
		temporaneo.aggiungiCasella(new Casella(CASELLA_2, 2));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_3, 3, prezzoVicoloStretto, VIOLA));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_4, 4, MALUS_PATRIMONIALE));
		temporaneo.aggiungiCasella(new Acquistabile(CASELLA_5, 5, valoreStazioneSud));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_6, 6, prezzoBastioniGranSasso, AZZURRO));
		temporaneo.aggiungiCasella(new Casella(CASELLA_7, 7));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_8, 8, prezzoVialeMonterosa, AZZURRO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_9, 9, prezzoVialeVesuvio, AZZURRO));
		
		temporaneo.aggiungiCasella(new Casella(CASELLA_10, 10));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_11, 11, prezzoViaAccademia, ARANCIONE));
		temporaneo.aggiungiCasella(new Societa(CASELLA_12, 12, valoreSocietaElettrica));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_13, 13, prezzoCorsoAteneo, ARANCIONE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_14, 14, prezzoPiazzaUniversita, ARANCIONE));
		temporaneo.aggiungiCasella(new Acquistabile(CASELLA_15, 15, valoreStazioneOvest));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_16, 16, prezzoViaVerdi , MARRONE));
		temporaneo.aggiungiCasella(new Casella(CASELLA_17, 17));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_18, 18, prezzoCorsoRaffaello , MARRONE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_19, 19, prezzoPiazzaDante , MARRONE));
		
		temporaneo.aggiungiCasella(new Casella(CASELLA_20, 20));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_21, 21, prezzoViaMarcoPolo , ROSSO));
		temporaneo.aggiungiCasella(new Casella(CASELLA_22, 22));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_23, 23, prezzoCorsoMagellano , ROSSO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_24, 24, prezzoLargoColombo , ROSSO));
		temporaneo.aggiungiCasella(new Acquistabile(CASELLA_25, 25, valoreStazioneNord));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_26, 26, prezzoVialeCostantino , GIALLO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_27, 27, prezzoVialeTraiano , GIALLO));
		temporaneo.aggiungiCasella(new Societa(CASELLA_28, 28, valoreSocietaAcquaPotabile));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_29, 29, prezzoPiazzaGiulioCesare , GIALLO));
		
		temporaneo.aggiungiCasella(new InPrigione(CASELLA_30, 30));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_31, 31, prezzoViaRoma, VERDE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_32, 32, prezzoCorsoImpero, VERDE));
		temporaneo.aggiungiCasella(new Casella(CASELLA_33, 33));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_34, 34, prezzoLargoAugusto, VERDE));
		temporaneo.aggiungiCasella(new Acquistabile(CASELLA_35, 35, valoreStazioneEst));
		temporaneo.aggiungiCasella(new Casella(CASELLA_36, 36));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_37, 37, prezzoVialeDeiGiardini, VIOLA));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_38, 38, MALUS_LUSSO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_39, 39, prezzoParcoDellaVittoria, VIOLA));
		
		
		return temporaneo;
	}

}
