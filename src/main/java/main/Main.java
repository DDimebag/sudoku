package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static Stage primaryStage;
	
	public static boolean solved = false;
	public static int level = 0;

	public void changeScene() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/view/Root.fxml"));
		Scene rootScene = new Scene(root);
		primaryStage.setScene(rootScene);

		primaryStage.setTitle("Sudoku for Progtech");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			Main.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/view/RootView.fxml"));
			Scene rootScene = new Scene(root);
			//primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/target/resources/icon.png")));
			Main.primaryStage.setScene(rootScene);

			Main.primaryStage.setTitle("Sudoku for Progtech");
			Main.primaryStage.setResizable(false);
			Main.primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
}