package Hilos_01;

public class E14_ConsumidorManzana implements Runnable {
	
	private E14_CajaManzana cajaManzana;
	private boolean salir=false;

	public E14_ConsumidorManzana(E14_CajaManzana c) {
		cajaManzana=c;
	}
	
	public void salida() {	
		salir=true;
	}

	public void run() {
		String varied;
		while (!salir) {
			try {
			varied=cajaManzana.get();
			}
			catch (InterruptedException ie) {
				
			}
		}
		System.out.println(Thread.currentThread().toString()+" termina.");
	}
}
