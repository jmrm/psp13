package PProcessBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Ejemplo_ProcessBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// primer comando
		ProcessBuilder pb = new ProcessBuilder("ls","-la","|","grep", "-i", " error");
		System.out.println("Visualizamos las variables de entorno del comando");
		/*
		 * Map es una clase de tipo pseudo-colección para mapear claves sobre valores. 
		 * Map <String, String> significa que vamos a tener una colección de parejas 
		 * clave->valor, donde tanto clave como valor serán Strings. 
		 */
		Map <String,String> entorno=pb.environment();
		/*
		 * Para recorrer el Map lo podemos hacer con un Iterator o, a partir de Java 1.5,
		 * mediante un for como el que hemos usado aquí.
		 * 
		 * El iterator sería:
		 * Iterator it = entorno.entrySet.iterator();
		 * while (it.hasNext()) {
		 * 	Map.Entry e = (Map.Entry)it.next();
		 *  System.out.println("["+e.getKey() + "=" + e.getValue()+"]");
		 * }
		 */
		for (Entry<String, String> e: entorno.entrySet()) {
	        System.out.println("["+e.getKey() + "=" + e.getValue()+"]");
	    }
		System.out.println("Visualizamos ahora el comando y sus argumentos");
		// Recorremos la colección de comando y argumentos mediante la colección List comando
		// sobre la que iteramos con un iterator
		List <String> comando=pb.command();
		Iterator it=comando.iterator();
		while (it.hasNext()) System.out.print(it.next());
		System.out.println("");
		// un nuevo comando, que ahora vamos a ejecutar
		pb = new ProcessBuilder("/bin/bash");
		File fShScript = new File("/home/jmrm/tmp/script01");
		File fOut = new File("/home/jmrm/tmp/salida.txt");
		File fErr = new File("/home/jmrm/tmp/error.txt");
		Process pr; 
		/*
		 * El siguiente ejemplo va a ejecutar el script contenido en "/home/jmrm/tmp/fShScript"
		 * almacenando la salida en salida.txt y el flujo de error en error.txt
		 * 
		 * #!/bin/bash
		 * # Un primer script
		 * echo "Hello World!"
		 * echo "You are really bad"
		 * echoitovsy
		 *   
		 */
		pb.redirectInput(fShScript);
		pb.redirectOutput(fOut);
		pb.redirectError(fErr);
		
		try  {
			pr=pb.start();
			int  exitVal;
			String linea;
			try {
				// Espera hasta que el proceso p termine y retorna el valor devuelto por éste 
				exitVal=pr.waitFor();
				System.out.println(">>>Valor de Salida al terminar: "+exitVal);
				System.out.println(">>>Contenido del archivo de salida estándar:");
				BufferedReader br=new BufferedReader(new FileReader (fOut));
				while ((linea=br.readLine())!=null) {
					System.out.println(linea);
				}
				br.close();
				System.out.println(">>>Contenido del archivo de error estándar:");
				br=new BufferedReader(new FileReader (fErr));
				while ((linea=br.readLine())!=null) {
					System.out.println(linea);
				}
				br.close();
			}
			catch (InterruptedException ie) {
				ie.getMessage();
				ie.printStackTrace();
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de IO");
				System.out.println(ioe.getMessage());
			}
		}
		catch (IOException ioe)  {
			System.out.printf("Se ha producido una excepcion. Mensaje: %s\n",ioe.getMessage());
		}
		// valor devuelto por el comando llamado desde Java
		
	}

}
