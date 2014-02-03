package Hilos_01;

public class E13_PingPong extends Thread {
	private String word;
	public E13_PingPong(String s) {word=s;}
	public void run()
	{
		for (int i=0;i<100;i++)
		{
			try {
				System.out.print(word);
				System.out.flush();
				sleep(10);
			}
			catch (InterruptedException ie) {
			
			}
		}
	}
	public static void main(String[] args)
	{
		Thread tP=new E13_PingPong("P");
		Thread tp=new E13_PingPong("p");
		//tP.setPriority(Thread.MAX_PRIORITY);
		//tp.setPriority(Thread.MIN_PRIORITY);
		tp.start();
		tP.start();
	}
}