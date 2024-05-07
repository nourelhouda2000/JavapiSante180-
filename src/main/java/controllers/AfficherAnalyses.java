package controllers;

import com.sun.javafx.charts.Legend;
import entities.Analyses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.File;
import java.io.BufferedWriter;


import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

import entities.Sante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServicesSante;
import services.ServiceAnalyses;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherAnalyses {

    @FXML
    private ImageView Image;
    @FXML
    private TextField imc;

    @FXML
    private TextField poids;

    @FXML
    private TextField poidsideal;

    @FXML
    private ComboBox<Analyses> AnalysesCombBox;
    @FXML
    private ComboBox<Sante> SanteCombobox;

    @FXML
    private VBox AnalysesContainer;



    @FXML
    private TextField taille;

    @FXML
    private TextField taux;


    @FXML
    private Label caloriesid;

    @FXML
    private Label maladieid;

    @FXML
    private Label medicamentid;

    @FXML
    private TextField calories;



    @FXML

    private TextField maladie;

    @FXML
    private TextField medicament;
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




    // Méthode d'initialisation




    // Méthode pour ajouter une santé à la VBox
    @FXML
    public void ajouterSante(String poids, String taills, String poidsideal ,String imc,String taux,String santem) {
        // Créer un nouveau VBox pour représenter la santé
        VBox vboxSante = new VBox();
        vboxSante.setStyle("-fx-background-color: #73AA18;");
        vboxSante.setPrefSize(404, 188);

        // Créer et configurer les labels pour afficher les informations de santé
        Label labellinee = new Label("________________________________________________________________________________________________" );
        Label labelpoids = new Label(" Poids : " +poids);
        Label labeltaille = new Label(" Taills : " +taills);
        Label labelpoisdideal = new Label(" Poids ideale : " +poidsideal);
        Label labeimc = new Label(" IMC : " +imc);
        Label labeltaux = new Label(" Taux : " +taux);
        Label labelMaladie = new Label(" Maladie : " +santem);
        Label labelline = new Label("________________________________________________________________________________________________" );
        labelMaladie.setTextFill(Color.WHITE);
        labelpoids.setTextFill(Color.WHITE);
        labeltaille.setTextFill(Color.WHITE);
        labelpoisdideal.setTextFill(Color.WHITE);
        labeimc.setTextFill(Color.WHITE);
        labeltaux.setTextFill(Color.WHITE);






        // Ajouter les labels au VBox de santé
        vboxSante.getChildren().addAll(labellinee,labelpoids,labeltaille, labelpoisdideal,labeimc,labeltaux,labelline);
        // Ajouter le VBox et l'image à la HBox

        // Ajouter la HBox à la VBox principale


        // Ajouter le VBox de santé à la VBox principale
        AnalysesContainer.getChildren().add(vboxSante);
    }

    // Méthode d'initialisation
    @FXML
    public void initialize() throws SQLException {
        Sante santem=new Sante();

        ServiceAnalyses ss=new ServiceAnalyses();
        // Exemple d'ajout de santés (vous pouvez remplacer par vos données réelles)
        List<Analyses> analyses = ss.afficher();
        ServicesSante ssa =new ServicesSante();
        List<Sante> santes =ssa.afficher();
        ObservableList<String> list4 = FXCollections.observableArrayList("PDF", "Excel", "Imprimer");



        for (Analyses analyse : analyses) {
            AnalysesCombBox.getItems().add(analyse);
            VBox carte = createCard(analyse);
            AnalysesContainer.getChildren().add(carte);
            int santei = analyse.getSanteid();
            for (Sante sante : santes) {
                if (sante.getId() == santei) {
                    santem=sante;
                    SanteCombobox.getItems().add(sante);
                }



            }

            AnalysesCombBox.setOnAction(event -> {
                try {
                    handle(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void handlee(ActionEvent event) {
        Sante selectedSante = SanteCombobox.getSelectionModel().getSelectedItem();
        if (selectedSante != null) {


        }
    }


    private void handle(ActionEvent event) throws SQLException {
        Sante santem =null;
        Analyses selectedSante = AnalysesCombBox.getSelectionModel().getSelectedItem();
        if (selectedSante != null) {
            ServicesSante ssa=new ServicesSante();

            List<Sante> santes =ssa.afficher();
            int santei = selectedSante.getSanteid();
            for (Sante sante : santes) {
                if (sante.getId() == santei) {
                    santem=sante;
                }
                System.out.println(selectedSante);
                // Afficher les informations de la "santé" sélectionnée


                poids.setText(String.valueOf(selectedSante.getPoids()));
                taille.setText(String.valueOf(selectedSante.getTaille()));
                poidsideal.setText(String.valueOf(selectedSante.getPoidsideal()));
                imc.setText(String.valueOf(selectedSante.getImc()));
                taux.setText(String.valueOf(selectedSante.getTaux()));
                SanteCombobox.getSelectionModel().select(santem);



            }
        }}
    @FXML
    void supprimers(ActionEvent event) {
        try {
            ServiceAnalyses ss=new ServiceAnalyses();
            Analyses selectedSante = AnalysesCombBox.getSelectionModel().getSelectedItem();
            ss.delete(selectedSante);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer");
            alert.setContentText("Analyse Supprimer");
            alert.show();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherAnalyses.fxml"));
            Parent root=loader.load();
            poids.getScene().setRoot(root);


        } catch (IOException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sql exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    public void update(ActionEvent event)
    {if (validerSaisies()){
        try {

            ServiceAnalyses ss=new ServiceAnalyses();
            Analyses selectedSante = AnalysesCombBox.getSelectionModel().getSelectedItem();
            Sante se = SanteCombobox.getSelectionModel().getSelectedItem();
            int santeid=se.getId();

            selectedSante.setPoids(Integer.parseInt(poids.getText()));
            selectedSante.setTaille(Integer.parseInt(taille.getText()));
            selectedSante.setPoidsideal(Integer.parseInt(poidsideal.getText()));
            selectedSante.setImc(Integer.parseInt(imc.getText()));
            selectedSante.setTaux(Integer.parseInt(taux.getText()));
            selectedSante.setSanteid(santeid);
            ss.update(selectedSante);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier");
            alert.setContentText("Analyse Modifier");
            alert.show();
            LoadPage();
        }

        catch (IOException | SQLException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sql exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();;

        }}}



    @FXML
    void NextPage(ActionEvent event) throws IOException {
        LoadPage();
    }


    public void LoadPage() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherAnalyses.fxml"));
        Parent root=loader.load();
        poids.getScene().setRoot(root);


    }
    @FXML
    public void santes() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
        Parent root=loader.load();
        poids.getScene().setRoot(root);


    }


    public boolean validerSaisies() {
        boolean p=validerFormat(poids, "\\d+");
        boolean t=validerFormat(taille, "\\d+");
        boolean po=validerFormat(poidsideal, "\\d+");
        boolean i=validerFormat(imc, "\\d+");
        boolean ta=validerFormat(taux, "\\d+");

        // Valider les chiffres uniquement pour les calories


        if(p && t && po && i && ta){
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
    @FXML
    public void santes(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
        Parent root=loader.load();
        poids.getScene().setRoot(root);


    }
    @FXML
    public void ajouterA(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AjouterAnalyses.fxml"));
        Parent root=loader.load();
        poids.getScene().setRoot(root);
    }
    @FXML
    private void Excel(ActionEvent event) throws IOException, SQLException {
        // Récupérer la liste à partir de la base de données ou d'une autre source
        ServiceAnalyses sv = new ServiceAnalyses();
        List<Analyses> list = sv.afficher();

        // Créer une liste pour stocker les données à convertir en Excel
        List<String> data1 = new ArrayList<>();
        data1.add("Poids");
        List<String> data2 = new ArrayList<>();
        data2.add("Taille");
        List<String> data3 = new ArrayList<>();
        data3.add("Poids ideale");
        List<String> data4 = new ArrayList<>();
        data4.add("Imc");
        List<String> data5 = new ArrayList<>();
        data5.add("Taux");

        // Ajouter les données de chaque analyse à la liste sous forme de chaînes
        for (Analyses analyse : list) {
            String rowData1 = String.valueOf(analyse.getPoids());
            data1.add(rowData1);
        }
        for (Analyses analyse : list) {
            String rowData2 = String.valueOf(analyse.getTaille());
            data2.add(rowData2);
        }
        for (Analyses analyse : list) {
            String rowData3 = String.valueOf(analyse.getPoidsideal());
            data3.add(rowData3);
        }
        for (Analyses analyse : list) {
            String rowData4 = String.valueOf(analyse.getImc());
            data4.add(rowData4);
        }
        for (Analyses analyse : list) {
            String rowData5 = String.valueOf(analyse.getTaux());
            data5.add(rowData5);
        }

        // Spécifier le chemin du fichier Excel de sortie
        String filePath = "C:\\Users\\rabeb\\Desktop\\1.xlsx";

        // Appeler la méthode convertListToExcel pour convertir la liste en Excel
        ListToExcelConverter.convertListToExcel(data1,data2,data3,data4,data5, filePath);
    }
    @FXML
    void stat(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/statimc.fxml"));
        Parent root=loader.load();




        Scene scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();

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
    @FXML
    void image(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root=loader.load();




        Scene scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void recAct(ActionEvent event) throws IOException {
        Analyses selectedAnalyse = AnalysesCombBox.getSelectionModel().getSelectedItem();
      int  imc = selectedAnalyse.getImc();
        if (imc < 18.5) {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/RecAct.fxml"));
            Parent root=loader.load();
            recAc controller=loader.getController();

            String n="Intermédiare";
            controller.afficherListeActivites(n);
            Scene scene=new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();



        } else if (imc >= 18.5 && imc < 25) {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/RecAct.fxml"));
            Parent root=loader.load();
            recAc controller=loader.getController();

            String n="avancé";
            controller.afficherListeActivites(n);
            Scene scene=new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();


        } else if (imc >= 25 && imc < 30) {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/RecAct.fxml"));
            Parent root=loader.load();
            recAc controller=loader.getController();

            String n="intermédiare";
            controller.afficherListeActivites(n);
            Scene scene=new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();

        } else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/RecAct.fxml"));
            Parent root=loader.load();
            recAc controller=loader.getController();

           String n="débutant";
            controller.afficherListeActivites(n);
            Scene scene=new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();

        }

    }

}