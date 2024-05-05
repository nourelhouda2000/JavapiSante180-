package controlleur;

import entites.Nutritions;
import entites.Rendezvous;
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
import services.NutritionsServices;

public class FrontDoctor implements Initializable {

    private NutritionsServices nutritionsServices = new NutritionsServices();
    @FXML
    private Button Activitepage;

    @FXML
    private AnchorPane Activités_form;

    @FXML
    private Button CalendarFD;

    @FXML
    private Button Home_page;
    @FXML
    private Button ADDNuttr1;
    @FXML
    private Button ReccetteeDoct;
    @FXML
    private Button selectnuttD1;
    @FXML
    private TextField IDF;
    @FXML
    private Button mappD;
    @FXML
    private Button REFD;
    @FXML
    private ListView<Rendezvous> ListeviewRDVFD;
    @FXML
    private TextField LipidesD;
    @FXML
    private TextField GlucidesD;

    @FXML
    private TextField FibresD;
    @FXML
    private TextField CaloriesD;

    @FXML
    private Button Deletenutt;
    @FXML
    private ListView<Nutritions> ListeviewNuttritionD;
    @FXML
    private Button Updatenutt1;
    @FXML
    private TextField ProteinesD;
    @FXML
    private Button TrieNutt_btn;
    @FXML
    private ComboBox<String> sortComboBoxNutt;
    @FXML
    private Button spotify;
    @FXML
    private Button PDFRDVFD;

    @FXML
    private AnchorPane Profil_form;
    @FXML
    private Button TrieRDVD_btn;
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
    private ComboBox<String> sortComboBoxRDVD;

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
////////////////////////////////////////////////////nour/////////////////////////////////////////







    /////////////////////////////////////////////////////////////////SamarNutt//////////////////////////

    @FXML
    public void addNUTReset() {

        FibresD.setText("");
        LipidesD.setText("");
        GlucidesD.setText("");
        ProteinesD.setText("");
        CaloriesD.setText("");


    }

    @FXML
    void addNutD(ActionEvent event) {
        try {
            // Vérifiez si les champs contiennent des valeurs valides
           /* if (FibresD.getText().isEmpty()) {
                controlefib.setText("Le champ Fibres ne peut pas être vide.");
                return;
            }
            if (CaloriesD.getText().isEmpty()) {
                controlecal.setText("Le champ Calories ne peut pas être vide.");
                return;
            }
            if (GlucidesD.getText().isEmpty()) {
                controleg.setText("Le champ Glucides ne peut pas être vide.");
                return;
            }
            if (proteins.getText().isEmpty()) {
                proteinscontr.setText("Le champ Protéines ne peut pas être vide.");

                return;
            }
            if (lipide.getText().isEmpty()) {
                controlelip.setText("Le champ Lipides ne peut pas être vide.");
                return;
            }*/

            // Si les champs ne sont pas vides, convertissez-les en entiers
            int fibresValue = Integer.parseInt(FibresD.getText());
            int caloriesValue = Integer.parseInt(CaloriesD.getText());
            int glucideValue = Integer.parseInt(GlucidesD.getText());
            int proteinsValue = Integer.parseInt(ProteinesD.getText());
            int lipideValue = Integer.parseInt(LipidesD.getText());

            // Réinitialisez les messages de contrôle
            addNUTReset();

            // Créez un nouvel objet Nutritions avec les données des champs UI
            Nutritions rendezvous = new Nutritions();
            rendezvous.setFibres(fibresValue);
            rendezvous.setCalories(caloriesValue);
            rendezvous.setGlucides(glucideValue);
            rendezvous.setProteines(proteinsValue);
            rendezvous.setLipides(lipideValue);

            // Appelez la méthode du service pour ajouter le rendezvous
            nutritionsServices.addEntity2(rendezvous);
            //
            addNUTReset();

            // Rafraîchir la ListView après l'ajout
            List<Nutritions> nutritionsList = nutritionsServices.getAllData();
            ListeviewNuttritionD.getItems().clear();
            ListeviewNuttritionD.getItems().addAll(nutritionsList);
        } catch (NumberFormatException e) {
            // Gérez l'exception si l'une des valeurs n'est pas un entier valide
            System.err.println("Erreur : Assurez-vous que tous les champs sont remplis avec des valeurs numériques valides.");
            e.printStackTrace();
        }
    }


