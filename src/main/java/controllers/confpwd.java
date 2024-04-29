package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServices; // Import the UserServices class

public class confpwd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField confirm_pwd;

    @FXML
    private TextField new_pwd;

    @FXML
    private Button newpwd_btn;

    private UserServices userServices; // Declare UserServices object

    // Setter method to inject UserServices
    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @FXML
    void handleNewpwd(ActionEvent event) {
        String newPassword = new_pwd.getText();
        String confirmPassword = confirm_pwd.getText();

        // Perform validation to ensure new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            // Display an error message or alert indicating password mismatch
            showAlert("Password Mismatch", "New password and confirm password do not match.");
            return;
        }

        // Assuming you have a way to obtain the user ID of the currently logged-in user
        int userId = getUserId(); // Replace getUserId() with the actual method to get the user ID

        // Check if UserServices is initialized
        if (userServices == null) {
            showAlert("Error", "UserServices is not initialized.");
            return;
        }

        // Call updateUserPwd method in UserServices with user ID and new password
        boolean success = userServices.updateUserPwd(userId, newPassword);

        if (success) {
            // Password updated successfully
            try {
                // Load the login screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent root = loader.load();

                // Create a new scene
                Scene scene = new Scene(root);

                // Get the current stage
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Set the new scene
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Handle FXML loading errors
            }
        } else {
            // Handle password update failure
            showAlert("Error", "Failed to update password.");
        }
    }

    // Method to obtain the user ID of the currently logged-in user
    private int getUserId() {
        // Implement your logic to retrieve the user ID here
        return 123; // Replace with the actual user ID
    }


    @FXML
    void initialize() {
        assert confirm_pwd != null : "fx:id=\"confirm_pwd\" was not injected: check your FXML file 'confpwd.fxml'.";
        assert new_pwd != null : "fx:id=\"new_pwd\" was not injected: check your FXML file 'confpwd.fxml'.";
        assert newpwd_btn != null : "fx:id=\"newpwd_btn\" was not injected: check your FXML file 'confpwd.fxml'.";

        // Initialize UserServices
        userServices = new UserServices();
    }

    // Helper method to show alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
