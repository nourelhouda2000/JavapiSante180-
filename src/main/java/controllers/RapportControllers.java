package controllers;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import services.RapportServices;
import entities.Rapport;
import entities.Rendezvous;
import javafx.scene.control.ListView;

public class RapportControllers implements Initializable {

    private RapportServices rapportServices = new RapportServices();
    private Rapport existingRapport;


    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField IDRDVRapport;

    @FXML
    private URL location;
    @FXML
    private ListView<Rendezvous> Listview_RDV;

    @FXML
    private TextField Rapport_Update;

    @FXML
    private Button Update_rapport_btn;

    @FXML
    private Button Ajouter_rapport;

    @FXML
    private TextField Rapport_Ajou;

    @FXML
    void AjouterRap(ActionEvent event) {
        // Récupérer le texte du champ de texte Rapport_Ajou
        String rapportText = Rapport_Ajou.getText();

        // Vérifier si le champ de texte n'est pas vide
        if (!rapportText.isEmpty()) {
            // Récupérer l'ID du rendez-vous depuis le champ de texte IDRDVRapport
            int idRendezvous = Integer.parseInt(IDRDVRapport.getText());

            // Créer un objet Rapport avec le texte saisi et l'ID du rendez-vous
            Rapport rapport = new Rapport();
            rapport.setRapport(rapportText);

            // Appeler la méthode pour ajouter le rapport
            rapportServices.addEntity2Rapport(rapport, idRendezvous);
        } else {
            System.out.println("Le champ rapport est vide.");
        }
    }




    @FXML
    void updateRapport(ActionEvent event) {
        int idRapport = getId();
        String nouveauRapport = Rapport_Update.getText();

        if (!nouveauRapport.isEmpty()) {
            rapportServices.updateRapport2(new Rapport(nouveauRapport), idRapport);
        } else {
            System.out.println("Le champ rapport est vide.");
        }
    }





////update//////////////////////////////////////
    private int getId() {
        if (existingRapport != null) {
            return existingRapport.getId_rapport();
        }
        return -1;
    }
///////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Ajouter_rapport != null : "fx:id=\"Ajouter_rapport\" was not injected: check your FXML file 'Rapport.fxml'.";
        assert Rapport_Ajou != null : "fx:id=\"Rapport_Ajou\" was not injected: check your FXML file 'Rapport.fxml'.";
        assert IDRDVRapport != null : "fx:id=\"IDRDVRapport\" was not injected: check your FXML file 'Rapport.fxml'.";
        assert Rapport_Update != null : "fx:id=\"Rapport_Update\" was not injected: check your FXML file 'UpdateRapport.fxml'.";
        assert Update_rapport_btn != null : "fx:id=\"Update_rapport_btn\" was not injected: check your FXML file 'UpdateRapport.fxml'.";

        if (existingRapport != null) {
            Rapport_Update.setText(existingRapport.getRapport());
        }
    }

    public void setExistingRapport(Rapport existingRapport) {
        this.existingRapport = existingRapport;

        if (existingRapport != null && Rapport_Update != null) {
            Rapport_Update.setText(existingRapport.getRapport());
        }
    }

    public void setIdR(int idRendezvous) {
        IDRDVRapport.setText(String.valueOf(idRendezvous));
    }


}
