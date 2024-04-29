package controllers;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signup implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField age_cree;

    @FXML
    private TextField email_cree;

    @FXML
    private Hyperlink loginAccount;

    @FXML
    private TextField nom_cree;

    @FXML
    private TextField prenom_cree;

    @FXML
    private TextField pwd_cree;

    @FXML
    private ComboBox<String> role_creee;

    @FXML
    private ComboBox<String> sexe_cree;

    @FXML
    private Button signup_btn;

    private UserServices userServices;

    @FXML
    void handleLoginAccount(ActionEvent event) {
        try {
            // Load the FXML file
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
    }

    @FXML
    void handleAjouterCompte(ActionEvent event) {
        // Get the values from the input fields
        String nom = nom_cree.getText();
        String prenom = prenom_cree.getText();
        String age = age_cree.getText();
        String sexe = sexe_cree.getValue();
        String email = email_cree.getText();
        String mdp = pwd_cree.getText();
        String roleAsString = role_creee.getValue();

        // Convert roleAsString to role integer value
        int role;
        switch (roleAsString) {
            case "Admin":
                role = 0;
                break;
            case "Doctor":
                role = 1;
                break;
            case "Patient":
                role = 2;
                break;
            default:
                role = -1; // Invalid role
        }

        // Create a new User object with the provided data
        User user = new User();
        user.setNomuser(nom);
        user.setPrenomuser(prenom);
        user.setAgeuser(age);
        user.setSexe(sexe);
        user.setEmail(email);
        user.setMdp(mdp);
        user.setRole(role);

        // Call the addUser method from UserServices to add the user
        UserServices userService = new UserServices();
        userService.addUser(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert age_cree != null : "fx:id=\"age_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert email_cree != null : "fx:id=\"email_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert loginAccount != null : "fx:id=\"loginAccount\" was not injected: check your FXML file 'signup.fxml'.";
        assert nom_cree != null : "fx:id=\"nom_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert prenom_cree != null : "fx:id=\"prenom_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert pwd_cree != null : "fx:id=\"pwd_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert role_creee != null : "fx:id=\"role_creee\" was not injected: check your FXML file 'signup.fxml'.";
        assert sexe_cree != null : "fx:id=\"sexe_cree\" was not injected: check your FXML file 'signup.fxml'.";
        assert signup_btn != null : "fx:id=\"signup_btn\" was not injected: check your FXML file 'signup.fxml'.";

        userServices = new UserServices();
    }
}
