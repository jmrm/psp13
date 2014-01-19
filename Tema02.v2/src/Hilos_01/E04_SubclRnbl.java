package Hilos_01;

public class E04_SubclRnbl {

    // Muestra un mensaje precedido con el nombre del hilo actual
    static void threadMessage(String msg) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,msg);
    }
    // MessageLoop es una subclase de threadMessage que implementa Runnable
    private static class MessageLoop implements Runnable {
    	
        public void run() {
            String info[] = {
                "Pues ya ves",
                "por aquí andamos",
                "pasando el rato",
                "sin nada mejor que hacer."
            };
            try {
                for (int i = 0;i < info.length;i++) {
                    // Pausa de 4 segundos
                    Thread.sleep(3000);
                    // Llamamos al método que imprime el mensaje
                    threadMessage(info[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("Me interrumpieron!");
            }
        }
    }

    public static void main(String args[])
        throws InterruptedException {

        // Retraso hasta que interrumpamos al hilo de mensajes
        // Por defecto 10 segundos (también se puede pasar en la línea de comandos)
        long paciencia = 1000 * 10;

        // Si se pasa en la linea de comandos, modificar la espera
        if (args.length > 0) {
            try {
                paciencia = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("El parámetro debe poder ser un entero largo!");
                System.exit(1);
            }
        }

        threadMessage("Arrancando el hilo...");
        // Tomamos el momento actual para saber el tiempo transcurrido
        long startTime = System.currentTimeMillis();
      //creamos un Hilo, pero no de la clase E04_SubclRnbl, sino de la subclase MessageLoop
        Thread t = new Thread(new MessageLoop());	
        t.start();
        threadMessage("Esperando a que termine el bucle de mensajes");
        while (t.isAlive()) {	//el hilo sigue vivo
            threadMessage("Esperando...");
            //Esperar la reunión del hilo un máximo de un segundo
            t.join(1000);
          //si hemos excedido el t. total y el hilo sigue
            if (((System.currentTimeMillis() - startTime) > paciencia) && t.isAlive()) {
            	t.interrupt();
                threadMessage("El hilo principal dice que se acabó la espera!");
                // Esperando la reunión definitiva, no debe tardar
                t.join();
            }
        }
        threadMessage("Fin!");
    }
}