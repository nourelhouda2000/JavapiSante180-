package controllers;

import services.UserServices;

import entities.Rapport;
import entities.Rendezvous;

import java.net.URL;

import entities.User;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.RapportServices;
import services.RendezvousServices;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.layout.AnchorPane;
import services.UserServices;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import services.UserServices;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.input.MouseEvent;
import javafx.scene.chart.BubbleChart;


import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

import services.UserServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.MyDB;


public class RendezvousControllers implements Initializable {
    private ObservableList<Rendezvous> rendezvousList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();

    private UserServices userServices = new UserServices();
    private RendezvousServices rendezvousServices = new RendezvousServices();
    private RapportServices rapportServices = new RapportServices();
    private ObservableList<Rendezvous> observableRendezvousList;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ADD_RDV;
    @FXML
    private Button Ajouter_Rapport;
    @FXML
    private Button ADD_Reclamation;

    @FXML
    private Button ADD_Rectt;

    @FXML
    private Button Clear_Reclamation;
    @FXML
    private Button Clear_Rectt;
    @FXML
    private Button Delete_Reclamation;

    @FXML
    private Button Delete_Rectt;

    @FXML
    private Button select_RDV;
    @FXML
    private Button ADD_user;

    @FXML
    private ListView<Rendezvous> Listview_RDV;
    @FXML
    private Button Clear_RDV;

    @FXML
    private Button Clear_user;
    @FXML
    private Button Reff_RDV;


    @FXML
    private Button Select_user;
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
    private AnchorPane Reclamation_form;

    @FXML
    private Button Reclamationpage;

    @FXML
    private Button Home_page;
    @FXML
    private DatePicker datePicker_RDV;


    @FXML
    private TextField id_R;
    @FXML
    private Button Edit_profil;
    @FXML
    private TextField ID_user1;
    @FXML
    private Label NOMUSER;

    @FXML
    private Button Updaterapport;
    @FXML
    private Button Update_Reclamation;

    @FXML
    private Button Update_Rectt;

    @FXML
    private AnchorPane RDV_form;

    @FXML
    private Button Rendezvouspage;

    @FXML
    private TextField role_ajou;

    @FXML
    private ListView<?> Listview_Reclamation;


    @FXML
    private TextField sexe_ajou;

    @FXML
    private TextField date_reclAjou;

    @FXML
    private TextField descriptionAjou;


    @FXML
    private TableColumn<?, ?> ingredient_1;

    @FXML
    private TextField ingredient_2;

    @FXML
    private TextField sexe_profil;

    @FXML
    private TextField role_profil;
    @FXML
    private TextField prenom_profil;
    @FXML
    private TextField nom_profil;


    @FXML
    private TextField email_profil;

    @FXML
    private TextField age_profil;
    @FXML
    private Button Profil_page;
    @FXML
    private AnchorPane Profil_form;
    @FXML
    private TextField Password_profil;

    @FXML
    private TextField ID_user;


    @FXML
    private TextField prioriterecAjou;

    @FXML
    private TextField Search_RDV;

    @FXML
    private TextField Searchuser;
    @FXML
    private TableColumn<?, ?> Nom_1;

    @FXML
    private TextField Nom_2;
    @FXML
    private Button Update_RDV;
    @FXML
    private ListView<User> Listview_user;
    @FXML
    private Button Update_user;

    @FXML
    private AnchorPane User_form;

    @FXML
    private TextField age_ajou;

    @FXML
    private TextField search_Rcc1;

    @FXML
    private TextField search_Recl;

    @FXML
    private Button close;

    @FXML
    private Button pdf_rdv;
    @FXML
    private TableColumn<?, ?> category_1;

    @FXML
    private TextField category_2;

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
    private AnchorPane main_form;
    @FXML
    private TableColumn<User, String> EmailRDV_affichage;
    @FXML
    private TableColumn<User, String> NomRDV_Affichage;
    @FXML
    private TableColumn<User, String> RapportRDV_affichage;

    @FXML
    private Button minimize;

    @FXML
    private TextField nom_ajou;

    @FXML
    private TextField prenom_ajou;


    @FXML
    private Button user_page;

