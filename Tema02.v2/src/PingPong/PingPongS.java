package PingPong;

/*
 * Como sólo hay dos jugadores la sincronización es muy sencilla. Usamos un objeto para
 * sincronizar. Si uno juega el otro no puede, y cuando el primero termina, avisa que ha
 * terminado, y entra en espera es el otro el que juega, avisa y entra en espera, y así 
 * sucesivamente se van alternando manteniendo el orden correcto.
 * Si hubiese tres o más jugadores este esquema no serviría y habría que implementar un
 * turno.
 */

public class PingPongS implements Runnable {
	private String word;
	private boolean salir=false;
	private Object o;
	public PingPongS(String s, Object ob) {word=s; o=ob;}
	public void run()
	{
		synchronized(o) {
			while (!salir) {
				System.out.print(word);
				o.notify();
				try {
					o.wait();
				}
				catch (InterruptedException ie) {
				}
			}
			o.notify(); //Se notifica al salir para que el otro hilo se desbloquee
		}
		System.out.format("%n%s", Thread.currentThread().toString()+" termina.");
	} 
	public void salida() {
		salir=true;
	}
	
	public static void main(String[] args)
	{
		Object o=new Object();
		PingPongS tP=new PingPongS("P",o);
		PingPongS tp=new PingPongS("p",o);
		Thread h1=new Thread(tP);
		Thread h2=new Thread(tp);
		h1.start();h2.start();
		try {
			Thread.sleep(100);
			tP.salida();
			tp.salida();
			h1.join();h2.join();
		}
		catch (InterruptedException ie) {
		}
		System.out.format("%n%s", "Se acabó el partido");
	}
}