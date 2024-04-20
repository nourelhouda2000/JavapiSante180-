package controllers;

import entities.Activite;
import entities.Exercice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Exerciceservice;
import services.Activiteservice;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifierExercice {

    @FXML
    private TextField idnameexe;

    @FXML
    private TextField iddescriptionexe;

    @FXML
    private ComboBox<String> idniveauexe;

    @FXML
    private TextField idnombreexe;

    @FXML
    private TextField idlikesexe;

    @FXML
    private ComboBox<Activite> activiteComboBox;

    private int exerciceId;

    public void initialize() {
        // Populate the ComboBox with activity names
        Activiteservice activiteService = new Activiteservice();
        List<Activite> activites = activiteService.getAllData();
        activiteComboBox.setItems(FXCollections.observableArrayList(activites));

        // Définition de la cell factory pour afficher uniquement le nom de l'activité
        activiteComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Activite item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
    }
    @FXML
    void Annuler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Afficher_Exercice.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setExerciceData(Exercice exercice) {
        exerciceId = exercice.getId();
        idnameexe.setText(exercice.getNom());
        iddescriptionexe.setText(exercice.getDescription());
        idniveauexe.setValue(exercice.getNiveau());
        idnombreexe.setText(String.valueOf(exercice.getNombre_repetition()));
        idlikesexe.setText(String.valueOf(exercice.getLikes()));

        // Recherche de l'objet Activite correspondant au nom de l'activité de l'exercice
        Activite activiteExercice = exercice.getActivite();
        activiteComboBox.getItems().stream()
                .filter(activite -> activite.getNom().equals(activiteExercice.getNom()))
                .findFirst()
                .ifPresent(activite -> activiteComboBox.setValue(activite));
    }

    @FXML
    private void saveExerciceData(ActionEvent event) {
        // Récupérer les détails de l'exercice à partir des champs de formulaire
        String nomExercice = idnameexe.getText();
        String description = iddescriptionexe.getText();
        String niveau = idniveauexe.getValue();
        int nombreRepetition = Integer.parseInt(idnombreexe.getText());
        int likes = Integer.parseInt(idlikesexe.getText());
        Activite activite = activiteComboBox.getValue();

        // Valider si un champ obligatoire est vide
        if (nomExercice.isEmpty() || description.isEmpty() || niveau == null || activite == null) {
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs !");
            return;
        }

        // Valider la longueur du nom de l'exercice
        if (nomExercice.length() < 3) {
            showAlert(Alert.AlertType.ERROR, "Données incorrectes", "Le nom de l'exercice doit avoir au moins 3 caractères !");
            return;
        }

        // Créer une instance de ExerciceService
        Exerciceservice exerciceService = new Exerciceservice();

        // Créer un nouvel objet Exercice avec les données saisies
        Exercice exercice = new Exercice(nomExercice, description, niveau, nombreRepetition, likes, activite);

        // Appeler la méthode saveEntity pour enregistrer l'exercice dans la base de données
        exerciceService.updateEntity(exercice);

        // Afficher un message de réussite
        showAlert(Alert.AlertType.INFORMATION, "Enregistrement réussi", "L'exercice a été enregistré avec succès.");
    }


    @FXML
    void modifierExercice() {
        try {
            String nom = idnameexe.getText();
            String description = iddescriptionexe.getText();
            String niveau = idniveauexe.getValue();
            int nombreRepetition = Integer.parseInt(idnombreexe.getText());
            int likes = Integer.parseInt(idlikesexe.getText());
            Activite activite = activiteComboBox.getValue();

            Exerciceservice exerciceService = new Exerciceservice();
            Exercice exercice = new Exercice(exerciceId, nom, description, niveau, nombreRepetition, likes, activite);

            exerciceService.updateEntity(exercice);

            showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "L'exercice a été modifié avec succès.");

            idnameexe.clear();
            iddescriptionexe.clear();
            idniveauexe.getSelectionModel().clearSelection();
            idnombreexe.clear();
            idlikesexe.clear();
            activiteComboBox.getSelectionModel().clearSelection();


        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de format", "Veuillez entrer des nombres valides pour les champs Nombre et Likes.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
