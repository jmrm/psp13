package PSP0203;

public class EmisorMensMil implements Runnable {
	
	private ColaMensajes<String> colaMens;
	private boolean salir=false; // 
 
	public EmisorMensMil(ColaMensajes<String> c) {
		colaMens=c;
	}

	public void salida() {
		salir=true;
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
		int destino;
		while (!salir) {
			mensa.setLength(0);
			destino=((int)(Math.random()*4))+1;
			mensa.append(destino);
			mensa.append(mensajes[(int)(Math.random()*10)]);
			//System.out.println(mensa);
			try {
				colaMens.put(mensa.toString());
				Thread.sleep((int)(Math.random()*10));
			} catch (InterruptedException e) {}
		}
		System.out.println(Thread.currentThread().toString()+" termina.");
	}

}
