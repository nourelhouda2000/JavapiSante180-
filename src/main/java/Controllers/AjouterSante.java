package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.Sante;
import services.ServicesSante;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjouterSante {

    @FXML
    private Button ajouterAnalyseButton;

    @FXML
    private Button annulerAnalyseButton;

    @FXML
    private TextField calories;

    @FXML
    private TextField maladie;

    @FXML
    private TextField medicament;
    ServicesSante ps= new ServicesSante();

    @FXML
    void addPerson(ActionEvent event) {
        if (validerSaisies()){
        try {
        ps.add(new Sante(maladie.getText(),medicament.getText(),Integer.parseInt( calories.getText())));
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ajout");
        alert.setContentText("Sante ajoutée");
        alert.show();
        LoadPage();


    } catch (IOException e) {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sql exception");
        alert.setContentText(e.getMessage());
        alert.showAndWait();;
    }}


    }

    @FXML
    void NextPage(ActionEvent event) throws IOException {
        LoadPage();
    }


    public void LoadPage() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
        Parent root=loader.load();
        maladie.getScene().setRoot(root);


    }
    @FXML
    void AnnulerS(ActionEvent event) throws IOException {

    FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
    Parent root=loader.load();
        maladie.getScene().setRoot(root);}
    public boolean validerSaisies() {
        boolean ca=validerFormat(calories, "\\d+"); // Valider les chiffres uniquement pour les calories
        boolean ma=validerFormat(maladie, "[a-zA-Z]+"); // Valider les lettres uniquement pour la maladie
        boolean me =validerFormat(medicament, "[a-zA-Z]+"); // Valider les lettres uniquement pour la maladie
        if(ca && ma && me){
            return true;
        }
        else return false;
    }

    // Méthode pour valider le texte avec une expression régulière
    private boolean validerFormat(TextField champTexte, String regex) {
        String texte = champTexte.getText();
        if (texte.matches(regex)&&!texte.matches("Invalide")) {
            // Si le texte est valide, définir la couleur du texte sur noir
            champTexte.setStyle("-fx-text-fill: black;");
            return true;
        } else {
            // Si le texte est invalide, définir la couleur du texte sur rouge
            champTexte.setStyle("-fx-text-fill: red;");
            champTexte.setText("Invalide");
            return false;
        }
    }

}


