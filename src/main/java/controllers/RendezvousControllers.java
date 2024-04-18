package controllers;


import entities.Rendezvous;
import entities.User;
import entities.Rapport;

import java.net.URL;

import javafx.fxml.Initializable;
import services.RapportServices;
import services.RendezvousServices;
import services.UserServices;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.List;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;




public class RendezvousControllers implements Initializable {
    private ObservableList<Rendezvous> rendezvousList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();

    private UserServices userServices = new UserServices();
    private RendezvousServices rendezvousServices = new RendezvousServices();
    private RapportServices rapportServices = new RapportServices();

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
    private Button ADD_user;

    @FXML
    private TableColumn<User, String> Age_afficher;

    @FXML
    private Button Clear_RDV;

    @FXML
    private Button Clear_user;

    @FXML
    private TableColumn<Rendezvous, String> Date_rAffichage;

    @FXML
    private Button Delete_RDV;

    @FXML
    private TextField mdp_ajou;

    @FXML
    private Button Delete_user;

    @FXML
    private TableColumn<Rendezvous, String> Heur_affichage;
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
    private TableColumn<Rendezvous, Integer> IDAffichage;

    @FXML
    private TextField id_R;

    @FXML
    private TableColumn<User, String> NomAfficher;

    @FXML
    private TableColumn<User, String> Prenom_afficher;

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
    private TableColumn<?, ?> date_AffiReclamation;

    @FXML
    private TextField sexe_ajou;

    @FXML
    private TextField date_reclAjou;

    @FXML
    private TextField descriptionAjou;

    @FXML
    private TableColumn<?, ?> description_aff;

    @FXML
    private TableView<Rendezvous> RendezvoustableView;


    @FXML
    private TableColumn<?, ?> ingredient_1;

    @FXML
    private TextField ingredient_2;

    @FXML
    private TableView<User> tableviewUser;
    @FXML
    private TableColumn<User, Integer> Role_afficher;
    @FXML
    private TextField ID_user;

    @FXML
    private TableColumn<?, ?> priorite_Affi;

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
    private TextField date_rAjou;
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
    private TableColumn<User, String> sexe_afficher;


    @FXML
    private Button user_page;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Rendezvous rendezvous;
    @FXML
    void initialize() {

    }


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


////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////RDVnour//////////////////////////////////////





    public void addRDVReset() {

        date_rAjou.setText("");
        heur_Ajou.setText("");

    }


    public void selectRendezVous() {
        Rendezvous rendezvous = RendezvoustableView.getSelectionModel().getSelectedItem();
        if (rendezvous != null) {
            id_R.setText(String.valueOf(rendezvous.getIdR()));
            date_rAjou.setText(rendezvous.getDate_r());
            heur_Ajou.setText(rendezvous.getHeur());

        }
    }

    // Constructor
    public RendezvousControllers() {
        // Initialize the rendezvous object
        this.rendezvous = new Rendezvous();

    }
    @FXML
    void addRDV(ActionEvent event) {
        // Get the currently logged-in user ID or fetch it from wherever it's stored
        int userId = 1; // Assuming you have a method to retrieve the user ID

        // Create a new Rendezvous object with data from UI fields
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setDate_r(date_rAjou.getText());
        rendezvous.setHeur(heur_Ajou.getText());

        // Call the service method to add the rendezvous
        rendezvousServices.addRendezvous2(rendezvous, userId);
        addRDVReset();


    }



