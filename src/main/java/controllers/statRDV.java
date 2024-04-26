package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Rendezvous;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
import services.RendezvousServices;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class statRDV implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String, Integer> RendezvousBarchart;

    @FXML
    private Button close_stat;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ObservableList<Rendezvous> rendezvousList;

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert RendezvousBarchart != null : "fx:id=\"RendezvousBarchart\" was not injected: check your FXML file 'statRendezvous.fxml'.";
        assert close_stat != null : "fx:id=\"close_stat\" was not injected: check your FXML file 'statRendezvous.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'statRendezvous.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'statRendezvous.fxml'.";

        // Initialiser le graphique
        xAxis.setLabel("Date");
        yAxis.setLabel("Nombre de rendez-vous");
        showAppointmentStatistics();
    }


    @FXML
    void showAppointmentStatistics() {
        if (RendezvousBarchart == null) {
            System.out.println("Le BarChart n'a pas été correctement initialisé dans le fichier FXML.");
            return;
        }

        // Obtenir les statistiques sur les rendez-vous par mois
        Map<String, Integer> appointmentCountsByMonth = getRDVStatisticsByMonth();

        // Mettre à jour les données du graphique
        displayAppointmentStatistics(appointmentCountsByMonth);
    }

    private Map<String, Integer> getRDVStatisticsByMonth() {
        // Appelez la méthode de service pour obtenir les statistiques sur les rendez-vous par mois
        RendezvousServices rendezvousServices = new RendezvousServices();
        return rendezvousServices.getRDVStatisticsByMonth();
    }

    private void displayAppointmentStatistics(Map<String, Integer> appointmentCountsByMonth) {
        // Configurer les données du graphique à barres
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : appointmentCountsByMonth.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Effacer les données existantes et ajouter la nouvelle série de données au graphique avec animation de fondu
        FadeTransition ft = new FadeTransition(Duration.millis(1000), RendezvousBarchart);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        RendezvousBarchart.getData().clear();
        RendezvousBarchart.getData().add(series);

        // Appliquer le style CSS pour les barres du graphique
        for (XYChart.Data<String, Integer> data : series.getData()) {
            // Récupérer le nœud représentant la barre de chaque point de données
            javafx.scene.Node node = data.getNode();
            // Appliquer le style CSS pour définir la couleur de la barre en vert
            node.setStyle("-fx-bar-fill: #73AA18;"); // Modifier la couleur de orange à vert
        }
    }

}
