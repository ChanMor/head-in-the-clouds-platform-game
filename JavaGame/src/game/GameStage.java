package game;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;
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
	private MediaPlayer bgvideoPlayer;
	private MediaView bg_vid;
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
		this.stage.setTitle("Head in the Clouds");
		this.stage.setResizable(false);
		stage.getIcons().add(new Image("file:src/images/icon.PNG"));
		
		this.initMain();
		this.initAbout();
		this.initTutorial();
		sendToMain();
		this.stage.show();
	}
	
	private void sendToMain()
	{	
		bgvideoPlayer.setMute(false);
		this.stage.setScene(this.mainScene);
	}
	
	private void sendToPlay()
	{
		bgvideoPlayer.setMute(true);
		this.initPlay();
		this.stage.setScene(this.playScene);
	}
	
	private void sendToAbout()
	{
		bgvideoPlayer.setMute(true);
		this.stage.setScene(this.aboutScene);
	}
	
	private void sendToTutorial()
	{
		bgvideoPlayer.setMute(true);
		this.stage.setScene(this.tutorialScene);
	}
	
	private void setBackground(String url)
	{
		Media videoFile = new Media(new File(url).toURI().toString());
	    
		bgvideoPlayer = new MediaPlayer(videoFile);
		bgvideoPlayer.setAutoPlay(true);
		bgvideoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		
		this.bg_vid = new MediaView(bgvideoPlayer);
	
		bg_vid.setFitHeight(GAME_HEIGHT);
		bg_vid.setFitWidth(GAME_WIDTH);
		
		bg_vid.setPreserveRatio(true);
	}
	
	private void initMain() 
	{
	    StackPane root = new StackPane();
		
	    setBackground("src/media/bg_main.mp4");
	    
	    AudioClip bgm = new AudioClip("file:src/media/audio_bgmdraft.mp3");
	    bgm.setVolume(0.50);
	    bgm.setCycleCount(AudioClip.INDEFINITE);
	    bgm.play();
	    
		
	    // Create buttons
	    ImageView playImageView = createImageView("file:src/images/btn_play.png", 350, 80);
	    playImageView.setOnMouseClicked(event -> sendToPlay());

	    ImageView aboutImageView = createImageView("file:src/images/btn_about.png", 170, 65);
	    aboutImageView.setOnMouseClicked(event -> sendToAbout());

	    ImageView tutorialImageView = createImageView("file:src/images/btn_tutorial.png", 170, 65);
	    tutorialImageView.setOnMouseClicked(event -> sendToTutorial());

	    // Create an HBox for "About" and "Tutorial" buttons
	    HBox aboutTutorialHBox = createHBox(10, aboutImageView, tutorialImageView, Pos.CENTER);

	    // Create a VBox to organize buttons
	    VBox buttonVBox = createVBox(10, playImageView, aboutTutorialHBox, Pos.CENTER); // Set vertical spacing between buttons
	    buttonVBox.setTranslateY(180);
	    
	    // Add background image and buttons to StackPane
	    root.getChildren().addAll(this.bg_vid, buttonVBox);
	    
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
	
	private VBox createVBox(int spacing, ImageView btn1, VBox btn2, Pos alignment) {
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
