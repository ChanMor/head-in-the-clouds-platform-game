package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class GameTimer extends AnimationTimer {

		private GraphicsContext gc;
		private Scene theScene;
		
		GameTimer (GraphicsContext gc, Scene theScene)
		{
			this.gc = gc;
			this.theScene = theScene;
	
		}
		
		public void handle (long currentTime)
		{
			
		}
}
