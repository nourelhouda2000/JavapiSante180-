package controllers;

import com.stripe.model.Card;
import com.sun.javafx.collections.MappingChange;
import entities.Activite;
import entities.Exercice;
import java.util.Map;

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



    private final Activiteservice activiteService;

    public AfficherActivite() {
        this.activiteService = new Activiteservice();
    }

    @FXML
    public void initialize() {
        afficherListeActivites();
    }

    @FXML
    private BarChart<String, Integer> activiteBarChart;


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
        card.setStyle("-fx-background-color: " + getRandomColor() + "; -fx-padding: 10px; -fx-margin: 10px;");

        // Contenu de la carte
        card.getChildren().add(new Label("Nom: " + activite.getNom()));
        card.getChildren().add(new Label("Description: " + activite.getDescription()));
        card.getChildren().add(new Label("Catégorie: " + activite.getCategorie()));
        card.getChildren().add(new Label("Niveau: " + activite.getNiveau()));

        // Créer le bouton Modifier
        Button modifierButton = new Button("Modifier");
        modifierButton.getStyleClass().add("action-button");
        modifierButton.setOnAction(event -> handleModifier(activite));

        // Créer le bouton Supprimer
        Button supprimerButton = new Button("Supprimer");
        supprimerButton.getStyleClass().add("action-button");
        supprimerButton.setOnAction(event -> handleSupprimer(activite));

        // Ajouter les boutons à la carte
        HBox buttonsBox = new HBox(modifierButton, supprimerButton);
        buttonsBox.setSpacing(10);
        card.getChildren().add(buttonsBox);

        return card;
    }



    private String getRandomColor() {
        // Générer une couleur aléatoire au format hexadécimal
        String[] colors = {"#FF5733", "#33FFB5", "#336CFF", "#FF33E0", "#B933FF", "#33FF57", "#FFD933"};
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
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
