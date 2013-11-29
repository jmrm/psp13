package Hilos_01;

public class UsaHiloEjemplo1_V2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloEjemplo1_V2 h;
		for (int i=0;i<3;i++) {
			h=new HiloEjemplo1_V2(i+1);
			h.setPriority((3-i)*3);
			h.start();
		}
		System.out.println("Tres hilos creados");
	}

}
