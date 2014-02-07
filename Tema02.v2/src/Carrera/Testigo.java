package Carrera;

import java.util.Random;

public class Testigo {
	private int orden;
	private long inicio,fin;
	
	public Testigo () {
		orden=1;
	}
	
	public synchronized void corre(int turno) {
		long[] tiempos= new long[2];
		while (true) {
			if (orden==turno) {
				tiempos[0]=System.currentTimeMillis();
				System.out.println("Atleta:"+Thread.currentThread().getName());
				System.out.println("Sale:"+tiempos[0]);
				Random semilla=new Random();
				try {
					Thread.sleep((semilla.nextInt(3)+9)*1000);
				}
				catch (InterruptedException ie) {
				}
				tiempos[1]=System.currentTimeMillis();
				break;
			} else {
				try {
					wait();
				}
				catch (InterruptedException ie) {
				}
			}
		}
		if (orden==1) inicio=tiempos[0];
		else if (orden==4) fin=tiempos[1];
		orden++;
		System.out.println("Llega:"+tiempos[1]);
		System.out.println("Tiempo (milis):"+(tiempos[1]-tiempos[0]));
		System.out.println("");
		notifyAll();
	}
	
	public long getInicio() {
		return inicio;
	}
	
	public long getFin() {
		return fin;
	}
}
