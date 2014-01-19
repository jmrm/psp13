package Hilos_01;

public class HiloEjemplo1_V2 extends Thread {
	private int c; //contador
	private int hilo;
	//constructor
	public HiloEjemplo1_V2 (int hilo) {
		this.hilo=hilo;
		System.out.println("CREANDO HILO:"+hilo);
	}
	//metodo run
	public void run() {
		c=0;
		while (c<=5) {
			System.out.println("Hilo:"+hilo+" Contador c="+c);
			c++;
		}
		System.out.println(this.toString());
	}
}
