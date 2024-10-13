package application.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	private Parent parent;
	private Scene scene;
	private Stage stage;
	private HashMap<String, String> mapping = new HashMap<>();
	
	@FXML
	TextField username, password;
	
	@FXML
	Label error, passError;
	
	@FXML
	void initialize() {
		try {
			FileReader file = new FileReader("src\\application\\server\\user.txt");
			BufferedReader reader = new BufferedReader(file);
			
			String line;
			while((line=reader.readLine()) != null) {
				String [] parts = line.split("-");
				mapping.put(parts[2], parts[3]);
			}
			reader.close();
		} catch (Exception e) {}
	}
	
	@FXML
	void login(ActionEvent event) {
		String u = username.getText();
		String p = password.getText();
		
		if(mapping.containsKey(u)) {
			error.setText("");
			String pass = mapping.get(u);
			
			if(pass.equals(p)) {
				//next scene area
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/home.fxml"));
					parent = loader.load();
					HomeController home = loader.getController();
					home.getUser(u);
					
					stage =(Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			else {
				passError.setText("Password wrong");
			}
		}
		else {
			error.setText("Username not found");
		}
	}
	
	//creating new account
	@FXML
	void createnow(ActionEvent event) throws IOException {
		parent = FXMLLoader.load(getClass().getResource("../ui/createnew.fxml"));
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
}
