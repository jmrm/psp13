package Hilos_01;

import java.util.Random;

public class E05_Join implements Runnable {
    public void run() {
    	// Crea una nueva semilla aleatoria
    	Random r=new Random();
    	// Genera un número pseudoaletorio en el rango 1-10
    	int segundos=r.nextInt(10) + 1;
        System.out.println(Thread.currentThread().toString()+" Sueño: me voy a dormir "+segundos+" seg...");
        try {
        	Thread.sleep(segundos*1000);
        }        
        catch (InterruptedException e)
        {
        	System.out.print("Me han despertado!");
        }
        System.out.println("Ya es primavera? "+Thread.currentThread().toString());
    }

    public static void main(String args[]) throws InterruptedException {
    	Thread [] t=new Thread[5];

    	for (int i=0; i<t.length; i++) {
    		t[i]=new Thread(new E05_Join());
    		t[i].start();
    	}
    	System.out.println("Esperando a que mis hilos terminen...");
    	for (int i=0; i<t.length; i++)
    		t[i].join();
    	System.out.println("Adiós!");
    }
}
