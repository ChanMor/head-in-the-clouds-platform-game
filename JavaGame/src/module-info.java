module FinalProject {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.media, javafx.fxml;
	opens game to javafx.graphics, javafx.media, javafx.fxml;
	opens main to javafx.graphics, javafx.media, javafx.fxml;
}
