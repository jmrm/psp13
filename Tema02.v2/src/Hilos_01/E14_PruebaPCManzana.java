package Hilos_01;

public class E14_PruebaPCManzana {

	public static void main(String[] args) {
		E14_CajaManzana m=new E14_CajaManzana();
		E14_ProductorManzana p=new E14_ProductorManzana(m);
		E14_ConsumidorManzana c=new E14_ConsumidorManzana(m);
		new Thread(p).start();
		new Thread(c).start();
		try { Thread.sleep(3000);}
		catch (InterruptedException ie) {};
		p.salida();c.salida();
		System.out.println("Se acabó la merienda");
	}

}
