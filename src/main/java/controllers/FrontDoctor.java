package controllers;

import entities.Rendezvous;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.RendezvousServices;

public class FrontDoctor implements Initializable {
    private ObservableList<Rendezvous> rendezvousList = FXCollections.observableArrayList();

    @FXML
    private Button Activitepage;

    @FXML
    private AnchorPane Activités_form;

    @FXML
    private Button CalendarFD;

    @FXML
    private Button Home_page;

    @FXML
    private TextField IDF;

    @FXML
    private ListView<Rendezvous> ListeviewRDVFD;

    @FXML
    private Button PDFRDVFD;

    @FXML
    private AnchorPane Profil_form;

    @FXML
    private AnchorPane RDV_form;

    @FXML
    private AnchorPane Recette_form;

    @FXML
    private Button Recettepage;

    @FXML
    private AnchorPane Reclamation_form;

    @FXML
    private Button Reclamationpage;

    @FXML
    private Button Rendezvouspage;

    @FXML
    private TextField SEARCHFD;

    @FXML
    private Button STATRDVFD;

    @FXML
    private AnchorPane Sante_form;

    @FXML
    private Button Santepage;

    @FXML
    private Button close;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logoutD_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button profilpage;

    @FXML
    private Label saisie_dateFF;

    @FXML
    private Label saisie_heureF;



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



    private void afficherListeRDV() {
        // Créez une instance de RendezvousServices
        RendezvousServices rendezvousServices = new RendezvousServices();

        // Appelez la méthode getAllDataRendezvous() sur cette instance
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();

        // Créer une liste observable à partir de la liste de rendez-vous
        ObservableList<Rendezvous> observableRendezvousList = FXCollections.observableArrayList(rendezvousList);

        // Définir les éléments dans le ListView avec la cellule personnalisée
        ListeviewRDVFD.setItems(observableRendezvousList);
        ListeviewRDVFD.setCellFactory(param -> new CustomListDoctorCell());
    }





    @FXML
    void generatePDF111F(ActionEvent event) {
        // Créez une instance de RendezvousServices
        RendezvousServices rendezvousServices = new RendezvousServices();

        // Appelez la méthode getAllDataRendezvous() sur cette instance
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();
        String fileName = "rendezvous";

        try {
            Rendezvous.savePDF(fileName); // Enregistre le fichier PDF avant la génération
            Rendezvous.generatePDF(rendezvousList, fileName);
            showAlert("Le fichier PDF a été généré et enregistré avec succès.");
        } catch (IOException e) {
            showAlert("Existe deja  : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }






    @FXML
    void openStatRDV1Page() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/statRendezvous.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Appliquer une transition d'échelle
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), root);
            scaleTransition.setFromX(0); // Échelle initiale X: 0 (invisible)
            scaleTransition.setFromY(0); // Échelle initiale Y: 0 (invisible)
            scaleTransition.setToX(1); // Échelle finale X: 1 (pleinement visible)
            scaleTransition.setToY(1); // Échelle finale Y: 1 (pleinement visible)

            // Appliquer une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(-100); // Décalage horizontal initial
            translateTransition.setToX(0); // Pas de décalage horizontal final

            // Jouer les transitions en séquence
            scaleTransition.play();
            translateTransition.play();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void openCalendar1Page() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CalendarRDV.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Appliquer une transition d'échelle
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), root);
            scaleTransition.setFromX(0); // Échelle initiale X: 0 (invisible)
            scaleTransition.setFromY(0); // Échelle initiale Y: 0 (invisible)
            scaleTransition.setToX(1); // Échelle finale X: 1 (pleinement visible)
            scaleTransition.setToY(1); // Échelle finale Y: 1 (pleinement visible)

            // Appliquer une transition de translation
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), root);
            translateTransition.setFromX(-100); // Décalage horizontal initial
            translateTransition.setToX(0); // Pas de décalage horizontal final

            // Jouer les transitions en séquence
            scaleTransition.play();
            translateTransition.play();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/////////////////////////////////////////////////////////////////
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


        ListeviewRDVFD.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        afficherListeRDV();
    }
}
