package entities;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Liste des rendez-vous");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                for (Rendezvous rendezvous : rendezvousList) {
                    contentStream.showText("Date Rendez-vous: " + rendezvous.getDate_r());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Heure Rendez-vous: " + rendezvous.getHeur());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Nom: " + rendezvous.getNomuser());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Email: " + rendezvous.getEmail());
                    contentStream.newLineAtOffset(0, -20);
                    // Ajoutez d'autres champs si nécessaire
                }

                contentStream.endText();
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
