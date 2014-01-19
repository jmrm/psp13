package Hilos_01;

public class E06_Interrupt implements Runnable {
	private static final long segundos=86400;
    public void run() {
        System.out.println("Sueño: me voy a dormir "+segundos+" seg...");
        try {
        	Thread.sleep(segundos*1000);
        }
        // En este caso, una vez q se atrapa esta interrupción se termina run()
        catch (InterruptedException e)
        {
        	System.out.print("Me han despertado!");
        }
    }

    public static void main(String args[]) throws InterruptedException {
    	final long segundos=5;
    	Thread [] t=new Thread[5];

    	for (int i=0; i<t.length; i++) {
    		t[i]=new Thread(new E06_Interrupt());
    		t[i].start();
    	}
    	System.out.println("Os dejo dormir "+segundos+" segundos, hilos mios...");
    	Thread.sleep(segundos*1000);
    	System.out.println("Se acabó el descanso!");
    	for (int i=0; i<t.length; i++)
    		if (t[i].isAlive()&&!(t[i].isInterrupted()))t[i].interrupt();
    }
}
