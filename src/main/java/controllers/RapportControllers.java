package controllers;

import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.RapportServices;
import entities.Rapport;
import entities.Rendezvous;
import javafx.scene.control.ListView;

public class RapportControllers implements Initializable {

    private RapportServices rapportServices = new RapportServices();
    private RendezvousControllers rendezvousControllers;

    private Rapport existingRapport;


    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField  IDRDVRapport;
    @FXML
    private Button closeUpdateRapport;
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
    private Button closeaddrapport;

    @FXML
    private Label saisie_update;
    @FXML
    private TextField Rapport_Ajou;

    @FXML
    private Label saisie_rapport;






    @FXML
    public void closeupdaterapport() {
        Stage stage = (Stage) closeUpdateRapport.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void closeaddrapport() {
        Stage stage = (Stage) closeaddrapport.getScene().getWindow();
        stage.close();
    }

    public void  addRappReset() {

        Rapport_Ajou.setText("");
    }
    @FXML
    void AjouterRap(ActionEvent event) {
        // Initialiser le label de contrôle
        saisie_rapport.setText("");

        try {
            // Récupérer le texte du champ de texte Rapport_Ajou
            String rapportText = Rapport_Ajou.getText();

            // Vérifier si le champ de texte n'est pas vide
            if (rapportText.isEmpty()) {
                saisie_rapport.setText("Veuillez saisir un rapport.");
                return;
            }

            // Récupérer l'ID du rendez-vous depuis le champ de texte IDRDVRapport
            int idRendezvous = Integer.parseInt(IDRDVRapport.getText());

            // Créer un objet Rapport avec le texte saisi et l'ID du rendez-vous
            Rapport rapport = new Rapport();
            rapport.setRapport(rapportText);

            // Appeler la méthode pour ajouter le rapport
            rapportServices.addEntity2Rapport(rapport, idRendezvous);

            // Masquer le message d'erreur si le champ est valide
            saisie_rapport.setVisible(false);

            // Afficher un message de succès
            showAlert("Succès", "Opération réussie", "Le rapport a été ajouté avec succès.", Alert.AlertType.INFORMATION);

            addRappReset();

            // Fermer la fenêtre actuelle
            Stage stage = (Stage) Rapport_Ajou.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            showAlert("Erreur de format", null, "Veuillez saisir un identifiant de rendez-vous valide.", Alert.AlertType.ERROR);

        } catch (Exception e) {
            showAlert("Erreur", null,"Une erreur s'est produite lors de l'ajout du rapport.", Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }





    @FXML
    void updateRapport(ActionEvent event) {
        // Initialiser le label de contrôle
        saisie_update.setText("");

        try {
            int idRapport = getId();
            String nouveauRapport = Rapport_Update.getText();

            // Vérifier si le champ de texte n'est pas vide
            if (nouveauRapport.isEmpty()) {
                saisie_update.setText("Veuillez saisir un nouveau rapport.");
                return;
            }

            rapportServices.updateRapport2(new Rapport(nouveauRapport), idRapport);
            Stage stage = (Stage) Rapport_Ajou.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            // Gérer l'exception si nécessaire
        } catch (Exception e) {
            // Gérer l'exception si nécessaire
        }
    }



    public void setRendezvousControllers(RendezvousControllers rendezvousControllers) {
        this.rendezvousControllers = rendezvousControllers;
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
        assert saisie_rapport != null : "fx:id=\"saisie_rapport\" was not injected: check your FXML file 'Rapport.fxml'.";
        assert saisie_update != null : "fx:id=\"saisie_update\" was not injected: check your FXML file 'UpdateRapport.fxml'.";
        assert closeaddrapport != null : "fx:id=\"closeaddrapport\" was not injected: check your FXML file 'Rapport.fxml'.";
        assert closeUpdateRapport != null : "fx:id=\"closeUpdateRapport\" was not injected: check your FXML file 'UpdateRapport.fxml'.";

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
