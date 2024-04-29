package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServices;

public class Resetpwd implements Initializable {

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

    private boolean emailExists(String email) {

        return userServices.emailExists(email);
    }

    @FXML
    private void handleResetPassword(ActionEvent event) {
        String email = Email_Rest.getText();
        if (emailExists(email)) {
            sendConfirmationEmail(email);
        } else {
            showAlert("Email Not Found", "This email does not exist in the database.");
        }

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/confpwd.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer les erreurs de chargement du fichier FXML
        }
    }

    private void sendConfirmationEmail(String email) {
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "nesrinefadhli14@gmail.com";
        String password = "vydz qyto rtqk yhag";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Reset Password Confirmation");
            message.setText("Dear User,\n\n"
                    + "Your password reset request has been received. Please follow the steps.");

            Transport.send(message);

            showAlert("Email Confirmation Sent", "An email confirmation has been sent to " + email);
        } catch (MessagingException e) {
            showAlert("Email Sending Error", "An error occurred while sending the confirmation email.");
            e.printStackTrace();
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
