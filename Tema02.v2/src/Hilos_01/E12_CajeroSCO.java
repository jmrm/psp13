package Hilos_01;

/* CajerosSCO
* Esta clase representa a hilos que son cajeros de banco que acceden de forma
* concurrente a cuentas bancarias mediante acceso sincronizado a nivel de código
* usando un objeto ad-hoc para sincronizar.
* El programa de prueba creará múltiples hilos cajeros que meten y sacan dinero
* concurrentemente, sumando el total de ingresos y reintegros la misma cantidad. 
* Si el número de operaciones es alto y ejecutamos repetidas
* veces, observaremos que el saldo final sí es igual al inicial. El problema 
* que había antes cuando accedíamos concurrentemente a un dato E10_CuentaS.saldo que 
* era una sección crítica sin sincronizar los accesos ha quedado resuelto. Sólo un
* hilo puede entrar simultáneamente en esa sección crítica.
*/

public class E12_CajeroSCO implements Runnable {
        // Los atributos son el dinero (+ para ingresos - para reintegros)
        // y la cuenta donde se va a operar.
        private double dinero;
        private E12_CuentaSCO cuenta;

        // Método run del hilo        
        public void run() {
                if (dinero>=0)
                        cuenta.ingresar(dinero);
                else
                        try {
                                cuenta.reintegrar(-dinero);
                        } catch (Exception e) {
                                System.out.println(e.getMessage());
                        }
        }

        // Constructor del cajero        
        E12_CajeroSCO(E12_CuentaSCO cuenta,double dinero) {
                this.dinero=dinero;
                this.cuenta=cuenta;
        }


        public static void main(String args []) {
                E12_CuentaSCO c=null;
                
                int numOperaciones=999;
                double saldoInicial=1000.;
                
                // un par de matrices de hilos, para ingresos y para reintegros
                Thread ti[]=new Thread[numOperaciones],
                         tr[]=new Thread[numOperaciones];
                
                // Esta es la cuenta con la que van a operar todos
                c= new E12_CuentaSCO("a12315456456df4adf54",saldoInicial);
                System.out.print("Datos iniciales de la cuenta: ");
                c.println();
                //Crear los hilos que meten y sacan dinero instanciando Thread a partir
                // de objetos E12_CajeroSCO
                for (int i=0; i<numOperaciones; i++)
                {
                        ti[i]=new Thread(new E12_CajeroSCO(c,1.));        //cada E12_CajeroSCO de éstos mete 1 euro
                        tr[i]=new Thread(new E12_CajeroSCO(c,-1.));        //cada E12_CajeroSCO de éstos saca 1 euro
                        // Los lanzamos a la vida
                        ti[i].start();
                        tr[i].start();
                }
                
                //Esperar que terminen los hilos
                for (int i=0; i<numOperaciones; i++)
                {
                        try {
                                ti[i].join();
                                tr[i].join();
                        } catch (InterruptedException e) {}
                }
                
                System.out.println("El saldo final debería ser "+saldoInicial);
                System.out.print("Datos finales de la cuenta:");
                c.println();
        }
}