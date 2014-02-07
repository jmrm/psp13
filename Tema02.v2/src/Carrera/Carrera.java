package Carrera;

import java.util.Date;

public class Carrera {

private static Date inicio,fin;
private static String[] secuenciaSalida= {
		"Preparados ...",
		"Listos ...",
		"Bang!!"
};
private static String raya="-----------------------------";

public static void main(String[] args) {
	Testigo t=new Testigo();
	Atleta a1=new Atleta(t,1);
	Atleta a2=new Atleta(t,2);
	Atleta a3=new Atleta(t,3);
	Atleta a4=new Atleta(t,4);
	Thread h1=new Thread(a1,"Atleta 1");
	Thread h2=new Thread(a2,"Atleta 2");
	Thread h3=new Thread(a3,"Atleta 3");
	Thread h4=new Thread(a4,"Atleta 4");
	int i;
	try {
		// Preparados ... listos ... Bang !!
		for (i=0;i<secuenciaSalida.length;i++) {
			System.out.println(secuenciaSalida[i]);
			Thread.sleep((int)Math.random()*500);
		}
		System.out.println(raya);
	}
	catch (InterruptedException ie) {
		
	}
	h1.start();h2.start();h3.start();h4.start();
	try { 
		h1.join();h2.join();h3.join();h4.join();
	}
	catch (InterruptedException ie) {};
	System.out.println("Se acabó la carrera");
	inicio=new Date(t.getInicio());
	fin=new Date(t.getFin());
	System.out.println(raya);
	System.out.println("Inicio de carrera:"+inicio);
	System.out.println("Final de carrera:"+fin);
	System.out.println("Duración (milis):"+(t.getFin()-t.getInicio()));
}

}