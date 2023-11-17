package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.print("Hi Franz!");
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hello world!");
		System.out.println("Hi axel :>!");
		System.out.println("pepe");
		for (int i = 0; i<5; i++) {
			System.out.println("hotdog");
			System.out.println("ni franz");
		}
<<<<<<< HEAD
		//NEW LAGAY
		//hi i am here
		//pepe mabango masarap
=======
	
		//NEW LAGAY
		//hi i am here
		//pepe mabango masarap
<<<<<<< HEAD
		//hihihhih
=======
		//HATDOG
>>>>>>> origin/main
>>>>>>> origin/main
	}
}
