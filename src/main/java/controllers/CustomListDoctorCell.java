package controllers;

import entities.Rendezvous;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomListDoctorCell extends ListCell<Rendezvous> {

    private Button addRapportButton;
    private Button UpdateRapportButton;

    public CustomListDoctorCell() {
        addRapportButton = new Button("Add Rapport");
        addRapportButton.setOnAction(event -> {
            Rendezvous rendezvous = getItem();
            if (rendezvous != null) {
                if (rapportExiste(rendezvous)) {
                    showAlert("Rapport existant", "Un rapport existe déjà pour ce rendez-vous.", Alert.AlertType.INFORMATION);
                } else {
                    try {
                        // Si aucun rapport n'existe, procéder à l'ajout du rapport
                        openRapportWindow(rendezvous.getIdR());
                    } catch (IOException e) {
                        e.printStackTrace();
                        showAlert("Erreur", "Impossible de charger la fenêtre de rapport.", Alert.AlertType.ERROR);
                    }
                }
            }
        });
        // Appliquer les styles CSS au bouton "Add Rapport"
        addRapportButton.setStyle("-fx-background-color: #3357a4; -fx-background-radius: 8px; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-cursor: hand;");
        UpdateRapportButton = new Button("Update Rapport");
        UpdateRapportButton.setOnAction(event -> {
            Rendezvous rendezvous = getItem();
            if (rendezvous != null) {
                // Logique pour mettre à jour un rapport pour le rendez-vous
                System.out.println("Update Rapport clicked for rendezvous with ID: " + rendezvous.getIdR());
            }
        });
        // Appliquer les styles CSS au bouton "Update Rapport"
        UpdateRapportButton.setStyle("-fx-background-color: #73AA18; -fx-background-radius: 8px; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-cursor: hand;");
    }
    private boolean rapportExiste(Rendezvous rendezvous) {
        // Implémenter la logique pour vérifier si un rapport existe pour le rendez-vous donné
        return !rendezvous.getRapport().equals("Aucun rapport");
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }



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
            // Ajout du bouton "Add Rapport" au GridPane

            gridPane.add(addRapportButton, 1, 5);
            gridPane.add(UpdateRapportButton, 2, 5);

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


        // Rafraîchir la liste après la fermeture de la fenêtre de rapport
        getListView().refresh();

    }

    private void openRapportWindow(int idRendezvous) throws IOException {
        try {
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

            // Appliquer l'animation d'ouverture à la fenêtre
            //applyOpenAnimation(root);

            // Afficher la fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
