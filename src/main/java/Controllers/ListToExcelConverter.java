package Controllers;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ListToExcelConverter {
    public static void convertListToExcel(List<String> listpoids,List<String> listtaille,List<String> listpid,List<String> listimc,List<String> listtaux, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Trouver la longueur maximale des listes
            int maxLength = Math.max(Math.max(Math.max(Math.max(listpoids.size(), listtaille.size()), listpid.size()), listimc.size()), listtaux.size());

            // Boucle pour écrire les éléments de la liste dans les cellules Excel
            for (int i = 0; i < maxLength; i++) {
                Row row = sheet.createRow(i);
                if (i < listpoids.size()) {
                    row.createCell(0).setCellValue(listpoids.get(i));
                }
                if (i < listtaille.size()) {
                    row.createCell(1).setCellValue(listtaille.get(i));
                }
                if (i < listpid.size()) {
                    row.createCell(2).setCellValue(listpid.get(i));
                }
                if (i < listimc.size()) {
                    row.createCell(3).setCellValue(listimc.get(i));
                }
                if (i < listtaux.size()) {
                    row.createCell(4).setCellValue(listtaux.get(i));
                }
            }

            // Écriture des données dans un fichier Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Conversion réussie. Fichier Excel créé à : " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
