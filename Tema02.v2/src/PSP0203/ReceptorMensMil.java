package PSP0203;

public class ReceptorMensMil implements Runnable {
	
	private int direccion;
	private ColaMensajes<String> colaMens;
	private boolean salir=false;

	public ReceptorMensMil(int direccion,ColaMensajes<String> c) {
		this.direccion=direccion;
		colaMens=c;
	}
	
	public void salida() {	
		salir=true;
	}

	public void run() {
		String mens;
		while (!salir) {
			try {
				mens=colaMens.get(direccion);
			}
			catch (InterruptedException ie) {				
			}
		}
		System.out.println(Thread.currentThread().toString()+" termina.");
	}
}
