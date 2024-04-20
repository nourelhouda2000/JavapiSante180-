package controllers;

import entities.Exercice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Activiteservice;
import entities.Activite;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

import java.sql.SQLException;

public class ModifierActivite {

    @FXML
    private TextField idnoma;

    @FXML
    private TextField iddesa;

    @FXML
    private TextField idcata;

    @FXML
    private ComboBox<String> idniva;

    // Field to store the ID of the activity to be modified
    private int activiteId;

    // Method to set the data of the activity to be modified
    public void setActiviteData(Activite activite) {
        activiteId = activite.getId(); // Assuming you have getId method in your Activite class
        idnoma.setText(activite.getNom());
        iddesa.setText(activite.getDescription());
        idcata.setText(activite.getCategorie());
        idniva.setValue(activite.getNiveau());
    }


    @FXML
    void retournerAffichage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Afficher_Activite.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void modifierActivite() {
        // Get the modified data from the text fields and combo box
        String nom = idnoma.getText();
        String description = iddesa.getText();
        String categorie = idcata.getText();
        String niveau = idniva.getValue();

        // Validate if any required field is empty
        if (nom.isEmpty() || description.isEmpty() || categorie.isEmpty() || niveau == null) {
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs !");
            return;
        }

        // Validate activity name and description length
        if (nom.length() > 22) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "Le nom de l'activité ne peut pas dépasser 22 caractères !");
            return;
        }

        if (description.length() < 5) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "La description de l'activité doit avoir au moins 5 caractères !");
            return;
        }

        if (nom.length() < 3) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "Le nom de l'activité doit avoir au moins 3 caractères !");
            return;
        }


        // Create an instance of ActiviteService
        Activiteservice activiteService = new Activiteservice();

        // Create a new Activite object with the modified data
        Activite activite = new Activite(activiteId, nom, description, categorie, niveau);

        // Call the updateEntity method to update the activity in the database
        activiteService.updateEntity(activite);

        // Show success message
        showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "L'activité a été modifiée avec succès.");

    }

    @FXML
    private void saveActiviteData(ActionEvent event) {
        // Récupérer les détails de l'activité à partir des champs de formulaire
        String nomActivite = idnoma.getText();
        String description = iddesa.getText();
        String categorie = idcata.getText();
        String niveau = idniva.getValue();

        // Valider si un champ obligatoire est vide
        if (nomActivite.isEmpty() || description.isEmpty() || categorie.isEmpty() || niveau == null) {
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs !");
            return;
        }

        // Valider la longueur du nom de l'activité
        if (nomActivite.length() > 22) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "Le nom de l'activité ne peut pas dépasser 22 caractères !");
            return;
        }

        // Valider la longueur de la description de l'activité
        if (description.length() < 3) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "La description de l'activité doit avoir au moins 3 caractères !");
            return;
        }

        // Créer une instance de Activiteservice
        Activiteservice activiteService = new Activiteservice();

        // Créer un nouvel objet Activite avec les données modifiées
        Activite activite = new Activite(activiteId, nomActivite, description, categorie, niveau);

        // Appeler la méthode updateEntity pour mettre à jour l'activité dans la base de données
        activiteService.updateEntity(activite);

        // Afficher un message de réussite
        showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "L'activité a été modifiée avec succès.");

    }


    // Method to show an alert message
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
