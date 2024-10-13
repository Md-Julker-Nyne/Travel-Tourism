package application.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import application.server.ClientThread;


public class Server {
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Waiting for client");
		ServerSocket serverSocket = new ServerSocket(1000);
		while(true) {
			Socket client = serverSocket.accept();
			System.out.println("Connection established");
			
			OutputStreamWriter o = new OutputStreamWriter(client.getOutputStream());
			BufferedWriter writer = new BufferedWriter(o);
			
			InputStreamReader i = new InputStreamReader(client.getInputStream());
			BufferedReader reader = new BufferedReader(i);
			
			Thread t = new ClientThread(client, writer, reader);
			t.start();
		}
	}
}
