package controllers;
import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent; // Importer la classe ActionEvent
import services.RapportServices;
import entities.Rapport; // Importer la classe Rapport



public class RapportControllers implements Initializable {
    private RapportServices rapportServices = new RapportServices();
    @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button Ajouter_rapport;

        @FXML
        private TextField Rapport_Ajou;



    @FXML
    void AjouterRapp(ActionEvent event) {
        // Récupérer le texte du champ de texte Rapport_Ajou
        String rapportText = Rapport_Ajou.getText();

        // Vérifier si le champ de texte n'est pas vide
        if (!rapportText.isEmpty()) {
            // Créer un objet Rapport avec le texte saisi et l'ID du rendez-vous (à remplacer par la méthode adéquate pour obtenir l'ID du rendez-vous)
            Rapport rapport = new Rapport();
            rapport.setRapport(rapportText);

            int idRendezvous = getIdRendezvous(); // À remplacer par la méthode adéquate pour obtenir l'ID du rendez-vous

            // Appeler la méthode pour ajouter le rapport
            rapportServices.addEntity2Rapport(rapport, idRendezvous);
        } else if (rapportText.isEmpty()) {
            System.out.println("Le champ rapport est vide.");
        }
    }

    // Méthode fictive pour obtenir l'ID du rendez-vous sélectionné
    private int getIdRendezvous() {
        // À remplacer par la méthode adéquate pour obtenir l'ID du rendez-vous
        return 0;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            assert Ajouter_rapport != null : "fx:id=\"Ajouter_rapport\" was not injected: check your FXML file 'Rapport.fxml'.";
            assert Rapport_Ajou != null : "fx:id=\"Rapport_Ajou\" was not injected: check your FXML file 'Rapport.fxml'.";

        }




}
