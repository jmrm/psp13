package Hilos_01;

public class E14_ProductorManzana_v2 implements Runnable {
	
	private E14_CajaManzana_v2 cajaManzana;
	private boolean salir=false; // 
 
	public E14_ProductorManzana_v2(E14_CajaManzana_v2 c) {
		cajaManzana=c;
	}

	public void salida() {
		salir=true;
	}

	public void run() {
		String[] mVariedad= {
				"Golden",
				"Starky",
				"Ambrosía",
				"Fuji",
				"Gala",
				"McIntosh"
		};
		String variedad;
		while (!salir) {
			variedad=mVariedad[(int)(Math.random()*6)];
			try {
				cajaManzana.put(variedad);
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {}
		}
		/*
		 * Es el productor el que avisa al consumidor que se ha acabado el
		 * proceso. Para ello usa una manzana "especial" 
		 */
		try {
			cajaManzana.put(cajaManzana.VSALIR);
		} catch (InterruptedException e) {}
		
		System.out.println(Thread.currentThread().toString()+" termina.");
	}

}
