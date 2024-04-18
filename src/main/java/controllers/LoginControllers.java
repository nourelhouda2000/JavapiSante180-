package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServices;

import java.io.IOException;

public class LoginControllers {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void handleLogin() throws IOException {
        String email = username.getText();
        String mdp = password.getText();

        UserServices userServices = new UserServices();
        boolean loginSuccess = userServices.loginUser(email, mdp);

        if (loginSuccess) {
            openRapportWindow();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect email or password.");
        }
    }
    private void openRapportWindow() throws IOException {
        // Load the Rendezvous.fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Rendezvous.fxml"));

        Parent root = loader.load();

        // Create a new scene
        Scene scene = new Scene(root);

        // Create a new stage and set the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Show the window
        stage.show();
    }





}
