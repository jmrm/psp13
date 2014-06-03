package Hilos_01;

public class E14_ConsumidorManzana_v2 implements Runnable {
	
	private E14_CajaManzana_v2 cajaManzana;
	private boolean salir=false;

	public E14_ConsumidorManzana_v2(E14_CajaManzana_v2 c) {
		cajaManzana=c;
	}
	
		public void run() {
		String varied=null;
		do {
		
			try {
				varied=cajaManzana.get();
			}
			catch (InterruptedException ie) {
				
			}
		} while (!varied.equals(cajaManzana.VSALIR));
		System.out.println(Thread.currentThread().toString()+" termina.");
	}
}