    @FXML
    void updateRDV(ActionEvent event) {
        // Create a new Rendezvous object with updated data from UI fields


        Alert alert;
        if (date_rAjou.getText().isEmpty() || heur_Ajou.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
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
                    rendezvous.setDate_r(date_rAjou.getText());
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
        Alert alert;
        if (date_rAjou.getText().isEmpty() || heur_Ajou.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Employee ID: " + id_R.getText() + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Create a new Rendezvous object with ID from UI field
                    Rendezvous rendezvous = new Rendezvous();
                    rendezvous.setIdR(Integer.parseInt(id_R.getText()));

                    // Call the service method to delete the rendezvous
                    rendezvousServices.deleteRendezvous(rendezvous);



                    addRDVShowListData();
                    addRDVReset();
                }
            });
        }

    }



    @FXML
    void ajouterRapport(ActionEvent event) {
       /* try {
            // Get the selected rendezvous from the table view
            Rendezvous selectedRendezvous = RendezvoustableView.getSelectionModel().getSelectedItem();

            // Check if a rendezvous is selected
            if (selectedRendezvous == null) {
                // If no rendezvous is selected, show an alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucun rendez-vous sélectionné");
                alert.setHeaderText("Veuillez sélectionner un rendez-vous.");
                alert.showAndWait();
                return; // Exit the method
            }

            // Get the ID of the selected rendezvous
            int idRendezvous = selectedRendezvous.getIdR();

            // Initialize the rapport object (assuming it's already defined elsewhere)
            Rapport rapport = new Rapport();

            // Check if a report already exists for the selected rendezvous
            // Check if a report already exists for the selected rendezvous
            // Check if a report already exists for the selected rendezvous
            if (rapportServices.entityExistsInRendezvous(rapport, rapport.getIdR())) {
                // If a report exists, retrieve and display the existing report
                Rapport existingRapport = rapportServices.getRapportByRendezvousId(idRendezvous);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rapport existant");
                alert.setHeaderText("Un rapport existe déjà pour ce rendez-vous.");
                alert.setContentText("Contenu du rapport :\n" + existingRapport.getRapport());
                alert.showAndWait();
            } else {
                // If no report exists, open the Rapport.fxml file in a new window
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Rapport.fxml"));
                Parent root = loader.load();

                // Create a new scene
                Scene scene = new Scene(root);

                // Create a new stage and set the scene
                Stage stage = new Stage();
                stage.setScene(scene);

                // Show the window
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }



    public void addRDVShowListData() {
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();

        NomRDV_Affichage.setCellValueFactory(new PropertyValueFactory<>("nomuser"));

        Date_rAffichage.setCellValueFactory(new PropertyValueFactory<>("date_r"));
        Heur_affichage.setCellValueFactory(new PropertyValueFactory<>("heur"));
        EmailRDV_affichage.setCellValueFactory(new PropertyValueFactory<>("email"));
        RapportRDV_affichage.setCellValueFactory(new PropertyValueFactory<>("Rapport"));

        // Convertir la liste en ObservableList
        ObservableList<Rendezvous> observableRendezvousList = FXCollections.observableArrayList(rendezvousList);

        // Définir les éléments dans la TableView
        RendezvoustableView.setItems(observableRendezvousList);

        // Rafraîchir la TableView
        RendezvoustableView.refresh();
    }



    @FXML
    public void rechercherRendezvous() {
       /* String searchTerm = Search_RDV.getText().trim().toLowerCase(); // Obtenir le terme de recherche du champ de texte
        if (!searchTerm.isEmpty()) {
            FilteredList<Rendezvous> filteredList = rendezvousList.filtered(rendezvous ->
                    rendezvous.getDate_r().toLowerCase().contains(searchTerm) ||
                            rendezvous.getHeur().toLowerCase().contains(searchTerm) ||
                            rendezvous.getNomuser().toLowerCase().contains(searchTerm) ||
                            rendezvous.getEmail().toLowerCase().contains(searchTerm) ||
                            rendezvous.getRapport().toLowerCase().contains(searchTerm)
            );

            RendezvoustableView.setItems(filteredList);
        } else {
            // Si le champ de recherche est vide, afficher tous les rendez-vous
            RendezvoustableView.setItems(rendezvousList);
        }*/
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
        User user = tableviewUser.getSelectionModel().getSelectedItem();

        // Vérifier si un utilisateur est sélectionné
        if (user != null) {
            // Mettre à jour les champs de texte avec les détails de l'utilisateur sélectionné
            ID_user.setText(String.valueOf(user.getIdUser()));
            nom_ajou.setText(user.getNomuser());
            prenom_ajou.setText(user.getPrenomuser());
            age_ajou.setText(user.getAgeuser());
            sexe_ajou.setText(user.getSexe());
            email_ajou.setText(user.getEmail());
            mdp_ajou.setText(user.getMdp());

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
                System.err.println("Invalid role value: " + roleText);
                return; // Exit the method as role value is invalid
        }

        // Set the role value in the User object
        user.setRole(roleValue);
        user.setMdp(""); // Assuming you don't handle password input here

        // Call the service method to add the user
        userServices.addUser(user);
        addUserShowListData();
        addUserReset();
        // Clear input fields after adding user

    }



    @FXML
    void updateUser(ActionEvent event) {
        // Get the selected user from the table view
        User selectedUser = tableviewUser.getSelectionModel().getSelectedItem();

        // Check if a user is selected
        if (selectedUser == null) {
            // If no user is selected, show an alert
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur.");
            return; // Exit the method
        }

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
                System.err.println("Invalid role value: " + role_ajou.getText());
                return; // Exit the method as role value is invalid
        }

        updatedUser.setRole(roleValue);

        // Call the service method to update the user
        userServices.updateUser(updatedUser);

        addUserShowListData();
        addUserReset(); // Clear input fields after updating user
    }

    @FXML
    void deleteUser(ActionEvent event) {
        // Get the selected user from the table view
        User selectedUser = tableviewUser.getSelectionModel().getSelectedItem();

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

    // Method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addUserShowListData() {
        List<User> userList = userServices.getAllUsersWithRoleNames();

        NomAfficher.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        Prenom_afficher.setCellValueFactory(new PropertyValueFactory<>("prenomuser"));
        Age_afficher.setCellValueFactory(new PropertyValueFactory<>("ageuser"));
        sexe_afficher.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        email_afficher.setCellValueFactory(new PropertyValueFactory<>("email"));
        Role_afficher.setCellValueFactory(new PropertyValueFactory<>("roleAsString")); // Assuming you have a method to get role as string

        // Convert the list to ObservableList
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);

        // Set the items in the TableView
        tableviewUser.setItems(observableUserList);

        // Refresh the TableView
        tableviewUser.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert ADD_RDV != null : "fx:id=\"ADD_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_Reclamation != null : "fx:id=\"ADD_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_Rectt != null : "fx:id=\"ADD_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ADD_user != null : "fx:id=\"ADD_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Age_afficher != null : "fx:id=\"Age_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Ajouter_Rapport != null : "fx:id=\"Ajouter_Rapport\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_RDV != null : "fx:id=\"Clear_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_Reclamation != null : "fx:id=\"Clear_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_Rectt != null : "fx:id=\"Clear_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Clear_user != null : "fx:id=\"Clear_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Date_rAffichage != null : "fx:id=\"Date_rAffichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_RDV != null : "fx:id=\"Delete_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_Reclamation != null : "fx:id=\"Delete_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_Rectt != null : "fx:id=\"Delete_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Delete_user != null : "fx:id=\"Delete_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert EmailRDV_affichage != null : "fx:id=\"EmailRDV_affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Heur_affichage != null : "fx:id=\"Heur_affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Home_page != null : "fx:id=\"Home_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ID_user != null : "fx:id=\"ID_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert NomAfficher != null : "fx:id=\"NomAfficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert NomRDV_Affichage != null : "fx:id=\"NomRDV_Affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Nom_1 != null : "fx:id=\"Nom_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Nom_2 != null : "fx:id=\"Nom_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Prenom_afficher != null : "fx:id=\"Prenom_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert RDV_form != null : "fx:id=\"RDV_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert R_Ajou != null : "fx:id=\"R_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert RapportRDV_affichage != null : "fx:id=\"RapportRDV_affichage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recette_form != null : "fx:id=\"Recette_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Recettepage != null : "fx:id=\"Recettepage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamation_form != null : "fx:id=\"Reclamation_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Reclamationpage != null : "fx:id=\"Reclamationpage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Rendezvouspage != null : "fx:id=\"Rendezvouspage\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert RendezvoustableView != null : "fx:id=\"RendezvoustableView\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Role_afficher != null : "fx:id=\"Role_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Search_RDV != null : "fx:id=\"Search_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Searchuser != null : "fx:id=\"Searchuser\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_RDV != null : "fx:id=\"Update_RDV\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_Reclamation != null : "fx:id=\"Update_Reclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_Rectt != null : "fx:id=\"Update_Rectt\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert Update_user != null : "fx:id=\"Update_user\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert User_form != null : "fx:id=\"User_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert age_ajou != null : "fx:id=\"age_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert category_1 != null : "fx:id=\"category_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert category_2 != null : "fx:id=\"category_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert date_AffiReclamation != null : "fx:id=\"date_AffiReclamation\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert date_rAjou != null : "fx:id=\"date_rAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert date_reclAjou != null : "fx:id=\"date_reclAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert descriptionAjou != null : "fx:id=\"descriptionAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert description_aff != null : "fx:id=\"description_aff\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_afficher != null : "fx:id=\"email_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert email_ajou != null : "fx:id=\"email_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert heur_Ajou != null : "fx:id=\"heur_Ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert home_form != null : "fx:id=\"home_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert id_R != null : "fx:id=\"id_R\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ingredient_1 != null : "fx:id=\"ingredient_1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert ingredient_2 != null : "fx:id=\"ingredient_2\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert main_form != null : "fx:id=\"main_form\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert minimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert nom_ajou != null : "fx:id=\"nom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert prenom_ajou != null : "fx:id=\"prenom_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert priorite_Affi != null : "fx:id=\"priorite_Affi\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert prioriterecAjou != null : "fx:id=\"prioriterecAjou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert role_ajou != null : "fx:id=\"role_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert search_Rcc1 != null : "fx:id=\"search_Rcc1\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert search_Recl != null : "fx:id=\"search_Recl\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_afficher != null : "fx:id=\"sexe_afficher\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert sexe_ajou != null : "fx:id=\"sexe_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert user_page != null : "fx:id=\"user_page\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert tableviewUser != null : "fx:id=\"tableviewUser\" was not injected: check your FXML file 'Rendezvous.fxml'.";
        assert mdp_ajou != null : "fx:id=\"mdp_ajou\" was not injected: check your FXML file 'Rendezvous.fxml'.";

        addUserShowListData();
        addRDVShowListData();

    }






}




