package Hilos_01;

public class E14_PruebaPCManzana {

	public static void main(String[] args) {
		E14_CajaManzana m=new E14_CajaManzana();
		E14_ProductorManzana p=new E14_ProductorManzana(m);
		E14_ConsumidorManzana c=new E14_ConsumidorManzana(m);
		Thread h1=new Thread(p,"Productor");
		Thread h2=new Thread(c,"Consumidor");
		h1.start();h2.start();
		try { 
			Thread.sleep(3000);
			p.salida();c.salida();
			if (h1.isAlive()) h1.interrupt();
			if (h2.isAlive()) h2.interrupt();
			h1.join();
			h2.join();
		}
		catch (InterruptedException ie) {};
		System.out.println("Se acabó la merienda");
	}

}
