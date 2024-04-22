package Controllers;

import entities.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServicePersonne;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class AfficherPersonne {

    @FXML
    private TableColumn<Personne, Integer> AgeId;

    @FXML
    private TableColumn<Personne, String> NomId;

    @FXML
    private TableColumn<Personne, String> PrenomId;

    @FXML
    private TableView<Personne> TableView;


    ServicePersonne ps = new ServicePersonne();

    @FXML
    void initialize() {

        try {
            List<Personne> persons=ps.afficher();
            ObservableList<Personne> observableList= FXCollections.observableList(persons);

            TableView.setItems(observableList);
            NomId.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            AgeId.setCellValueFactory(new PropertyValueFactory<>("age"));
        }
       catch (SQLException e){

       }
    }

}
