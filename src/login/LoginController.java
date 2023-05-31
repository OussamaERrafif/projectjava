package login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import admin.*;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("login") && password.equals("login")) {
        	try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/admin.fxml"));
                BorderPane root = loader.load();
                admin admin = loader.getController();

                Stage stage = new Stage();
                stage.setTitle("Welcome to the Pharmacy App");
                stage.setScene(new Scene(root));
                stage.show();

                // Close the login window
                usernameField.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }        } else {
            // Invalid login, show an error message
            showAlert("Error", "Invalid username or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
