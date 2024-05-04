package controlleur;

import entites.*;
import entites.Nutritions;
import javafx.event.Event;
import javafx.scene.control.Alert;


import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;
import services.*;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.net.URLEncoder;
import java.util.List;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;


import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


public class RendezvousControllers implements Initializable {
    private ObservableList<Rendezvous> rendezvousList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<Nutritions> nutritionlist = FXCollections.observableArrayList();

    private ObservableList<User> reclamationsList = FXCollections.observableArrayList();



    private NutritionsServices nutritionsServices = new NutritionsServices();
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Button ADD_RDV;
    @FXML
    private Button pdfsamar;
    @FXML
    private Button addnutri;
    @FXML
    private Button deletenutri;
    @FXML
    private Button updatenutr;
    @FXML
    private Button selectNut;

    @FXML
    private Button Ajouter_Rapport;
    @FXML
    private Button ADD_Reclamation;
    @FXML
    private Button repponse;

    @FXML
    private Label proteinscontr;
    @FXML
    private Label controleg;
    @FXML
    private Label controlecal;
    @FXML
    private Label controlefib;
    @FXML
    private Label controlelip;


    @FXML
    private Label desccontrole;

    @FXML
    private Label datecontrole;
    @FXML
    private Button Clear_Reclamation;
    @FXML
    private Button Delete_Reclamation;

    @FXML
    private Button ADD_user;

    @FXML
    private ListView<Rendezvous> Listview_RDV;
    @FXML
    private ListView<Nutritions> listeviewnutr;
    @FXML
    private TableColumn<User, String> Age_afficher;

    @FXML
    private Button Clear_RDV;

    @FXML
    private Button Clear_user;



    @FXML
    private Button Delete_RDV;

    @FXML
    private Button Delete_user;


    @FXML
    private TextField R_Ajou;
    @FXML
    private AnchorPane Recette_form;

    @FXML
    private Button Recettepage;
    @FXML
    private Button Recette;

    @FXML
    private AnchorPane Reclamation_form;

    @FXML
    private Button Reclamationpage;

    @FXML
    private Button Home_page;


    @FXML
    private TextField id_R;
    @FXML
    private TextField fibres;
    @FXML
    private TextField proteins;
    @FXML
    private TextField lipide;
    @FXML
    private TextField glucide;
    @FXML
    private TextField calories;


    @FXML
    private TableColumn<User, String> NomAfficher;

    @FXML
    private TableColumn<User, String> Prenom_afficher;

    @FXML
    private Button Update_Reclamation;

    @FXML
    private AnchorPane RDV_form;

    @FXML
    private Button Rendezvouspage;

    @FXML
    private TextField role_ajou;
    @FXML
    private ListView<Reclamations> Listview_Reclamation;




    @FXML
    private TextField sexe_ajou;

    @FXML
    private TextField descriptionAjou;


    @FXML
    private TableView<User> user_tableview;
    @FXML
    private TableColumn<User, Integer> Role_afficher;
    @FXML
    private TextField ID_user;


    @FXML
    private TextField Search_RDV;

    @FXML
    private TextField Searchuser;

    @FXML
    private Button Update_RDV;
    @FXML
    private Button statrecl;

    @FXML
    private Button Update_user;

    @FXML
    private AnchorPane User_form;

    @FXML
    private TextField age_ajou;

    @FXML
    private TextField search_Recl;


    @FXML
    private Button close;

    @FXML
    private TextField date_rAjou;

    @FXML
    private TableColumn<User, String> email_afficher;

    @FXML
    private TextField email_ajou;

    @FXML
    private TextField heur_Ajou;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;

    @FXML
    private Button pdfrecla;
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TextField nom_ajou;

    @FXML
    private TextField prenom_ajou;


    @FXML
    private TableColumn<User, String> sexe_afficher;


