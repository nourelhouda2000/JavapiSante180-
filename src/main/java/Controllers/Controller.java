package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextArea textAreaa;

    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // Load the image
            String imagePath = file.getAbsolutePath();

            // Perform OCR
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\Dell\\Downloads\\Tess4J\\tessdata"); // Replace with the path to your tessdata directory
            try {
                String result = tesseract.doOCR(new File(imagePath));

                // Extract weight and height from the recognized text
                String weight = extractValue(result, "Kg");
                String height = extractValue(result, "m");

                // Display the extracted values
                textAreaa.setText(result);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        }
    }

    private String extractValue(String text, String unit) {
        // Regular expression pattern to extract the first occurrence of a decimal number followed by the specified unit
        Pattern pattern = Pattern.compile("(\\d+(,\\d+)?)\\s*" + unit);
        Matcher matcher = pattern.matcher(text);

        // Check if a match is found
        if (matcher.find()) {
            return matcher.group(1).replace(",", "."); // Return the matched value, replace ',' with '.'
        } else {
            return "N/A"; // Return "N/A" if no match is found
        }
    }
}
