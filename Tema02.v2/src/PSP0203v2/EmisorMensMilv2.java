package PSP0203v2;

public class EmisorMensMilv2 implements Runnable {
	
	private ColaMensajesv2<PaqueteDatos> colaMens;
	private boolean salir=false; //
	private int numRecept;
	private int numPaquetes;
	private PaqueteDatos paqDat;
	
 
	public EmisorMensMilv2(int nRecep,int numPaq,ColaMensajesv2<PaqueteDatos> c) {
		numRecept=nRecep;
		numPaquetes=numPaq;
		colaMens=c;
	}

	public void salida() {
		salir=true;
	}
	
	public boolean getSalir() {
		return salir;
	}
	
	public int getNumPaquetes() {
		return numPaquetes;
	}

	public void run() {
		String[] mensajes= {
				"Buenos días",
				"Buenas tardes",
				"Buenas noches",
				"¿ Qué tal ?",
				"¿ Qué hase ?",
				"¿ Cómo lo llevas ?",
				"Estudias o laboras",
				"Hace un tiempo de perros, ¿ eh ?",
				"No me pises que llevo chanclas",
				"Ya te advertí que a veces me enfado"
		};
		StringBuilder mensa=new StringBuilder();
		int destino, paqEnv=0;
		/*
		 * El emisor se puede parar tanto pasándole un número
		 * de paquetes distinto de 0, con lo que se detendrá cuando 
		 * llegue a ese número de paquetes, o antes si se invoca
		 * el método salida(), como invocando directamente a este 
		 * método
		 */
		while (!salir) {
			mensa.setLength(0);
			destino=((int)(Math.random()*numRecept))+1;
			mensa.append(mensajes[(int)(Math.random()*10)]);
			paqDat=new PaqueteDatos(destino,mensa.toString());
			//System.out.println(mensa);
			try {
				colaMens.put(paqDat);
				paqEnv++;
			} catch (InterruptedException e) {}
			if (!getSalir()) if(paqEnv==numPaquetes) salida();
		}
		// A continuación manda a los receptores el mensaje de paro
		mensa.setLength(0);
		mensa.append(colaMens.getStop());
		for (destino=1;destino<=numRecept;destino++) {
			paqDat=new PaqueteDatos(destino,mensa.toString());
			try {
				colaMens.put(paqDat);
			} catch (InterruptedException e) {}
		}
		System.out.println(Thread.currentThread().toString()+" termina.");
	}

}
