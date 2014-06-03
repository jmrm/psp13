package Hilos_01;

public class E14_CajaManzana_v2 {
	/* Variedad "especial" para que el productor avise al consumidor que hay
	 * que terminar el proceso
	 */
	
	public final static String VSALIR="salir";
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
		if (!variedad.equals(VSALIR)) 
			System.out.println(Thread.currentThread().getName()+" obtiene la variedad:"+variedad);
		else 
			System.out.println(Thread.currentThread().getName()+" recibe la orden de salir.");
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
		if (!variedad.equals(VSALIR)) 
			System.out.println(Thread.currentThread().getName()+" almacena la variedad:"+variedad);
		else 
			System.out.println(Thread.currentThread().getName()+" recibe y transmite al consumidor orden de salir");
		hayManzana = true;
		notify();
	}
}