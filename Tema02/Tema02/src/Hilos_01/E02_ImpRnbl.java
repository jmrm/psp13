package Hilos_01;

public class E02_ImpRnbl implements Runnable {
//Forma recomendada de crear un hilo
//Ventaja: la clase podría extender a cualquier clase 
    public void run() {
    	String qHilo=Thread.currentThread().toString();
    	System.out.println("Inicio: "+qHilo);
        for (int i=1; i<7; i++)
        {
        	System.out.print(qHilo+":"+i);
        	System.out.print(',');
        }
        System.out.println("Fin: "+qHilo);
    }

    public static void main(String args[]) {
    	Thread esteHilo=Thread.currentThread();
    	System.out.println("Implements Runnable");
    	esteHilo.setName("Hilo principal");
    	System.out.println("Inicio: "+esteHilo.getName());
    	for (int i=1; i<=5; i++)
    		(new Thread(new E02_ImpRnbl())).start();
    	System.out.println("Inicio: "+esteHilo.getName());
    }
}
