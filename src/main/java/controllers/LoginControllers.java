package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserServices;

import javafx.scene.control.Hyperlink;


public class LoginControllers {
    private UserServices userServices = new UserServices();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Login_form;

    @FXML
    private Button close;

    @FXML
    private TextField email_btn;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_btn;

    @FXML
    private Hyperlink linkpwd;

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void handleLogin() throws IOException {
        String email = email_btn.getText();
        String mdp = password_btn.getText();

        boolean loginSuccess = userServices.loginUser(email, mdp);

        if (loginSuccess) {
            User user = userServices.getUserByEmail(email);// Obtenir le nom de l'utilisateur
            openRapportWindow(user);
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect email or password.");
        }
    }

    private void openRapportWindow(User user) throws IOException {
        // Load the Rendezvous.fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Rendezvous.fxml"));

        Parent root = loader.load();

        // Pass the username to the RendezvousControllers
        RendezvousControllers rendezvousController = loader.getController();
        rendezvousController.setNomUtilisateur(user.getNomuser(), user.getPrenomuser(), user.getAgeuser(), user.getSexe(),user.getRole(), user.getEmail(), user.getMdp());

        // Create a new scene
        Scene scene = new Scene(root);

        // Create a new stage and set the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Show the window
        stage.show();
    }

    public void close() {
        System.exit(0);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Login_form != null : "fx:id=\"Login_form\" was not injected: check your FXML file 'Login.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Login.fxml'.";
        assert email_btn != null : "fx:id=\"email_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert password_btn != null : "fx:id=\"password_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert linkpwd != null : "fx:id=\"linkpwd\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
