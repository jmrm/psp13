/*To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Dafne
 */
public class CliSockStream2 {

    public static void main(String[] args) {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

        try {

            String dirServidor = "localhost";
            int puerto = 5555;

            //Creamos el socket del cliente
            Socket miCliente = new Socket();
            System.out.println("Socket cliente creado");
            System.out.println("Estableciendo la conexión");

            // Obtenemos una dirección con los datos del servidor
            InetSocketAddress datosServer = new InetSocketAddress(dirServidor, puerto);

            //Conectamos con el servidor
            miCliente.connect(datosServer);
            //System.out.println("Cliente conectado a " + dirServidor + " en puerto " + puerto);

            //Canal de entrada para recibir mensajes
            BufferedReader in= new BufferedReader(new InputStreamReader(miCliente.getInputStream()));
            // Crea un PrintWriter con flush automático
            PrintWriter out=new PrintWriter(miCliente.getOutputStream(),true);

            String cliConectado;
            cliConectado=in.readLine();
            System.out.println(cliConectado);

            String mensaje,confirmacion;

            //Hace un bucle para que mientras el mensaje del cliente sea diferente a "close" siga enviando mensajes
           do {
                //Prepara el mensaje para enviarlo
                System.out.println("Introducir mensaje cliente: ");
                mensaje = leer.readLine();
                if (mensaje.length()>0) {
                	System.out.println("Enviando mensaje al servidor");
                	//Envia el mensaje
                	out.println(mensaje);
                	System.out.println("Mensaje enviado");
                	confirmacion=in.readLine();
                	//Muestra el mensaje recibido
                	System.out.println(confirmacion);
                	System.out.println("");
            
                } else System.out.println("No vale enviar mensajes vacíos");
            } while (mensaje.compareToIgnoreCase("close") != 0);

            

            //Cierra el socket del cliente
            miCliente.close();
            System.out.println("Socket del cliente cerrado");

            System.out.println("Programa terminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
