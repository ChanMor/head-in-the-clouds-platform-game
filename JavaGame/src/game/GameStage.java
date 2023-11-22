package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameStage 
{
	
	public static int GAME_WIDTH = 480;
	public static int GAME_HEIGHT = 720;
	
	private Stage stage;
	private Scene splashScene;
	
	private Scene mainScene;
	private Scene tutorialScene;
	private Scene aboutScene;
	private Scene gameScene;
	
	private Group root;
	private Canvas canvas;
	
	public GameStage()
	{
		this.root = new Group();
		this.splashScene = new Scene(root, GameStage.GAME_WIDTH,GameStage.GAME_HEIGHT);
		this.canvas = new Canvas(GameStage.GAME_WIDTH,GameStage.GAME_HEIGHT);
	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
		this.stage.setTitle("Head in the Clouds");
		this.stage.setResizable(false);
		
		sendToMain();
		this.stage.show();
	}
	
	private void sendToMain()
	{
		this.initMain();
		this.stage.setScene(this.mainScene);
	}
	
	private void sendToAbout()
	{
		this.initAbout();
		this.stage.setScene(this.aboutScene);
	}
	
	private void sendToTutorial()
	{
		this.initTutorial();
		this.stage.setScene(this.tutorialScene);
	}
	
	private void initMain()
	{
	    Image bg = new Image("file:src/images/bg_main.png");
	    
	    StackPane root = new StackPane();
	    root.getChildren().addAll(this.createCanvas(bg));
	    
	    Button b1 = new Button("About");
	    b1.setOnMouseClicked(event -> sendToAbout());
	    
	    Button b2 = new Button("Tutorial");
	    b2.setOnMouseClicked(event -> sendToTutorial());
	    
	    HBox hbox = new HBox();
	    hbox.getChildren().addAll(b1, b2);
	    hbox.setSpacing(10); // set the spacing between buttons
	    hbox.setAlignment(Pos.BOTTOM_CENTER); // align the buttons to the bottom center
	    
	    root.getChildren().add(hbox);
	    
	    this.mainScene = new Scene(root); 
	}
	
	private void initAbout()
	{
		Image bg = new Image("file:src/images/bg_about.jpg");
		
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(bg),this.createVBox());
        this.aboutScene = new Scene(root);
        
        Button b1 = new Button("Back");
        b1.setOnMouseClicked(event -> sendToMain());
        root.getChildren().add(b1);
	}
	
	private void initTutorial()
	{
		Image bg = new Image("file:src/images/bg_tutorial.jpg");
		
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(bg),this.createVBox());
        this.tutorialScene = new Scene(root);
        
        Button b1 = new Button("Back");
        b1.setOnMouseClicked(event -> sendToMain());
        root.getChildren().add(b1);
	}
	
	private Canvas createCanvas(Image bg) {
    	Canvas canvas = new Canvas(GameStage.GAME_WIDTH,GameStage.GAME_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.drawImage(bg, 0, 0);
        return canvas;
    }
	
	private VBox createVBox() {
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);        
        return vbox;
    }
}
