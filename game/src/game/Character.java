package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Character {

    public static int JUMP_INTERVAL = 300;

    private Canvas canvas;
    private GraphicsContext gc;
    private Scene playScene;

    private double characterX;
    private double characterY;

    private double velocityY = 0;
    private double velocityX = 0;



    private double groundLevel;
    private boolean continuousJumping = false;

    public Character(double initialX, double initialY, Canvas canvas, Scene scene) {
        this.characterX = initialX;
        this.characterY = initialY;
        this.canvas = canvas;
        this.playScene = scene;


        this.groundLevel = canvas.getHeight() - 80;
        initialize();
    }

    private void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();

        characterX = canvas.getWidth() / 2 - 40;
        characterY = canvas.getHeight();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateCharacter()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline jumpTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            if (characterY == groundLevel) {
                jump();
            }
        }));
        jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        jumpTimeline.play();

        this.playScene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.LEFT) {
                moveCharacterLeft();
            } else if (code == KeyCode.RIGHT) {
                moveCharacterRight();
            } else if (!continuousJumping) {
                continuousJumping = true;
                jump();
            }
        });
    }

    
    double getCharacterY() {
    	return characterY;
    }
    
    void moveCharacterLeft() {
        velocityX = -5;
    }

    void moveCharacterRight() {
        velocityX = 5;
    }

    private void jump() {
        if (characterY == groundLevel) {
            velocityY = -14;
        }

        if (characterY > groundLevel) {
            this.playScene.setOnKeyPressed(event -> {
                KeyCode code = event.getCode();
                if (code == KeyCode.LEFT) {
                    moveCharacterLeft();
                } else if (code == KeyCode.RIGHT) {
                    moveCharacterRight();
                } else if (!continuousJumping) {
                    continuousJumping = true;
                    jump();
                }
            });
        }
    }

    private void updateCharacter() {
        characterX += velocityX;
        characterY += velocityY;

        velocityY += 0.5;

        if (characterX < 0) {
            characterX = 0;
            velocityX = 0;
        } else if (characterX > canvas.getWidth() - 80) {
            characterX = canvas.getWidth() - 80;
            velocityX = 0;
        }

        if (characterY >= groundLevel) {
            characterY = groundLevel;
            velocityY = 0;
            continuousJumping = false;
        }

        drawCharacter();
    }

    private void drawCharacter() {
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Image characterImage = new Image("file:src/images/character.png", 80, 80, true, true);
        this.gc.drawImage(characterImage, characterX, characterY);
    }
}
