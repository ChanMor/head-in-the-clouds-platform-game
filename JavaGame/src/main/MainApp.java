package main;

import game.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
	public void start(Stage stage) throws Exception {
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
