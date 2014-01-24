package Mthrd;
/*
* Esta es la clase Cuenta pedida. El código de cuenta no
* es controlado (en lo que se refiere a los códigos "reales" de un banco)
* solamente en cuanto al tamaño, que si es más de 10 se trunca y si es menor
* se rellena con espacios en blanco
*/
public class CuentaSCO {
        private String numCuenta;
        private double saldo;
        private Object lock= new Object();
        public CuentaSCO (String c,double s) {
                StringBuilder sb=new StringBuilder();
                String relleno="          ";
                sb.append(c);
                // Si c tiene más de 10 caracteres truncamos
                if (sb.length()>10) sb.setLength(10);
                else // Si c tiene menos de 10 caracteres, rellenamos con espacios
                        if(sb.length()<10)
                                sb.append(relleno.substring(10-sb.length()));
                saldo=s;numCuenta=sb.toString();
        }
        public CuentaSCO() {
                this("0123456789",100);
        }
        public double getSaldo() {
                return this.saldo;
        }
        public void print() {
                System.out.print("Cuenta num: "+numCuenta+" saldo: "+saldo);
        }
        public void println() {
                print();System.out.println();
        }
        public double ingresar(double i) {
        		synchronized (lock) {
        			this.saldo+=i;
        		}
                return this.saldo;
        }
        public double reintegrar (double r) throws Exception {
                if (r<=this.saldo) {
                		synchronized (lock) {
                			this.saldo-=r;
                		}
                } else
                        throw new Exception("Saldo insuficiente: reintegro "+r+" y solo hay "+this.saldo);
                return saldo;
        }
}        
