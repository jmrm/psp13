package Hilos_01;

public class E14_ProductorManzana implements Runnable {
	
	private E14_CajaManzana cajaManzana;
	private boolean salir=false; // 
 
	public E14_ProductorManzana(E14_CajaManzana c) {
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
				System.out.println(Thread.currentThread().getName()+" almacena la variedad:"+variedad);
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {}
		}
	}

}
