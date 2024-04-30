package controllers;

import entities.Activite;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import services.Activiteservice;

import java.io.IOException;

import java.util.List;

import javafx.scene.control.Label;

import entities.WeatherAPI;



public class AfficherActivite {

    @FXML
    private VBox activiteContainer;

    @FXML
    private BarChart<String, Integer> activiteBarChart;
    private ObservableList<Activite> activiteDataList;
    @FXML
    private Pagination pagination;










    private final Activiteservice activiteService;

    public AfficherActivite() {
        this.activiteService = new Activiteservice();
    }



   @FXML
    public void initialize() {
        // Récupérer la liste complète des exercices et l'assigner à exerciceDataList
        activiteDataList = FXCollections.observableArrayList(Activiteservice.getAllData());

        // Créer un objet Pagination avec le nombre total de pages et une fonction pour générer le contenu de chaque page
        int itemsPerPage = 1; // Nombre d'exercices par page
        int pageCount = (int) Math.ceil((double) activiteDataList.size() / itemsPerPage); // Calcul du nombre total de pages
        pagination = new Pagination(pageCount, 0);
        pagination.setStyle("-fx-font-size: 16px; -fx-background-color: #f2f2f2;");
        pagination.setPageFactory(pageIndex -> createPageContent(pageIndex, itemsPerPage, activiteDataList));

        // Ajouter la pagination au conteneur sans supprimer le contenu existant
        activiteContainer.getChildren().add(pagination);

        // Afficher la liste des exercices initiale
        // afficherListeExercices();
    }




    private void afficherListeActivites() {
        // Récupérer la liste des activités depuis le service
        List<Activite> activites = Activiteservice.getAllData();

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

        //

        Label weatherLabel = new Label("Météo: ");
        try {
            String cityName = "Tunis"; // Vous pouvez également utiliser activite.getCity() si la ville est disponible dans l'objet activite
            JSONObject weatherData = WeatherAPI.getWeather(cityName);
            String weatherInfo = extractWeatherInfo(weatherData);
            weatherLabel.setText("Météo: " + weatherInfo);
        } catch (Exception e) {
            // Gérer l'exception ici, par exemple en affichant un message d'erreur
            e.printStackTrace();
            weatherLabel.setText("Météo: Non disponible");
        }

        // Ajouter le label météo à la carte
        card.getChildren().add(weatherLabel);

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
    private Node createPageContent(int pageIndex, int itemsPerPage, List<Activite> activites) {
        int fromIndex = pageIndex * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, activites.size());
        List<Activite> currentPageData = activites.subList(fromIndex, toIndex);

        // Créer dynamiquement les cartes d'exercices pour la page actuelle
        FlowPane pageContent = new FlowPane();
        pageContent.setPadding(new Insets(10));
        pageContent.setHgap(10);
        pageContent.setVgap(10);

        for (Activite activite : currentPageData) {
            // Créer la carte d'exercice et ajouter à la page
            Node card = createCard(activite);
            pageContent.getChildren().add(card);
        }

        return pageContent;
    }
    /*private Node createCard(Activite activite) {
        // Code pour créer et personnaliser la carte d'exercice
        // Retournez la carte d'exercice créée

        // Exemple de code pour créer une carte d'exercice fictive avec VBox
        VBox card = new VBox();
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 07px; -fx-margin: 07px; -fx-border-radius: 07px;");

        // Titre de l'exercice
        Label titleLabel = new Label(activite.getNom());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: purple; -fx-padding: 12px;");
        // Description de l'exercice
        Label descriptionLabel = new Label( "Description: " +activite.getDescription());

        // Niveau de l'exercice
        Label levelLabel = new Label("Niveau: " + activite.getNiveau());
        Label levelLabell = new Label("Categorie: " + activite.getCategorie());



        // Ajouter les éléments à la carte
        card.getChildren().addAll(titleLabel, descriptionLabel, levelLabel,levelLabell);

        return card;
    }
*/