    @FXML
    private BubbleChart<?, ?> Charts;
    @FXML
    private PieChart gender_piechart;
    @FXML
    private BarChart<String, Number> age_barchart;

    @FXML
    private CheckBox checkpwd_edit;

    @FXML
    private PasswordField showpwd_edit;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Rendezvous rendezvous;


    private UserServices userService = new UserServices();



    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


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
    public void switchForm(ActionEvent event) {


        if (event.getSource() == Profil_page) {
            Profil_form.setVisible(true);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Profil_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Rendezvouspage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);

            RDV_form.setVisible(true);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Rendezvouspage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Home_page) {
            Profil_form.setVisible(false);
            home_form.setVisible(true);
            RDV_form.setVisible(false);

            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Home_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Rendezvouspage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(true);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);

            Rendezvouspage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == user_page) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(true);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(false);
            user_page.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Recettepage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(true);
            Reclamation_form.setVisible(false);
            Recettepage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
            Reclamationpage.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Reclamationpage) {
            Profil_form.setVisible(false);
            home_form.setVisible(false);
            RDV_form.setVisible(false);
            User_form.setVisible(false);
            Recette_form.setVisible(false);
            Reclamation_form.setVisible(true);

            Reclamationpage.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            Profil_page.setStyle("-fx-background-color:transparent");
            Home_page.setStyle("-fx-background-color:transparent");
            Rendezvouspage.setStyle("-fx-background-color:transparent");
            Recettepage.setStyle("-fx-background-color:transparent");
            user_page.setStyle("-fx-background-color:transparent");
        }


    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////RDVnour//////////////////////////////////////


    public void addRDVReset() {
        datePicker_RDV.setValue(null);
        heur_Ajou.setText("");
    }


    public void selectRendezVous() {
        Rendezvous rendezvous = Listview_RDV.getSelectionModel().getSelectedItem();
        if (rendezvous != null) {
            // Utilisez la valeur de la date du rendez-vous pour initialiser le DatePicker
            datePicker_RDV.setValue(LocalDate.parse(rendezvous.getDate_r()));

            // Affichez l'heure du rendez-vous
            heur_Ajou.setText(rendezvous.getHeur());
            id_R.setText(String.valueOf(rendezvous.getIdR()));
        }
    }


    // Constructor
    public RendezvousControllers() {
        // Initialize the rendezvous object
        this.rendezvous = new Rendezvous();

    }
    /*@FXML
    void addRDV(ActionEvent event) {
        // Get the currently logged-in user ID or fetch it from wherever it's stored
        int userId = 1; // Assuming you have a method to retrieve the user ID

        // Create a new Rendezvous object with data from UI fields
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setDate_r(date_rAjou.getText());
        rendezvous.setHeur(heur_Ajou.getText());

        // Call the service method to add the rendezvous
        rendezvousServices.addRendezvous2(rendezvous, userId);
        addRDVShowListData();
        addRDVReset();


    }*/

    @FXML
    void addRDV(ActionEvent event) {
        // Get the currently logged-in user ID or fetch it from wherever it's stored
        int userId = 1; // Assuming you have a method to retrieve the user ID

        // Create a new Rendezvous object with data from UI fields
        Rendezvous rendezvous = new Rendezvous();
        // Utilisez la valeur sélectionnée dans le DatePicker pour définir la date
        rendezvous.setDate_r(datePicker_RDV.getValue().toString());
        rendezvous.setHeur(heur_Ajou.getText());

        // Call the service method to add the rendezvous
        rendezvousServices.addRendezvous2(rendezvous, userId);
        addRDVShowListData();

    }


    @FXML
    void updateRDV(ActionEvent event) {
        Alert alert;
        if (datePicker_RDV.getValue() == null || heur_Ajou.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date and fill all blank fields");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Update Employee ID: " + id_R.getText() + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Rendezvous rendezvous = new Rendezvous();
                    rendezvous.setIdR(Integer.parseInt(id_R.getText()));
                    // Utilisez la valeur sélectionnée dans le DatePicker pour définir la nouvelle date
                    rendezvous.setDate_r(datePicker_RDV.getValue().toString());
                    rendezvous.setHeur(heur_Ajou.getText());
                    // Call the service method to update the rendezvous
                    rendezvousServices.updateRendezvous(rendezvous);
                    addRDVShowListData();
                    addRDVReset();
                }
            });
        }
    }


    @FXML
    void deleteRDV(ActionEvent event) {
        // Get the selected user from the table view
        Rendezvous selectedRDV = Listview_RDV.getSelectionModel().getSelectedItem();

        // Check if a user is selected
        if (selectedRDV == null) {
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
            // If user confirms deletion, call the service method to delete the user
            rendezvousServices.deleteRendezvous(selectedRDV);

            // Clear input fields after deleting user

            addRDVShowListData();
            addRDVReset();
        }
    }

