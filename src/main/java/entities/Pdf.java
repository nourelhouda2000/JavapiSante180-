package entities;


import entities.Analyses;
import services.ServiceAnalyses;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author AZAYEZ BINSA
 */
public class Pdf {

    public void GeneratePdf(String filename, Analyses p, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        ServiceAnalyses us = new ServiceAnalyses();
        document.add(new Paragraph("            Analyse "));
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));

        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph("le poids :         "+p.getPoids()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("la taille :         " + p.getTaille()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("le poids ideal :    " + p.getPoidsideal()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("l IMC:              " + p.getImc()));
        document.add(new Paragraph("                     l IMC normale entre 18,5 et 24,9 " ));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("le taux du graisse :" + p.getTaux()+"%"));
        document.add(new Paragraph("                       le taux du graisse normal entre 14% et 31 %              " ));
        document.add(new Paragraph("                      "));



        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              SANTE 180°                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}