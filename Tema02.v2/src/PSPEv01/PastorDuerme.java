package PSPEv01;

import java.util.Random;

public class PastorDuerme implements Runnable{

	public void run() {
    	// Crea una nueva semilla aleatoria
    	Random r=new Random();
    	long inicio=0l, fin=0l, suegno=0l;
    	// Genera un número pseudoaletorio en el rango 5-10
    	int segundos=r.nextInt(6) + 5;
        System.out.println(Thread.currentThread().toString()+" Sueño: me voy a dormir "+segundos+" seg...");
        System.out.println("Soy de la clase: "+this.getClass().getName());
        try {
        	inicio=System.currentTimeMillis();
        	Thread.sleep(segundos*1000);
        	fin=System.currentTimeMillis();   	
        	System.out.println(Thread.currentThread().toString()+" Me he despertado solito!");
     
        }        
        catch (InterruptedException e)
        {
        	fin=System.currentTimeMillis();
        	System.out.println(Thread.currentThread().toString()+" Me han despertado!");
        }
        suegno=fin-inicio;
    	System.out.println(Thread.currentThread().toString()+" He dormido "+suegno+" milisegundos");
    }

    public static void main(String args[]) throws InterruptedException {
    	Thread [] t=new Thread[8]; 
    	Random r=new Random();
    	int espera=r.nextInt(10)+1, prioridad;
    	String nombre;
    	for (int i=0; i<t.length; i++) {
    		nombre="oveja "+i;
    		t[i]=new Thread(new PastorDuerme(),nombre);
    		prioridad=r.nextInt(10)+1;
    		//inicio[i]=System.currentTimeMillis();
    		t[i].setPriority(prioridad);
    		t[i].start();
    	}
    	System.out.println("Esperando "+espera+" segundos a que mis hilos terminen...");
    	Thread.sleep(espera*1000);
    	for (int i=0; i<t.length; i++) {
    		if (t[i].isAlive()) t[i].interrupt();
    	}
    	System.out.println("Adiós!");
    }
}
