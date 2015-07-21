package main;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import carte.*;
import caselle.*;

/**
 * @author Daniele Barattieri Carlo Giannini Alessandro Grazioli
 *
 */
public class Data {

	
	//------------------IMPOSTAZIONI DI DEFAULT-------------------
	public final static int NUMERO_TURNI = 20;
	public final static int POSIZIONE_DEFAULT = 0;
	public final static int CAPITALE_DEFAULT = 5000;
	public final static int PRIGIONE = 10;
	public final static double BONUS_VIA = 500;
	public final static double TASSA_PRIGIONE = 50;
	
	//-------------------BONUS/MALUS PROBABILITA'/IMPREVISTI---------------
	private final static double MALUS_PATRIMONIALE = 250;
	private final static double MALUS_LUSSO = 10;
	private final static double BENEFICIENZA = 50;
	private final static double CEDOLE_CARTELLE = 375;
	private final static double INTERESSI = 125;
	private final static double REGALO = 25;
	private final static double CAUSA = 250;
	private final static double CREDITORI = 500;
	private final static double CEDOLE_AZIONI = 60;
	private final static double PREMIO_ASSICURAZIONE = 125;
	
	//---------------------COLORI CASELLE---------------------------
	public final static String ROSA = "rosa";
	public final static String AZZURRO = "azzurro";
	public final static String ARANCIONE = "arancione";
	public final static String MARRONE = "marrone";
	public final static String ROSSO = "rosso";
	public final static String GIALLO = "giallo";
	public final static String VERDE = "verde";
	public final static String VIOLA= "viola";
	public final static String NERO = "nero";
	public final static String BIANCO = "bianco";
	
	//-------------------VALORI PROPRIETA'----------------------
	private final static double prezzoVicoloCorto = 60;
	private final static double prezzoVicoloStretto = 60;
	private final static double prezzoBastioniGranSasso = 100;
	private final static double prezzoVialeMonterosa = 100;
	private final static double prezzoVialeVesuvio = 120;
	private final static double prezzoViaAccademia = 140;
	private final static double prezzoCorsoAteneo = 140;
	private final static double prezzoPiazzaUniversita = 160;
	private final static double prezzoViaVerdi = 180;
	private final static double prezzoCorsoRaffaello = 180;
	private final static double prezzoPiazzaDante = 200;
	private final static double prezzoViaMarcoPolo = 220;
	private final static double prezzoCorsoMagellano = 220;
	private final static double prezzoLargoColombo = 240;
	private final static double prezzoVialeCostantino = 260;
	private final static double prezzoVialeTraiano = 260;
	private final static double prezzoPiazzaGiulioCesare = 280;
	private final static double prezzoViaRoma = 300;
	private final static double prezzoCorsoImpero = 300;
	private final static double prezzoLargoAugusto = 320;
	private final static double prezzoVialeDeiGiardini = 350;
	private final static double prezzoParcoDellaVittoria = 400;
	
	//-------------------VALORI SOCIETA'---------------------
	private final static double valoreSocietaElettrica = 150;
	private final static double valoreSocietaAcquaPotabile = 150;
	
	//-------------------VALORI STAZIONI----------------------
	private final static double valoreStazioneNord = 20;
	private final static double valoreStazioneSud = 20;
	private final static double valoreStazioneEst = 20;
	private final static double valoreStazioneOvest = 20;

	//----------------------NOMI CASELLE---------------------------
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
	
	//----------------------IMPREVISTI-----------------------------
	private final static String IMPREVISTI_01 ="Andate sino a Largo Colombo: se passate dal \"VIA!\", ritirate 500 euro dalla banca.";
	private final static String IMPREVISTI_02 ="Andate in prigione direttamente, senza passare dal \"VIA!\"";
	private final static String IMPREVISTI_03 ="Fate tre passi indietro.";
	private final static String IMPREVISTI_04 ="Andate sino a Via Accademia: se passate dal \"VIA!\", ritirate 500 euro dalla banca.";
	private final static String IMPREVISTI_05 ="Versate in banca 50 euro per beneficienza.";
	private final static String IMPREVISTI_06 ="Maturano le cedole delle vostre cartelle di rendita: ritirate 375 euro dalla banca.";
	private final static String IMPREVISTI_07 ="La banca vi paga gli interessi del vostro conto corrente: ritirate 125 euro.";
	private final static String IMPREVISTI_08 ="Andate avanti sino al \"VIA!\" e ritirate 500 euro dalla banca.";

	//----------------------PROBABILITA'----------------------------
	private final static String PROBABILITA_01 = "Ritornate al Vicolo Corto.";
	private final static String PROBABILITA_02 = "E' maturata la cedola delle vostre azioni: ritirate 60 euro dalla banca.";
	private final static String PROBABILITA_03 = "Scade il vostro premio di assicurazione: pagate 125 euro in banca.";
	private final static String PROBABILITA_04 = "Andate avanti sino al \"VIA!\" e ritirate 500 euro dalla banca.";
	private final static String PROBABILITA_05 = "Siete creditori verso la banca di 500 euro: ritirateli.";
	private final static String PROBABILITA_06 = "Avete perso una causa: pagate 250 euro in banca.";
	private final static String PROBABILITA_07 = "Andate in prigione direttamente, senza passare dal \"VIA!\".";
	private final static String PROBABILITA_08 = "E' il vostro compleanno: ogni giocatore vi regala 25 euro.";
	

