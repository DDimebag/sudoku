package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Board;
import toplist.Toplist;
import view.SolverScene;

public class NameInputController implements Initializable {
	@FXML 
	private TextField nameField;
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		Main.solved = false;
		Board board = new Board();
		SolverScene.solvableBoard = new Board(board);

		Parent root = FXMLLoader.load(getClass().getResource("/view/RootView.fxml"));
		Scene rootScene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Sudoku for Progtech");
		primaryStage.setScene(rootScene);
		primaryStage.show();
    }
	
	public void submitButtonClicked(ActionEvent event) throws IOException {
		String nickname = nameField.getText();
		
		Toplist topList = new Toplist();
		
		topList.updateNicknameById(Main.level, nickname);
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/ToplistView.fxml"));
		Scene rootScene = new Scene(root);
		
		//Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Main.primaryStage.setTitle("We are the Champions!!");
		Main.primaryStage.setScene(rootScene);
		Main.primaryStage.show();
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
