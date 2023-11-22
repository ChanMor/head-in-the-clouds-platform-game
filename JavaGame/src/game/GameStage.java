package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;


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
	    ImageView playImageView = createImageView("file:src/images/play_button.png", 280, 80);
	    playImageView.setOnMouseClicked(event -> sendToPlay());

	    ImageView aboutImageView = createImageView("file:src/images/about_button.png", 130, 60);
	    aboutImageView.setOnMouseClicked(event -> sendToAbout());

	    ImageView tutorialImageView = createImageView("file:src/images/btn_tutorial.png", 130, 60);
	    tutorialImageView.setOnMouseClicked(event -> sendToTutorial());
	    tutorialImageView.setFitWidth(130); // Set the tutorial button width
	    tutorialImageView.setFitHeight(60); // Set the tutorial button height

	    // Create an HBox for "About" and "Tutorial" buttons
	    HBox aboutTutorialHBox = createHBox(-10, aboutImageView, tutorialImageView, Pos.CENTER);

	    // Create a VBox to organize buttons
	    VBox buttonVBox = createVBox(-5, playImageView, aboutTutorialHBox, Pos.CENTER); // Set vertical spacing between buttons
	    buttonVBox.setTranslateY(140);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);

	    
	    this.mainScene = new Scene(root);
    }

    private ImageView createImageView(String imageUrl, int a, int b) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(a); // Adjust the size as needed
        imageView.setFitHeight(b); // Adjust the size as needed
        imageView.setPreserveRatio(false);
        
        // Add drop shadow effect on hover
        DropShadow dropShadow = new DropShadow();
        imageView.setOnMouseEntered(event -> imageView.setEffect(dropShadow));
        imageView.setOnMouseExited(event -> imageView.setEffect(null));
        
        return imageView;
    }
	
	private HBox createHBox(int spacing, ImageView btn1, ImageView btn2, Pos alignment) {
		HBox newHBox = new HBox(spacing);
		newHBox.getChildren().addAll(btn1, btn2);
		newHBox.setAlignment(alignment);
		return newHBox;
	}
	
	private VBox createVBox(int spacing, ImageView btn1, HBox btn2, Pos alignment) {
		VBox newHBox = new VBox(spacing);
		newHBox.getChildren().addAll(btn1, btn2);
		newHBox.setAlignment(alignment);
		return newHBox;
	}

	private void initPlay()
	{
	    Image bg = new Image("file:src/images/bg_play.jpg");

	    StackPane root = new StackPane();
	    ImageView imageView = new ImageView(bg);
	    imageView.setFitWidth(GAME_WIDTH);
	    imageView.setFitHeight(GAME_HEIGHT);

	    this.playScene = new Scene(root);
	    
	    ImageView backImageView = createImageView("file:src/images/btn_back.png", 130, 60);
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

	    ImageView backImageView = createImageView("file:src/images/btn_back.png", 130, 60);
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

	    
	    ImageView backImageView = createImageView("file:src/images/btn_back.png", 130, 60);
	    backImageView.setOnMouseClicked(event -> sendToMain());

	    // Create a VBox to center the buttons vertically
	    VBox buttonVBox = new VBox();
	    buttonVBox.setAlignment(Pos.CENTER);
	    buttonVBox.getChildren().addAll(backImageView);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(imageView, buttonVBox);
	}
	
}
