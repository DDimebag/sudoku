package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.BoardController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Board;

public class SolverView {
	
		public static Board solvableBoard = new Board();
		BoardController ctrl = new BoardController();
		List<Tile> tiles = new ArrayList<>();
		private boolean[] isRed = new boolean[81]; 
		private static final String IDLE_BUTTON_STYLE = "-fx-background-color: white; -fx-background-radius: 0px";
	    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-weight: bold";
		
		public Parent createContent(Board b) {
			Pane root = new Pane();
			root.setPrefSize(600, 600);

			Button backButton = new Button("Back");
			backButton.setLayoutX(75);
			backButton.setLayoutY(25);
			
			backButton.setStyle(IDLE_BUTTON_STYLE);
			backButton.setOnMouseEntered(e -> backButton.setStyle(HOVERED_BUTTON_STYLE));
			backButton.setOnMouseExited(e -> backButton.setStyle(IDLE_BUTTON_STYLE));
			
			
			backButton.setOnAction(e -> {
				try {
					ctrl.backButtonClicked(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			root.getChildren().add(backButton);
			
			Button solveButton = new Button("Solve");
			solveButton.setLayoutX(470);
			solveButton.setLayoutY(25);
			
			solveButton.setStyle(IDLE_BUTTON_STYLE);
			solveButton.setOnMouseEntered(e -> solveButton.setStyle(HOVERED_BUTTON_STYLE));
			solveButton.setOnMouseExited(e -> solveButton.setStyle(IDLE_BUTTON_STYLE));
			
			//TODO
			solveButton.setOnAction(e -> {
				try {
					ctrl.solveButtonPushed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			root.getChildren().add(solveButton);
			
			for (int row = 0; row < 9; row++)
				for (int col = 0; col < 9; col++)
					tiles.add(new Tile(solvableBoard.getValue(row, col), row * 9 + col));

			for (int i = 0; i < tiles.size(); i++) {
				Tile tile = tiles.get(i);
				tile.setTranslateX((50 * (i % 9)) + 75);
				tile.setTranslateY((50 * (i / 9)) + 75);
				root.getChildren().add(tile);
			}

			Line line = new Line(225, 78, 225, 523);
			Line line2 = new Line(375, 78, 375, 523);
			Line line3 = new Line(78, 225, 523, 225);
			Line line4 = new Line(78, 375, 523, 375);

			line.setStrokeWidth(5);
			line2.setStrokeWidth(5);
			line3.setStrokeWidth(5);
			line4.setStrokeWidth(5);

			root.getChildren().add(line);
			root.getChildren().add(line2);
			root.getChildren().add(line3);
			root.getChildren().add(line4);

			return root;
		}

		public class Tile extends StackPane {
			public Text text = new Text();
			public int index;

			public Tile(Integer value, int index) {
				this.index = index;

				Rectangle border = new Rectangle(50, 50);
				border.setFill(null);
				border.setStroke(Color.BLACK);

				if (value == 0) {
					text.setText("");
					text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 30));
					text.setFill(Color.RED);
				} 
				else {
					text.setText(Integer.toString(value));
					text.setFont(Font.font(30));
				}
				
				if(isRed[this.index]){
					text.setText(Integer.toString(value));
					text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 30));
					text.setFill(Color.RED);
				} 

				setAlignment(Pos.CENTER);
				getChildren().addAll(border, text);

				this.setOnMouseClicked(e -> ctrl.tileClicked2(e, this));
			}
		}

		public void notSolvableAlert() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("NOOOOO");
			alert.setHeaderText("Something went wrong!");
			alert.setContentText("This board is not a valid Sudoku board!");
			alert.showAndWait();
		}
		
		public void setIsRed(boolean[] array) {
			this.isRed = array;
		}
}
