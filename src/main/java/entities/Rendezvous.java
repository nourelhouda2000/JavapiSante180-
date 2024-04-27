package entities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import java.io.File;

public class Rendezvous {
    //private int id_r;
    private int idR;
    private String date_r;
    private String heur;
    private int idUser;
    private int idRapport;
    private String nomuser;
    private String email;
    private String rapport;
    // Constructeurs, getters et setters
    private static final String PDF_PATH = "src/main/resources/PDF/";

    // Constructeur sans paramètre
    public Rendezvous() {
    }

    // Constructeur avec date et heure
    public Rendezvous(String date_r, String heur) {
        this.date_r = date_r;
        this.heur = heur;
    }

    // Constructeur avec tous les attributs
    public Rendezvous(int idR, String date_r, String heur, int idUser, int idRapport) {
        this.idR = idR;
        this.date_r = date_r;
        this.heur = heur;
        this.idUser = idUser;
        this.idRapport = idRapport;
    }

    // Getters et setters pour chaque attribut
    // Notez qu'ils ne sont plus statiques

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getDate_r() {
        return date_r;
    }

    public void setDate_r(String date_r) {
        this.date_r = date_r;
    }

    public String getNomuser() {
        return nomuser;
    }

    public String getEmail() {
        return email;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getHeur() {
        return heur;
    }

    public void setHeur(String heur) {
        this.heur = heur;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(int idRapport) {
        this.idRapport = idRapport;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Rendezvous{" +

                ", Nom='" + nomuser + '\'' +
                ", Rapport='" + rapport + '\'' +
                ", Date Rendez-vous='" + date_r + '\'' +
                ", Heure Rendez-vous='" + heur + '\'' +
                ", email='" + email + '\'' +


                '}';
    }


    //////////////////////////////////pdf////

    public static void generatePDF(List<Rendezvous> rendezvousList, String fileName) throws IOException {
        String filePath = PDF_PATH + fileName + ".pdf";
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Chargez l'image du logo
                PDImageXObject logoImage = PDImageXObject.createFromFile("src/main/resources/logo.png", document);

                // Définissez les coordonnées et la taille de l'image
                float xImage = 50; // Position X du coin supérieur gauche de l'image
                float yImage = page.getMediaBox().getHeight() - 150; // Position Y du coin supérieur gauche de l'image
                float widthImage = 100; // Largeur de l'image
                float heightImage = 50; // Hauteur de l'image

                // Ajoutez l'image du logo à la page PDF
                contentStream.drawImage(logoImage, xImage, yImage, widthImage, heightImage);

                // Définissez les coordonnées pour le début du tableau
                float xTable = 50; // Position X du coin supérieur gauche du tableau
                float yTable = page.getMediaBox().getHeight() - 250; // Position Y du coin supérieur gauche du tableau

                // Créez un tableau pour afficher les détails des rendez-vous
                float tableWidth = page.getMediaBox().getWidth() - 2 * xTable;
                float tableHeight = 100;
                float cellMargin = 10;
                float cellWidth = (tableWidth - 4 * cellMargin) / 4;
                float cellHeight = 20;

                // Dessinez une ligne pour séparer l'en-tête du contenu du tableau
                float headerLineY = yTable + cellHeight + cellMargin / 2;
                contentStream.moveTo(xTable, headerLineY);
                contentStream.lineTo(xTable + tableWidth, headerLineY);
                contentStream.stroke();

                // Ajoutez les en-têtes de colonne avec du texte vert
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                float yText = yTable - cellHeight / 2;
                contentStream.setNonStrokingColor(Color.GREEN);
                contentStream.beginText();
                contentStream.newLineAtOffset(xTable + cellMargin, yText);
                contentStream.showText("Date");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(xTable + cellMargin + cellWidth + cellMargin, yText);
                contentStream.showText("Heure");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(xTable + cellMargin + 2 * (cellWidth + cellMargin), yText);
                contentStream.showText("Nom");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(xTable + cellMargin + 3 * (cellWidth + cellMargin), yText);
                contentStream.showText("Email");
                contentStream.endText();

                // Remplissez le tableau avec les détails des rendez-vous
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                for (Rendezvous rendezvous : rendezvousList) {
                    contentStream.beginText();
                    yText -= cellHeight;
                    contentStream.newLineAtOffset(xTable + cellMargin, yText);
                    contentStream.showText(rendezvous.getDate_r());
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xTable + cellMargin + cellWidth + cellMargin, yText);
                    contentStream.showText(rendezvous.getHeur());
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xTable + cellMargin + 2 * (cellWidth + cellMargin), yText);
                    contentStream.showText(rendezvous.getNomuser());
                    contentStream.endText();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xTable + cellMargin + 3 * (cellWidth + cellMargin), yText);
                    contentStream.showText(rendezvous.getEmail());
                    contentStream.endText();
                }
            }

            document.save(filePath);
        }
    }

    public static void savePDF(String fileName) throws IOException {
        String filePath = PDF_PATH + fileName + ".pdf";
        File file = new File(filePath);
        if (file.exists()) {
            // Générer un nom de fichier unique si le fichier existe déjà
            fileName = generateUniqueFileName(fileName);
            filePath = PDF_PATH + fileName + ".pdf";
            file = new File(filePath);
        }
        file.createNewFile();
    }

    private static String generateUniqueFileName(String fileName) {
        // Ajouter une logique pour générer un nom de fichier unique
        // Par exemple, vous pouvez ajouter un timestamp à la fin du nom de fichier
        return fileName + "_" + System.currentTimeMillis();
    }


}
