package controllers;

import entities.Rendezvous;
import entities.Rapport;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.util.Duration;
import services.RendezvousServices;
import services.RapportServices;

public class FrontPatient implements Initializable {


    private ObservableList<Rendezvous> rendezvousList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ADDRDVF;
    @FXML
    private Button UpdateRDVF;

    @FXML
    private Button DELETERDVF;

    @FXML
    private Button PDFRDVF;

    @FXML
    private Button STATRDVF;
    @FXML
    private Button CalendarF;
    @FXML
    private Button Activitepage;

    @FXML
    private AnchorPane Activités_form;

    @FXML
    private DatePicker DATEF;
    @FXML
    private TextField IDF;
    @FXML
    private TextField HEUREF;

    @FXML
    private Button Home_page;

    @FXML
    private ListView<Rendezvous> ListeviewRDVF;

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
    private Label saisie_dateFF;

    @FXML
    private Label saisie_heureF;
    @FXML
    private Button Reclamationpage;

    @FXML
    private Button Rendezvouspage;

    @FXML
    private TextField SEARCHF;

    @FXML
    private AnchorPane Sante_form;

    @FXML
    private Button Santepage;

    @FXML
    private Button close;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logoutP_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button profilpage;


  /////////////////////////////////////////////////////////////////////////////

    public void closeP() {
        System.exit(0);
    }

    public void minimizeP() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void logoutP() {
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


////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void  addRDVReset() {
        DATEF.setValue(null);
        HEUREF.setText("");
    }
    @FXML
    void addRDVF(ActionEvent event) {
        // Get the currently logged-in user ID or fetch it from wherever it's stored
        int userId = 1; // Assuming you have a method to retrieve the user ID

        String rdvText = DATEF.getValue() != null ? DATEF.getValue().toString() : "";
        String rdvhText = HEUREF.getText();

        // Clear any previous error messages
        saisie_dateFF.setText("");
        saisie_heureF.setText("");

        if (rdvText.isEmpty() || rdvhText.isEmpty()) {
            // Check if either the date or time field is empty
            if (rdvText.isEmpty()) {
                saisie_dateFF.setText("Champ vide.");
            }
            if (rdvhText.isEmpty()) {
                saisie_heureF.setText("Champ vide.");
            }
            return;
        }

        // Create a new Rendezvous object with data from UI fields
        Rendezvous rendezvous = new Rendezvous();
        // Utilize the value selected in the DatePicker for the date
        rendezvous.setDate_r(rdvText);
        rendezvous.setHeur(rdvhText);

        // Create an instance of RendezvousServices
        RendezvousServices rendezvousServices = new RendezvousServices();

        // Call the instance method to add the rendezvous
        rendezvousServices.addRendezvous2(rendezvous, userId);
        afficherListeRDV();

        // Reset the text of the input fields
        addRDVReset();
    }


    private void afficherListeRDV() {
        // Créez une instance de RendezvousServices
        RendezvousServices rendezvousServices = new RendezvousServices();

        // Appelez la méthode getAllDataRendezvous() sur cette instance
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();

        // Créer une liste observable à partir de la liste de rendez-vous
        ObservableList<Rendezvous> observableRendezvousList = FXCollections.observableArrayList(rendezvousList);

        // Définir les éléments dans le ListView avec la cellule personnalisée
        ListeviewRDVF.setItems(observableRendezvousList);
        ListeviewRDVF.setCellFactory(param -> new CustomListCell());
    }












