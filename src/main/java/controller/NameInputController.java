package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import main.Main;
import toplist.Toplist;

public class NameInputController implements Initializable {
	@FXML 
	private TextField nameField;
	
	public void submitButtonClicked(ActionEvent event) throws IOException {
		String nickname = nameField.getText();
		
		if(nickname.length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("WTF?");
			alert.setHeaderText("That's an interesting name...");
			alert.setContentText("Please enter a valid name.");
			alert.showAndWait();
		}
		
		
		else {
			if(nickname.length() >= 12) {
				nickname = nickname.substring(0, 12);
			
				Toplist topList = new Toplist();
				
				topList.updateNicknameById(Main.level, nickname);
				
				Parent root = FXMLLoader.load(getClass().getResource("/view/ToplistView.fxml"));
				Scene rootScene = new Scene(root);
				
				Main.primaryStage.setTitle("We are the Champions!!");
				Main.primaryStage.setScene(rootScene);
				Main.primaryStage.show();
			}
			else {
				Toplist topList = new Toplist();
				
				topList.updateNicknameById(Main.level, nickname);
				
				Parent root = FXMLLoader.load(getClass().getResource("/view/ToplistView.fxml"));
				Scene rootScene = new Scene(root);
				
				Main.primaryStage.setTitle("We are the Champions!!");
				Main.primaryStage.setScene(rootScene);
				Main.primaryStage.show();
			}
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
