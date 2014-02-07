package Carrera;

public class Atleta implements Runnable {
	
	private Testigo testigo;
	private int turno;
 
	public Atleta(Testigo t, int turno) {
		testigo=t;this.turno=turno;
	}

	public void run() {
		testigo.corre(turno);
	}	
}
