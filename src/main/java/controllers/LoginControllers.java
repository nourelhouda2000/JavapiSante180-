package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import javafx.scene.control.CheckBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.scene.input.MouseEvent;

public class LoginControllers {
    private UserServices userServices = new UserServices();

    private int loginAttempts = 0;
    private final int MAX_LOGIN_ATTEMPTS = 3;

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

    @FXML
    private Hyperlink signup;

    @FXML
    private CheckBox checkbox_pwd;

    @FXML
    private TextField login_showpwd;

    @FXML
    private FontAwesomeIconView facebook;

    @FXML
    private FontAwesomeIconView instagram;

    // Method to send an email
    private void sendEmail(String recipient, String subject, String body) {
        // Set up email server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session object
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nesrinefadhli14@gmail.com", "vydz qyto rtqk yhag");
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the sender email address
            message.setFrom(new InternetAddress("nesrinefadhli14@gmail.com"));
            // Set the recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            // Set the email subject
            message.setSubject(subject);
            // Set the email body
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @FXML
    void handleResetPassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resetpwd.fxml"));
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
    void handleSignUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
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
            User user = userServices.getUserByEmail(email);
            openRapportWindow(user);
        } else {
            loginAttempts++; // Increment the login attempts counter
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Please try again later. You have attempted more than 3 times.");

                // Send email notification
                sendEmail(email, "Login Attempt Limit Exceeded", "You have exceeded the maximum number of login attempts. Please try again later.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect email or password.");
            }
        }
    }

    private void openRapportWindow(User user) throws IOException {
        // Reset the login attempts counter
        loginAttempts = 0;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("Rendezvous.fxml"));

        Parent root = loader.load();

        RendezvousControllers rendezvousController = loader.getController();
        rendezvousController.setNomUtilisateur(user.getNomuser(), user.getPrenomuser(), user.getAgeuser(), user.getSexe(),user.getRole(), user.getEmail(), user.getMdp());

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void showPasswordCheckbox(ActionEvent event) {
        if (checkbox_pwd.isSelected()) {
            login_showpwd.setText(password_btn.getText());
            login_showpwd.setVisible(true);
            password_btn.setVisible(false);
        } else {
            password_btn.setText(login_showpwd.getText());
            login_showpwd.setVisible(false);
            password_btn.setVisible(true);
        }
    }

    public void close() {
        System.exit(0);
    }

    private static final String FACEBOOK_APP_ID = "YOUR_FACEBOOK_APP_ID";
    private static final String FACEBOOK_APP_SECRET = "YOUR_FACEBOOK_APP_SECRET";
    private static final String FACEBOOK_REDIRECT_URI = "YOUR_FACEBOOK_REDIRECT_URI";

    @FXML
    void handleFacebookLogin(ActionEvent event) throws IOException {
        String accessToken = getFacebookAccessToken();
        if (accessToken != null) {
            User user = getFacebookUser(accessToken);
        } else {
            showAlert(Alert.AlertType.ERROR, "Facebook Login Failed", "Failed to obtain Facebook access token.");
        }
    }

    private String getFacebookAccessToken() {
        try {
            String facebookAuthUrl = "https://www.facebook.com/dialog/oauth?client_id=" + FACEBOOK_APP_ID +
                    "&redirect_uri=" + FACEBOOK_REDIRECT_URI + "&scope=email";

            return "YOUR_FACEBOOK_ACCESS_TOKEN";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getFacebookUser(String accessToken) {

        return null;
    }

    @FXML
    void handleInstagramLogin(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert Login_form != null : "fx:id=\"Login_form\" was not injected: check your FXML file 'Login.fxml'.";
        assert checkbox_pwd != null : "fx:id=\"checkbox_pwd\" was not injected: check your FXML file 'Login.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Login.fxml'.";
        assert email_btn != null : "fx:id=\"email_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert linkpwd != null : "fx:id=\"linkpwd\" was not injected: check your FXML file 'Login.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert password_btn != null : "fx:id=\"password_btn\" was not injected: check your FXML file 'Login.fxml'.";
        assert signup != null : "fx:id=\"signup\" was not injected: check your FXML file 'Login.fxml'.";
        assert login_showpwd != null : "fx:id=\"login_showpwd\" was not injected: check your FXML file 'Login.fxml'.";
        assert facebook != null : "fx:id=\"facebook\" was not injected: check your FXML file 'Login.fxml'.";
        assert instagram != null : "fx:id=\"instagram\" was not injected: check your FXML file 'Login.fxml'.";
    }
}
