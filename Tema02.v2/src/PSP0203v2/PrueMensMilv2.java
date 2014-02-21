package PSP0203v2;

public class PrueMensMilv2 {

	public static void main(String[] args) {
		int numRecep=4, capacidadCola=20,i,numMensa=40;
		ReceptorMensMilv2[] receptor=new ReceptorMensMilv2[numRecep]; 
		Thread[] hiloR=new Thread[numRecep]; 
		ColaMensajesv2 <PaqueteDatos> m=new ColaMensajesv2 <PaqueteDatos>(capacidadCola);
		EmisorMensMilv2 emisor=new EmisorMensMilv2(numRecep,numMensa,m);
		for (i=0;i<numRecep;i++)
			receptor[i] =new ReceptorMensMilv2(i+1,m);
		Thread hiloE=new Thread(emisor,"Emisor");
		for (i=0;i<numRecep;i++)
			hiloR[i]=new Thread(receptor[i],"Receptor "+(i+1));
		hiloE.start();
		for (i=0;i<numRecep;i++)
			hiloR[i].start();
		try { 
			/*
			 * Sólo hace falta dormir el programa main e invocar
			 * al método salida de emisor, cuando éste no está controlado
			 * por número de paquetes a enviar
			 */
			if (emisor.getNumPaquetes()==0) {
				Thread.sleep(1000);
				emisor.salida();
			}
			/* Esto que sigue no hace falta porque es el emisor
			 * el que para a los receptores y todos van a recibir
			 * un mensaje de detención, tampoco hace falta por ello
			 * el control y relanzamiento de la excepción InterruptedException
			 * en los receptores, pero lo dejamos ahí en plan decorativo
			 * 
			for (i=0;i<numRecep;i++)
				receptor[i].salida();
				
			for (i=0;i<numRecep;i++)
			if (hiloE.isAlive()) hiloE.interrupt();
			for (i=0;i<numRecep;i++)
				if (hiloR[i].isAlive()) hiloR[i].interrupt();
			*/
			hiloE.join();
			for (i=0;i<numRecep;i++)
				hiloR[i].join();
		}
		catch (InterruptedException ie) {};
		System.out.println("Se acabó el proceso");
	}

}
