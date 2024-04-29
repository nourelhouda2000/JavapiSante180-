package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FrontDoctor implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Activitepage;

    @FXML
    private AnchorPane Activités_form;

    @FXML
    private Button Home_page;

    @FXML
    private AnchorPane Profil_form;

    @FXML
    private AnchorPane RDV_form;

    @FXML
    private AnchorPane Recette_form;
    @FXML
    private Button logoutD_btn;
    @FXML
    private Button Recettepage;

    @FXML
    private AnchorPane Reclamation_form;

    @FXML
    private Button Reclamationpage;

    @FXML
    private Button Rendezvouspage;

    @FXML
    private AnchorPane Sante_form;

    @FXML
    private Button Santepage;

    @FXML
    private Button close;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button profilpage;



/////////////////////////////////////////////////////////////////////////////////////////
    public void closeD() {
        System.exit(0);
    }

    public void minimizeD() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void logoutD() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            // Si l'utilisateur clique sur "OK", fermez la fenêtre
            Stage stage = (Stage) main_form.getScene().getWindow();
            stage.close();
        }
    }
    public void switchForm(ActionEvent event) {



        if (event.getSource() == profilpage) {
            Profil_form.setVisible(true);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);

            profilpage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Rendezvouspage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);

            RDV_form.setVisible(true);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);

            Rendezvouspage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");
        }

        else if (event.getSource() == Home_page) {
            Profil_form.setVisible(false);
            home_form.setVisible(true);
            RDV_form.setVisible(false);

            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);

            Home_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Rendezvouspage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(true);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);

            Rendezvouspage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == Recettepage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            Recette_form.setVisible(true);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);
            Recettepage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == Reclamationpage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(true);
            Activités_form.setVisible(false);
            Sante_form.setVisible(false);

            Reclamationpage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");




        }else if (event.getSource() == Activitepage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(true);
            Sante_form.setVisible(false);

            Activitepage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Santepage.setStyle("-fx-background-color:transparent");
        }else if (event.getSource() == Santepage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);

            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            Activités_form.setVisible(false);
            Sante_form.setVisible(true);

            Santepage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            profilpage.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");
            Activitepage.setStyle("-fx-background-color:transparent");
        }


    }
    ////////////////////////////////////////////////////////////////////////////////




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Activitepage != null : "fx:id=\"Activitepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Activités_form != null : "fx:id=\"Activités_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Profil_form != null : "fx:id=\"Profil_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Sante_form != null : "fx:id=\"Sante_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Santepage != null : "fx:id=\"Santepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert profilpage != null : "fx:id=\"profilpage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert logoutD_btn != null : "fx:id=\"logoutD_btn\" was not injected: check your FXML file 'Rendezvous.fxml'.";

    }
}
