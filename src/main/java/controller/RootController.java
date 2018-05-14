package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import main.Main;
import model.Board;
import model.EreaseValues;
import model.ShuffleBoard;
import model.Solver;
import model.TimerClass;
import view.SolverView;
import view.SudokuView;

/**
 * Controller for the Main Menu.
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
		SudokuView sv = new SudokuView(timer);
		SudokuView.modifiedBoard = new Board(boardBlank);
		
		Main.primaryStage.setTitle("Level: Easy Peasy");
		Main.primaryStage.setScene(new Scene(sv.createContent(b)));
		Main.primaryStage.setResizable(false);
		Main.primaryStage.show();
		Main.primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
			System.exit(0);
	    });
	}

	public void easyButtonClicked(ActionEvent event) throws IOException {
		int blanks = 30;
		Main.level = 2;
		Board b = generateBoard(blanks);
		TimerClass timer = new TimerClass();
		SudokuView sv = new SudokuView(timer);
		SudokuView.modifiedBoard = new Board(boardBlank);

		Main.primaryStage.setTitle("Level: Easy");
		Main.primaryStage.setScene(new Scene(sv.createContent(b)));
		Main.primaryStage.setResizable(false);
		Main.primaryStage.show();
		Main.primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
			System.exit(0);
	    });
	}

	public void hardButtonClicked(ActionEvent event) throws IOException {
		int blanks = 40;
		Main.level = 3;
		Board b = generateBoard(blanks);
		TimerClass timer = new TimerClass();
		SudokuView sv = new SudokuView(timer);
		SudokuView.modifiedBoard = new Board(boardBlank);

		Main.primaryStage.setTitle("Level: Hard");
		Main.primaryStage.setScene(new Scene(sv.createContent(b)));
		Main.primaryStage.setResizable(false);
		Main.primaryStage.show();
		Main.primaryStage.setOnCloseRequest((WindowEvent event1) -> {
			timer.timer.cancel();
			System.exit(0);
	    });
	}


	public void solverButtonClicked(ActionEvent event) throws IOException {
		Board b = new Board();
		SolverView sv = new SolverView();

		Main.primaryStage.setTitle("Solver");
		Main.primaryStage.setScene(new Scene(sv.createContent(b)));
		Main.primaryStage.setResizable(false);
		Main.primaryStage.show();
	}
	
	public void toplistButtonClicked(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/view/ToplistView.fxml"));
		Scene rootScene = new Scene(root);
		
		Main.primaryStage.setTitle("We are the Champions!!");
		Main.primaryStage.setScene(rootScene);
		Main.primaryStage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
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
