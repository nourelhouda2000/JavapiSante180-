package controllers;

import entities.Activite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import entities.WeatherAPI;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import services.Activiteservice;
import services.Exerciceservice;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;


public class AjoutActivite {

    private final Activiteservice serviceActivite = new Activiteservice();

    @FXML
    private TextField idnomactivite;

    @FXML
    private TextField iddescriptionactivite;

    @FXML
    private TextField idcategorieactivite;

    @FXML
    private ComboBox<String> niveauact;

    @FXML
    void ajouter(ActionEvent event) {
        try {
            String nomActivite = idnomactivite.getText();
            String descriptionActivite = iddescriptionactivite.getText();
            String categorieActivite = idcategorieactivite.getText();
            String niveauActivite = niveauact.getValue();

            if (nomActivite.isEmpty() || descriptionActivite.isEmpty() || categorieActivite.isEmpty() || niveauActivite == null) {
                afficherAlerte("Veuillez remplir tous les champs !");
                return;
            }

            if (nomActivite.length() > 22) {
                afficherAlerte("Le nom de l'activité ne peut pas dépasser 22 caractères !");
                return;
            }

            if (descriptionActivite.length() < 8) {
                afficherAlerte("La description de l'activité doit avoir au moins 8 caractères !");
                return;
            }

            if (nomActivite.length() < 3) {
                afficherAlerte("Le nom de l'activité doit avoir au moins 3 caractères !");
                return;
            }

            if (contientChiffres(nomActivite)) {
                afficherAlerte("Le nom de l'activité ne peut pas contenir de chiffres !");
                return;
            }

            // Créer une nouvelle activité avec les valeurs récupérées des champs
            Activite nouvelleActivite = new Activite();
            nouvelleActivite.setNom(nomActivite);
            nouvelleActivite.setDescription(descriptionActivite);
            nouvelleActivite.setCategorie(categorieActivite);
            nouvelleActivite.setNiveau(niveauActivite);


            serviceActivite.addEntity(nouvelleActivite);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Activite ajoutée avec succès !");
            alert.showAndWait();

            // Code de redirection ici si nécessaire

        } catch (NumberFormatException e) {
            afficherAlerte("Veuillez entrer un nombre valide pour les répétitions et les likes !");
        }
    }

    private boolean contientChiffres(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void consulterActivite() {
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
    private void consulterExercice() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ajout_Exercice.fxml"));
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
    void chatbot(ActionEvent event) throws IOException, SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatbot.fxml"));
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

