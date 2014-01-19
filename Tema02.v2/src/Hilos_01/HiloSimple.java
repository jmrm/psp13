package Hilos_01;

public class HiloSimple extends Thread {
	public HiloSimple(String n) {
		super(n);
	}
	public void run() {
		for (int i=0; i<10; i++) 
			System.out.println("Desde dentro del hilo "+getName()+" "+i);
	}
}
