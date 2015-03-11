package utilities;

import java.io.Serializable;

public class MyMenu implements Serializable {
	
	private final static String CORNICE = "-------------------------------------------------";
    private final static String VOCE_USCITA = "0 - Esci ";
    private final static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";
	
	private String [] vociMenu;
	private String titolo;
	private int i;
	
	public MyMenu(String _titolo, String [] _vociMenu)
	{
		titolo = _titolo;
		vociMenu = _vociMenu;
	}
	
	public int scegli()
	{
		stampa();
		return MyUtil.riceviLimite(RICHIESTA_INSERIMENTO,0,vociMenu.length);
	}

	public void stampa()
	{
		System.out.println(CORNICE);
		System.out.println(titolo);
		System.out.println(CORNICE);
		
		for (i = 0; i < vociMenu.length ; i ++)
		{
			System.out.println( (i+1) + "- " +  vociMenu[i]);
		}
		
		System.out.println(" ");
		System.out.println(VOCE_USCITA);
		System.out.println(" ");
	}
}
