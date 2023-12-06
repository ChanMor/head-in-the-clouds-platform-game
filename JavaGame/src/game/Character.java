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

    private int gameWidth;
    private int gameHeight;

    private double groundLevel;
    private boolean continuousJumping = false;

    public Character(double initialX, double initialY, int width, int height, Canvas canvas, Scene scene) {
        this.characterX = initialX;
        this.characterY = initialY;
        this.canvas = canvas;
        this.playScene = scene;

        this.gameWidth = width;
        this.gameHeight = height;
        this.groundLevel = this.gameHeight - 80;
        initialize(width, height);
    }

    private void initialize(int width, int height) {
        this.gc = this.canvas.getGraphicsContext2D();

        characterX = width / 2 - 40;
        characterY = height;

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

    private void moveCharacterLeft() {
        velocityX = -5;
    }

    private void moveCharacterRight() {
        velocityX = 5;
    }

    private void jump() {
        if (characterY == groundLevel) {
            velocityY = -15;
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
        } else if (characterX > gameWidth - 80) {
            characterX = gameWidth - 80;
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
        this.gc.clearRect(0, 0, gameWidth, gameHeight);
        Image characterImage = new Image("file:src/images/character.png", 80, 80, true, true);
        this.gc.drawImage(characterImage, characterX, characterY);
    }
}