	public static String[] getNomiCaselle() {
		return NOMI_CASELLE;
	}
	
	
	/**
	 * Inizializza il tabellone inserendo tutte le caselle in ordine
	 * @return tabellone completo
	 */
	public static Tabellone creaTabellone(){
		
		Tabellone temporaneo = new Tabellone();
		
		temporaneo.aggiungiCasella(new Neutra(CASELLA_0, 0));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_1, 1, prezzoVicoloCorto, ROSA));
		temporaneo.aggiungiCasella(new Probabilita(CASELLA_2, 2));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_3, 3, prezzoVicoloStretto, ROSA));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_4, 4, MALUS_PATRIMONIALE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_5, 5, valoreStazioneSud, NERO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_6, 6, prezzoBastioniGranSasso, AZZURRO));
		temporaneo.aggiungiCasella(new Imprevisti(CASELLA_7, 7));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_8, 8, prezzoVialeMonterosa, AZZURRO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_9, 9, prezzoVialeVesuvio, AZZURRO));
		
		temporaneo.aggiungiCasella(new Neutra(CASELLA_10, 10));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_11, 11, prezzoViaAccademia, ARANCIONE));
		temporaneo.aggiungiCasella(new Societa(CASELLA_12, 12, valoreSocietaElettrica, BIANCO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_13, 13, prezzoCorsoAteneo, ARANCIONE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_14, 14, prezzoPiazzaUniversita, ARANCIONE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_15, 15, valoreStazioneOvest, NERO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_16, 16, prezzoViaVerdi , MARRONE));
		temporaneo.aggiungiCasella(new Probabilita(CASELLA_17, 17));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_18, 18, prezzoCorsoRaffaello , MARRONE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_19, 19, prezzoPiazzaDante , MARRONE));
		
		temporaneo.aggiungiCasella(new Neutra(CASELLA_20, 20));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_21, 21, prezzoViaMarcoPolo , ROSSO));
		temporaneo.aggiungiCasella(new Imprevisti(CASELLA_22, 22));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_23, 23, prezzoCorsoMagellano , ROSSO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_24, 24, prezzoLargoColombo , ROSSO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_25, 25, valoreStazioneNord, NERO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_26, 26, prezzoVialeCostantino , GIALLO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_27, 27, prezzoVialeTraiano , GIALLO));
		temporaneo.aggiungiCasella(new Societa(CASELLA_28, 28, valoreSocietaAcquaPotabile, BIANCO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_29, 29, prezzoPiazzaGiulioCesare , GIALLO));
		
		temporaneo.aggiungiCasella(new InPrigione(CASELLA_30, 30));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_31, 31, prezzoViaRoma, VERDE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_32, 32, prezzoCorsoImpero, VERDE));
		temporaneo.aggiungiCasella(new Probabilita(CASELLA_33, 33));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_34, 34, prezzoLargoAugusto, VERDE));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_35, 35, valoreStazioneEst, NERO));
		temporaneo.aggiungiCasella(new Imprevisti(CASELLA_36, 36));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_37, 37, prezzoVialeDeiGiardini, VIOLA));
		temporaneo.aggiungiCasella(new Tassa(CASELLA_38, 38, MALUS_LUSSO));
		temporaneo.aggiungiCasella(new Terreno(CASELLA_39, 39, prezzoParcoDellaVittoria, VIOLA));
		
		temporaneo.mescolaImprevisti(creaImprevisti());
		temporaneo.mescolaProbabilita(creaProbabilita());
		
		return temporaneo;
	}
	
	/**
	 * 
	 * @return Il mazzo degli imprevisti mescolato
	 */
	public static Mazzo creaImprevisti(){
		
		Mazzo temporaneo = new Mazzo();
		
		temporaneo.aggiungiCarta(new VaiA(IMPREVISTI_01, 24));
		temporaneo.aggiungiCarta(new VaiInPrigione(IMPREVISTI_02));
		temporaneo.aggiungiCarta(new TornaIndietro(IMPREVISTI_03, 3));
		temporaneo.aggiungiCarta(new VaiA(IMPREVISTI_04, 11));
		temporaneo.aggiungiCarta(new PagaDebito(IMPREVISTI_05, BENEFICIENZA));
		temporaneo.aggiungiCarta(new RitiraCredito(IMPREVISTI_06, CEDOLE_CARTELLE));
		temporaneo.aggiungiCarta(new RitiraCredito(IMPREVISTI_07, INTERESSI));
		temporaneo.aggiungiCarta(new VaiA(IMPREVISTI_08, 0));
		
		Collections.shuffle(temporaneo.getCarte());
		
		return temporaneo;
	}
	
	/**
	 * 
	 * @return Il mazzo delle probabilita' gia' mescolato
	 */
	public static Mazzo creaProbabilita(){
        Mazzo temporaneo = new Mazzo();
		
		temporaneo.aggiungiCarta(new VaiNoVia(PROBABILITA_01, 1));
		temporaneo.aggiungiCarta(new RitiraCredito(PROBABILITA_02, CEDOLE_AZIONI));
		temporaneo.aggiungiCarta(new PagaDebito(PROBABILITA_03, PREMIO_ASSICURAZIONE));
		temporaneo.aggiungiCarta(new VaiA(PROBABILITA_04, 0));
		temporaneo.aggiungiCarta(new RitiraCredito(PROBABILITA_05, CREDITORI));
		temporaneo.aggiungiCarta(new PagaDebito(PROBABILITA_06, CAUSA));
		temporaneo.aggiungiCarta(new VaiInPrigione(PROBABILITA_07));
		temporaneo.aggiungiCarta(new Compleanno(PROBABILITA_08, REGALO));
		
		Collections.shuffle(temporaneo.getCarte());
		
		return temporaneo;
		
	}

}
