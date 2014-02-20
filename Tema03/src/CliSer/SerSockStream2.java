/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dafne
 */
public class SerSockStream2 {

    public String dirServidor = "localhost";
    public int puerto = 5555;

    public static void main(String[] args) {
        String dirServidor = "localhost";
        int puerto = 5555;

        try {
            //Crear socket servidor
            ServerSocket miServidor = new ServerSocket();
            System.out.println("Socket Servidor creado");

            //System.out.println("Haciendo binding");

            InetSocketAddress datosServidor = new InetSocketAddress(dirServidor, puerto);
            miServidor.bind(datosServidor);

            //Esperamos y aceptamos conexiones entrantes
            System.out.println("Esperando conexiones");
            Socket nuevoCliente = miServidor.accept();
            System.out.println("Cliente conectado");

            //Canal de entrada para recibir mensajes
            BufferedReader in= new BufferedReader(new InputStreamReader(nuevoCliente.getInputStream()));
            // Crea un PrintWriter con flush automático
            PrintWriter out=new PrintWriter(nuevoCliente.getOutputStream(),true);

            //Mensaje de confirmacion de conexion para el cliente
            String cliConectado = ("Te has conectado al servidor " + dirServidor + " en el puerto " + puerto);
            //Enviamos el mensaje
            out.println(cliConectado);

            //Declara una cadena para el mensaje que recibe del cliente
            String msj, mRecibido = "El servidor dice:ack";
            do{
                //Lee el mensaje 
        	    msj=in.readLine();
                //Muestra el mensaje recibido
                System.out.println("Mensaje recibido: " + msj +" Leídos:"+msj.length()+" caracteres.");
                //Confirma la recepción del mensaje al cliente                     
                out.println(mRecibido);
            }while (!msj.equalsIgnoreCase("close")) ;
            
            
            //Cierra el socket del cliente
            nuevoCliente.close();
            System.out.println("Socket del cliente cerrado");
            //Cierra el socket del servidor
            miServidor.close();
            System.out.println("Socket del servidor cerrado");

            System.out.println("Programa terminado");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
