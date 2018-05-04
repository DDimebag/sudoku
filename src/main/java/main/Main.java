package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static boolean solved = false;
	public static int level = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Root.fxml"));
			Scene rootScene = new Scene(root);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
			primaryStage.setTitle("Sudoku for Progtech");
			primaryStage.setScene(rootScene);
			primaryStage.setResizable(false);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
}