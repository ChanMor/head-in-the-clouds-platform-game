package cloudLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameStage {
    private Canvas canvas;
    private Stage stage;
    private Scene scene;
    private GraphicsContext gc;
    private Group root;

    private Image cloud;

    private Random rand;

    private int rowgen[][];
    private final double VERTICAL_SPACING = 135.0; // Adjust this value for the desired vertical spacing
    private int currentRowIndex = 0; // Keep track of the current row index
    private Timeline timeline;

    public GameStage() {
        this.root = new Group();
        this.scene = new Scene(root, 480, 720, Color.SKYBLUE);
        this.canvas = new Canvas(480, 720);
        this.gc = canvas.getGraphicsContext2D();

        this.rand = new Random();

        this.cloud = new Image("file:src/images/cloud.PNG", 100, 30, false, false);

        this.rowgen = new int[6][5];

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    gc.clearRect(0, 0, 480, 720);
                    generateCloudsRow();
                }
            }
        });

        // Initial cloud generation
        for (int i = 0; i < 5; i++) {
            generateCloudsRow();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;

        this.addComponents();
        this.stage.setScene(this.scene);

        this.root.getChildren().add(canvas);
        this.stage.show();
    }

    private void addComponents() {
        gc.clearRect(0, 0, 480, 720);

        // Initial cloud generation
        generateCloudsRow();
    }

    public void generateCloudsRow() {
        int numClouds = rand.nextInt(4) + 1;

        // Shift the array, removing the last row
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                rowgen[i][j] = rowgen[i - 1][j];
            }
        }

     // Generate new row with random positions
        List<Integer> positions = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            positions.add(j);
        }
        Collections.shuffle(positions);

        for (int j = 0; j < 5; j++) {
            rowgen[0][positions.get(j)] = (j < numClouds) ? 1 : 0;
        }


        // Adjust cloud heights and draw
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (rowgen[i][j] == 1) {
                    double x = j * 100; // Adjust based on cloud image size
                    double y = i * VERTICAL_SPACING;
                    gc.drawImage(cloud, x, y);
                }
            }
        }
    }
}