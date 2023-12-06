package game;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainMenu extends Scene {

    private static final String BACKGROUND_VIDEO_URL = "src/media/bg_main.mp4";
    private static final String BGM_AUDIO_URL = "file:src/media/audio_bgmdraft.mp3";

    private StackPane root;
    
    private Runnable playClickedHandler;

    public MainMenu(int width, int heigth) {
        super(new StackPane(), width, heigth); // Set width and height as per your requirements
        root = (StackPane) this.getRoot();
        initialize();
    }

    private void initialize() {
        setBackground(BACKGROUND_VIDEO_URL);
        playBackgroundMusic(BGM_AUDIO_URL);

        ImageView playButton = createButton("file:src/images/btn_play.png", 350, 80, this::sendToPlay);
        ImageView aboutButton = createButton("file:src/images/btn_about.png", 170, 65, () -> {});
        ImageView tutorialButton = createButton("file:src/images/btn_tutorial.png", 170, 65, () -> {});

        HBox aboutTutorialHBox = createHBox(10, aboutButton, tutorialButton, Pos.CENTER);
        VBox buttonVBox = createVBox(10, playButton, aboutTutorialHBox, Pos.CENTER);
        buttonVBox.setTranslateY(180);

        root.getChildren().addAll(buttonVBox);
    }

    private void setBackground(String videoUrl) {
        Media backgroundVideo = new Media(new File(videoUrl).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(backgroundVideo);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        MediaView mediaView = new MediaView(mediaPlayer);
        root.getChildren().add(mediaView);
    }

    private void playBackgroundMusic(String audioUrl) {
        AudioClip bgm = new AudioClip(audioUrl);
        bgm.setVolume(0.65);
        bgm.setCycleCount(AudioClip.INDEFINITE);
        bgm.play();
    }

    private ImageView createButton(String imageUrl, int width, int height, Runnable action) {
        Image image = new Image(imageUrl);
        ImageView button = new ImageView(image);
        button.setFitWidth(width);
        button.setFitHeight(height);
        button.setPreserveRatio(false);

        DropShadow dropShadow = new DropShadow();
        button.setOnMouseEntered(event -> button.setEffect(dropShadow));
        button.setOnMouseExited(event -> button.setEffect(null));
        button.setOnMouseClicked(event -> action.run());

        return button;
    }

    private HBox createHBox(int spacing, ImageView btn1, ImageView btn2, Pos alignment) {
        HBox hBox = new HBox(spacing);
        hBox.getChildren().addAll(btn1, btn2);
        hBox.setAlignment(alignment);
        return hBox;
    }

    private VBox createVBox(int spacing, ImageView btn1, HBox btn2, Pos alignment) {
        VBox vBox = new VBox(spacing);
        vBox.getChildren().addAll(btn1, btn2);
        vBox.setAlignment(alignment);
        return vBox;
    }

    public void setOnPlayClicked(Runnable handler) {
        this.playClickedHandler = handler;
    }

    private void sendToPlay() {
        if (playClickedHandler != null) {
            playClickedHandler.run();
        }
    }


}
