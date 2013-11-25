package Hilos_01;

public class UsaHiloSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSimple hs=new HiloSimple();
		hs.start();
		for (int i=0;i<10;i++) System.out.println("Desde fuera del hilo");
	}

}
