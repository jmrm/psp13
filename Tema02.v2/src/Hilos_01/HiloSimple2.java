package Hilos_01;

public class HiloSimple2 implements Runnable {
	public void run() {
		for (int i=0; i<10; i++) System.out.println("Desde dentro del hilo "+i);
	}
}
