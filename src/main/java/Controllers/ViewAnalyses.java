package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAnalyses implements Initializable {

    @FXML
    private Label imca;

    @FXML
    private Label poidsa;

    @FXML
    private Label poidsia;

    @FXML
    private Label santea;

    @FXML
    private Label taiilea;

    @FXML
    private Label tauxa;
     @Override
     public void initialize(URL arg0, ResourceBundle arg1){

     }
     public void MyFunction(String poids,String taille ,String poidsideal,String Imc ,String taux ) throws IOException {
         poidsa.setText(poids);
         taiilea.setText(taille);
         poidsia.setText(poidsideal);
         imca.setText(Imc);
         tauxa.setText(taux);


     }


}
