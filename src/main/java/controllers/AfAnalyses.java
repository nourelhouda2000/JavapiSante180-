package controllers;

import entities.Analyses;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import services.ServiceAnalyses;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import entities.Analyses;
public class AfAnalyses {





    @FXML
    private AnchorPane pa;
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
    @FXML
    private VBox vboxSante;

    @FXML

    public void initialize() throws SQLException {



    }
    public List<Analyses> setlist(int id) throws SQLException {

        ServiceAnalyses ss =new ServiceAnalyses();
        List<Analyses> analyses = ss.afficher();
        List<Analyses> a = new ArrayList<>();

        for (Analyses analyse : analyses) {
            if (analyse.getSanteid()==id)
            {a.add(analyse);
                VBox carte = createCard(analyse);
                vboxSante.getChildren().add(carte);
            }}
        return a;

    }
    @FXML

    public void MyFunction( ) throws IOException, SQLException {
    }
    private VBox createCard(Analyses analyse) {




        VBox card = new VBox();
        card.setPrefWidth(300);
        card.setSpacing(10); // Espacement entre les éléments à l'intérieur de la carte
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");

        // Créer une HBox pour contenir l'image à gauche et les labels à droite
        HBox contentBox = new HBox();
        contentBox.setAlignment(Pos.CENTER_LEFT);
        contentBox.setSpacing(10); // Espacement entre l'image et les labels

        // Créer une imageView pour l'image
        ImageView imageView = new ImageView(new Image("file:///C://Users//rabeb//Desktop//images//teamm.jpg"));
        imageView.setFitWidth(150); // Largeur de l'image
        imageView.setFitHeight(150); // Hauteur de l'image

        // Ajouter l'image à la HBox
        contentBox.getChildren().add(imageView);

        // Créer une VBox pour contenir les labels
        VBox labelsBox = new VBox();
        labelsBox.setSpacing(5); // Espacement entre les labels

        // Créer les labels pour les attributs de l'analyse
        Label labellinee = new Label("________________________________________________________________________________________________" );

        Label descriptionLabel = new Label("Poids: " + analyse.getPoids());
        descriptionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label categorieLabel = new Label("Taille: " + analyse.getTaille());
        categorieLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label cLabel = new Label("Poids idéal: " + analyse.getPoidsideal());
        cLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label niveauLabel = new Label("IMC: " + analyse.getImc());
        niveauLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label ca = new Label("Taux: " + analyse.getTaux());
        ca.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label labelline = new Label("________________________________________________________________________________________________" );

        // Ajouter les labels à la VBox
        labelsBox.getChildren().addAll(labellinee,descriptionLabel,cLabel, categorieLabel,ca, niveauLabel,labelline);
        int Imc= analyse.getImc();

// Créer un label pour afficher le poids à perdre
        Label perdreLabel = new Label();
        Pane pane= new Pane();
        Label etat=new Label();
        perdreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

// Affichage du poids à perdre

        try {
            double imcValue = Double.valueOf(Imc);
            double poidsIdeal = Double.valueOf(analyse.getPoidsideal());
            double poidsActuel = Double.valueOf(analyse.getPoids());
            double poidsAPerdre = poidsActuel - poidsIdeal;
            DecimalFormat df = new DecimalFormat("#.##");
            String poidsAPerdreFormatted = df.format(poidsAPerdre);
            perdreLabel.setText("Poids à perdre : " + poidsAPerdreFormatted);

            // Changer la couleur du fond selon l'IMC
            if (imcValue < 18.5) {
                pane.setStyle("-fx-background-color: #0033FF ;"); // Sous-poids (bleu clair)
                etat.setText(" Sous-poids ");
                etat.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#0033FF  ;");
                perdreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#0033FF  ;");
            } else if (imcValue >= 18.5 && imcValue < 25) {
                pane.setStyle("-fx-background-color: #00FF00;"); // Poids normal (vert)
                etat.setText(" Poids normal ");
                etat.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#66FF33 ;");
                perdreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#66FF33  ;");

            } else if (imcValue >= 25 && imcValue < 30) {
                pane.setStyle("-fx-background-color: #FFA500;"); // Surpoids (orange)
                etat.setText(" Surpoids ");
                etat.setStyle("-fx-font-size: 16px;-fx-text-fill: black; -fx-background-color: #FFA500;");
                perdreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#FFA500 ;");
            } else {
                pane.setStyle("-fx-background-color: #FF0000;"); // Obésité (rouge)
                etat.setText(" Obésité ");
                etat.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#FF0000;");
                perdreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#FF0000 ;");
            }
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion de IMC en double : " + e.getMessage());
        }


// Ajouter le label du poids à perdre au labelsBox
        labelsBox.getChildren().addAll(pane,etat,perdreLabel);
        // Ajouter la VBox des labels à la HBox
        contentBox.getChildren().add(labelsBox);

        // Ajouter la HBox à la carte
        card.getChildren().add(contentBox);

        // Retourner la carte
        return card;
    }


}