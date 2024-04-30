package Controllers;


import entities.Analyses;
import entities.Pdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entities.Sante;
import javafx.stage.Stage;
import services.ServicesSante;
import services.ServiceAnalyses;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterAnalyses {

    @FXML
    private Button ajouterAnalyseButton;

    @FXML
    private Button annulerAnalyseButton;

    @FXML
    private ComboBox<Sante> SanteCombobox;





    @FXML
    private TextField poids;



    @FXML
    private TextField taille;

    @FXML
    private TextField taux;
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




    ServicesSante ps= new ServicesSante();
    ServiceAnalyses sa=new ServiceAnalyses();
    @FXML
    public void initialize() throws SQLException {
        ServicesSante ss=new ServicesSante();
        // Exemple d'ajout de santés (vous pouvez remplacer par vos données réelles)
        List<Sante> santes = ss.afficher();

        for (Sante sante : santes) {

            SanteCombobox.getItems().add(sante);
        }
        SanteCombobox.getSelectionModel().selectFirst();

    }

    private void handle(ActionEvent event) {
        Sante selectedSante = SanteCombobox.getSelectionModel().getSelectedItem();
        if (selectedSante != null) {


        }
    }


    @FXML
    void addPerson(ActionEvent event) {
        if (validerSaisies()){
            try {
                Sante selectedSante = SanteCombobox.getSelectionModel().getSelectedItem();
                Integer santeid= selectedSante.getId();
               int p= Integer.parseInt( poids.getText());
               int t= Integer.parseInt( taille.getText());
               double imcd=p/((t*0.01)*(t*0.01));
               int imc=(int)imcd;
               double poidsi=22*((t*0.01)*(t*0.01));
               int pi=(int)poidsi;

                sa.add(new Analyses(
                        Integer.parseInt( poids.getText()),
                        Integer.parseInt( taille.getText()),

                        pi,
                        imc,
                        Integer.parseInt( taux.getText()),
                        santeid));

                    Analyses voy = new Analyses( Integer.parseInt( poids.getText()),
                            Integer.parseInt( taille.getText()),

                            pi,
                            imc,
                            Integer.parseInt( taux.getText()),
                            santeid);

                    Pdf pd=new Pdf();
                    try{
                        pd.GeneratePdf("MesInformations",voy,voy.getId());

                        System.out.println("impression done");
                    } catch (Exception ex) {
                        Logger.getLogger(ServiceAnalyses.class.getName()).log(Level.SEVERE, null, ex);
                    }

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ajout");
                alert.setContentText("Analyse ajoutée");
                alert.show();
                LoadPage();
                NextPage();



            } catch (IOException e) {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Sql exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();;
            }}


    }

    @FXML
    void NextPage() throws IOException {

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherAnalyses.fxml"));
            Parent root=loader.load();
            poids.getScene().setRoot(root);




    }



    public void LoadPage() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/afficher1Analyse.fxml"));
        Parent root=loader.load();
        ViewAnalyses controller=loader.getController();
        String imc=calculerIMC(poids.getText(),taille.getText());
        String poidsideal=calculerPoidsIdeal(taille.getText());

        controller.MyFunction(poids.getText(),taille.getText(),poidsideal,imc,taux.getText());
        Scene scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();





    }


    @FXML
    void AnnulerS(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
        Parent root=loader.load();
        poids.getScene().setRoot(root);}
    public boolean validerSaisies() {
        boolean p=validerFormat(poids, "[5-9][0-9]|100");

        boolean t=validerFormat(taille, "1[1-9][0-9]|200");


        boolean ta=validerFormat(taux, "[5-9][0-9]|100");
        boolean com=validerCombo(SanteCombobox);

        // Valider les chiffres uniquement pour les calories


        if(p && t && ta && com){
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
    private boolean validerCombo(ComboBox<Sante> comboBox) {
        Sante selectedItem = comboBox.getSelectionModel().getSelectedItem();
        if (selectedItem != null ) {
            // Si l'élément sélectionné est valide, définir la couleur du texte sur noir
            comboBox.setStyle("-fx-text-fill: black;");
            return true;
        } else {
            // Si l'élément sélectionné est invalide, définir la couleur du texte sur rouge
            comboBox.setStyle("-fx-text-fill: red;");
            // Vous pouvez également définir un message d'erreur ou réinitialiser la sélection
            // dans le ComboBox si nécessaire.
            return false;
        }
    }
    private boolean validerPoids(TextField champTexte, String regex) {
        String texte = champTexte.getText();
        if (texte.matches(regex)&&!texte.matches("Poids Invalide")) {
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
    private boolean validerTaille(TextField champTexte, String regex) {
        String texte = champTexte.getText();
        if (texte.matches(regex)&&!texte.matches("Taille Invalide")) {
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
    public static String calculerIMC(String poidsStr, String tailleStr) {
        try {
            double poids = Double.parseDouble(poidsStr);
            double taille = Double.parseDouble(tailleStr)*0.01;

            double imc = poids / (taille * taille);
            return String.format("%.2f", imc); // Formate l'IMC avec deux décimales
        } catch (NumberFormatException e) {
            // Gérer une exception si les chaînes de caractères ne peuvent pas être converties en nombres
            return "Erreur : Les données de poids ou de taille ne sont pas valides.";
        } catch (ArithmeticException e) {
            // Gérer une exception si la taille est égale à zéro
            return "Erreur : La taille ne peut pas être égale à zéro.";
        }
    }
    public static String calculerPoidsIdeal(String tailleStr) {
        try {
            double taille = Double.parseDouble(tailleStr)*0.01;

            // Calcul de l'IMC correspondant à un poids idéal
            double poidsIdeal = 22 * (taille * taille); // Poids idéal pour un IMC de 22

            return String.format("%.2f", poidsIdeal); // Formate le poids idéal avec deux décimales
        } catch (NumberFormatException e) {
            // Gérer une exception si la chaîne de caractères ne peut pas être convertie en nombre
            return "Erreur : La donnée de taille n'est pas valide.";
        } catch (ArithmeticException e) {
            // Gérer une exception si la taille est égale à zéro
            return "Erreur : La taille ne peut pas être égale à zéro.";
        }
    }





}



