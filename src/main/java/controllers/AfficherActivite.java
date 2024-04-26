package controllers;

import com.stripe.model.Card;
import com.sun.javafx.collections.MappingChange;
import entities.Activite;
import entities.Exercice;
import java.util.Map;
import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.Activiteservice;

import java.io.IOException;

import java.util.List;
import java.util.Random;
import javafx.scene.control.Alert;


public class AfficherActivite {

    @FXML
    private VBox activiteContainer;

    @FXML
    private BarChart<String, Integer> activiteBarChart;




    private final Activiteservice activiteService;

    public AfficherActivite() {
        this.activiteService = new Activiteservice();
    }

    @FXML
    public void initialize() {
        afficherListeActivites();
    }




    private void afficherListeActivites() {
        // Récupérer la liste des activités depuis le service
        List<Activite> activites = activiteService.getAllData();

        // Parcourir la liste des activités et les afficher dans le conteneur
        for (Activite activite : activites) {
            VBox card = createActiviteCard(activite);
            activiteContainer.getChildren().add(card);
        }
    }




    private VBox createActiviteCard(Activite activite) {
        // Créer une carte (VBox) pour l'activité
        VBox card = new VBox();
        card.setPrefWidth(300);
        card.setSpacing(10); // Espacement entre les éléments à l'intérieur de la carte
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");

        // Créer une HBox pour contenir le nom de l'activité et l'aligner au centre
        HBox nomBox = new HBox();
        nomBox.setAlignment(Pos.CENTER);
        Label nomLabel = new Label(activite.getNom());
        nomLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        nomBox.getChildren().add(nomLabel);

        // Ajouter la HBox contenant le nom de l'activité à la carte
        card.getChildren().add(nomBox);

        // Autres attributs de l'activité en noir et alignés à gauche
        Label descriptionLabel = new Label("Description: " + activite.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label categorieLabel = new Label("Catégorie: " + activite.getCategorie());
        categorieLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label niveauLabel = new Label("Niveau: " + activite.getNiveau());
        niveauLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        // Aligner les labels à gauche
        descriptionLabel.setAlignment(Pos.BASELINE_LEFT);
        categorieLabel.setAlignment(Pos.BASELINE_LEFT);
        niveauLabel.setAlignment(Pos.BASELINE_LEFT);

        // Ajouter les labels à la carte
        card.getChildren().addAll(descriptionLabel, categorieLabel, niveauLabel);

        // Créer le bouton Modifier
        Button modifierButton = new Button("Modifier");
        modifierButton.getStyleClass().add("action-button");
        modifierButton.setStyle("-fx-background-color: #AD1457; -fx-text-fill: white; -fx-font-size: 16px;");
        modifierButton.setOnAction(event -> handleModifier(activite));

        // Créer le bouton Supprimer
        Button supprimerButton = new Button("Supprimer");
        supprimerButton.getStyleClass().add("action-button");
        supprimerButton.setStyle("-fx-background-color: #AD1457; -fx-text-fill: white; -fx-font-size: 16px;");
        supprimerButton.setOnAction(event -> handleSupprimer(activite));

        // Ajouter les boutons à la carte
        HBox buttonsBox = new HBox(modifierButton, supprimerButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);
        card.getChildren().add(buttonsBox);

        return card;
    }

    private void handleModifier(Activite activite) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modifier_Activite.fxml"));
            Parent root = loader.load();
            ModifierActivite controller = loader.getController();
            controller.setActiviteData(activite);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void handleSupprimer(Activite activite) {

        Activiteservice activiteService = new Activiteservice();
        // Supprimer l'activité
        activiteService.deleteEntity(activite);

        // Afficher une alerte de succès
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Succès");
        successAlert.setHeaderText(null);
        successAlert.setContentText("L'activité a été supprimée avec succès !");
        successAlert.show();

        // Effacer le conteneur VBox
        activiteContainer.getChildren().clear();

        // Repopuler le conteneur VBox avec la liste mise à jour des activités depuis la base de données
        afficherListeActivites();

    }
    @FXML
    private void handleActiviteStatsButtonAction(ActionEvent event) {
        // Obtenez la liste des activités
        List<Activite> activites = Activiteservice.getAllData();

        // Comptez le nombre d'occurrences de chaque niveau d'activité
        Map<String, Integer> stats = new HashMap<>();
        for (Activite activite : activites) {
            String niveau = activite.getNiveau();
            stats.put(niveau, stats.getOrDefault(niveau, 0) + 1);
        }

        // Créez une série de données pour le graphique
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Activité par niveau");
        for (String niveau : stats.keySet()) {
            int count = stats.get(niveau);
            XYChart.Data<String, Integer> data = new XYChart.Data<>(niveau, count);
            String label = niveau + " (" + count + ")";
            Tooltip tooltip = new Tooltip(label);
            Tooltip.install(data.getNode(), tooltip);
            series.getData().add(data);
        }

        // Afficher le graphique
        activiteBarChart.getData().clear();
        activiteBarChart.getData().add(series);
        activiteBarChart.getXAxis().setLabel("Niveau d'activité");
        activiteBarChart.getYAxis().setLabel("Nombre d'activités");
        activiteBarChart.setTitle("Statistiques des activités par niveau");
    }


}
