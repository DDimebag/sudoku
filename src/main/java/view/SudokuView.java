package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.BoardController;
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
import model.TimerClass;

public class SudokuView {
	
	public static Board modifiedBoard;
	BoardController ctrl = new BoardController();
	TimerClass tc;
	private static final String IDLE_BUTTON_STYLE = "-fx-background-color: white; -fx-background-radius: 0px";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-weight: bold";

	public SudokuView(TimerClass timer) {
		tc = timer;
	}
	
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
				tc.timer.cancel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		root.getChildren().add(backButton);
		
		tc.timerText.setX(500);
		tc.timerText.setY(50);
		tc.timerText.setFill(Color.GREEN);
		tc.timerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
		tc.startTimer();
		root.getChildren().add(tc.timerText);

		List<Tile> tiles = new ArrayList<>();

		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				tiles.add(new Tile(b.getValue(row, col), row * 9 + col));

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
			if(value == 0)
				border.setFill(Color.LIGHTCYAN);
			else
				border.setFill(null);
			border.setStroke(Color.BLACK);

			if (value == 0) {
				text.setText("");
				text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 30));
				text.setFill(Color.RED);
			} else {
				text.setText(Integer.toString(value));
				text.setFont(Font.font(30));
			}

			getChildren().addAll(border, text);

			this.setOnMouseClicked(e -> {
				try {
					ctrl.tileClicked(e, this, tc);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		}	
	}
	
	public void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Congratulations");
		alert.setHeaderText("You're awesome!");
		alert.setContentText("You've managed to solve this puzzle in: " + tc.getSecondsPassed() + " seconds");
		alert.showAndWait();
	}
}