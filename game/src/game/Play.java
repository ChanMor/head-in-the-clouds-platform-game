package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Play extends Scene {

    private static final String BACKGROUND_IMAGE_URL = "file:src/images/bg_play.jpg";
    private static final String BGM_AUDIO_URL = "file:src/media/audio_bgmdraft.mp3";

    private StackPane root;
    
    private Canvas characterCanvas;
    private Canvas cloudsCanvas;

    private Cloud clouds;
    private Character character;
    
    public Play(int width, int height) {
    	
        super(new StackPane(), width, height, Color.SKYBLUE); 
        
        this.root = (StackPane) this.getRoot();
        this.characterCanvas = new Canvas(width, height);
        this.cloudsCanvas = new Canvas(width, height);
        
        this.clouds =  new Cloud(cloudsCanvas);
        this.character = new Character(0, height - 80, characterCanvas, this);
        
        initialize(width, height);
    }

    private void initialize(int width, int height) {
        //setBackground(BACKGROUND_IMAGE_URL);
        playBackgroundMusic(BGM_AUDIO_URL);



        root.getChildren().addAll(cloudsCanvas, characterCanvas);
        
        setKeyEventHandler();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
        	System.out.println(character.getCharacterY());

            if (character.getCharacterY() == 640) {
            	System.out.println(character.getCharacterY());
                clouds.moveClouds();
            }
        }));
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    
    
    private void setKeyEventHandler() {
        this.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.LEFT) {
                 character.moveCharacterLeft();
            } else if (code == KeyCode.RIGHT) {
                character.moveCharacterRight();
            }
        });
    }
    
    private void setBackground(String imageUrl) {
        Image backgroundImage = new Image(imageUrl);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(getWidth());
        backgroundImageView.setFitHeight(getHeight());

        root.getChildren().add(backgroundImageView);
    }

    private void playBackgroundMusic(String audioUrl) {
        AudioClip bgm = new AudioClip(audioUrl);
        bgm.setVolume(0.65);
        bgm.setCycleCount(AudioClip.INDEFINITE);
        bgm.play();
    }
}
