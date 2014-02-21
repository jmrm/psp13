package PSP0203v2;

import java.util.LinkedList;
import java.util.Queue;

public class ColaMensajesv2 <T>{
	private Queue<T> cola = new LinkedList<T>();
	private int capacidad;
	private static final String stop="*S*T*O*P*";
	
	public ColaMensajesv2(int capacidad) {
	this.capacidad=capacidad;
	}
	
	public synchronized String get(int destinatario) throws InterruptedException {
		PaqueteDatos item;
		String mensaje;
		// Mientras no haya mensajes o yo no sea el destinatario
		while (cola.isEmpty()||(((PaqueteDatos)cola.element()).getDestino()!=destinatario)) {
			try {
				wait();
			} catch (InterruptedException e) {
				notify();
				throw new InterruptedException();
			}
		}
		// Sacamos el mensaje de la cola y lo visualizamos
		item= (PaqueteDatos) cola.poll();
		mensaje=item.getMens();
		System.out.println(Thread.currentThread().getName()+" recibe el mensaje:"+mensaje+" Tamaño cola:"+cola.size());
		/*
		 * Tenemos que usar notifyAll() para despertar siempre al
		 * emisor, ya que si éste está bloqueado porque la cola está
		 * llena no podrá finalizar el proceso 
		 */
		notifyAll();
		return mensaje;
	}
	
	public synchronized void put(PaqueteDatos item) throws InterruptedException {
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
		destino=item.getDestino();
		mensaje=item.getMens();
		System.out.println(Thread.currentThread().getName()+
				" envía el mensaje:"+mensaje+" al destinatario:"+destino);
		cola.add((T)item);
		// Si el buffer no está lleno sólo notifica a un hilo
		// si no a todos
		if (cola.size()<capacidad) {
			notify();
		} else notifyAll();
	}
	
	public String getStop() {
		return stop;
	}
}