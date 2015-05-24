package utilities;

import java.util.*;

public class MyUtil {

	private static Scanner in = creaScanner();

	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}

	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non  nel formato corretto";
	private final static String MESSAGGIO_RANGE = "Attenzione: il dato inserito non  compreso nel range";
	private final static String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere";
	private final static String MESSAGGIO_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";
	private final static char RISPOSTA_SI = 'S';
	private final static char RISPOSTA_NO = 'N';

	public static String riceviString(String messaggio)

	{

		System.out.println(messaggio);
		return in.next();

	}// riceviString

	public static String StringNonVuota(String messaggio) {
		boolean finito = false;
		String lettura = null;
		do {
			lettura = riceviString(messaggio);
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito = true;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);

		return lettura;
	}// stringa non vuota

	public static char riceviChar(String messaggio) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			System.out.print(messaggio);
			String lettura = in.next();
			if (lettura.length() > 0) {
				valoreLetto = lettura.charAt(0);
				finito = true;
			} else {
				System.out.println(ERRORE_STRINGA_VUOTA);
			}
		} while (!finito);
		return valoreLetto;
	}// char

	public static char riceviUpperChar(String messaggio, String ammissibili) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			valoreLetto = riceviChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = true;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
		} while (!finito);
		return valoreLetto;
	}// upperChar

	public static int riceviNumero(String messaggio)

	{
		boolean finito = false;
		int valoreLetto = 0;

		do {
			System.out.print(messaggio);
			if (in.hasNextInt()) {
				valoreLetto = in.nextInt();
				finito = true;

			}// if
			else {
				System.out.println(ERRORE_FORMATO);
				String daButtare = in.next();
			}// else
		}// do
		while (!finito);
		return valoreLetto;

	}// riceviIntero

	public static double riceviDouble(String messaggio) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = in.nextDouble();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = in.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static double riceviDoubleLimiteSx(String messaggio, double minimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = riceviDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static int riceviLimite(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;

		do {
			System.out.print(messaggio);
			if (in.hasNextInt()) {
				valoreLetto = in.nextInt();
				if (valoreLetto >= minimo && valoreLetto <= massimo) {
					finito = true;
				}// second if
				else {
					System.out.println(MESSAGGIO_RANGE);
				}// second else
			}// first if
			else {
				System.out.println(ERRORE_FORMATO);
				String daButtare = in.next();
			}// first else

		}// do
		while (!finito);
		return valoreLetto;

	}// riceviLimite

	public static int riceviLimiteSx(String messaggio, int minimo) {
		boolean finito = false;
		int valoreLetto = 0;

		do {
			System.out.print(messaggio);
			if (in.hasNextInt()) {
				valoreLetto = in.nextInt();
				if (valoreLetto >= minimo) {
					finito = true;
				}// second if
				else {
					System.out.println(MESSAGGIO_RANGE);
				}// second else
			}// first if
			else {
				System.out.println(ERRORE_FORMATO);
				String daButtare = in.next();
			}// first else

		}// do
		while (!finito);
		return valoreLetto;

	}// riceviLimitesx

	public static int riceviLimiteDx(String messaggio, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;

		do {
			System.out.print(messaggio);
			if (in.hasNextInt()) {
				valoreLetto = in.nextInt();
				if (valoreLetto <= massimo) {
					finito = true;
				}// second if
				else {
					System.out.println(MESSAGGIO_RANGE);
				}// second else
			}// first if
			else {
				System.out.println(ERRORE_FORMATO);
				String daButtare = in.next();
			}// first else

		}// do
		while (!finito);
		return valoreLetto;

	}// riceviLimitedx

	public static char riceviCharControl(String messaggio, char myChar1,
			char myChar2, char myChar3)

	{
		boolean finito = false;
		String valoreLetto = null;
		// valoreLetto = in.nextLine();
		do {
			System.out.println(messaggio);
			valoreLetto = in.next();
			if (valoreLetto.length() == 0
					|| (valoreLetto.charAt(0) != myChar1
							&& valoreLetto.charAt(0) != myChar2 && valoreLetto
							.charAt(0) != myChar3)) {
				System.out.println("il comando non  corretto");
			}// if
			else
				finito = true;

		}// do
		while (!finito);
		return valoreLetto.charAt(0);

	}// riceviCharControl

	public static boolean yesOrNo(String messaggio) {
		String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO
				+ ")";
		char valoreLetto = riceviUpperChar(mioMessaggio,
				String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return true;
		else
			return false;
	}

}// MyUtil

