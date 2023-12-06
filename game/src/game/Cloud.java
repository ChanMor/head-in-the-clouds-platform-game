package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cloud {
    private Canvas canvas;
    private GraphicsContext gc;

    private Image cloud;

    private Random rand;

    private int rowgen[][];
    private final double VERTICAL_SPACING = 135.0;
    private double incrementalMove = 1.0; 
    public Cloud(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.rand = new Random();

        this.cloud = new Image("file:src/images/cloud.PNG", 100, 30, false, false);
        this.rowgen = new int[6][5];

        initialize();
  
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            generateCloudsRow();
        }
        
        addComponents();
    }
    
    void addComponents() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        generateCloudsRow();
    }

    void moveClouds() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (rowgen[i][j] == 1) {
                    double x = j * 100;
                    double y = i * VERTICAL_SPACING;
                    y -= incrementalMove; 
                    gc.drawImage(cloud, x, y);
                }
            }
        }

        generateCloudsRow(); 
    }

    public void generateCloudsRow() {
        int numClouds = rand.nextInt(4) + 1;

        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                rowgen[i][j] = rowgen[i - 1][j];
            }
        }

        List<Integer> positions = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            positions.add(j);
        }
        Collections.shuffle(positions);

        for (int j = 0; j < 5; j++) {
            rowgen[0][positions.get(j)] = (j < numClouds) ? 1 : 0;
        }
    }
}