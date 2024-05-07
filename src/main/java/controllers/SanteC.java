package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Sante;

public class SanteC {

    @FXML
    private Label caloriesid;



    @FXML
    private Label maladieid;

    @FXML
    private Label medicamentid;
    public void setData(Sante sante){
        caloriesid.setText(String.valueOf(sante.getCalories()));
        maladieid.setText(sante.getMaladie());
        medicamentid.setText(sante.getMedicment());
    }

}
