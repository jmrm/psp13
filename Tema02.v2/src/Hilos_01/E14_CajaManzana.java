package Hilos_01;

public class E14_CajaManzana {
	private String variedad;
	private boolean hayManzana = false;
	
	public synchronized String get() {
		while (!hayManzana) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		hayManzana = false;
		notify();
		return variedad;
	}
	
	public synchronized void put(String varManzana) {
		while (hayManzana) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		variedad = varManzana;
		hayManzana = true;
		notify();
	}
}