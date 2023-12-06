package game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;


public class Play extends Scene {

    private static final String BACKGROUND_IMAGE_URL = "file:src/images/bg_play.jpg";
    private static final String BGM_AUDIO_URL = "file:src/media/audio_bgmdraft.mp3";

    private StackPane root;
    private Canvas canvas;

    
    public Play(int width, int height) {
        super(new StackPane(), width, height); // Set width and height as per your requirements
        this.root = (StackPane) this.getRoot();
        initialize(width, height);
    }

    private void initialize(int width, int height) {
        setBackground(BACKGROUND_IMAGE_URL);
        playBackgroundMusic(BGM_AUDIO_URL);


        this.canvas = new Canvas(width, height);
        
        new Character(0, height - 80, width, height, canvas, this);

        this.root.getChildren().add(canvas);

        // Set the canvas size to match the scene size
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
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
