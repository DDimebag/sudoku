package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import model.Board;
import model.Solver;
import model.TimerClass;
import toplist.Toplist;
import view.SolverScene;
import view.SudokuScene;
import view.SudokuScene.Tile;

/**
 * Controller for the Easy Peasy, Easy, Hard and Solver options.
 */
public class BoardController {
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		Main.solved = false;
		Board board = new Board();
		SolverScene.solvableBoard = new Board(board);

		Parent root = FXMLLoader.load(getClass().getResource("/view/Root.fxml"));
		Scene rootScene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Sudoku for Progtech");
		primaryStage.setScene(rootScene);
		primaryStage.show();
    }
	

	public void solveButtonPushed(ActionEvent event) throws IOException {
		System.out.println(Main.solved);
		if(Main.solved) {
			System.out.println("solveeeed");

		}
		else {
			Solver solver = new Solver();
			SolverScene solverScene = new SolverScene();
			
			System.out.println("solveButton");
					
			Board tempBoard = new Board(SolverScene.solvableBoard);
			
			solverScene.setIsRed(tempBoard.isZero(tempBoard));
			
			tempBoard.printBoard();
			
			if(solver.isValidBoard(tempBoard) && !solver.isMultipleSolution(tempBoard)) {
				System.out.println("megold");
				solver.solveBoardAsc(tempBoard);
				SolverScene.solvableBoard = tempBoard;
				SolverScene.solvableBoard.printBoard();
				
				Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    	primaryStage.setScene(new Scene(solverScene.createContent(tempBoard)));
		        primaryStage.show();
		        Main.solved = true;
			}
			else {
					solverScene.notSolvableAlert();
					System.out.println("not solvable");
				}
	    }
	}

	public void tileClicked(MouseEvent event, Tile tile, TimerClass tc) {
		MouseButton button = event.getButton();
		int value = 0;
		int solveTime = 0;
		int levelBestTime = 0;

		if (tile.text.getFill() != Color.BLACK) {
			if (tile.text.getText().equals("") && button == MouseButton.PRIMARY) {
				tile.text.setText(Integer.toString(++value));
				SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, 1);
			}

			else if (tile.text.getText().equals("9") && button == MouseButton.PRIMARY) {
				tile.text.setText("");
				value = 0;
				SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, value);
			}
			else {
				if(button == MouseButton.PRIMARY) {
					value = Integer.parseInt(tile.text.getText());
					tile.text.setText(Integer.toString(++value));
					SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, value);
				}
			}
		}
		
		if (tile.text.getFill() != Color.BLACK) {
			if (tile.text.getText().equals("") && button == MouseButton.SECONDARY) {
				value = 9;
				tile.text.setText(Integer.toString(value));
				SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, value);
			}

			else if (tile.text.getText().equals("1") && button == MouseButton.SECONDARY) {
				value = 0;
				tile.text.setText("");
				SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, value);
			}
			else {
				if(button == MouseButton.SECONDARY) {
					value = Integer.parseInt(tile.text.getText());
					tile.text.setText(Integer.toString(--value));
					SudokuScene.modifiedBoard.setValue(tile.index / 9, tile.index % 9, value);
				}
			}
		}

		SudokuScene.modifiedBoard.printBoard();
		System.out.println("full:");
		RootController.boardFull.printBoard();
		System.out.println();
		if (Board.isEqual(RootController.boardFull, SudokuScene.modifiedBoard)) {
			SudokuScene ss = new SudokuScene(tc);
			solveTime = tc.getSecondsPassed();
			tc.timer.cancel();
			ss.showAlert();

			Toplist tl = new Toplist();
			tl.createToplistXML();
			System.out.println(solveTime);
			System.out.println(Main.level);

			levelBestTime = tl.getTimeById(Main.level);
			System.out.println(levelBestTime);
			if (solveTime < levelBestTime || levelBestTime == 0) {
				System.out.println("LEEEL");
				tl.updateToplistXML(Main.level, solveTime);
			}
				
		}
	}


	public void tileClicked2(MouseEvent event, view.SolverScene.Tile tile) {
		MouseButton button = event.getButton();
		int value = 0;

		if (tile.text.getFill() != Color.BLACK) {
			if (tile.text.getText().equals("") && button == MouseButton.PRIMARY) {
				tile.text.setText(Integer.toString(++value));
				SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, 1);
			}

			else if (tile.text.getText().equals("9") && button == MouseButton.PRIMARY) {
				tile.text.setText("");
				value = 0;
				SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, value);
			}
			else {
				if (button == MouseButton.PRIMARY) {
					value = Integer.parseInt(tile.text.getText());
					tile.text.setText(Integer.toString(++value));
					SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, value);
				}
			}
		}
		
		if (tile.text.getFill() != Color.BLACK) {
			if (tile.text.getText().equals("") && button == MouseButton.SECONDARY) {
				value = 9;
				tile.text.setText(Integer.toString(value));
				SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, value);
			}

			else if (tile.text.getText().equals("1") && button == MouseButton.SECONDARY) {
				value = 0;
				tile.text.setText("");
				SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, value);
			}
			else {
				if (button==MouseButton.SECONDARY) {
					value = Integer.parseInt(tile.text.getText());
					tile.text.setText(Integer.toString(--value));
					SolverScene.solvableBoard.setValue(tile.index / 9, tile.index % 9, value);
				}
			}
		}
		
		System.out.println(tile.text);
		SolverScene.solvableBoard.printBoard();
		System.out.println();
	}
}
