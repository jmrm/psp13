package CliSer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class ServidorSocketStream {
	public static void main(String[] args) {
		try {
			System.out.println("Creando socket servidor");
			ServerSocket serverSocket = new ServerSocket();
			System.out.println("Haciendo binding");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
			System.out.println("Aceptando conexiones en un socket nuevo para intercambio");
			Socket newSocket = serverSocket.accept();
			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			byte[] mensaje = new byte[25];
			is.read(mensaje);
			System.out.println("Mensaje recibido:"+new String (mensaje));
			System.out.println("Cerrando el socket de intercambio");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("Programa terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}