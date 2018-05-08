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
import javafx.scene.text.Text;
import main.Main;
import model.Board;
import toplist.Toplist;
import view.SolverScene;

public class ToplistController implements Initializable {
	 	@FXML
	    private Text textField1;

	    @FXML
	    private Text textField2;

	    @FXML
	    private Text textField3;
	    
	public void backButtonClicked(ActionEvent event) throws IOException {
		Main.solved = false;
		Board board = new Board();
		SolverScene.solvableBoard = new Board(board);

		Parent root = FXMLLoader.load(getClass().getResource("/view/RootView.fxml"));
		Scene rootScene = new Scene(root);
		
		//Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Main.primaryStage.setTitle("Sudoku for Progtech");
		Main.primaryStage.setScene(rootScene);
		Main.primaryStage.show();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Toplist topList = new Toplist();
		
		String text1 = topList.getNicknameById(1);
		String text2 = topList.getNicknameById(2);
		String text3 = topList.getNicknameById(3);
		
		textField1.setText(text1);
		textField2.setText(text2);
		textField3.setText(text3);
		
	}

}
