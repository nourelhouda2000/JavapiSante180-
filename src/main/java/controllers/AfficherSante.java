package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceAnalyses;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Sante;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import services.AnalysesServices;
import services.ServicesSante;

public class AfficherSante {
    @FXML
    private VBox santeContainer;
    @FXML
    private Label caloriesid;

    @FXML
    private Label maladieid;

    @FXML
    private Label medicamentid;
    @FXML
    private ComboBox<Sante> santeCombBox;
    @FXML
    private TextField calories;



    @FXML

    private TextField maladie;

    @FXML
    private TextField medicament;




    // Méthode d'initialisation




    // Méthode pour ajouter une santé à la VBox
    public void ajouterSante(String maladie, String medicament, String calories) {
        // Créer un nouveau VBox pour représenter la santé
        VBox vboxSante = new VBox();
        vboxSante.setStyle("-fx-background-color: #73AA18;");
        vboxSante.setPrefSize(404, 188);

        // Créer et configurer les labels pour afficher les informations de santé
        Label labellinee = new Label("________________________________________________________________________________________________" );
        Label labelMaladie = new Label(" Maladie : " + maladie);
        Label labelMedicament = new Label(" Médicament : " + medicament);
        Label labelCalories = new Label(" Calories : " + calories);
        Label labelline = new Label("________________________________________________________________________________________________" );
        labelMaladie.setTextFill(Color.WHITE);
        labelMedicament.setTextFill(Color.WHITE);
        labelCalories.setTextFill(Color.WHITE);





        // Ajouter les labels au VBox de santé
        vboxSante.getChildren().addAll(labellinee,labelMaladie, labelMedicament, labelCalories,labelline);
        // Ajouter le VBox et l'image à la HBox

        // Ajouter la HBox à la VBox principale


        // Ajouter le VBox de santé à la VBox principale
        santeContainer.getChildren().add(vboxSante);
    }

    // Méthode d'initialisation
    @FXML
    public void initialize() throws SQLException {
        ServicesSante ss=new ServicesSante();
        // Exemple d'ajout de santés (vous pouvez remplacer par vos données réelles)
        List<Sante> santes = ss.afficher();
        for (Sante sante : santes) {
            ajouterSante(sante.getMaladie(), sante.getMedicment(), String.valueOf(sante.getCalories()));
            santeCombBox.getItems().add(sante);
        }
        santeCombBox.setOnAction(this::handle);
    }


    private void handle(ActionEvent event) {
        Sante selectedSante = santeCombBox.getSelectionModel().getSelectedItem();
        if (selectedSante != null) {
            System.out.println(selectedSante);
            // Afficher les informations de la "santé" sélectionnée
            maladie.setText(selectedSante.getMaladie());
            medicament.setText(selectedSante.getMedicment());
            calories.setText(String.valueOf(selectedSante.getCalories()));
        }
    }
    @FXML
    void supprimers(ActionEvent event) {
        try {
            ServicesSante ss=new ServicesSante();
            Sante selectedSante = santeCombBox.getSelectionModel().getSelectedItem();
            ss.delete(selectedSante);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer");
            alert.setContentText("Santé supprimer");
            alert.show();
            LoadPage();


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

            ServicesSante ss=new ServicesSante();
            Sante selectedSante = santeCombBox.getSelectionModel().getSelectedItem();
            selectedSante.setMaladie(maladie.getText());
            selectedSante.setMedicment(medicament.getText());
            selectedSante.setCalories(Integer.parseInt(calories.getText()));
            ss.update(selectedSante);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier");
            alert.setContentText("Santé modifiée");
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherSante.fxml"));
        Parent root=loader.load();
        maladie.getScene().setRoot(root);


    }

    @FXML
    void Analyses(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherAnalyses.fxml"));
        Parent root=loader.load();
        maladie.getScene().setRoot(root);

    }
    @FXML
    public void ajouterS(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AjouterSante.fxml"));
        Parent root=loader.load();
        maladie.getScene().setRoot(root);
    }
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
    @FXML
    void affAnalyseButton1(ActionEvent event) throws IOException, SQLException {
        Sante selectedSante = santeCombBox.getSelectionModel().getSelectedItem();
        int id = selectedSante.getId();

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/afAnalyses.fxml"));
        Parent root=loader.load();
        AfAnalyses controller=loader.getController();


        controller.setlist(id);
        Scene scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();



    }

}