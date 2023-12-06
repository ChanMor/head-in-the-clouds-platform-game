package main;

import game.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) throws Exception {
		GameStage gameStage = new GameStage();
		gameStage.start(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