    @FXML
    void updateRDV(ActionEvent event) {
        // Vérifiez d'abord si un rendez-vous est sélectionné dans la liste
        Rendezvous selectedRDV = ListeviewRDVF.getSelectionModel().getSelectedItem();
        if (selectedRDV == null) {
            // Si aucun rendez-vous n'est sélectionné, affichez un message d'erreur et quittez la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a rendezvous from the list");
            alert.showAndWait();
            return;
        }

        // Ensuite, vérifiez la date et l'heure
        if (DATEF.getValue() == null || HEUREF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date and fill all blank fields");
            alert.showAndWait();
        } else {
            // Si tout est correct, continuez avec la mise à jour du rendez-vous
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Update the rendezvous for the selected date?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Créez un objet Rendezvous avec les nouvelles valeurs
                    Rendezvous rendezvous = new Rendezvous();
                    rendezvous.setIdR(selectedRDV.getIdR());
                    rendezvous.setDate_r(DATEF.getValue().toString());
                    rendezvous.setHeur(HEUREF.getText());
                    // Créez une instance de RendezvousServices et appelez la méthode de mise à jour
                    RendezvousServices rendezvousServices = new RendezvousServices();
                    // Call the instance method to update the rendezvous
                    rendezvousServices.updateRendezvous(rendezvous);

                    // Refresh the list view after updating the rendezvous
                    afficherListeRDV();

                    // Reset input fields
                    addRDVReset();
                }
            });
        }
    }

    @FXML
    void deleteRDV(ActionEvent event) {
        // Get the selected user from the table view
        Rendezvous selectedRDV = ListeviewRDVF.getSelectionModel().getSelectedItem();
        // Check if a user is selected
        if (selectedRDV== null) {
            // If no user is selected, show an alert
            showAlert("Aucun Rendez-vous sélectionné", "Veuillez sélectionner Rendez-vous.");
            return; // Exit the method
        }
        // Show confirmation dialog before deleting user
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE the rendezvous for the selected date?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Create an instance of RendezvousServices
            RendezvousServices rendezvousServices = new RendezvousServices();
            // If user confirms deletion, call the non-static method on the instance
            rendezvousServices.deleteRendezvous(selectedRDV);

            // Refresh the list view after deleting the rendezvous
            afficherListeRDV();

            // Clear input fields after deleting user
            addRDVReset();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
////////////////////////////////////////m/////////////

    @FXML
    void generatePDF11F(ActionEvent event) {
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
    void openStatRDVPage() {
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
    void openCalendarPage() {
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
////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        assert ADDRDVF != null : "fx:id=\"ADDRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Activitepage != null : "fx:id=\"Activitepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Activités_form != null : "fx:id=\"Activités_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert CalendarF != null : "fx:id=\"CalendarF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert DATEF != null : "fx:id=\"DATEF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert HEUREF != null : "fx:id=\"HEUREF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert ListeviewRDVF != null : "fx:id=\"ListeviewRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert PDFRDVF != null : "fx:id=\"PDFF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Profil_form != null : "fx:id=\"Profil_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert SEARCHF != null : "fx:id=\"SEARCHF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert DELETERDVF != null : "fx:id=\"STATF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert UpdateRDVF != null : "fx:id=\"STATF\" was not injected: check your FXML file 'FrontPatient.fxml'.";

        assert STATRDVF != null : "fx:id=\"STATF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Sante_form != null : "fx:id=\"Sante_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Santepage != null : "fx:id=\"Santepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert logoutP_btn != null : "fx:id=\"logoutP_btn\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert profilpage != null : "fx:id=\"profilpage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert IDF != null : "fx:id=\"IDF\" was not injected: check your FXML file 'Rendezvous.fxml'.";

        ListeviewRDVF.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ListeviewRDVF.setOnMouseClicked(event -> {
            // Vérifiez si un élément est sélectionné
            Rendezvous selectedRDV = ListeviewRDVF.getSelectionModel().getSelectedItem();
            if (selectedRDV != null) {
                // Récupérez la date et l'heure du rendez-vous sélectionné
                String selectedDate = selectedRDV.getDate_r();
                String selectedHeure = selectedRDV.getHeur();

                // Mettez à jour les champs de date et d'heure du formulaire avec les valeurs sélectionnées
                DATEF.setValue(LocalDate.parse(selectedDate));
                HEUREF.setText(selectedHeure);
            }
        });
        afficherListeRDV();
    }




    }

