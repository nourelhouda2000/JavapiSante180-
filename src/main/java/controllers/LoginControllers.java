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

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.exception.FacebookException;

import com.restfb.scope.ScopeBuilder;
//import com.restfb.types.User;
/*
import dev.niekirk.com.instagram4j.Instagram4j;
import dev.niekirk.com.instagram4j.requests.InstagramLoginRequest;
import dev.niekirk.com.instagram4j.requests.InstagramLogoutRequest;
import dev.niekirk.com.instagram4j.requests.payload.InstagramLoginResult;

 */

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
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nesrinefadhli14@gmail.com", "vydz qyto rtqk yhag");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nesrinefadhli14@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            showAlert("Email Sending Error", "An error occurred while sending the email.");
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



    private static final String FACEBOOK_APP_ID = "1799987317079344";
    private static final String FACEBOOK_APP_SECRET = "https://www.facebook.com/";
    private static final String FACEBOOK_REDIRECT_URI = "https://localhost:8080/facebook/callback";
    public static final Version VERSION_11_0 = Version.VERSION_11_0;


    // Method to handle Facebook login
    @FXML
    void handleFacebookIconClick(MouseEvent event) {
        String accessToken = getFacebookAccessToken();
        if (accessToken != null) {
            User user = getFacebookUser(accessToken);
            if (user != null) {
                // Handle the retrieved user data as needed
                // For example, you can display user information or proceed to the next screen
                System.out.println("Facebook login successful. User: " + user);
            } else {
                showAlert(Alert.AlertType.ERROR, "Facebook Login Failed", "Failed to obtain user data from Facebook.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Facebook Login Failed", "Failed to obtain Facebook access token.");
        }
    }
    /*
    private String getFacebookAccessToken() {
        // Use FACEBOOK_APP_ID when creating the DefaultFacebookClient
        FacebookClient fbClient = new DefaultFacebookClient(Version.VERSION_11_0, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET);
        // Fetch user information using the access token
        User me = fbClient.fetchObject("me", User.class);
        System.out.println(me.getNomuser()); // Just for debugging, remove in production

        return "EAAsbuEw8tboBO4AlRzgsY3sFJg1RbpHYHpRs2nGpf1f8LMydioxAfmGZA0s6HZAevao39iBarniptfsPKQ520W18htUCl0hbctmrl9LDzdzZAMx9iGxXnad5yrE8eC4k0KTivoef16NpETvRWYGGjBMUR039X1sZA83d07sGZAtDeIZB0NekfDm8XQ34cKRfodb15RhBTIVKw7g9ZARL7avaO3ZBzqZAEul1Y8ZC0aZBCZBjHLtyXlsN6hKD4NPUAkxaatwRKAZDZD";
    }

     */

    private String getFacebookAccessToken() {
        // No need to create a DefaultFacebookClient instance, just return the hardcoded access token
        return "EAAsbuEw8tboBO4AlRzgsY3sFJg1RbpHYHpRs2nGpf1f8LMydioxAfmGZA0s6HZAevao39iBarniptfsPKQ520W18htUCl0hbctmrl9LDzdzZAMx9iGxXnad5yrE8eC4k0KTivoef16NpETvRWYGGjBMUR039X1sZA83d07sGZAtDeIZB0NekfDm8XQ34cKRfodb15RhBTIVKw7g9ZARL7avaO3ZBzqZAEul1Y8ZC0aZBCZBjHLtyXlsN6hKD4NPUAkxaatwRKAZDZD";
    }


    // Method to fetch Facebook user data
    private User getFacebookUser(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        try {
            com.restfb.types.User fbUser = facebookClient.fetchObject("me", com.restfb.types.User.class);
            // Convert com.restfb.types.User to your User class
            User user = new User();
            user.setNomuser(fbUser.getName());
            // Set other properties as needed
            return user;
        } catch (FacebookException e) {
            e.printStackTrace();
            return null;
        }

    }

/*
    private User getFacebookUser(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        try {
            return facebookClient.fetchObject("me", User.class);
        } catch (FacebookException e) {
            e.printStackTrace();
            return null;
        }
    }

 */


/*
    @FXML
    void handleInstagramLogin(ActionEvent event) {
        String username = "YOUR_INSTAGRAM_USERNAME";
        String password = "YOUR_INSTAGRAM_PASSWORD";

        try {
            Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
            instagram.setup();
            InstagramLoginResult loginResult = instagram.sendRequest(new InstagramLoginRequest(username, password));

            if (loginResult.getStatus().equals("ok")) {
                // Instagram login successful, obtain user data as needed
                // For example, you can retrieve the user's profile information:
                // InstagramUser user = instagram.sendRequest(new InstagramGetUserRequest(username)).getUser();
            } else {
                showAlert(Alert.AlertType.ERROR, "Instagram Login Failed", "Failed to log in to Instagram.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Instagram Login Failed", "An error occurred while logging in to Instagram.");
        }
    }

 */

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
        facebook.setOnMouseClicked(this::handleFacebookIconClick);
    }

}
