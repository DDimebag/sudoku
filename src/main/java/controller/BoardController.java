package controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.Main;
import model.Board;
import model.Solver;
import model.TimerClass;
import toplist.Toplist;
import view.SolverScene;
import view.SudokuScene;
import view.SudokuScene.Tile;

/**
 * Controller Class for the Easy Peasy, Easy, Hard and Solver options.
 */
public class BoardController {
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
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
	

	public void solveButtonPushed(ActionEvent event) throws IOException {
		logger.info("solved value: " + Main.solved);
		if(Main.solved) {
			;

		}
		else {
			Solver solver = new Solver();
			SolverScene solverScene = new SolverScene();
					
			Board tempBoard = new Board(SolverScene.solvableBoard);
			
			solverScene.setIsRed(tempBoard.boardToBool());
			
			
			if(solver.isNoConflictsOnBoard(tempBoard) && !solver.isMultipleSolution(tempBoard)) {
				logger.info("Solving the Board...");
				solver.solveBoardAsc(tempBoard);
				SolverScene.solvableBoard = tempBoard;
				
				//Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				Main.primaryStage.setScene(new Scene(solverScene.createContent(tempBoard)));
				Main.primaryStage.show();
		        Main.solved = true;
			}
			else {
					solverScene.notSolvableAlert();
					logger.warn("Board is not solvable");
				}
	    }
	}

	public void tileClicked(MouseEvent event, Tile tile, TimerClass tc) throws IOException {
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

		if (Board.isEqual(RootController.boardFull, SudokuScene.modifiedBoard)) {
			SudokuScene ss = new SudokuScene(tc);
			solveTime = tc.getSecondsPassed();
			tc.timer.cancel();
			ss.showAlert();

			Toplist tl = new Toplist();
			logger.trace("current time: " + solveTime);
			logger.trace("level id: " + Main.level);

			levelBestTime = tl.getTimeById(Main.level);
			
			logger.trace("best time: " + levelBestTime);
			
			if (solveTime < levelBestTime || levelBestTime == 0) {
				logger.trace("new record");
				tl.updateTimeById(Main.level, solveTime);
				Parent root = FXMLLoader.load(getClass().getResource("/view/NameInputView.fxml"));
				Scene rootScene = new Scene(root);
				Main.primaryStage.setScene(rootScene);

				Main.primaryStage.setTitle("Oh, hi Mark!");
				Main.primaryStage.setResizable(false);
				Main.primaryStage.show();
			}
			else {
				Parent root = FXMLLoader.load(getClass().getResource("/view/RootView.fxml"));
				Scene rootScene = new Scene(root);
				Main.primaryStage.setScene(rootScene);

				Main.primaryStage.setTitle("Sudoku for Progtech");
				Main.primaryStage.setResizable(false);
				Main.primaryStage.show();
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
		logger.trace("tile text: " + tile.text.getText());
	}
}