    /*private Node createCard(Activite activite) {
        // Créez la carte d'activité
        VBox card = new VBox();
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 07px; -fx-margin: 07px; -fx-border-radius: 07px;");

        // Titre de l'activité
        Label titleLabel = new Label(activite.getNom());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: purple; -fx-padding: 12px;");

        // Description de l'activité
        Label descriptionLabel = new Label("Description: " + activite.getDescription());

        // Niveau de l'activité
        Label levelLabel = new Label("Niveau: " + activite.getNiveau());

        // Catégorie de l'activité
        Label categoryLabel = new Label("Catégorie: " + activite.getCategorie());

        // Label pour afficher la météo
        Label weatherLabel = new Label("Météo: ");
        try {
            String cityName = "Tunis";
            WeatherAPI weatherApi = new WeatherAPI();
            JSONObject weatherData = weatherApi.getWeather(cityName);
            String weatherInfo = extractWeatherInfo(weatherData);
            weatherLabel.setText("Météo: " + weatherInfo);
        } catch (Exception e) {
            // Gérer l'exception ici, par exemple en affichant un message d'erreur
            e.printStackTrace();
            weatherLabel.setText("Météo: Non disponible");
        }

        // Ajoutez tous les éléments à la carte
        card.getChildren().addAll(titleLabel, descriptionLabel, levelLabel, categoryLabel, weatherLabel);

        return card;
    }*/



        // ...

        private Node createCard(Activite activite) {
            // Créez la carte d'activité
            VBox card = new VBox();
            card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 07px; -fx-margin: 07px; -fx-border-radius: 07px;");

            // Titre de l'activité
            Label titleLabel = new Label(activite.getNom());
            titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: purple; -fx-padding: 12px;");

            // Description de l'activité
            Label descriptionLabel = new Label("Description: " + activite.getDescription());

            // Niveau de l'activité
            Label levelLabel = new Label("Niveau: " + activite.getNiveau());

            // Catégorie de l'activité
            Label categoryLabel = new Label("Catégorie: " + activite.getCategorie());

            // Label pour afficher la météo
            Label weatherLabel = new Label("Météo: ");
            try {
                String cityName = "Tunis";
                WeatherAPI weatherApi = new WeatherAPI();
                JSONObject weatherData = weatherApi.getWeather(cityName);
                String weatherInfo = extractWeatherInfo(weatherData);
                weatherLabel.setText("Météo: " + weatherInfo);
                // Vérifiez si l'activité est réalisable en fonction des conditions météorologiques
                String activityStatus = getActivityStatus(weatherData, activite);
                Label statusLabel = new Label("Status: " + activityStatus);
                card.getChildren().add(statusLabel);
            } catch (Exception e) {
                // Gérer l'exception ici, par exemple en affichant un message d'erreur
                e.printStackTrace();
                weatherLabel.setText("Météo: Nuageux");
            }

            // Ajoutez tous les éléments à la carte
            card.getChildren().addAll(titleLabel, descriptionLabel, levelLabel, categoryLabel, weatherLabel);

            return card;
        }

        // Méthode utilitaire pour extraire les informations météorologiques à partir de l'objet JSONObject
        private String extractWeatherInfo(JSONObject weatherData) {
            // Ici, vous pouvez extraire les informations météorologiques pertinentes de l'objet JSON
            // et les retourner sous forme de chaîne de caractères
            // Par exemple :
            String weatherInfo = weatherData.getString("description");
            return weatherInfo;
        }

        // Méthode pour déterminer si l'activité peut être réalisée en fonction des conditions météorologiques actuelles
        private String getActivityStatus(JSONObject weatherData, Activite activite) {
            // Ajoutez votre logique ici pour déterminer si l'activité peut être réalisée
            // en fonction des conditions météorologiques actuelles
            // Par exemple, si l'activité est une activité en extérieur et qu'il pleut, vous pouvez
            // retourner "Non recommandé" ou "Impossible"
            return "Recommandé"; // Remplacez cela par votre propre logique
        }




    // Méthode utilitaire pour extraire les informations météorologiques à partir de l'objet JSONObject
   /* private String extractWeatherInfo(JSONObject weatherData) {
        // Ici, vous pouvez extraire les informations météorologiques pertinentes de l'objet JSON
        // et les retourner sous forme de chaîne de caractères
        // Par exemple :
        String weatherDescription = weatherData.getJSONObject("weather").getString("description");
        return weatherDescription;
    }
*/

    public void afficherrListeActivites() {
        // Récupérer la liste des exercices depuis le service
        List<Activite> activites = Activiteservice.getAllData();

        // Parcourir la liste des exercices et les afficher dans le conteneur
        for (Activite activite : activites) {
            VBox card = createActiviteCard(activite);
            activiteContainer.getChildren().add(card);
        }
    }

}
