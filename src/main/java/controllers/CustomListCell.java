package controllers;

import entities.Rendezvous;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javafx.scene.paint.Color;

import java.awt.*;

public class CustomListCell extends ListCell<Rendezvous> {


    @Override
    protected void updateItem(Rendezvous rendezvous, boolean empty) {
        super.updateItem(rendezvous, empty);

        if (empty || rendezvous == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Création d'un GridPane pour afficher les détails du rendez-vous sous forme de tableau
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(5);
            gridPane.setPadding(new Insets(5));

            // Ajout des labels pour les noms des attributs
            Label dateLabel = new Label("Date:");
            Label heureLabel = new Label("Heure:");
            Label nomLabel = new Label("Nom de l'utilisateur:");
            Label emailLabel = new Label("Email de l'utilisateur:");
            Label rapportLabel = new Label("Rapport:");

            // Ajout des valeurs des attributs du rendez-vous
            Label dateValue = new Label(rendezvous.getDate_r());
            Label heureValue = new Label(rendezvous.getHeur());
            Label nomValue = new Label(rendezvous.getNomuser());
            Label emailValue = new Label(rendezvous.getEmail());
            Label rapportValue = new Label(rendezvous.getRapport());

            // Si le rapport est "Aucun rapport", appliquer un style CSS pour le colorer en rouge
            if (rendezvous.getRapport().equals("Aucun rapport")) {
                rapportValue.setTextFill(Color.RED);
            }

            // Ajout des éléments au GridPane
            gridPane.addRow(0, dateLabel, dateValue);
            gridPane.addRow(1, heureLabel, heureValue);
            gridPane.addRow(2, nomLabel, nomValue);
            gridPane.addRow(3, emailLabel, emailValue);
            gridPane.addRow(4, rapportLabel, rapportValue);

            // Définir le contenu de la cellule comme le GridPane contenant les détails du rendez-vous
            setGraphic(gridPane);

            // Définir la couleur de fond en alternance entre vert et blanc
            if (getIndex() % 2 == 0) {
                // Ligne paire : fond vert
                setStyle("-fx-background-color: #B6D7A8; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");
            } else {
                // Ligne impaire : fond blanc
                setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");
            }

            // Personnalisez le texte affiché pour chaque rendez-vous
            setTextFill(Color.BLACK); // Couleur du texte
        }
    }

}
