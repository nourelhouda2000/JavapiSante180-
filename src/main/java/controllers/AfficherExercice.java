package controllers;
import entities.Exercice;
import entities.Activite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;


import javafx.scene.control.Pagination;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import services.Exerciceservice;

import java.io.IOException;

import java.util.List;


import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ApiVedio.Video;





public class AfficherExercice {

    @FXML
    private VBox exerciceContainer;

    private final Exerciceservice exerciceService;

    public AfficherExercice() {
        this.exerciceService = new Exerciceservice();
    }
    private Activite activite;
    @FXML
    private TextField nomExerciceField;

    @FXML
    private TextField descriptionExerciceField;

    @FXML
    private ComboBox<String> niveauExerciceField;
    @FXML
    private VBox resultatsVBox;
    @FXML
    private Pagination pagination;

    private Label likesCountLabel;

    private boolean isLiked = false;





    @FXML
    private Button rechercheButton;



    private ObservableList<Exercice> masterData;

    private ObservableList<Exercice> exerciceDataList;
    private final int itemsPerPage = 2;





    @FXML
    public void initialize() {
        // Récupérer la liste complète des exercices et l'assigner à exerciceDataList
        exerciceDataList = FXCollections.observableArrayList(Exerciceservice.getAllData());

        // Créer un objet Pagination avec le nombre total de pages et une fonction pour générer le contenu de chaque page
        int itemsPerPage = 1; // Nombre d'exercices par page
        int pageCount = (int) Math.ceil((double) exerciceDataList.size() / itemsPerPage); // Calcul du nombre total de pages
        pagination = new Pagination(pageCount, 0);
        pagination.setStyle("-fx-font-size: 16px; -fx-background-color: #f2f2f2;");
        pagination.setPageFactory(pageIndex -> createPageContent(pageIndex, itemsPerPage, exerciceDataList));

        // Ajouter la pagination au conteneur sans supprimer le contenu existant
        exerciceContainer.getChildren().add(pagination);


        // Afficher la liste des exercices initiale
        // afficherListeExercices();
    }



    private Node createPageContent(int pageIndex, int itemsPerPage, List<Exercice> exercices) {
        int fromIndex = pageIndex * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, exercices.size());
        List<Exercice> currentPageData = exercices.subList(fromIndex, toIndex);

        // Créer dynamiquement les cartes d'exercices pour la page actuelle
        FlowPane pageContent = new FlowPane();
        pageContent.setPadding(new Insets(10));
        pageContent.setHgap(10);
        pageContent.setVgap(10);

        for (Exercice exercice : currentPageData) {
            // Créer la carte d'exercice et ajouter à la page
            Node card = createCard(exercice);
            pageContent.getChildren().add(card);
        }

