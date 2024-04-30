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
import services.UserServices;

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

    private UserServices userServices;

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @FXML
    void handleNewpwd(ActionEvent event) {
        String newPassword = new_pwd.getText();
        String confirmPassword = confirm_pwd.getText();

        if (!newPassword.equals(confirmPassword)) {
            showAlert("Password Mismatch", "New password and confirm password do not match.");
            return;
        }

        int userId = getUserId();

        if (userServices == null) {
            showAlert("Error", "UserServices is not initialized.");
            return;
        }

        boolean success = userServices.updateUserPwd(userId, newPassword);

        if (success) {
            showAlert("Success", "Password updated successfully!");
            redirectToLoginScreen(event);
        } else {
            showAlert("Error", "Failed to update password.");
        }
    }

    private int getUserId() {
        return 123;
    }

    private void redirectToLoginScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert confirm_pwd != null : "fx:id=\"confirm_pwd\" was not injected: check your FXML file 'confpwd.fxml'.";
        assert new_pwd != null : "fx:id=\"new_pwd\" was not injected: check your FXML file 'confpwd.fxml'.";
        assert newpwd_btn != null : "fx:id=\"newpwd_btn\" was not injected: check your FXML file 'confpwd.fxml'.";
        userServices = new UserServices();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
