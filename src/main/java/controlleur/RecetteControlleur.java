package controlleur;

import entites.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import entites.Recette;
import services.*;

import java.net.URL;
import java.util.*;


import javafx.scene.control.*;

import javafx.fxml.FXML;


import services.RecetteServices;

import javafx.scene.control.Alert.AlertType;

import javax.swing.*;


public class RecetteControlleur implements Initializable {
    @FXML
    private ListView<Recette> listeviewrecette;
    @FXML
    private Label controlecate;
    @FXML
    private Label controleingre;
    @FXML
    private Label controlenom;
    @FXML
    private TextField search_Rcc1;
    @FXML
    private TextField nutritionrec;
    @FXML
    private TextField categorirecette;
    @FXML
    private TextField ingRecette;
    @FXML
    private TextField search_rect;
    @FXML
    private TextField search_Recting;
    @FXML
    private Button selectrecette;
    @FXML
    private Button updaterecette;
    @FXML
    private Button deleterecette;
    @FXML
    private TextField nomRecette;
    private RecetteServices recetteServices = new RecetteServices();
    private NutritionsServices nutritionsServices = new NutritionsServices();
    private ObservableList<Nutritions> nutritionlist = FXCollections.observableArrayList();
    private ObservableList<Recette> recetteObservableList = FXCollections.observableArrayList();
    public void afficherRecette() {
        // Get the list of recettes from the service
        List<Recette> recettes = recetteServices.getAllData();

        if (recettes != null) {
            // Convert the list to an ObservableList
            ObservableList<Recette> recetteliste = FXCollections.observableArrayList(recettes);

            // Set the items in the ListView
            listeviewrecette.setItems(recetteliste);

            // Configure the custom list cell to display the details of each recette
            listeviewrecette.setCellFactory(param -> new ListCell<Recette>() {
                @Override
                protected void updateItem(Recette recette, boolean empty) {
                    super.updateItem(recette, empty);

                    if (empty || recette == null) {
                        setText(null);
                    } else {
                        // Create a string representing the details of the recette
                        String recetteDetails =
                                "Nom: " + recette.getNom() +
                                        "\nCatégorie: " + recette.getCategory() +
                                        "\nIngrédient: " + recette.getIngredient() +
                                        "\nNutrition: " + recette.getNutritions().getId();

                        setText(recetteDetails);
                    }
                }
            });
        } else {
            // Handle the case where no recettes are retrieved
            System.out.println("No recettes found.");
        }
    }
    @FXML
    void addrecette(ActionEvent event) {
        try {
            // Initialiser les labels de contrôle
            controlecate.setText("");
            controleingre.setText("");
            controlenom.setText("");

            // Vérifiez si les champs contiennent des valeurs
            if (!ingRecette.getText().isEmpty() && !categorirecette.getText().isEmpty()
                    && !nomRecette.getText().isEmpty() && !nutritionrec.getText().isEmpty()) {

                // Récupérez les valeurs des champs
                String fibresValue = ingRecette.getText();
                String caloriesValue = categorirecette.getText();
                String glucideValue = nomRecette.getText();
                String nutritionValue = nutritionrec.getText();

                // Vérifiez si les noms commencent par une majuscule
                if (!Character.isUpperCase(glucideValue.charAt(0))) {
                    controlenom.setText("Le nom de la recette doit commencer par une lettre majuscule.");
                    return;
                }

                // Vérifiez si la catégorie est valide
                List<String> categoriesValides = Arrays.asList("plat principal", "brunch", "petit déjeuner", "dîner");
                if (!categoriesValides.contains(caloriesValue.toLowerCase())) {
                    controlecate.setText("La catégorie de la recette doit être 'plat principal', 'brunch', 'petit déjeuner' ou 'dîner'.");
                    return;
                }

                // Vérifiez si les autres champs texte ne sont pas vides
                if (fibresValue.trim().isEmpty()) {
                    controleingre.setText("Veuillez saisir une valeur pour les ingrédients.");
                    return;
                }

                if (caloriesValue.trim().isEmpty()) {
                    controlecate.setText("Veuillez saisir une valeur pour la catégorie.");
                    return;
                }


                // Créer un nouvel objet Recette avec les données des champs UI
                Recette recette = new Recette();
                recette.setIngredient(fibresValue);
                recette.setCategory(caloriesValue);
                recette.setNom(glucideValue);

                // Créer un objet Nutrition à partir de l'ID récupéré
                Nutritions nutrition = new Nutritions();
                nutrition.setId(Integer.parseInt(nutritionValue));

                // Définir la nutrition de la recette
                recette.setNutritions(nutrition);

                // Appelez la méthode du service pour ajouter la recette
                recetteServices.addEntity2(recette);
                clearRecetteFields();

                // Rafraîchir la ListView après l'ajout
                recetteObservableList.clear(); // Effacez la liste observable
                recetteObservableList.addAll(recetteServices.getAllData()); // Ajoutez les nouvelles recettes à la liste observable

                // Mettre à jour la ListView
                afficherRecette();
            } else {
                // Gérez le cas où l'un des champs est vide
                System.err.println("Erreur : Assurez-vous que tous les champs sont remplis avec des valeurs.");
            }
        } catch (NumberFormatException e) {
            // Gérez l'exception si l'une des valeurs n'est pas un entier valide
            System.err.println("Erreur : Assurez-vous que tous les champs sont remplis avec des valeurs numériques valides.");
            e.printStackTrace();
        }
    }


