package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextArea textAreaa;
    @FXML
    private Label path;
    @FXML
    private ImageView Image;

    @FXML
    private void selectImage() throws IOException, TesseractException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // Load the image
            String imagePath = file.getAbsolutePath();
            Random rand = new Random();
            int x = rand.nextInt(1000);


            //String DBPath = "C:\\\\xampp\\\\htdocs\\\\Version-Integre\\\\public\\\\uploads\\\\" + x + ".jpg";
            String DBPath = "" + x + ".jpg";

            if (file != null) {
                FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
                FileOutputStream Fdestination = new FileOutputStream(DBPath);
                BufferedInputStream bin = new BufferedInputStream(Fsource);
                BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
                System.out.println(file.getAbsoluteFile());
                String p= String.valueOf(file.getAbsoluteFile());
                path.setText(p);



                Image img = new Image(file.toURI().toString());
                Image.setImage(img);

                int b = 0;
                while (b != -1) {
                    b = bin.read();
                    bou.write(b);
                }
                bin.close();
                bou.close();

            } else {
                System.out.println("error");

            }

            // Perform OCR
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\Dell\\Downloads\\Tess4J\\tessdata"); // Replace with the path to your tessdata directory

                String result = tesseract.doOCR(new File(imagePath));

                // Extract weight and height from the recognized text


                // Display the extracted values
                textAreaa.setText(result);

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
