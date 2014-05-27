package Hilos_01;

/*
 * La JVM ejecutará los hilos daemon mientras hay algún no daemon ejecutándose. En este ejemplo, cuando
 * el hilo principal y el Thread2 mueren, la máquina corta al daemon sin esperar a que haya terminado la cuenta
 * atrás
 */

public class E08_Conta_Daemon implements Runnable {
	private Thread cuentaAtras;
	private int iteracion;
	
	E08_Conta_Daemon(String nombre, int iter) {
		cuentaAtras=new Thread(this);  //target=this objeto cuyo run() se ejecutará
		iteracion=iter;
		if (nombre!=null) 
			cuentaAtras.setName(nombre);
		if (nombre.equalsIgnoreCase("Hilo 1->")) cuentaAtras.setDaemon(true);
		System.out.println("Hilo "+nombre+".isdaemon()="+cuentaAtras.isDaemon());
		cuentaAtras.start();
	}
	
	E08_Conta_Daemon() {
		this(null,1000);
	}
		
	public void run() {
		for (int i=iteracion; i>0; i--)
			System.out.print("\t\t\t"+cuentaAtras.getName()+" "+i+"\r");
		System.out.println();
	}
	
	public static void main(String [] args) {
		new E08_Conta_Daemon("Hilo 1->",2000);
		new E08_Conta_Daemon("Hilo 2->",1000);
		try {
		  Thread.sleep(10);	//probar aquí con diferentes tiempos
		} catch (InterruptedException e) {
			
		}
		System.out.println("Programa Principal\n");
		System.exit(0);		//probar que pasa si se hace esto
	}
}