    @FXML
    private Button user_page;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Rendezvous rendezvous;
    private String message;
    private AlertType alertType;
    @FXML
    private Button select;
    @FXML
    private Button clear_nutr;
    @FXML
    private ImageView codeQrImage;
    @FXML
    private TextField AiInput;
    @FXML
    private Button remplissagebtn;


    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == Home_page) {
            home_form.setVisible(true);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Home_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Rendezvouspage) {
            home_form.setVisible(false);
            RDV_form.setVisible(true);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Rendezvouspage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == user_page) {
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(true);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            user_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == Recettepage) {
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(true);
            Reclamation_form.setVisible(false);
            Recettepage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == Reclamationpage) {
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(true);

            Reclamationpage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
        }

    }


    ////////////////////////////////samar//////////////

    //tfaragh les text field 3aytnelha fil  bouton clear
    @FXML
    public void addNUTReset() {

        fibres.setText("");
        lipide.setText("");
        glucide.setText("");
        proteins.setText("");
        calories.setText("");


    }
    @FXML
    void generatePDFsamar(ActionEvent event) {
        List<Nutritions> reclamationsList = nutritionsServices.getAllData();
        String fileName = "Nutrition";

        try {
            nutritionsServices.savePDF(fileName); // Enregistre le fichier PDF avant la génération
            nutritionsServices.generatePDF(reclamationsList, fileName);
            showAlert("Succès", "Le fichier PDF a été généré et enregistré avec succès.", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert("Erreur", "Existe déjà : " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    private void showAlert(String message, String s, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //lors de la modification  on select  les text field
    @FXML
    public void selectNut() {
        Nutritions rec = (Nutritions) listeviewnutr.getSelectionModel().getSelectedItem();
        if (rec != null) {
            // Afficher les valeurs dans les champs texte
            fibres.setText(String.valueOf(rec.getFibres()));
            calories.setText(String.valueOf(rec.getCalories()));
            glucide.setText(String.valueOf(rec.getGlucides()));
            proteins.setText(String.valueOf(rec.getProteines()));
            lipide.setText(String.valueOf(rec.getLipides()));
            afficherCodeQr(rec);

        }
    }
    //afichher
    public void afficherNutrition() {
        // Récupérer toutes les données de nutrition de la base de données
        List<Nutritions> nutritionList = nutritionsServices.getAllData();

        // Créer une liste observable à partir de la liste de données de nutrition
        ObservableList<Nutritions> observableNutritionList = FXCollections.observableArrayList(nutritionList);

        // Effacer les éléments existants dans le ListView
        listeviewnutr.getItems().clear();

        // Ajouter les nouveaux éléments à la ListView
        listeviewnutr.getItems().addAll(observableNutritionList);
        afficherCodeQr(observableNutritionList.get(0));

        // Configurer la cellule de liste personnalisée pour afficher les détails de chaque nutrition
        listeviewnutr.setCellFactory(param -> new ListCell<Nutritions>() {
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

    private void afficherCodeQr(Nutritions nutrition) {

        String nutritionDetails =
                " Fibres: " + nutrition.getFibres() +
                        "\n Glucides: " + nutrition.getGlucides() +
                        "\n Protéines: " + nutrition.getProteines() +
                        "\n Lipides: " + nutrition.getLipides() +
                        "\n Calories: " + nutrition.getCalories();
        byte[] qrCodeBytes = QRCode.from(nutritionDetails).to(ImageType.PNG).stream().toByteArray();
        Image qrCodeImage = new Image(new ByteArrayInputStream(qrCodeBytes));
        codeQrImage.setImage(qrCodeImage);


    }

    //ajouter
    @FXML
    void addNut(ActionEvent event) {
        try {
            // Vérifiez si les champs contiennent des valeurs valides
            if (fibres.getText().isEmpty()) {
                controlefib.setText("Le champ Fibres ne peut pas être vide.");
                return;
            }
            if (calories.getText().isEmpty()) {
                controlecal.setText("Le champ Calories ne peut pas être vide.");
                return;
            }
            if (glucide.getText().isEmpty()) {
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
            }

            // Si les champs ne sont pas vides, convertissez-les en entiers
            int fibresValue = Integer.parseInt(fibres.getText());
            int caloriesValue = Integer.parseInt(calories.getText());
            int glucideValue = Integer.parseInt(glucide.getText());
            int proteinsValue = Integer.parseInt(proteins.getText());
            int lipideValue = Integer.parseInt(lipide.getText());

            // Réinitialisez les messages de contrôle
            controlefib.setText("");
            controlecal.setText("");
            controleg.setText("");
            proteinscontr.setText("");
            controlelip.setText("");

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
            listeviewnutr.getItems().clear();
            listeviewnutr.getItems().addAll(nutritionsList);
        } catch (NumberFormatException e) {
            // Gérez l'exception si l'une des valeurs n'est pas un entier valide
            System.err.println("Erreur : Assurez-vous que tous les champs sont remplis avec des valeurs numériques valides.");
            e.printStackTrace();
        }
    }
    //
    @FXML
    void updateNUT(ActionEvent event) {
        // Initialiser les valeurs par défaut
        int fibresValue = 0;
        int caloriesValue = 0;
        int glucideValue = 0;
        int proteinsValue = 0;
        int lipideValue = 0;
        boolean isError = false;

        // Vérifier si les champs sont vides et s'ils contiennent des valeurs numériques valides
        if (fibres.getText().isEmpty()) {
            controlefib.setText("Le champ Fibres ne peut pas être vide.");
            isError = true;
        } else {
            try {
                fibresValue = Integer.parseInt(fibres.getText());
            } catch (NumberFormatException e) {
                controlefib.setText("Veuillez entrer une valeur numérique valide.");
                isError = true;
            }
        }

        if (calories.getText().isEmpty()) {
            controlecal.setText("Le champ Calories ne peut pas être vide.");
            isError = true;
        } else {
            try {
                caloriesValue = Integer.parseInt(calories.getText());
            } catch (NumberFormatException e) {
                controlecal.setText("Veuillez entrer une valeur numérique valide.");
                isError = true;
            }
        }

        if (glucide.getText().isEmpty()) {
            controleg.setText("Le champ Glucides ne peut pas être vide.");
            isError = true;
        } else {
            try {
                glucideValue = Integer.parseInt(glucide.getText());
            } catch (NumberFormatException e) {
                controleg.setText("Veuillez entrer une valeur numérique valide.");
                isError = true;
            }
        }

        if (proteins.getText().isEmpty()) {
            proteinscontr.setText("Le champ Protéines ne peut pas être vide.");
            isError = true;
        } else {
            try {
                proteinsValue = Integer.parseInt(proteins.getText());
            } catch (NumberFormatException e) {
                proteinscontr.setText("Veuillez entrer une valeur numérique valide.");
                isError = true;
            }
        }

        if (lipide.getText().isEmpty()) {
            controlelip.setText("Le champ Lipides ne peut pas être vide.");
            isError = true;
        } else {
            try {
                lipideValue = Integer.parseInt(lipide.getText());
            } catch (NumberFormatException e) {
                controlelip.setText("Veuillez entrer une valeur numérique valide.");
                isError = true;
            }
        }

        if (isError) {
            return; // Sortir de la méthode si une erreur est détectée
        }

        Nutritions rec = (Nutritions) listeviewnutr.getSelectionModel().getSelectedItem();
        if (rec != null) {
            // Mettre à jour les valeurs de l'objet Nutritions sélectionné
            rec.setFibres(fibresValue);
            rec.setCalories(caloriesValue);
            rec.setGlucides(glucideValue);
            rec.setProteines(proteinsValue);
            rec.setLipides(lipideValue);

            // Appeler la méthode du service pour mettre à jour l'objet Nutritions
            nutritionsServices.updateEntity(rec);
            afficherNutrition();
            addNUTReset();
        } else {
            controlelip.setText("Veuillez sélectionner une nutrition à mettre à jour.");
        }
    }




    @FXML
    void deleteNut(ActionEvent event) {
        // Get the selected user from the table view
        Nutritions selectedUser = (Nutritions) listeviewnutr.getSelectionModel().getSelectedItem();

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
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette réclamation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If user confirms deletion, call the service method to delete the user
            nutritionsServices.deleteEntity(selectedUser);

            // Refresh the table view after deleting user
            listeviewnutr.getItems().remove(selectedUser);

            // Clear input fields after deleting user
            addNUTReset();
        }
    }

//bouton de linterface REcette

    @FXML
    void handleRecetteButton(ActionEvent event) {
        try {
            // Charger la nouvelle interface FXML
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Recette.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la nouvelle interface chargée
            Scene scene = new Scene(root);

            // Obtenir le stage (fenêtre) actuel à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Changer la scène du stage pour afficher la nouvelle interface
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert ADD_RDV != null : "fx:id=\"ADD_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_Reclamation != null : "fx:id=\"ADD_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert ADD_Rectt != null : "fx:id=\"ADD_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_user != null : "fx:id=\"ADD_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Age_afficher != null : "fx:id=\"Age_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Ajouter_Rapport != null : "fx:id=\"Ajouter_Rapport\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_RDV != null : "fx:id=\"Clear_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_Reclamation != null : "fx:id=\"Clear_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
      //  assert Clear_Rectt != null : "fx:id=\"Clear_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_user != null : "fx:id=\"Clear_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert repponse != null : "fx:id=\"ajouterReponse\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_RDV != null : "fx:id=\"Delete_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_Reclamation != null : "fx:id=\"Delete_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert Delete_Rectt != null : "fx:id=\"Delete_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_user != null : "fx:id=\"Delete_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
    //    assert EmailRDV_affichage != null : "fx:id=\"EmailRDV_affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ID_user != null : "fx:id=\"ID_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert NomAfficher != null : "fx:id=\"NomAfficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
   //     assert NomRDV_Affichage != null : "fx:id=\"NomRDV_Affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
   //     assert Nom_1 != null : "fx:id=\"Nom_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
  //      assert Nom_2 != null : "fx:id=\"Nom_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Prenom_afficher != null : "fx:id=\"Prenom_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert R_Ajou != null : "fx:id=\"R_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert RapportRDV_affichage != null : "fx:id=\"RapportRDV_affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Role_afficher != null : "fx:id=\"Role_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Search_RDV != null : "fx:id=\"Search_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Searchuser != null : "fx:id=\"Searchuser\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_RDV != null : "fx:id=\"Update_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert addnutri != null : "fx:id=\"Update_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_Reclamation != null : "fx:id=\"Update_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
       // assert Update_Rectt != null : "fx:id=\"Update_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_user != null : "fx:id=\"Update_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert User_form != null : "fx:id=\"User_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert age_ajou != null : "fx:id=\"age_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
      //  assert category_1 != null : "fx:id=\"category_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert category_2 != null : "fx:id=\"category_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert date_rAjou != null : "fx:id=\"date_rAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert date_reclAjou != null : "fx:id=\"date_reclAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert descriptionAjou != null : "fx:id=\"descriptionAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_afficher != null : "fx:id=\"email_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_ajou != null : "fx:id=\"email_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recette != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert heur_Ajou != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
 //       assert averageRatingLabel != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
 //       assert numberOfVotesLabel != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert id_R != null : "fx:id=\"id_R\" was not injected: check your FXML file 'Rendezvous.fxml'.";
   //     assert ingredient_1 != null : "fx:id=\"ingredient_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
//        assert ingredient_2 != null : "fx:id=\"ingredient_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert updatenutr != null : "fx:id=\"main_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert selectNut != null : "fx:id=\"main_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert nom_ajou != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert fibres != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert proteins != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert lipide != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert glucide != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert calories != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert statrecl != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";


        assert prenom_ajou != null : "fx:id=\"prenom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
    //    assert prioriterecAjou != null : "fx:id=\"prioriterecAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert role_ajou != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
     //   assert search_Rcc1 != null : "fx:id=\"search_Rcc1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert search_Recl != null : "fx:id=\"search_Recl\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_afficher != null : "fx:id=\"sexe_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_ajou != null : "fx:id=\"sexe_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert user_page != null : "fx:id=\"user_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert user_tableview != null : "fx:id=\"user_tableview\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Listview_RDV != null : "fx:id=\"Listview_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert deletenutri != null : "fx:id=\"Listview_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert listeviewnutr != null : "fx:id=\"listeviewnutr\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Listview_Reclamation != null : "fx:id=\"Listview_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert desccontrole != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert datecontrole != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert pdfsamar != null : "fx:id=\"comb\" was not injected: check your FXML file 'Rendezvous.fxml'.";

        assert comb != null : "fx:id=\"comb\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        comb.getItems().addAll("haute ", "moyenne", "basse");


        assert proteinscontr != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert controleg != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert controlecal != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert controlefib != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert controlelip != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert pdfrecla != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";






        afficherNutrition();


    }


    @FXML
    public void chercherNut(ActionEvent actionEvent) {
        System.out.println("Im in AI fn");
        String aiInputText=AiInput.getText();
        String query="";
        try {
             query = URLEncoder.encode(aiInputText, "UTF-8");
        }catch (Exception e){
            System.out.println("can't incode query");
        }
        String url = "https://api.api-ninjas.com/v1/nutrition?query=" + query;
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Api-Key", "nRfbxRkN98EE3SZeuTrm5g==tKeMDJsDHPgVfzJ5");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONArray jsonArray = new JSONArray(response.toString());
                double calories=0;
                double protein_g=0;
                double fiber_g=0;
                double fat_total_g=0;
                double carbohydrates_total_g=0;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    calories=calories+jsonObject.getDouble("calories");
                    protein_g=protein_g+jsonObject.getDouble("protein_g");
                    fiber_g=fiber_g+jsonObject.getDouble("fiber_g");
                    fat_total_g=fat_total_g+jsonObject.getDouble("fat_total_g");
                    carbohydrates_total_g=carbohydrates_total_g+jsonObject.getDouble("carbohydrates_total_g");


                }
                fibres.setText(String.valueOf((int)fiber_g));
                this.calories.setText(String.valueOf((int)calories));
                proteins.setText(String.valueOf((int)protein_g));
                lipide.setText(String.valueOf((int)fat_total_g));
                glucide.setText(String.valueOf((int)carbohydrates_total_g));


                // Example: Display the JSON response in TextArea
            } else {
                System.out.println(connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}











