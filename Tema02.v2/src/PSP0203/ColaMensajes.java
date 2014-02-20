package PSP0203;

import java.util.LinkedList;
import java.util.Queue;

public class ColaMensajes <T>{
	private Queue<String> cola = new LinkedList<String>();
	private int capacidad;
	
	public ColaMensajes(int capacidad) {
	this.capacidad=capacidad;
	}
	
	public synchronized String get(int destinatario) throws InterruptedException {
		String item="";
		String mensaje="";
		// Mientras no haya mensajes o yo no sea el destinatario
		while (cola.isEmpty()||Integer.parseInt(((String) cola.element()).substring(0,1))!=destinatario) {
			try {
				wait();
			} catch (InterruptedException e) {
				notify();
				throw new InterruptedException();
			}
		}
		// Sacamos el mensaje de la cola y lo visualizamos
		item= (String) cola.poll();
		mensaje=item.substring(1);
		System.out.println(Thread.currentThread().getName()+" recibe el mensaje:"+mensaje+" Tamaño cola:"+cola.size());
		notify();
		return mensaje;
	}
	
	public synchronized void put(String item) throws InterruptedException {
		String mensaje="";
		int destino;
		while (cola.size()==capacidad) {
			try {
				wait();
			} catch (InterruptedException e) {
				notify();
				throw new InterruptedException();
			}
		}
		destino=Integer.parseInt(item.substring(0,1));
		mensaje=item.substring(1);
		System.out.println(Thread.currentThread().getName()+
				" envía el mensaje:"+mensaje+" al destinatario:"+destino);
		cola.add((String)item);
		if (cola.size()<capacidad) {
			notify();
		} else notifyAll();
	}
}