////raporrt/////////////////////////////////////////////////

    @FXML
    void ajouterRapport(ActionEvent event) {
        try {
            // Récupérer le rendez-vous sélectionné dans le ListView
            Rendezvous selectedRendezvous = Listview_RDV.getSelectionModel().getSelectedItem();

            // Vérifier si un rendez-vous est sélectionné
            if (selectedRendezvous == null) {
                // Si aucun rendez-vous n'est sélectionné, afficher une alerte
                showAlert("Aucun rendez-vous sélectionné", "Veuillez sélectionner un rendez-vous.", Alert.AlertType.WARNING);
                return; // Sortir de la méthode
            }

            // Récupérer l'ID du rendez-vous sélectionné
            int idRendezvous = selectedRendezvous.getIdR();

            // Vérifier si un rapport existe déjà pour le rendez-vous sélectionné
            if (rapportServices.entityExistsInRendezvous(idRendezvous)) {
                // Si un rapport existe, afficher une alerte
                showAlert("Rapport existant", "Un rapport existe déjà pour ce rendez-vous.", Alert.AlertType.INFORMATION);
            } else {
                // Si aucun rapport n'existe, prendre l'ID du rendez-vous pour ajouter un nouveau rapport
                // Appeler la méthode pour ouvrir le fichier Rapport.fxml dans une nouvelle fenêtre
                openRapportWindow(idRendezvous);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger la fenêtre de rapport.", Alert.AlertType.ERROR);
        }
    }

    private void openRapportWindow(int idRendezvous) throws IOException {
        // Charger le fichier Rapport.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Rapport.fxml"));
        Parent root = loader.load();

        // Passer l'ID du rendez-vous sélectionné au contrôleur RapportControllers
        RapportControllers rapportController = loader.getController();
        rapportController.setIdR(idRendezvous);

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Créer un nouveau stage et définir la scène
        Stage stage = new Stage();
        stage.setScene(scene);

        // Afficher la fenêtre
        stage.show();
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    @FXML
    void Rapportupdate(ActionEvent event) {
        try {
            // Get the selected rendezvous from the table view
            Rendezvous selectedRendezvous = Listview_RDV.getSelectionModel().getSelectedItem();

            // Check if a rendezvous is selected
            if (selectedRendezvous == null) {
                // If no rendezvous is selected, show an alert
                showAlert("Aucun rendez-vous sélectionné", "Veuillez sélectionner un rendez-vous.", Alert.AlertType.WARNING);
                return; // Exit the method
            }

            // Get the ID of the selected rendezvous
            int idRendezvous = selectedRendezvous.getIdR();

            // Check if a report already exists for the selected rendezvous
            if (rapportServices.entityExistsInRendezvous(idRendezvous)) {
                // If a report exists, open the UpdateRapport.fxml file in a new window
                openUpdateRapportWindow(idRendezvous);
            } else {
                // If no report exists, show an alert
                showAlert("Aucun rapport existant", "Aucun rapport n'existe pour ce rendez-vous.", Alert.AlertType.INFORMATION);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger la fenêtre de mise à jour du rapport.", Alert.AlertType.ERROR);
        }
    }


    private void openUpdateRapportWindow(int idRendezvous) throws IOException {
        // Load the UpdateRapport.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("UpdateRapport.fxml"));
        Parent root = loader.load();

        // Pass the ID of the selected rendezvous to the UpdateRapportController
        RapportControllers updateRapportController = loader.getController();
        updateRapportController.setIdR(idRendezvous);

        // Get the existing rapport for the selected rendezvous
        Rapport existingRapport = rapportServices.getRapportByRendezvousId(idRendezvous);
        if (existingRapport != null) {
            // Pass the existing rapport to the UpdateRapportController
            updateRapportController.setExistingRapport(existingRapport);
        }

        // Create a new scene
        Scene scene = new Scene(root);

        // Create a new stage and set the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Show the window
        stage.show();
    }


    public void addRDVShowListData() {
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();

        // Créer une liste observable à partir de la liste de rendez-vous
        ObservableList<Rendezvous> observableRendezvousList = FXCollections.observableArrayList(rendezvousList);

        // Définir les éléments dans le ListView
        Listview_RDV.setItems(observableRendezvousList);

        // Configurer la cellule de liste personnalisée pour afficher les détails de chaque rendez-vous
        Listview_RDV.setCellFactory(param -> new ListCell<Rendezvous>() {
            @Override
            protected void updateItem(Rendezvous rendezvous, boolean empty) {
                super.updateItem(rendezvous, empty);

                if (empty || rendezvous == null) {
                    setText(null);
                } else {

                    // Créer une chaîne représentant les détails du rendez-vous
                    String rendezvousDetails =
                            " Date: " + rendezvous.getDate_r() +
                                    "\n Heure: " + rendezvous.getHeur() +
                                    "\n Nom de l'utilisateur: " + rendezvous.getNomuser() +
                                    "\n Email de l'utilisateur: " + rendezvous.getEmail() +
                                    "\n Rapport: " + rendezvous.getRapport();
                    setText(rendezvousDetails);
                }
            }
        });
    }


    @FXML
    public void rechercherRendezvous() {
        String searchTerm = Search_RDV.getText().trim().toLowerCase();
        if (!searchTerm.isEmpty()) {
            // Créer un prédicat pour filtrer les rendez-vous en fonction du terme de recherche
            Predicate<Rendezvous> rendezvousFilter = rendezvous ->
                    rendezvous.getDate_r().toLowerCase().contains(searchTerm) ||
                            rendezvous.getHeur().toLowerCase().contains(searchTerm) ||
                            rendezvous.getNomuser().toLowerCase().contains(searchTerm) ||
                            rendezvous.getEmail().toLowerCase().contains(searchTerm) ||
                            rendezvous.getRapport().toLowerCase().contains(searchTerm);

            // Créer une FilteredList à partir de la liste observable des rendez-vous
            FilteredList<Rendezvous> filteredList = observableRendezvousList.filtered(rendezvousFilter);

            // Mettre à jour la liste affichée dans le ListView
            Listview_RDV.setItems(filteredList);
        } else {
            // Si le terme de recherche est vide, afficher tous les rendez-vous
            Listview_RDV.setItems(observableRendezvousList);
        }
    }
////////////////////////////////////////////metrdv pdf///////////////

    /*
        @FXML
        void generatePDF11(ActionEvent event) {
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
    */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////usernesrine////////////////////////////////////////


    public void addUserReset() {
        nom_ajou.setText("");
        prenom_ajou.setText("");
        age_ajou.setText("");
        email_ajou.setText("");
        // Clearing the ComboBox values
        role_ajou.setText("");
        sexe_ajou.setText("");
    }


    public void selectUser() {
        // Obtenir l'utilisateur sélectionné dans le TableView
        User user = Listview_user.getSelectionModel().getSelectedItem();

        // Vérifier si un utilisateur est sélectionné
        if (user != null) {
            // Mettre à jour les champs de texte avec les détails de l'utilisateur sélectionné
            ID_user.setText(String.valueOf(user.getIdUser()));
            nom_ajou.setText(user.getNomuser());
            prenom_ajou.setText(user.getPrenomuser());
            age_ajou.setText(user.getAgeuser());
            sexe_ajou.setText(user.getSexe());
            email_ajou.setText(user.getEmail());
            // mdp_ajou.setText(user.getMdp());

            // Convertir le rôle de l'utilisateur en texte correspondant
            String roleText = "";
            switch (user.getRole()) {
                case 0:
                    roleText = "admin";
                    break;
                case 1:
                    roleText = "doctor";
                    break;
                case 2:
                    roleText = "patient";
                    break;
                default:
                    // Handle invalid role
                    System.err.println("Invalid role value: " + user.getRole());
            }

            // Afficher le rôle dans le champ role_ajou
            role_ajou.setText(roleText);
        } else {
            // Si aucun utilisateur n'est sélectionné, afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un utilisateur.");
            alert.showAndWait();
        }
    }


    @FXML
    void addUser(ActionEvent event) {
        if (validateFields()) {
            // Create a new User object with data from UI fields
            User user = new User();
            user.setNomuser(nom_ajou.getText());
            user.setPrenomuser(prenom_ajou.getText());
            user.setAgeuser(age_ajou.getText());
            user.setSexe(sexe_ajou.getText());
            user.setEmail(email_ajou.getText());

            // Get the role text from the role_ajou field
            String roleText = role_ajou.getText();
            int roleValue;

            // Map role text to integer value
            switch (roleText.toLowerCase()) {
                case "admin":
                    roleValue = 0;
                    break;
                case "doctor":
                    roleValue = 1;
                    break;
                case "patient":
                    roleValue = 2;
                    break;
                default:
                    // Handle invalid role input
                    showAlert("Rôle invalide", "Le rôle spécifié est invalide.");
                    return; // Exit the method as role value is invalid
            }

            // Set the role value in the User object
            user.setRole(roleValue);
            user.setMdp(""); // Assuming you don't handle password input here

            // Call the service method to add the user
            userServices.addUser(user);
            addUserShowListData();

            // Clear input fields after adding user
        } else {
            showAlert("Champs vides", "Veuillez remplir tous les champs obligatoires.");
        }
    }




    private boolean validateFields() {
        return !nom_ajou.getText().isEmpty() &&
                !prenom_ajou.getText().isEmpty() &&
                !age_ajou.getText().isEmpty() &&
                !sexe_ajou.getText().isEmpty() &&
                !email_ajou.getText().isEmpty() &&
                !role_ajou.getText().isEmpty();
    }

    @FXML
    void updateUser(ActionEvent event) {
        // Get the selected user from the table view
        User selectedUser = Listview_user.getSelectionModel().getSelectedItem();

        // Check if a user is selected
        if (selectedUser == null) {
            // If no user is selected, show an alert
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur.");
            return; // Exit the method
        }

        // Check if all required fields are filled
        if (validateFields()) {
            // Create a new User object with updated data from UI fields
            User updatedUser = new User();
            updatedUser.setIdUser(selectedUser.getIdUser());
            updatedUser.setNomuser(nom_ajou.getText());
            updatedUser.setPrenomuser(prenom_ajou.getText());
            updatedUser.setAgeuser(age_ajou.getText());
            updatedUser.setSexe(sexe_ajou.getText());
            updatedUser.setEmail(email_ajou.getText());
            updatedUser.setMdp(""); // Assuming you don't handle password input here

            // Parse role string to integer, you might need to change this logic depending on your UI
            int roleValue;

            // Map role text to integer value
            switch (role_ajou.getText().toLowerCase()) {
                case "admin":
                    roleValue = 0;
                    break;
                case "doctor":
                    roleValue = 1;
                    break;
                case "patient":
                    roleValue = 2;
                    break;
                default:
                    // Handle invalid role input
                    showAlert("Valeur de rôle invalide", "La valeur de rôle spécifiée est invalide.");
                    return; // Exit the method as role value is invalid
            }

            updatedUser.setRole(roleValue);

            // Call the service method to update the user
            userServices.updateUser(updatedUser);

            addUserShowListData();
            addUserReset(); // Clear input fields after updating user
        } else {
            showAlert("Champs vides", "Veuillez remplir tous les champs obligatoires.");
        }
    }



    // Method to retrieve the ID of the logged-in user
    private int getLoggedInUserId() {
        return UserServices.getInstance().getIdUser();
    }



    @FXML
    void EditUser(ActionEvent event) {
        if (validateFields1()) {
            User updatedUser = new User();
            updatedUser.setIdUser(getLoggedInUserId());
            updatedUser.setNomuser(nom_profil.getText());
            updatedUser.setPrenomuser(prenom_profil.getText());
            updatedUser.setMdp(Password_profil.getText());

            boolean success = userServices.updateUserpwdprofil1(updatedUser);

            if (success) {
                addUserShowListData();
                addUserReset();
                showAlert("Succès", "Les informations de l'utilisateur ont été mises à jour avec succès.");
            }
        }
    }

    @FXML
    void edit_check(ActionEvent event) {
        if (checkpwd_edit.isSelected()) {
            Password_profil.setText(showpwd_edit.getText());
            Password_profil.setVisible(true);
            showpwd_edit.setVisible(false);
        } else {
            showpwd_edit.setText(Password_profil.getText());
            Password_profil.setVisible(false);
            showpwd_edit.setVisible(true);
        }
    }


    private boolean validateFields1() {
        // Vérifie si les champs nom_profil, prenom_profil et Password_profil sont remplis
        return !nom_profil.getText().isEmpty() &&
                !prenom_profil.getText().isEmpty() &&
                !Password_profil.getText().isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void deleteUser(ActionEvent event) {
        // Get the selected user from the table view
        User selectedUser = Listview_user.getSelectionModel().getSelectedItem();

        // Check if a user is selected
        if (selectedUser == null) {
            // If no user is selected, show an alert
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur.");
            return; // Exit the method
        }

        // Show confirmation dialog before deleting user
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If user confirms deletion, call the service method to delete the user
            userServices.deleteUser(selectedUser);

            // Clear input fields after deleting user

            addUserShowListData();
            addUserReset();
        }
    }


    public void addUserShowListData() {
        // Obtenez la liste des utilisateurs avec les noms de rôle correspondants
        List<User> userList = userServices.getAllUsersWithRoleNames();

        // Créez une liste observable pour les utilisateurs
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);

        // Définissez les éléments de la ListView sur la liste observable des utilisateurs
        Listview_user.setItems(observableUserList);

        // Configurez la cellule de liste personnalisée pour afficher les détails de chaque utilisateur
        Listview_user.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);

                if (empty || user == null) {
                    setText(null);
                } else {
                    // Créez une chaîne représentant les détails de l'utilisateur
                    String userDetails = "\nNom: " + user.getNomuser() + ", \nPrénom: " + user.getPrenomuser() + ",\n Âge: " + user.getAgeuser() + ",\n Sexe: " + user.getSexe() + ", \nEmail: " + user.getEmail() + ",\n Rôle: " + user.getRoleAsString();
                    setText(userDetails);
                }
            }
        });
    }

    public void setNomUtilisateur(String nom, String prenom, String age, String sexe, int role, String email, String password) {
        NOMUSER.setText(nom);
        nom_profil.setText(nom);
        prenom_profil.setText(prenom);
        age_profil.setText(age);
        sexe_profil.setText(sexe);
        email_profil.setText(email);
        Password_profil.setText(password);
        role_profil.setText(getRoleAsString(role)); // Afficher le rôle sous forme de chaîne de caractères
    }

    private String getRoleAsString(int role) {
        switch (role) {
            case 0:
                return "admin";
            case 1:
                return "doctor";
            case 2:
                return "patient";
            default:
                return "Unknown";
        }
    }


    // Define a static method to load FXML files
    public static void loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RendezvousControllers.class.getResource(fxml));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void userCharts(MouseEvent event) {

    }

    @FXML
    void userCharts() {
        Map<String, Long> genderDistribution = userService.getGenderDistribution();

        // Create PieChart data
        PieChart.Data[] pieChartData = genderDistribution.entrySet().stream()
                .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                .toArray(PieChart.Data[]::new);

        // Add data to PieChart
        gender_piechart.getData().addAll(pieChartData);

        // Retrieve age distribution data
        Map<String, Long> ageDistribution = userService.getGenderDistribution(); // Implement this method in userService

        // Create BarChart data
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        ageDistribution.entrySet().forEach(entry -> series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue())));

        // Add data series to BarChart
        age_barchart.getData().add(series);
    }


    @FXML
    void backtolist(ActionEvent event) throws IOException {
        RendezvousControllers.loadFXML("/Rendezvous.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert ADD_RDV != null : "fx:id=\"ADD_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_Reclamation != null : "fx:id=\"ADD_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_Rectt != null : "fx:id=\"ADD_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_user != null : "fx:id=\"ADD_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Ajouter_Rapport != null : "fx:id=\"Ajouter_Rapport\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_RDV != null : "fx:id=\"Clear_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_Reclamation != null : "fx:id=\"Clear_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_Rectt != null : "fx:id=\"Clear_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_user != null : "fx:id=\"Clear_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_RDV != null : "fx:id=\"Delete_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_Reclamation != null : "fx:id=\"Delete_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_Rectt != null : "fx:id=\"Delete_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_user != null : "fx:id=\"Delete_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ID_user != null : "fx:id=\"ID_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ID_user1 != null : "fx:id=\"ID_user1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Listview_RDV != null : "fx:id=\"Listview_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Listview_Reclamation != null : "fx:id=\"Listview_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Listview_user != null : "fx:id=\"Listview_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert NOMUSER != null : "fx:id=\"NOMUSER\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Nom_1 != null : "fx:id=\"Nom_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Nom_2 != null : "fx:id=\"Nom_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Password_profil != null : "fx:id=\"Password_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Profil_form != null : "fx:id=\"Profil_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Profil_page != null : "fx:id=\"Profil_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert R_Ajou != null : "fx:id=\"R_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reff_RDV != null : "fx:id=\"Reff_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Search_RDV != null : "fx:id=\"Search_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Searchuser != null : "fx:id=\"Searchuser\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Select_user != null : "fx:id=\"Select_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_RDV != null : "fx:id=\"Update_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_Reclamation != null : "fx:id=\"Update_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_Rectt != null : "fx:id=\"Update_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_user != null : "fx:id=\"Update_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Updaterapport != null : "fx:id=\"Updaterapport\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert User_form != null : "fx:id=\"User_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert age_ajou != null : "fx:id=\"age_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert age_profil != null : "fx:id=\"age_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert category_1 != null : "fx:id=\"category_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert category_2 != null : "fx:id=\"category_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert datePicker_RDV != null : "fx:id=\"datePicker_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert date_reclAjou != null : "fx:id=\"date_reclAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert descriptionAjou != null : "fx:id=\"descriptionAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_ajou != null : "fx:id=\"email_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_profil != null : "fx:id=\"email_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert heur_Ajou != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert id_R != null : "fx:id=\"id_R\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ingredient_1 != null : "fx:id=\"ingredient_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ingredient_2 != null : "fx:id=\"ingredient_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert nom_ajou != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert nom_profil != null : "fx:id=\"nom_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert prenom_ajou != null : "fx:id=\"prenom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert prenom_profil != null : "fx:id=\"prenom_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert prioriterecAjou != null : "fx:id=\"prioriterecAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert role_ajou != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert role_profil != null : "fx:id=\"role_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert search_Rcc1 != null : "fx:id=\"search_Rcc1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert search_Recl != null : "fx:id=\"search_Recl\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert select_RDV != null : "fx:id=\"select_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_ajou != null : "fx:id=\"sexe_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_profil != null : "fx:id=\"sexe_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert user_page != null : "fx:id=\"user_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Edit_profil != null : "fx:id=\"Edit_profil\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert pdf_rdv != null : "fx:id=\"pdf_rdv\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Charts != null : "fx:id=\"Charts\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert checkpwd_edit != null : "fx:id=\"checkpwd_edit\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert showpwd_edit != null : "fx:id=\"showpwd_edit\" was not injected: check your FXML file 'Rendezvous.fxml'.";


        datePicker_RDV.setValue(LocalDate.now());
        observableRendezvousList = FXCollections.observableArrayList();
        // Ajouter un écouteur d'événements pour détecter les changements de date
        datePicker_RDV.setOnAction(event -> {
            LocalDate selectedDate = datePicker_RDV.getValue();
            System.out.println("Date sélectionnée : " + selectedDate);    });
        addRDVShowListData();
        addUserShowListData();

        // Ajouter un écouteur de changement pour le champ de recherche
        Search_RDV.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherRendezvous();
        });


    }






}




