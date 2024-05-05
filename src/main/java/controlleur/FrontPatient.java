package controlleur;

import entites.Nutritions;
import entites.Rendezvous;
import entites.Rapport;
import entites.Recette;
import entites.Nutritions;
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

import services.RecetteServices;
import services.NutritionsServices;

public class FrontPatient implements Initializable {

    private ObservableList<Nutritions> nutritionlist = FXCollections.observableArrayList();
    private NutritionsServices nutritionsServices = new NutritionsServices();
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
    private Button spotifyP;
    @FXML
    private Button DELETERDVF;
    @FXML
    private Button Activ_btnP;
    @FXML
    private Button Exer_btnP;
    @FXML
    private Button PDFRDVF;
    @FXML
    private Button mapp;
    @FXML
    private Button STATRDVF;
    @FXML
    private Button CalendarF;
    @FXML
    private Button Activitepage;
    @FXML
    private ComboBox<String> sortComboBoxRDVP;
    @FXML
    private Button TrieRDVP_btn;
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
    private Button RecetteP;
    @FXML
    private Label Searchnutrition;
    @FXML
    private ListView<Nutritions> listnutritionP;
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










////////////////////////Samarnutt///////////////////////////////////////////////////////////////

    @FXML
    public void afficherNutrition() {
        // Récupérer toutes les données de nutrition de la base de données
        List<Nutritions> nutritionList = nutritionsServices.getAllData();

        // Créer une liste observable à partir de la liste de données de nutrition
       ObservableList<Nutritions> observableNutritionList = FXCollections.observableArrayList(nutritionList);

        // Effacer les éléments existants dans le ListView
        listnutritionP.getItems().clear();

        // Ajouter les nouveaux éléments à la ListView
        listnutritionP.getItems().addAll(observableNutritionList);
      //  afficherCodeQr(observableNutritionList.get(0));

        // Configurer la cellule de liste personnalisée pour afficher les détails de chaque nutrition
        listnutritionP.setCellFactory(param -> new ListCell<Nutritions>() {
            @Override
            protected void updateItem(Nutritions nutrition, boolean empty) {
                super.updateItem(nutrition, empty);

                if (empty || nutrition == null) {
                    setText(null);
                } else {
                    // Créer une chaîne représentant les détails de la nutrition
                    String nutritionDetails =
                            " Fibres: " + nutrition.getFibres() +
                                    "\n Glucides: " + nutrition.getGlucides() +
                                    "\n Protéines: " + nutrition.getProteines() +
                                    "\n Lipides: " + nutrition.getLipides() +
                                    "\n Calories: " + nutrition.getCalories();

                    // Appliquer un style CSS pour rendre le texte en gras
                    setText(nutritionDetails);
                    setStyle("-fx-font-weight: bold");
                }
            }
        });
    }


    @FXML
    void openRecettePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/recetteP.fxml"));
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

    /////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        assert ADDRDVF != null : "fx:id=\"ADDRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Activ_btnP != null : "fx:id=\"Activ_btnP\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Activitepage != null : "fx:id=\"Activitepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Activités_form != null : "fx:id=\"Activités_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert CalendarF != null : "fx:id=\"CalendarF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert DATEF != null : "fx:id=\"DATEF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert DELETERDVF != null : "fx:id=\"DELETERDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Exer_btnP != null : "fx:id=\"Exer_btnP\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert HEUREF != null : "fx:id=\"HEUREF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert IDF != null : "fx:id=\"IDF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert ListeviewRDVF != null : "fx:id=\"ListeviewRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert PDFRDVF != null : "fx:id=\"PDFRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Profil_form != null : "fx:id=\"Profil_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert RecetteP != null : "fx:id=\"RecetteP\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert SEARCHF != null : "fx:id=\"SEARCHF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert STATRDVF != null : "fx:id=\"STATRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Sante_form != null : "fx:id=\"Sante_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Santepage != null : "fx:id=\"Santepage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert Searchnutrition != null : "fx:id=\"Searchnutrition\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert TrieRDVP_btn != null : "fx:id=\"TrieRDVP_btn\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert UpdateRDVF != null : "fx:id=\"UpdateRDVF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert listnutritionP != null : "fx:id=\"listnutritionP\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert logoutP_btn != null : "fx:id=\"logoutP_btn\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert mapp != null : "fx:id=\"mapp\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert profilpage != null : "fx:id=\"profilpage\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert saisie_dateFF != null : "fx:id=\"saisie_dateFF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert saisie_heureF != null : "fx:id=\"saisie_heureF\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert sortComboBoxRDVP != null : "fx:id=\"sortComboBoxRDVP\" was not injected: check your FXML file 'FrontPatient.fxml'.";
        assert spotifyP != null : "fx:id=\"spotifyP\" was not injected: check your FXML file 'FrontPatient.fxml'.";

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

        afficherNutrition();
    }




    }

