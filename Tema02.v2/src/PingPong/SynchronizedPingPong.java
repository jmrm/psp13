package PingPong;

public class SynchronizedPingPong extends Thread	
{	
	private String word;	
	public SynchronizedPingPong(String s) {word=s;}	

	public void run()	
	// “Para entrar por aquí tenemos que conseguir el
	{	
		// intrínseco de la clase SynchronizedPingPong”
		synchronized(getClass())	
		{	
			for (int i=0;i<3000;i++)	
			{	
				System.out.print(word);	 //Ejecuto una iteración
				System.out.flush();	
				getClass().notifyAll();	
				// Aviso de que he terminado
				try	
				// Espero
				{getClass().wait();}	
				catch (java.lang.InterruptedException e) {}	
			}	
			getClass().notifyAll();	
		}	
	}	

	public static void main(String[] args)	
	{	
		SynchronizedPingPong tP=new SynchronizedPingPong("P");	
		SynchronizedPingPong tp=new SynchronizedPingPong("p");	
		tp.start();	
		tP.start();	
	}	
}	

