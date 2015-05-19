package main;

import utilities.MyRandom;

public class Dado {

private static final int DADO_MIN = 1;
private static final int DADO_MAX = 6;
	
private int lancio1;
private int lancio2;


	public Dado ( int lancio1, int lancio2){
		this.lancio1=lancio1;
		this.lancio2=lancio2;
			
	}
	
	public int risultato() {
      return lancio1 + lancio2;
	}
	
	public boolean sonoUguali(){
		return lancio1==lancio2;
	}
	
	
	
	public int getLancio1() {
		return lancio1;
	}
	
	public int getLancio2() {
		return lancio2;
	}
	
	public void setLancio1(int lancio1) {
		this.lancio1 = lancio1;
	}

	public void setLancio2(int lancio2) {
		this.lancio2 = lancio2;
	}

	
	
	public static int getDadoMIN(){
		return DADO_MIN;
	}
	
	public static int getDadoMAX(){
		return DADO_MAX;
	}
	
}
