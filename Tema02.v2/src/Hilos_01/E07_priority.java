package Hilos_01;

public class E07_priority implements Runnable {
// 

	private int prioridad=Thread.MIN_PRIORITY;
	
	public void run() {
		//importante, el hilo no se crea en el constructor, sino en run()
    	Thread.currentThread().setPriority(prioridad);
    	String datosHilo=Thread.currentThread().toString();
        for (int i=1; i<=10; i++)
        {
        	System.out.print(i+"("+datosHilo+")");
        	System.out.println();
        }
    }
	
	// Constructor parametrizado
    E07_priority(int prio) {
    	prioridad=prio;
    }

    public static void main(String args[]) {
    	//min priority=1, max prio=10, norm prio=5
    	Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    	boolean switcher=true;
    	for (int i=5; i>=1; i--) {
    		if (switcher) { 
    			new Thread(new E07_priority(12-(2*i))).start();
    			switcher=false;
    		} else {
    			new Thread(new E07_priority(2*i)).start();
    			switcher=true;
    		}
    	}
    		
    	Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
    }
}
