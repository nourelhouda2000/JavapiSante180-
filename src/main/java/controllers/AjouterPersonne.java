package controllers;

import entities.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePersonne;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPersonne {

    @FXML
    private TextField AgeId;

    @FXML
    private TextField NomId;

    @FXML
    private TextField PrenomId;


   ServicePersonne ps=new ServicePersonne();

    @FXML
    void AjouterPersonne(ActionEvent event)  {

        try {
            ps.add(new Personne(NomId.getText(),PrenomId.getText(),Integer.parseInt( AgeId.getText())));
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout");
            alert.setContentText("Personne ajoutée");
            alert.show();
            LoadPage();

        }catch (SQLException e){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sql exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sql exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();;
        }


    }

    @FXML
    void NextPage(ActionEvent event) throws IOException {
LoadPage();
    }


    public void LoadPage() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherPersonne.fxml"));
        Parent root=loader.load();
        NomId.getScene().setRoot(root);


    }


}
