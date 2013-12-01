package Hilos_01;

public class E01_ExtThrd extends Thread {
//Forma NO recomendada de crear un hilo
//Problema: la clase no puede derivarse de otra, solamente de Thread
    public void run() {
    	// el objeto actual hereda de Thread
    	String qHilo=this.getName();
    	System.out.println("Inicio: "+qHilo);
        for (int i=1; i<7; i++)
        {
        	System.out.print(qHilo+":"+i);
        	System.out.print(',');
        }
        System.out.println("Fin: "+qHilo);
    }

    public static void main(String args[]) {
    	// todo es un hilo, incluyendo main
    	Thread esteHilo=Thread.currentThread();
    	System.out.println("Extends Thread");
    	esteHilo.setName("Hilo principal");
        System.out.println("Inicio: "+esteHilo.getName());
        for (int i=1; i<=5; i++)
        	// Arrancamos los hilos
              	(new E01_ExtThrd()).start();
        System.out.println("Fin: "+esteHilo.getName());
    }

}
