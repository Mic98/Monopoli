package main;

public class Tassa extends Casella{
	
	private int malus;
	
	public Tassa (String nome, int numero, int malus){
		super(nome, numero);
		this.malus = malus;
	}

	public int getMalus() {
		return malus;
	}
}
