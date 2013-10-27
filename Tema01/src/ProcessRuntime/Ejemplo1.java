package ProcessRuntime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Runtime.getRuntime() obtiene un objeto de la clase Runtime con el 
		 * entorno de ejecución del proceso Java 
		 */
		Runtime r = Runtime.getRuntime();
		// Comando que se va a ejecutar
		String comando="ls -la";
		Process p=null;
		try {
			/*
			 * Invocamos la ejecución del comando mediante el método exec de
			 * la clase Runtime
			 */
			p=r.exec(comando);
			/*
			 * El proceso lanzado redirige su salida estándar stdout, su entrada
			 * estándar stdin, y su canal de errores estándar stderr al proceso
			 * Java que lo ha creado. Si queremos ver la salida del proceso 
			 * lanzado es preciso redirigirla a la salida del proceso Java
			 * invocante. Para ello empleamos Process.getInputStream()
			 * 
			 */
			InputStream is = p.getInputStream();
			/*
			 * Vamos a manejar el stream resultante mediante búffer
			 */
			BufferedReader br = new BufferedReader (new InputStreamReader (is));
			String linea;
			/*
			 * Visualiza en la salida estándar del proceso Java invocante la
			 * del proceso invocado
			 */
			while ((linea=br.readLine())!=null)
				System.out.println(linea);
		}
		catch (Exception e) {
			System.out.println("Error en: "+comando);
			System.out.println(e.getMessage());
		}
		// valor devuelto por el comando llamado desde Java
		int exitVal;
		try {
			// Espera hasta que el proceso p termine y retorna el valor devuelto por éste 
			exitVal=p.waitFor();
			System.out.println("Valor de Salida: "+exitVal);
		}
		catch (InterruptedException ie) {
			ie.getMessage();
			ie.printStackTrace();
		}
	}

}
