package PSPEv01;

import java.util.Random;

public class PSPEv01a implements Runnable{

	public void run() {
    	// Crea una nueva semilla aleatoria
    	Random r=new Random();
    	long inicio=0l, fin=0l, suegno=0l;
    	// Genera un número pseudoaletorio en el rango 3-8
    	int segundos=r.nextInt(6) + 3;
        System.out.println(Thread.currentThread().toString()+" Sueño: me voy a dormir "+segundos+" seg...");
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
    	Thread [] t=new Thread[5];
    	Long [] inicio=new Long[5]; 
    	Random r=new Random();
    	int espera=r.nextInt(9)+1;
    	String nombre;
    	for (int i=0; i<t.length; i++) {
    		nombre="Hilo "+i;
    		t[i]=new Thread(new PSPEv01a(),nombre);
    		inicio[i]=System.currentTimeMillis();
    		t[i].start();
    	}
    	System.out.println("Esperando "+espera+" segundos a que mis hilos terminen...");
    	Thread.sleep(espera*1000);
    	// Los que quedan vivos los interrumpe al final
    	for (int i=0; i<t.length; i++) {
    		if (t[i].isAlive()) t[i].interrupt();
    	}
    	System.out.println("Adiós!");
    }
}
