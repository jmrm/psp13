package Hilos_01;

public class E03_sleep implements Runnable {

	private int numH;
	
	// Constructor
	public E03_sleep (int qHilo) {
		numH=qHilo;
	}
	
    public void run() {
        System.out.println("Nuevo hilo:"+numH);
        for (int i=1; i<10; i++)
        {
        	System.out.print(numH+":"+i+", ");
        	// Cede el paso al siguiente hilo
        	if (numH==3) Thread.yield();
        	try
        	{
        		// Si el hilo es el 4 o un múltiplo de 4 lo duerme 1 s. y 200 ns
        		if (i%4==0)
        			Thread.sleep(1000,200); //msecs, nsecs
        	}
        	catch (InterruptedException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        System.out.println("Finaliza hilo:"+numH);
    }

    public static void main(String args[]) throws InterruptedException {
    for (int i=1; i<=5; i++)
        (new Thread(new E03_sleep(i))).start();
    }
}
