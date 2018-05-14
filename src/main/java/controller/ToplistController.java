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
import view.SolverView;

public class ToplistController implements Initializable {
	 	@FXML
	    private Text t1, t2, t3, n1, n2, n3;

	    
	public void backButtonClicked(ActionEvent event) throws IOException {
		Main.solved = false;
		Board board = new Board();
		SolverView.solvableBoard = new Board(board);

		Parent root = FXMLLoader.load(getClass().getResource("/view/RootView.fxml"));
		Scene rootScene = new Scene(root);
		
		Main.primaryStage.setTitle("Sudoku for Progtech");
		Main.primaryStage.setScene(rootScene);
		Main.primaryStage.show();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Toplist topList = new Toplist();
		
		String name1 = topList.getNicknameById(1);
		String name2 = topList.getNicknameById(2);
		String name3 = topList.getNicknameById(3);
		
		String time1 = Integer.toString(topList.getTimeById(1));
		String time2 = Integer.toString(topList.getTimeById(2));
		String time3 = Integer.toString(topList.getTimeById(3));
		
		n1.setText(name1);
		n2.setText(name2);
		n3.setText(name3);
		
		if(time1.equals("0"))
			t1.setText("");
		else
			t1.setText(time1);
		
		if(time2.equals("0"))
			t2.setText("");
		else
			t2.setText(time2);
		
		if(time3.equals("0"))
			t3.setText("");
		else
			t3.setText(time3);
	}
}
