 package services;

import entites.Nutritions;
import entites.Reclamations;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import utils.MyDB;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class NutritionsServices implements Iservices<Nutritions> {
    private static final String PDF_PATH = "src/main/resources/PDFNUT/";
    public boolean entityExists(Nutritions nutritions) {
        try {
            String requete = "SELECT * FROM Nutritions WHERE calories = ? AND glucides = ? AND lipides = ? AND fibres = ? AND proteines = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, nutritions.getCalories());
            pst.setInt(2, nutritions.getGlucides());
            pst.setInt(3, nutritions.getLipides());
            pst.setInt(4, nutritions.getFibres());
            pst.setInt(5, nutritions.getProteines());
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence de la nutrition : " + e.getMessage());
            return false;
        }
    }



    public void addEntity2(Nutritions nutritions) {
        try {
            if (entityExists(nutritions)) {
                System.out.println("La nutrition existe déjà dans la base de données.");
            } else {
                String requete = "INSERT INTO Nutritions (calories, glucides, lipides, fibres, proteines) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setInt(1, nutritions.getCalories());
                pst.setInt(2, nutritions.getGlucides());
                pst.setInt(3, nutritions.getLipides());
                pst.setInt(4, nutritions.getFibres());
                pst.setInt(5, nutritions.getProteines());
                pst.executeUpdate();
                System.out.println("Nutrition ajoutée avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la nutrition : " + e.getMessage());
        }
    }

    public void updateEntity(Nutritions nutritions) {
        String requete = "UPDATE Nutritions SET calories = ?, glucides = ?, lipides = ?, fibres = ?, proteines = ? WHERE id = ?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, nutritions.getCalories());
            pst.setInt(2, nutritions.getGlucides());
            pst.setInt(3, nutritions.getLipides());
            pst.setInt(4, nutritions.getFibres());
            pst.setInt(5, nutritions.getProteines());
            pst.setInt(6, nutritions.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nutrition mise à jour");
            } else {
                System.out.println("Nutrition non trouvée");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEntity(Nutritions nutritions) {
        String requete = "DELETE FROM Nutritions WHERE id=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, nutritions.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nutrition supprimée");
            } else {
                System.out.println("Nutrition non trouvée");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Nutritions> getAllData() {
        List<Nutritions> data = new ArrayList<>();
        String requete = "SELECT * FROM Nutritions";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Nutritions nutritions = new Nutritions();
                nutritions.setId(rs.getInt("id"));
                nutritions.setCalories(rs.getInt("calories"));
                nutritions.setGlucides(rs.getInt("glucides"));
                nutritions.setLipides(rs.getInt("lipides"));
                nutritions.setFibres(rs.getInt("fibres"));
                nutritions.setProteines(rs.getInt("proteines"));
                data.add(nutritions);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }



    public static void generatePDF(List<Nutritions> reclamationsList, String fileName) throws IOException {
        String filePath = PDF_PATH + fileName + ".pdf";
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);

            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Load logo image
                PDImageXObject logoImage = PDImageXObject.createFromFile("src/main/resources/logo.png", document);

                // Set image coordinates and size
                float xImage = 50;
                float yImage = page.getMediaBox().getHeight() - 150;
                float widthImage = 100;
                float heightImage = 50;

                // Add logo image to PDF page
                contentStream.drawImage(logoImage, xImage, yImage, widthImage, heightImage);

                // Add current date and time to document content
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = "Date: " + dtf.format(now);

                float xDate = 50;
                float yDate = page.getMediaBox().getHeight() - 200;
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(xDate, yDate);
                contentStream.showText(currentDate);
                contentStream.endText();

                // Draw a line to separate date from the rest of the content
                float lineY = yDate - 10;
                contentStream.moveTo(xDate, lineY);
                contentStream.lineTo(xDate + 150, lineY);
                contentStream.stroke();

                // Define coordinates for table start
                float xTable = 50;
                float yTable = page.getMediaBox().getHeight() - 250;

                // Create a table to display appointment details
                float tableWidth = page.getMediaBox().getWidth() - 2 * xTable;
                float tableHeight = 100;
                float cellMargin = 10;
                float cellWidth = (tableWidth - 4 * cellMargin) / 4;
                float cellHeight = 20;

                // Draw a line to separate table header from table content
                float headerLineY = yTable + cellHeight + cellMargin / 2;
                contentStream.moveTo(xTable, headerLineY);
                contentStream.lineTo(xTable + tableWidth, headerLineY);
                contentStream.stroke();

                // Add column headers with green text
                contentStream.setNonStrokingColor(Color.GREEN);
                contentStream.beginText();
                float xText = xTable + cellMargin;
                float yText = yTable - cellHeight / 2;
                contentStream.newLineAtOffset(xText, yText);
                contentStream.showText("fibres");
                contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                contentStream.showText("glucides");
                contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                contentStream.showText("lipides");
                contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                contentStream.showText("protéines");
                contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                contentStream.showText("calories");
                contentStream.endText();

                // Fill the table with appointment details
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                float yOffset = yText - cellHeight; // Offset to start filling table content
                for (Nutritions nutritions : reclamationsList) {
                    yOffset -= cellHeight + cellMargin;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xText, yOffset);
                    contentStream.showText(String.valueOf(nutritions.getFibres()));
                    contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                    contentStream.showText(String.valueOf(nutritions.getGlucides()));
                    contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                    contentStream.showText(String.valueOf(nutritions.getLipides()));
                    contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                    contentStream.showText(String.valueOf(nutritions.getProteines()));
                    contentStream.newLineAtOffset(cellWidth + cellMargin, 0);
                    contentStream.showText(String.valueOf(nutritions.getCalories()));
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
        // Créer le fichier
        if (!file.createNewFile()) {
            // Gérer l'échec de la création du fichier si nécessaire
            System.err.println("Erreur: Impossible de créer le fichier PDF.");
        }
    }

    private static String generateUniqueFileName(String fileName) {
        // Ajouter une logique pour générer un nom de fichier unique
        // Par exemple, vous pouvez ajouter un timestamp à la fin du nom de fichier
        return fileName + "_" + System.currentTimeMillis();
    }

}
