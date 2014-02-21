package PSP0203v2;

public class PaqueteDatos {
	private int destino;
	private String mens;
	
	public PaqueteDatos (int d, String m) {
		destino=d;mens=m;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public String getMens() {
		return mens;
	}
	public void setMens(String mens) {
		this.mens = mens;
	}


}
