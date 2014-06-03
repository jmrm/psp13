package Hilos_01;

public class E14_PruebaPCManzana_v2 {

	public static void main(String[] args) {
		E14_CajaManzana_v2 m=new E14_CajaManzana_v2();
		E14_ProductorManzana_v2 p=new E14_ProductorManzana_v2(m);
		E14_ConsumidorManzana_v2 c=new E14_ConsumidorManzana_v2(m);
		Thread h1=new Thread(p,"Productor");
		Thread h2=new Thread(c,"Consumidor");
		h1.start();h2.start();
		try { 
			Thread.sleep(3000);
			p.salida();//c.salida();
			//if (h1.isAlive()) h1.interrupt();
			//if (h2.isAlive()) h2.interrupt();
			h1.join();
			h2.join();
		}
		catch (InterruptedException ie) {};
		System.out.println("Se acabó la merienda");
	}

}
