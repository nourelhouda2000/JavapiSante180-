package controllers;

import entities.Rendezvous;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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

            // Ajout des éléments au GridPane
            gridPane.addRow(0, dateLabel, dateValue);
            gridPane.addRow(1, heureLabel, heureValue);
            gridPane.addRow(2, nomLabel, nomValue);
            gridPane.addRow(3, emailLabel, emailValue);
            gridPane.addRow(4, rapportLabel, rapportValue);


            // Définir le contenu de la cellule comme le GridPane contenant les détails du rendez-vous et les boutons d'action
            setGraphic(gridPane);
        }
    }
}
