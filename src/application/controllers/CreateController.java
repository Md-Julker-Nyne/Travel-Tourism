package application.controllers;

import java.io.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {
	private Parent parent;
	private Scene scene;
	private Stage stage;
	@FXML
	TextField name, email, username, pass;
	
	@FXML
	void onCreate(ActionEvent event) {
		try {
			FileWriter file = new FileWriter("src\\application\\server\\user.txt", true);
			BufferedWriter writer = new BufferedWriter(file);
			String n = name.getText();
			String e = email.getText();
			String u = username.getText();
			String p = pass.getText();
			
			String info = n+"-"+ e +"-"+ u +"-"+ p;
			writer.write(info);
			writer.newLine();
			writer.close();
			
			parent = FXMLLoader.load(getClass().getResource("../ui/main.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {}
	}
	
	@FXML
	void onBack(ActionEvent event) throws Exception{
		parent = FXMLLoader.load(getClass().getResource("../ui/main.fxml"));
		stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
}
