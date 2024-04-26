package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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
    private ComboBox<?> role_creee;

    @FXML
    private ComboBox<?> sexe_cree;

    @FXML
    private Button signup_btn;



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

    }
}

