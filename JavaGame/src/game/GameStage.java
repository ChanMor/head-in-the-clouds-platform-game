package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class GameStage 
{
	
	public static int GAME_WIDTH = 480;
	public static int GAME_HEIGHT = 720;
	
	private Stage stage;
	
	private Scene mainScene;
	private Scene tutorialScene;
	private Scene aboutScene;
	private Scene playScene;
	
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
	
	private void sendToPlay()
	{
		this.initPlay();
		this.stage.setScene(this.playScene);
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
		Image bg = new Image("file:src/images/bg_main.jpg");

	    StackPane root = new StackPane();

	    // Use ImageView to stretch the background image
	    ImageView imageView = new ImageView(bg);
	    imageView.setFitWidth(GAME_WIDTH);
	    imageView.setFitHeight(GAME_HEIGHT);

	    // Create buttons
	    ImageView playImageView = createImageView("file:src/images/play_button.png");
	    playImageView.setOnMouseClicked(event -> sendToPlay());

	    ImageView aboutImageView = createImageView("file:src/images/about_button.png");
	    aboutImageView.setOnMouseClicked(event -> sendToAbout());

	    ImageView tutorialImageView = createImageView("file:src/images/tutorial_button.png");
	    tutorialImageView.setOnMouseClicked(event -> sendToTutorial());

	    // Create a VBox to center the buttons vertically
	    VBox buttonVBox = new VBox();
	    buttonVBox.setAlignment(Pos.CENTER);
	    buttonVBox.getChildren().addAll(playImageView, aboutImageView, tutorialImageView);

	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);
	    
	    this.mainScene = new Scene(root);
    }

    private ImageView createImageView(String imageUrl) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Adjust the size as needed
        imageView.setPreserveRatio(true);
        return imageView;
    }

	private void initPlay()
	{
	    Image bg = new Image("file:src/images/bg_play.jpg");

	    StackPane root = new StackPane();
	    ImageView imageView = new ImageView(bg);
	    imageView.setFitWidth(GAME_WIDTH);
	    imageView.setFitHeight(GAME_HEIGHT);

	    this.playScene = new Scene(root);
	    
	    ImageView backImageView = createImageView("file:src/images/tutorial_button.png");
	    backImageView.setOnMouseClicked(event -> sendToMain());

	    // Create a VBox to center the buttons vertically
	    VBox buttonVBox = new VBox();
	    buttonVBox.setAlignment(Pos.CENTER);
	    buttonVBox.getChildren().addAll(backImageView);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);
	}
    
	private void initAbout()
	{
	    Image bg = new Image("file:src/images/bg_about.jpg");

	    StackPane root = new StackPane();
	    ImageView imageView = new ImageView(bg);
	    imageView.setFitWidth(GAME_WIDTH);
	    imageView.setFitHeight(GAME_HEIGHT);

	    this.aboutScene = new Scene(root);

	    ImageView backImageView = createImageView("file:src/images/tutorial_button.png");
	    backImageView.setOnMouseClicked(event -> sendToMain());

	    // Create a VBox to center the buttons vertically
	    VBox buttonVBox = new VBox();
	    buttonVBox.setAlignment(Pos.CENTER);
	    buttonVBox.getChildren().addAll(backImageView);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);
	}
	
	private void initTutorial()
	{
	    Image bg = new Image("file:src/images/bg_tutorial.jpg");

	    StackPane root = new StackPane();
	    ImageView imageView = new ImageView(bg);
	    imageView.setFitWidth(GAME_WIDTH);
	    imageView.setFitHeight(GAME_HEIGHT);

	    this.tutorialScene = new Scene(root);

	    
	    ImageView backImageView = createImageView("file:src/images/tutorial_button.png");
	    backImageView.setOnMouseClicked(event -> sendToMain());

	    // Create a VBox to center the buttons vertically
	    VBox buttonVBox = new VBox();
	    buttonVBox.setAlignment(Pos.CENTER);
	    buttonVBox.getChildren().addAll(backImageView);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);
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
