module PlayerMovementLogic {

	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.media, javafx.fxml;
	opens game to javafx.graphics, javafx.media, javafx.fxml;
	opens main to javafx.graphics, javafx.media, javafx.fxml;

}
