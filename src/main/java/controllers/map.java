package controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class map {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webView;

    @FXML
    void initialize() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(Objects.requireNonNull(getClass().getResource("/map.html")).toExternalForm());

        // Enable JavaScript communication
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
            }
        });
    }


}
