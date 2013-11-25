package Hilos_01;

public class UsaHiloSimple2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSimple2 hs2=new HiloSimple2();
		Thread hs20=new Thread(hs2);
		hs20.start();
		for (int i=0;i<10;i++) System.out.println("Desde fuera del hilo");
	}

}
