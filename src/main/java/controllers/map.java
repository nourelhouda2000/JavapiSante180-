package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class map implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Créer un objet WebEngine pour charger le contenu HTML dans le WebView
        WebEngine webEngine = webView.getEngine();

        // Charger le contenu HTML à partir d'un fichier dans le répertoire des ressources
        URL resourceUrl = getClass().getClassLoader().getResource("map1.html");
        if (resourceUrl != null) {
            webEngine.load(resourceUrl.toExternalForm());
        } else {
            System.err.println("Error: map.html not found!");
            // Gérer l'erreur ici, comme afficher un message à l'utilisateur
        }
    }
}
