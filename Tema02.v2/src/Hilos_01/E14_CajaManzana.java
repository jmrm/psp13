package Hilos_01;

public class E14_CajaManzana {
	private String variedad;
	private boolean hayManzana = false;
	
	public synchronized String get() throws InterruptedException {
		while (!hayManzana) {
			try {
				wait();
			} catch (InterruptedException e) {
				notify();
				throw new InterruptedException();
			}
		}
		System.out.println(Thread.currentThread().getName()+" obtiene la variedad:"+variedad);
		hayManzana = false;
		notify();
		return variedad;
	}
	
	public synchronized void put(String varManzana) throws InterruptedException {
		while (hayManzana) {
			try {
				wait();
			} catch (InterruptedException e) {
				notify();
				throw new InterruptedException();
			}
		}
		variedad = varManzana;
		System.out.println(Thread.currentThread().getName()+" almacena la variedad:"+variedad);
		hayManzana = true;
		notify();
	}
}