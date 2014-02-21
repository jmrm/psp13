package PSP0203v2;

public class ReceptorMensMilv2 implements Runnable {
	
	private int direccion;
	private ColaMensajesv2<PaqueteDatos> colaMens;

	public ReceptorMensMilv2(int direccion,ColaMensajesv2<PaqueteDatos> c) {
		this.direccion=direccion;
		colaMens=c;
	}
	
	public void run() {
		String mens="", stop;
		stop=colaMens.getStop();
		while (!mens.contentEquals(stop)) {
			try {
				mens=colaMens.get(direccion);
			}
			catch (InterruptedException ie) {				
			}
		}
		System.out.println(Thread.currentThread().toString()+" termina.");
	}
}
