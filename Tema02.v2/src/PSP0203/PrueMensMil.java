package PSP0203;

public class PrueMensMil {

	public static void main(String[] args) {
		int numRecep=4, capacidadCola=4,i;
		ReceptorMensMil[] receptor=new ReceptorMensMil[numRecep]; 
		Thread[] hiloR=new Thread[numRecep]; 
		ColaMensajes <String> m=new ColaMensajes <String>(capacidadCola);
		EmisorMensMil emisor=new EmisorMensMil(m);
		for (i=0;i<numRecep;i++)
			receptor[i] =new ReceptorMensMil(i+1,m);
		Thread hiloE=new Thread(emisor,"Emisor");
		for (i=0;i<numRecep;i++)
			hiloR[i]=new Thread(receptor[i],"Receptor "+(i+1));
		hiloE.start();
		for (i=0;i<numRecep;i++)
			hiloR[i].start();
		try { 
			Thread.sleep(1000);
			emisor.salida();
			for (i=0;i<numRecep;i++)
				receptor[i].salida();
			for (i=0;i<numRecep;i++)
			if (hiloE.isAlive()) hiloE.interrupt();
			for (i=0;i<numRecep;i++)
				if (hiloR[i].isAlive()) hiloR[i].interrupt();
			hiloE.join();
			for (i=0;i<numRecep;i++)
				hiloR[i].join();
		}
		catch (InterruptedException ie) {};
		System.out.println("Se acabó el proceso");
	}

}