    @FXML
    void updateRecette(ActionEvent event) {
        try {
            // Récupérer la recette sélectionnée dans le ListView
            Recette selectedRecette = listeviewrecette.getSelectionModel().getSelectedItem();

            // Vérifier si une recette est sélectionnée
            if (selectedRecette == null) {
                showAlert("Aucune recette sélectionnée", "Veuillez sélectionner une recette à mettre à jour.", AlertType.ERROR);
                return;
            }

            // Vérifier que les champs sont remplis
            if (nomRecette.getText().isEmpty() || ingRecette.getText().isEmpty() || categorirecette.getText().isEmpty() || nutritionrec.getText().isEmpty()) {
                showAlert("Champs manquants", "Veuillez remplir tous les champs.", AlertType.ERROR);
                return;
            }

            // Récupérer les valeurs des champs
            String nomRecetteValue = nomRecette.getText();
            String ingRecetteValue = ingRecette.getText();
            String categorirecetteValue = categorirecette.getText();
            String nutritionrecValue = nutritionrec.getText();

            // Vérifier si le nom commence par une majuscule
            if (!Character.isUpperCase(nomRecetteValue.charAt(0))) {
                showAlert("Nom de recette invalide", "Le nom de la recette doit commencer par une lettre majuscule.", AlertType.ERROR);
                return;
            }

            // Vérifier si la catégorie est valide
            List<String> categoriesValides = Arrays.asList("plat principal", "brunch", "petit déjeuner", "dîner");
            if (!categoriesValides.contains(categorirecetteValue.toLowerCase())) {
                showAlert("Catégorie invalide", "La catégorie de la recette doit être 'plat principal', 'brunch', 'petit déjeuner' ou 'dîner'.", AlertType.ERROR);
                return;
            }

            // Créer un nouvel objet Nutritions avec les valeurs récupérées
            Nutritions nutritions = new Nutritions();
            nutritions.setId(Integer.parseInt(nutritionrecValue)); // Supposons que l'ID de nutrition est récupéré directement depuis le champ de nutrition

            // Créer un nouvel objet Recette avec les données des champs UI
            Recette updatedRecette = new Recette();
            updatedRecette.setId(selectedRecette.getId()); // Assurez-vous de définir l'ID de la recette sélectionnée
            updatedRecette.setNom(nomRecetteValue);
            updatedRecette.setIngredient(ingRecetteValue);
            updatedRecette.setCategory(categorirecetteValue);
            updatedRecette.setNutritions(nutritions);

            // Appeler la méthode du service pour mettre à jour la recette
            recetteServices.updateEntity(updatedRecette);
            clearRecetteFields();

            // Rafraîchir la ListView après la mise à jour
            afficherRecette();

            // Réinitialiser les champs d'entrée après la mise à jour de la recette
            clearRecetteFields();

            // Afficher un message de succès
            showAlert("Recette mise à jour", "La recette a été mise à jour avec succès.", AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            showAlert("Erreur de format", "Veuillez saisir une valeur numérique valide pour l'ID de nutrition.", AlertType.ERROR);
        } catch (Exception e) {
            // Gérer les erreurs
            e.printStackTrace();
            showAlert("Erreur", "Une erreur s'est produite lors de la mise à jour de la recette.", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void selectRecette() {
        Recette rec = (Recette) listeviewrecette.getSelectionModel().getSelectedItem();
        if (rec != null) {
            // Afficher les valeurs dans les champs texte
            categorirecette.setText(rec.getCategory());
            nomRecette.setText(rec.getNom());
            ingRecette.setText(rec.getIngredient());

            // Récupérer les informations nutritionnelles de la recette
            Nutritions nutrition = rec.getNutritions();
            if (nutrition != null) {
                // Afficher les valeurs nutritionnelles dans les champs de texte
                nutritionrec.setText(String.valueOf(nutrition.getId())); // Vous devriez afficher l'ID ou une autre propriété de l'objet Nutrition
            }
        }
    }



    private void showAlert(String s, AlertType error) {
    }

    // Méthode pour effacer les champs de saisie
    private void clearRecetteFields() {
        nomRecette.clear();
        ingRecette.clear();
        categorirecette.clear();
        nutritionrec.clear();
    }
    @FXML
    void deleteRecette(ActionEvent event) {
        // Récupérer la recette sélectionnée dans le ListView
        Recette selectedRecette = listeviewrecette.getSelectionModel().getSelectedItem();

        // Vérifier si une recette est sélectionnée
        if (selectedRecette == null) {
            // Si aucune recette n'est sélectionnée, afficher une alerte
            showAlert("Aucune recette sélectionnée", "Veuillez sélectionner une recette à supprimer.", AlertType.WARNING);
            return; // Sortir de la méthode
        }

        // Afficher une boîte de dialogue de confirmation avant de supprimer la recette
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette recette ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Si l'utilisateur confirme la suppression, appeler la méthode de service pour supprimer la recette
            recetteServices.deleteEntity(selectedRecette);

            // Rafraîchir le ListView après la suppression de la recette
            afficherRecette(); // Vous devez implémenter cette méthode pour rafraîchir votre ListView
        }
    }

    @FXML

    public void handleSearchByCriteria(ActionEvent event) {
        String nom = search_rect.getText();
        String category = search_Recting.getText();

        // Vérifier si les champs de recherche ne sont pas vides
        if (!nom.isEmpty() || !category.isEmpty()) {
            // Appeler la méthode de recherche par critères dans le service
            List<Recette> result = recetteServices.searchByCriteria(nom, category);

            // Mettre à jour la ListView avec les réclamations correspondantes

            refreshTableVieww(result);
        } else {
            // Afficher un message d'alerte indiquant que les champs de recherche sont vides
            showAlert("Champs de recherche vides", "Veuillez entrer au moins un critère de recherche.", Alert.AlertType.WARNING);
            // Recharger toutes les données initiales après la recherche
            refreshAllData();
        }
    }
    @FXML
    private void search(ActionEvent event) {
        String searchTerm = search_rect.getText().trim().toLowerCase();

        // Vérifier si le terme de recherche n'est pas vide
        if (!searchTerm.isEmpty()) {
            List<Recette> filteredCabinets = new ArrayList<>();

            // Filtrer les réclamations qui correspondent au terme de recherche
            for (Recette reclamations : listeviewrecette.getItems()) {
                // Convertir la date en une chaîne de caractères et vérifier si elle contient le terme de recherche

                if (reclamations.getNom().toLowerCase().contains(searchTerm) ||
                        reclamations.getIngredient().toLowerCase().contains(searchTerm) )
                        {
                    filteredCabinets.add(reclamations);
                }
            }

            // Mettre à jour les données affichées dans le ListView avec les résultats de la recherche
            listeviewrecette.getItems().clear();
            listeviewrecette.getItems().addAll(filteredCabinets);
        } else {
            // Si le champ de recherche est vide, afficher toutes les données
            afficherRecette();
        }
    }
    private void refreshTableVieww(List<Recette> reclamationsList) {
        // Effacer le contenu actuel du TableView
        listeviewrecette.getItems().clear();

        // Ajouter les réclamations récupérées au TableView
        listeviewrecette.getItems().addAll(reclamationsList);
    }

    private void refreshAllData() {
        // Charger toutes les données initiales dans la ListView
        List<Recette> allData = recetteServices.getAllData();
        listeviewrecette.getItems().clear();
        listeviewrecette.getItems().addAll(allData);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert nomRecette != null : "fx:id=\"IDrecl\" was not injected: check your FXML file 'reponse.fxml'.";
        assert ingRecette != null : "fx:id=\"IDrecl\" was not injected: check your FXML file 'reponse.fxml'.";
        assert categorirecette != null : "fx:id=\"IDrecl\" was not injected: check your FXML file 'reponse.fxml'.";
        assert nutritionrec != null : "fx:id=\"IDrecl\" was not injected: check your FXML file 'reponse.fxml'.";
        assert listeviewrecette != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert selectrecette != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert updaterecette != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert deleterecette != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert controlecate != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert controleingre != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert controlenom != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert search_Rcc1 != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert search_rect != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        assert search_Recting != null : "fx:id=\"listeviewrecette\" was not injected: check your FXML file 'reponse.fxml'.";
        afficherRecette();

    }

}
