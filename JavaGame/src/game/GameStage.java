package game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameStage extends Application {

    public static int GAME_WIDTH = 480;
    public static int GAME_HEIGHT = 720;

    private Stage stage;
    private MainMenu mainMenu;
    private Play play;

    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.stage.setTitle("Head in the Clouds");
        this.stage.setResizable(false);
        this.stage.getIcons().add(new Image("file:src/images/icon.PNG")); 

        init_main();
        init_play();
        
        this.stage.show();
    }

    private void init_main() {
        this.mainMenu = new MainMenu(GAME_WIDTH, GAME_HEIGHT);
        stage.setScene(this.mainMenu);
    }
 
    
    private void init_play() {
        this.play = new Play(GAME_WIDTH, GAME_HEIGHT);
        this.mainMenu.setOnPlayClicked(() -> stage.setScene(play));
    }

    

    
}