        return pageContent;
    }

    private Node createCard(Exercice exercice) {
        // Code pour créer et personnaliser la carte d'exercice
        // Retournez la carte d'exercice créée

        // Exemple de code pour créer une carte d'exercice fictive avec VBox
        VBox card = new VBox();
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");

        // Titre de l'exercice
        Label titleLabel = new Label(exercice.getNom());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: purple; -fx-padding: 12px;");
        // Description de l'exercice
        Label descriptionLabel = new Label("Description: " + exercice.getDescription());

        // Niveau de l'exercice
        Label levelLabel = new Label("Niveau: " + exercice.getNiveau());

        Label numberLabel = new Label("Nombre_Repetition: " + exercice.getNombre_repetition());
        //Label likesLabel = new Label("Likes: " + exercice.getLikes());
        Label actLabel = new Label("Activité: " + exercice.getActivite());

        // Ajouter les éléments à la carte
        card.getChildren().addAll(titleLabel, descriptionLabel, levelLabel, numberLabel, actLabel);

        // Bouton de like
        Button likeButton = new Button("❤️"); // Utilisez une icône de cœur pour le bouton
        likeButton.setStyle("-fx-font-size: 20px; -fx-text-fill: GRAY;"); // Style pour agrandir et colorer le cœur
        likeButton.setOnAction(event -> handleLike(exercice, likeButton));

        // Compte des likes
        likesCountLabel = new Label("Likes: " + exercice.getLikes()); // Initialisation de likesCountLabel
        likesCountLabel.setStyle("-fx-font-size: 14px;");

        // Ajouter le bouton de like et le compte des likes à la carte
        card.getChildren().addAll(likeButton, likesCountLabel);


        return card;
    }



    private void handleLike(Exercice exercice, Button likeButton) {
        if (isLiked) {
            // Si l'exercice est déjà liké et que le bouton est cliqué à nouveau, désactivez-le (dislike) et décrémentez le nombre de likes
            exercice.setLikes(exercice.getLikes() - 1);
            likeButton.setStyle("-fx-font-size: 20px; -fx-text-fill: GRAY; -fx-border-color: red;"); // Changer le cœur en gris et le contour en rouge
        } else {
            // Sinon, c'est un nouveau like, donc activez-le (like) et incrémente le nombre de likes
            exercice.setLikes(exercice.getLikes() + 1);
            likeButton.setStyle("-fx-font-size: 20px; -fx-text-fill: red;"); // Garder le cœur rouge
        }

        isLiked = !isLiked; // Inversez le statut du bouton (toggle)

        // Mettez à jour le nombre de likes dans la base de données
        Exerciceservice.updateLikes(exercice.getId(), exercice.getLikes());

        // Mettez à jour l'affichage du nombre de likes sur la carte d'exercice
        likesCountLabel.setText("Likes: " + exercice.getLikes());
    }








    public void afficherListeExercices() {
        // Récupérer la liste des exercices depuis le service
        List<Exercice> exercices = Exerciceservice.getAllData();

        // Parcourir la liste des exercices et les afficher dans le conteneur
        for (Exercice exercice : exercices) {
            FlowPane card = createExerciceCard(exercice);
            exerciceContainer.getChildren().add(card);
        }
    }

    private FlowPane createExerciceCard(Exercice exercice) {
        // Créer une carte pour l'exercice
        FlowPane card = new FlowPane();
        card.setPrefWidth(300);
        card.setPrefHeight(200);
        card.setStyle("-fx-background-color: #B6D7A8; -fx-padding: 10px; -fx-margin: 10px; -fx-border-radius: 10px;");

        // Contenu de la carte
        VBox content = new VBox();
        content.setSpacing(10);

        // Nom de l'exercice avec formatage
        TextFlow nomTextFlow = new TextFlow();
        Text nomText = new Text("Nom: ");
        nomText.setFill(Color.BLACK);
        nomText.setFont(Font.font(null, FontWeight.BOLD, 16));

        Text nomExercice = new Text(exercice.getNom());
        nomExercice.setFill(Color.DARKGREEN);
        nomExercice.setFont(Font.font(null, FontWeight.BOLD, 16));
        nomExercice.setUnderline(true);

        nomTextFlow.getChildren().addAll(nomText, nomExercice);

        // Autres attributs de l'exercice
        Label descriptionLabel = new Label("Description: " + exercice.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px;");
        Label niveauLabel = new Label("Niveau: " + exercice.getNiveau());
        niveauLabel.setStyle("-fx-font-size: 14px;");
        Label repetitionsLabel = new Label("Répétitions: " + exercice.getNombre_repetition());
        repetitionsLabel.setStyle("-fx-font-size: 14px;");

        Label activiteLabel = new Label("Activité: " + exercice.getActivite().getNom());
        activiteLabel.setStyle("-fx-font-size: 14px;");

        content.getChildren().addAll(nomTextFlow, descriptionLabel, niveauLabel, repetitionsLabel, activiteLabel);

        // Ajouter le contenu à la carte
        card.getChildren().add(content);

        // Créer un HBox pour les boutons et les placer à droite de la carte
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER_RIGHT); // Aligner les boutons à droite
        buttonBox.setSpacing(10);

        // Créer le bouton Modifier
        Button modifierButton = new Button("Modifier");
        modifierButton.getStyleClass().add("action-button");
        modifierButton.setStyle("-fx-background-color: #AD1457; -fx-text-fill: white; -fx-font-size: 16px;");
        modifierButton.setOnAction(event -> handleModifierexercice(exercice));

        // Créer le bouton Supprimer
        Button supprimerButton = new Button("Supprimer");
        supprimerButton.getStyleClass().add("action-button");
        supprimerButton.setStyle("-fx-background-color: #AD1457; -fx-text-fill: white; -fx-font-size: 16px;");
        supprimerButton.setOnAction(event -> handleSupprimerExercice(exercice));

        // Ajouter les boutons au HBox
        buttonBox.getChildren().addAll(modifierButton, supprimerButton);

        // Ajouter le HBox à la carte
        card.getChildren().add(buttonBox);

        return card;
    }






    private void handleModifierexercice(Exercice exercice) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modifier_Exercice.fxml"));
            Parent root = loader.load();
            ModifierExercice controller = loader.getController();
            controller.setExerciceData(exercice);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Ajoutez un écouteur pour rafraîchir la liste lorsque la fenêtre de modification est fermée
            // Ajouter un écouteur pour rafraîchir la liste lorsque la fenêtre de modification est fermée
            stage.setOnHidden(windowEvent -> refreshExerciceList());


            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleSupprimerExercice(Exercice exercice) {
        Exerciceservice exerciceService = new Exerciceservice();
        exerciceService.deleteEntity(exercice);


        // Afficher une alerte de succès
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Succès");
        successAlert.setHeaderText(null);
        successAlert.setContentText("L'exercice a été supprimé avec succès !");
        successAlert.show();
        afficherListeExercices();
    }
    // Méthode pour rafraîchir la liste des exercices
    private void refreshExerciceList() {
        // Réinitialiser la liste des exercices en récupérant à nouveau les données du service
        Exerciceservice exerciceService = new Exerciceservice();
        List<Exercice> exercices = Exerciceservice.getAllData();
        // Assurez-vous d'avoir une ComboBox pour les exercices
        // Remplacez activiteComboBox par le nom de votre ComboBox pour les exercices
      //  exerciceComboBox.setItems(FXCollections.observableArrayList(exercices));
    }





    @FXML
    private void boutonimrimer(ActionEvent event) {
        // Créer un document PDF
        Document document = new Document();

        try {
            // Définir le chemin de sortie du fichier PDF
            PdfWriter.getInstance(document, new FileOutputStream("RapportExercices.pdf"));
            document.open();

            // Titre du document
            Chunk titre = new Chunk("Rapport des Exercices");
            Paragraph titreParagraph = new Paragraph(titre);
            titreParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            titreParagraph.setSpacingAfter(10f);

            // Ajouter la date actuelle avec l'heure et les minutes
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();
            Paragraph date = new Paragraph("Date et heure: " + dtf.format(now));
            date.setAlignment(Paragraph.ALIGN_RIGHT);

            document.add(date);
            document.add(titreParagraph);

            // Créer une table pour afficher les données des exercices
            PdfPTable table = new PdfPTable(6); // 6 colonnes pour les attributs des exercices

            // Ajouter les en-têtes de colonne à la table
            PdfPCell[] headers = new PdfPCell[]{
                    new PdfPCell(new Paragraph("Nom")),
                    new PdfPCell(new Paragraph("Description")),
                    new PdfPCell(new Paragraph("Niveau")),
                    new PdfPCell(new Paragraph("Répétitions")),
                    new PdfPCell(new Paragraph("Likes")),
                    new PdfPCell(new Paragraph("Activité"))
            };

            // Colorer les en-têtes de colonne
            for (PdfPCell cell : headers) {
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }

            // Récupérer la liste des exercices depuis le service
            Exerciceservice exerciceService = new Exerciceservice();
            List<Exercice> exercices = Exerciceservice.getAllData();

            // Ajouter les données des exercices à la table
            for (Exercice exercice : exercices) {
                table.addCell(exercice.getNom());
                table.addCell(exercice.getDescription());
                table.addCell(exercice.getNiveau());
                table.addCell(String.valueOf(exercice.getNombre_repetition()));
                table.addCell(String.valueOf(exercice.getLikes()));
                table.addCell(exercice.getActivite().getNom());
            }

            document.add(table);

            // Afficher une alerte de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le rapport des exercices a été généré avec succès !");
            successAlert.show();

            document.close(); // Fermer le document PDF

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
            // Afficher une alerte en cas d'erreur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur s'est produite lors de la génération du rapport des exercices !");
            errorAlert.show();
        }
    }








    @FXML
    public void rechercherExercices(ActionEvent event) {
        String nom = nomExerciceField.getText();
        String description = descriptionExerciceField.getText();

        // Récupérer la valeur sélectionnée dans le ComboBox
        String niveau = niveauExerciceField.getValue();

        // Appeler la méthode de recherche d'exercices dans le service
        List<Exercice> exercices = exerciceService.rechercherExercices(nom, description, niveau);

        // Mettre à jour la pagination avec les résultats de la recherche
        updatePaginationWithResults(exercices);
    }


    private void updatePaginationWithResults(List<Exercice> exercices) {
        // Effacer les résultats précédents
        resultatsVBox.getChildren().clear();

        // Vérifier si des exercices ont été trouvés
        if (!exercices.isEmpty()) {
            // Créer un nouvel objet Pagination avec le nombre total de pages basé sur les résultats de la recherche
            int pageCount = (int) Math.ceil((double) exercices.size() / itemsPerPage);
            pagination = new Pagination(pageCount, 0);
            pagination.setStyle("-fx-font-size: 16px; -fx-background-color: #f2f2f2;");
            pagination.setPageFactory(pageIndex -> createPageContent(pageIndex, itemsPerPage, exercices));

            // Ajouter la pagination au conteneur des résultats
            resultatsVBox.getChildren().add(pagination);
        } else {
            // Aucun exercice trouvé, afficher un message approprié
            Label aucunResultatLabel = new Label("Aucun résultat trouvé !");
            resultatsVBox.getChildren().add(aucunResultatLabel);
        }
    }





}