    @FXML
    void updateNUT(ActionEvent event) {
        Nutritions rec = (Nutritions) ListeviewNuttritionD.getSelectionModel().getSelectedItem();
        if (rec != null) {
            try {
                int fibresValue = Integer.parseInt(FibresD.getText());
                int caloriesValue = Integer.parseInt(CaloriesD.getText());
                int glucideValue = Integer.parseInt(GlucidesD.getText());
                int proteinsValue = Integer.parseInt(ProteinesD.getText());
                int lipideValue = Integer.parseInt(LipidesD.getText());

                rec.setFibres(fibresValue);
                rec.setCalories(caloriesValue);
                rec.setGlucides(glucideValue);
                rec.setProteines(proteinsValue);
                rec.setLipides(lipideValue);

                nutritionsServices.updateEntity(rec);
                // Reset fields
                addNUTReset();
                // Update the ListView
                afficherNutritionD();
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.err.println("Erreur : Assurez-vous que tous les champs sont remplis avec des valeurs numériques valides.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void selectNut() {
        Nutritions rec = (Nutritions) ListeviewNuttritionD.getSelectionModel().getSelectedItem();
        if (rec != null) {
            // Afficher les valeurs dans les champs texte
            FibresD.setText(String.valueOf(rec.getFibres()));
            CaloriesD.setText(String.valueOf(rec.getCalories()));
            GlucidesD.setText(String.valueOf(rec.getGlucides()));
            ProteinesD.setText(String.valueOf(rec.getProteines()));
            LipidesD.setText(String.valueOf(rec.getLipides()));
        }
    }


    @FXML
    void deleteNut(ActionEvent event) {
        // Get the selected user from the table view
        Nutritions selectedUser = (Nutritions) ListeviewNuttritionD.getSelectionModel().getSelectedItem();

        // Check if a user is selected
        if (selectedUser == null) {
            // If no user is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune nutrition sélectionnée");
            alert.setHeaderText("Veuillez sélectionner une nutrition.");
            alert.showAndWait();
            return; // Exit the method
        }

        // Show confirmation dialog before deleting user
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If user confirms deletion, call the service method to delete the user
            nutritionsServices.deleteEntity(selectedUser);

            // Refresh the table view after deleting user
            ListeviewNuttritionD.getItems().remove(selectedUser);

            // Clear input fields after deleting user
            addNUTReset();
        }
    }


    @FXML
    public void afficherNutritionD() {
        // Récupérer toutes les données de nutrition de la base de données
        List<Nutritions> nutritionList = nutritionsServices.getAllData();

        // Créer une liste observable à partir de la liste de données de nutrition
        ObservableList<Nutritions> observableNutritionList = FXCollections.observableArrayList(nutritionList);

        // Effacer les éléments existants dans le ListView
        ListeviewNuttritionD.getItems().clear();

        // Ajouter les nouveaux éléments à la ListView
        ListeviewNuttritionD.getItems().addAll(observableNutritionList);
        //  afficherCodeQr(observableNutritionList.get(0));

        // Configurer la cellule de liste personnalisée pour afficher les détails de chaque nutrition
        ListeviewNuttritionD.setCellFactory(param -> new ListCell<Nutritions>() {
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
    void openRecetteDPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/recetteD.fxml"));
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
    ////////////trienutfront////////
    @FXML
    public void handleSortPp(ActionEvent event) {
        String sortBy = sortComboBoxNutt.getValue(); // Obtenir la valeur sélectionnée dans le ComboBox

        NutritionsServices nutritionsServices = new NutritionsServices(); // Créer une instance du service de nutrition

        if (sortBy == null || sortBy.equals("Calories")) {
            // Aucune option de tri sélectionnée ou tri par calories
            List<Nutritions> nutritionsSortedByCalories = nutritionsServices.nutritionsSorted("Calories");
            // Mettre à jour la ListView avec les nutritions triées par calories
            ListeviewNuttritionD.setItems(FXCollections.observableArrayList(nutritionsSortedByCalories));
        } else if (sortBy.equals("Protéines")) {
            // Tri par protéines
            List<Nutritions> nutritionsSortedByProteines = nutritionsServices.nutritionsSorted("Protéines");
            // Mettre à jour la ListView avec les nutritions triées par protéines
            ListeviewNuttritionD.setItems(FXCollections.observableArrayList(nutritionsSortedByProteines));
        }
    }











    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert ADDNuttr1 != null : "fx:id=\"ADDNuttr1\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert ReccetteeDoct != null : "fx:id=\"ReccetteeDoct\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert selectnuttD1 != null : "fx:id=\"selectnuttD1\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Activitepage != null : "fx:id=\"Activitepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Activités_form != null : "fx:id=\"Activités_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert CalendarFD != null : "fx:id=\"CalendarFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert CaloriesD != null : "fx:id=\"CaloriesD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Deletenutt != null : "fx:id=\"Deletenutt\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert FibresD != null : "fx:id=\"FibresD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert GlucidesD != null : "fx:id=\"GlucidesD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert LipidesD != null : "fx:id=\"LipidesD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert ListeviewNuttritionD != null : "fx:id=\"ListeviewNuttritionD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert ListeviewRDVFD != null : "fx:id=\"ListeviewRDVFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert PDFRDVFD != null : "fx:id=\"PDFRDVFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Profil_form != null : "fx:id=\"Profil_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert ProteinesD != null : "fx:id=\"ProteinesD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert REFD != null : "fx:id=\"REFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert SEARCHFD != null : "fx:id=\"SEARCHFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert STATRDVFD != null : "fx:id=\"STATRDVFD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Sante_form != null : "fx:id=\"Sante_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Santepage != null : "fx:id=\"Santepage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert TrieNutt_btn != null : "fx:id=\"TrieNutt_btn\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert TrieRDVD_btn != null : "fx:id=\"TrieRDVD_btn\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert Updatenutt1 != null : "fx:id=\"Updatenutt1\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert logoutD_btn != null : "fx:id=\"logoutD_btn\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert mappD != null : "fx:id=\"mappD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert profilpage != null : "fx:id=\"profilpage\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert sortComboBoxNutt != null : "fx:id=\"sortComboBoxNutt\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert sortComboBoxRDVD != null : "fx:id=\"sortComboBoxRDVD\" was not injected: check your FXML file 'FrontDoctor.fxml'.";
        assert spotify != null : "fx:id=\"spotify\" was not injected: check your FXML file 'FrontDoctor.fxml'.";

        ListeviewRDVFD.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        afficherNutritionD();
    }
}
