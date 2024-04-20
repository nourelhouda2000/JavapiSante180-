package controllers;

import entities.Exercice;
import entities.Activite;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import services.Activiteservice;
import services.Exerciceservice;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.scene.image.Image;



import javafx.stage.Stage;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.sql.SQLException;

public class AjoutExercice {

    private final Exerciceservice serviceExercice = new Exerciceservice();

    @FXML
    private TextField idName;
    @FXML
    private TextField iddescription;
    @FXML
    private ComboBox<String> idniveau;
    @FXML
    private TextField idrepetition;
    @FXML
    private TextField idlikes;

    @FXML
    private ComboBox<Activite> activiteComboBox;




    @FXML
    void initialize() {
        // Remplir la ComboBox avec les activités lors du chargement du formulaire
        Activiteservice serviceActivite = new Activiteservice();
        activiteComboBox.setItems(FXCollections.observableArrayList(serviceActivite.getAllData()));

        // Remplacer la cellule par défaut pour afficher uniquement les noms des activités
        activiteComboBox.setCellFactory(param -> new ListCell<Activite>() {
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

        // Convertisseur de chaîne pour afficher uniquement le nom de l'activité dans la ComboBox
        activiteComboBox.setConverter(new StringConverter<Activite>() {
            @Override
            public String toString(Activite activite) {
                return activite != null ? activite.getNom() : "";
            }

            @Override
            public Activite fromString(String string) {
                return null; // Cette méthode est nécessaire pour implémenter l'interface, mais nous n'en avons pas besoin ici
            }
        });
    }

            @FXML
    void ajouter(ActionEvent event) {
        try {
            String nomExercice = idName.getText();
            String descriptionExercice = iddescription.getText();
            String niveauExercice = idniveau.getValue();
            String repetitionText = idrepetition.getText();
            String likesText = idlikes.getText();

            if (nomExercice.isEmpty() || descriptionExercice.isEmpty() || niveauExercice.isEmpty() ||
                    repetitionText.isEmpty() || likesText.isEmpty()) {
                setFieldAsInvalid(idName);
                setFieldAsInvalid(iddescription);
                setFieldAsInvalid(idniveau);
                setFieldAsInvalid(idrepetition);
                setFieldAsInvalid(idlikes);
                afficherAlerte("Veuillez remplir tous les champs !");
                return;
            }

            if (nomExercice.length() < 3) {
                setFieldAsInvalid(idName);
                afficherAlerte("Le nom de l'exercice doit avoir au moins 3 caractères !");
                return;
            }

            if (descriptionExercice.length() < 8) {
                setFieldAsInvalid(iddescription);
                afficherAlerte("La description de l'exercice doit avoir au moins 8 caractères !");
                return;
            }
            if (!repetitionText.matches("\\d+")) {
                setFieldAsInvalid(idrepetition);
                afficherAlerte("Le nombre de répétitions doit être un nombre entier positif !");
                return;
            }
            // Valider que le champ "likes" contient uniquement des chiffres
            if (!likesText.matches("\\d+")) {
                setFieldAsInvalid(idlikes);
                afficherAlerte("Le nombre de likes doit être un nombre entier positif !");
                return;
            }
            // Vérifier si le niveau est sélectionné
            niveauExercice = idniveau.getValue();
            if (niveauExercice == null || niveauExercice.isEmpty()) {
                setFieldAsInvalid(idniveau);
                afficherAlerte("Veuillez sélectionner un niveau !");
                return;
            }

            // Vérifier si l'activité est sélectionnée
            Activite selectedActivite = activiteComboBox.getValue();
            if (selectedActivite == null) {
                setFieldAsInvalid(activiteComboBox);
                afficherAlerte("Veuillez sélectionner une activité !");
                return;
            }



            int repetition = Integer.parseInt(repetitionText);
            int likes = Integer.parseInt(likesText);

            // Ici, vous devez obtenir l'activité sélectionnée à partir de votre ComboBox d'activité.
            // Vous devez également vous assurer que l'utilisateur a sélectionné une activité.



            setFieldAsValid(idName);
            setFieldAsValid(iddescription);
            setFieldAsValid(idniveau);
            setFieldAsValid(idrepetition);
            setFieldAsValid(idlikes);

            Exercice nouvelExercice = new Exercice();
            nouvelExercice.setNom(nomExercice);
            nouvelExercice.setDescription(descriptionExercice);
            nouvelExercice.setNiveau(niveauExercice);
            nouvelExercice.setNombre_repetition(repetition);
            nouvelExercice.setLikes(likes);
            nouvelExercice.setActivite(selectedActivite); // Assurez-vous de définir l'activité sélectionnée sur l'exercice.

            serviceExercice.addEntity(nouvelExercice);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Exercice ajouté avec succès !");
            alert.showAndWait();

            idName.clear();
            iddescription.clear();
            idniveau.getSelectionModel().clearSelection();
            idrepetition.clear();
            idlikes.clear();
            activiteComboBox.getSelectionModel().clearSelection();

            // Code de redirection ici si nécessaire

        } catch (NumberFormatException e) {
            afficherAlerte("Veuillez entrer un nombre valide pour les répétitions et les likes !");
        }
    }


    private void setFieldAsValid(TextField field) {
        field.getStyleClass().removeAll("invalid-field");
        field.getStyleClass().add("valid-field");
    }

    private void setFieldAsInvalid(TextField field) {
        field.getStyleClass().removeAll("valid-field");
        field.getStyleClass().add("invalid-field");
        field.setPromptText("Champ invalide");
    }

    private void setFieldAsValid(ComboBox<?> field) {
        field.getStyleClass().removeAll("invalid-field");
        field.getStyleClass().add("valid-field");
    }

    private void setFieldAsInvalid(ComboBox<?> field) {
        field.getStyleClass().removeAll("valid-field");
        field.getStyleClass().add("invalid-field");
        field.setPromptText("Champ invalide");
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void consulterExercices() {
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
}


