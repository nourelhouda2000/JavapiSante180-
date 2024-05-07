package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
    @FXML
    private AnchorPane pane;
    @FXML
    private Label etat;
    @FXML
    private Label perdre;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Associer l'événement de clic sur le bouton PDF à la méthode handlePDFButtonClick()

    }
    @FXML

    public void MyFunction(String poids,String taille ,String poidsideal,String Imc ,String taux ) throws IOException {
         poidsa.setText(poids);
         taiilea.setText(taille);
         poidsia.setText(poidsideal);
         imca.setText(Imc);
         tauxa.setText(taux);
         Imc = Imc.replace(',', '.');
         poidsideal =poidsideal.replace(',','.');
         poids =poids.replace(',','.');
         if (!Imc.isEmpty()) {
             try {
                 double imcValue = Double.valueOf(Imc);
                 double poidsIdeal = Double.valueOf(poidsideal);
                 double poidsActuel = Double.valueOf(poids);
                 double poidsAPerdre = poidsActuel - poidsIdeal;
                 DecimalFormat df = new DecimalFormat("#.##");
                 String poidsAPerdreFormatted = df.format(poidsAPerdre);
                 // Affichage du poids à perdre
                 perdre.setText("Poids à perdre : " + poidsAPerdreFormatted);

                 // Changer la couleur du fond selon l'IMC
                 if (imcValue < 18.5) {
                     pane.setStyle("-fx-background-color: #ADD8E6;"); // Sous-poids (bleu clair)
                     etat.setText("Sous-poids");
                 } else if (imcValue >= 18.5 && imcValue < 25) {
                     pane.setStyle("-fx-background-color: #00FF00;"); // Poids normal (vert)
                     etat.setText("Poids normal");
                 } else if (imcValue >= 25 && imcValue < 30) {
                     pane.setStyle("-fx-background-color: #FFA500;"); // Surpoids (orange)
                     etat.setText("Surpoids");
                 } else {
                     pane.setStyle("-fx-background-color: #FF0000;"); // Obésité (rouge)
                     etat.setText("Obésité");
                 }
             } catch (NumberFormatException e) {
                 // Gérer le cas où Imc n'est pas parsable en double
                 System.err.println("Erreur de conversion de IMC en double : " + e.getMessage());
                 // Vous pouvez définir une couleur par défaut ou une action de gestion d'erreur appropriée ici
             }
         } else {
             // Gérer le cas où Imc est vide
             System.err.println("IMC est vide.");
             // Vous pouvez définir une couleur par défaut ou une action de gestion d'erreur appropriée ici
         }




     }


}
