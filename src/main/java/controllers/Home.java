package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Home.primaryStage = primaryStage;
        loadFXML("/AjouerCommun_means_of_transport.fxml");



    }

    public static FXMLLoader loadFXML(String fxmlFileName) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource(fxmlFileName));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        return loader;
    }



}