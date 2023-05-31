module projectjava {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	exports login to javafx.graphics;
	opens admin to javafx.fxml;
	exports admin;

}
