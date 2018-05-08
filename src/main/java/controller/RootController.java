package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Main;
import model.Board;
import model.EreaseValues;
import model.ShuffleBoard;
import model.Solver;
import model.TimerClass;
import view.SolverScene;
import view.SudokuScene;

/**
 * Controller for the Main Menu
 *
 */
public class RootController implements Initializable {

	public static Board boardBlank;
	public static Board boardFull;


	public void easyPeasyButtonClicked(ActionEvent event) throws IOException {
		int blanks = 5;
		Main.level = 1;
		Board b = generateBoard(blanks);
		TimerClass timer = new TimerClass();
		SudokuScene ss = new SudokuScene(timer);
		SudokuScene.modifiedBoard = new Board(boardBlank);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Level: Easy Peasy");
		primaryStage.setScene(new Scene(ss.createContent(b)));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
	    });
	}

	public void easyButtonClicked(ActionEvent event) throws IOException {
		int blanks = 30;
		Main.level = 2;
		Board b = generateBoard(blanks);
		TimerClass timer = new TimerClass();
		SudokuScene ss = new SudokuScene(timer);
		SudokuScene.modifiedBoard = new Board(boardBlank);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Level: Easy");
		primaryStage.setScene(new Scene(ss.createContent(b)));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
	    });
	}

	public void hardButtonClicked(ActionEvent event) throws IOException {
		int blanks = 40;
		Main.level = 3;
		Board b = generateBoard(blanks);
		TimerClass timer = new TimerClass();
		SudokuScene ss = new SudokuScene(timer);
		SudokuScene.modifiedBoard = new Board(boardBlank);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Level: Hard");
		primaryStage.setScene(new Scene(ss.createContent(b)));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
	    });
	}


	public void solverButtonClicked(ActionEvent event) throws IOException {
		Board b = new Board();
		SolverScene ss = new SolverScene();

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Solver");
		primaryStage.setScene(new Scene(ss.createContent(b)));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void toplistButtonClicked(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/view/ToplistView.fxml"));
		Scene rootScene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setTitle("We are the Champions!!");
		primaryStage.setScene(rootScene);
		primaryStage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}

	/**
	 * Generates a valid Sudoku board with the given number of blanks.
	 * 
	 * @param blanks number of cells to be emptied
	 * @return 	     the generated board
	 */
	public Board generateBoard(int blanks) {
		Board board = new Board();
		board.setValue((int) (Math.random() * 9), (int) (Math.random() * 9), (int) (Math.random() * 9 + 1));

		Solver slv = new Solver();
		slv.solveBoardAsc(board);

		ShuffleBoard sb = new ShuffleBoard();
		sb.shuffleBoard(board);

		boardFull = new Board(board);

		EreaseValues ev = new EreaseValues();
		ev.shuffleIndexes();
		ev.erease(board, blanks);

		boardBlank = new Board(board);
		
		return board;
	}
}
