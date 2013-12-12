package Mthrd;

/* CajerosNS
 * Esta clase representa a hilos que son cajeros de banco que acceden de forma
 * concurrente a cuentas bancarias mediante acceso no sincronizado 
 * El programa de prueba creará múltiples hilos cajeros que meten y sacan dinero
 * concurrentemente. Si el número de operaciones es alto y ejecutamos repetidas
 * veces, observaremos que el saldo final no es 1000. El problema es que estamos
 * accediendo concurrentemente a un dato Cuenta.saldo que es una sección crítica
 * pero sin sincronizar los accesos (sincronizadamente sólo un hilo modificaría 
 * cada vez), con lo que el resultado final exacto es impredecible 
 */

public class CajeroNS implements Runnable {
	// Los atributos son el dinero (+ para ingresos - para reintegros)
	// y la cuenta donde se va a operar.
	private double dinero;
	private Cuenta cuenta;

	// Método run del hilo	
	public void run() {
		if (dinero>=0)
			cuenta.ingresar(dinero);
		else
			try {
				cuenta.reintegrar(-dinero);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

	// Constructor del cajero	
	CajeroNS(Cuenta cuenta,double dinero) {
		this.dinero=dinero;
		this.cuenta=cuenta;
	}


	public static void main(String args []) {
		Cuenta c=null;
		
		int numOperaciones=999;
		double saldoInicial=1000.;
		
		// un par de matrices de hijos, para ingresos y para reintegros
		Thread ti[]=new Thread[numOperaciones],
			   tr[]=new Thread[numOperaciones];
		
		// Esta es la cuenta con la que van a operar todos
		c= new Cuenta("a12315456456df4adf54",saldoInicial);
		System.out.print("Datos iniciales de la cuenta: ");
		c.println();
		//Crear los hilos que meten y sacan dinero instanciando Thread a partir
		// de objetos CajeroNS
		for (int i=0; i<numOperaciones; i++)
		{
			ti[i]=new Thread(new CajeroNS(c,1.));	//cada CajeroNS de éstos mete 1 euro
			tr[i]=new Thread(new CajeroNS(c,-1.));	//cada CajeroNS de éstos saca 1 euro
			// Los lanzamos a la vida
			ti[i].start();
			tr[i].start();
		}
		
		//Esperar que terminen los hilos
		for (int i=0; i<numOperaciones; i++)
		{
			try {
				ti[i].join();
				tr[i].join();
			} catch (InterruptedException e) {}
		}
		
		System.out.println("El saldo final debería ser "+saldoInicial);
		System.out.print("Datos finales de la cuenta:");
		c.println();
	}
}

