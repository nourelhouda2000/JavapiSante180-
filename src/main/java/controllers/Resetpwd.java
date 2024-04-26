package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import services.UserServices;

public class Resetpwd implements Initializable  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Email_Rest;

    @FXML
    private Button Restpassword_btn;

    private UserServices userServices;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Email_Rest != null : "fx:id=\"Email_Rest\" was not injected: check your FXML file 'resetpwd.fxml'.";
        assert Restpassword_btn != null : "fx:id=\"Restpassword_btn\" was not injected: check your FXML file 'resetpwd.fxml'.";
        userServices = new UserServices();
    }


    // Méthode pour vérifier si l'e-mail existe dans la base de données
    private boolean emailExists(String email) {
        return userServices.entityExistsbyemail(email);
    }

    @FXML
    private void handleResetPassword() {
        // Récupérer l'e-mail saisi par l'utilisateur
        String email = Email_Rest.getText();

        // Vérifier si l'e-mail existe dans la base de données
        if (emailExists(email)) {
            // Envoyer un e-mail de confirmation
            sendConfirmationEmail(email);
        } else {
            // Afficher un message d'erreur indiquant que l'e-mail n'existe pas dans la base de données
            showAlert("Email Not Found", "This email does not exist in the database.");
        }
    }

    // Méthode pour envoyer un e-mail de confirmation
    private void sendConfirmationEmail(String email) {
        // Mettez ici votre code pour envoyer un e-mail de confirmation à l'utilisateur avec l'adresse e-mail spécifiée
        // Vous pouvez utiliser la méthode sendEmail() fournie précédemment pour envoyer l'e-mail
        // Par exemple :
        // sendEmail(email);
        showAlert("Email Confirmation Sent", "An email confirmation has been sent to " + email);
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
