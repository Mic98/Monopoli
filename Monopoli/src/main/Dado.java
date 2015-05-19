package main;
public class Dado {
int valore;
int lati;
public Dado(){
	this.lati= 6;
}
	public Dado ( int lati){
		this.lati=lati;
			
	}
	public void Tiro() {
        this.valore = ((int) (Math.random() * lati)) +1;}
	public int getValore(){
		this.Tiro();
		return valore;
	}
	
}
