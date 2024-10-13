package application.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HomeController {
	private String user;
	private Socket client;
	private BufferedWriter sWriter;
	private BufferedReader sReader;
	@FXML
	TextArea show;
	
	@FXML
	TextField searchBar;
	
	@FXML
	void search() {
		try {
			sWriter.write("location\n");
			sWriter.write(searchBar.getText()+"\n");
			sWriter.flush();
			
			String strList = sReader.readLine();
			String [] partsStrlist = strList.split("--");
			for(String s: partsStrlist) {
				String [] parts = s.split("#");
				for(String s2: parts) {
					show.appendText(s2+"\n");
				}
				show.appendText("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void initialize() {
		try {
			client = new Socket("localhost", 1000);
			OutputStreamWriter o = new OutputStreamWriter(client.getOutputStream());
			sWriter = new BufferedWriter(o);
			
			InputStreamReader i = new InputStreamReader(client.getInputStream());
			sReader = new BufferedReader(i);
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void getUser(String user) {
		this.user = user;
	}
	
	
}
