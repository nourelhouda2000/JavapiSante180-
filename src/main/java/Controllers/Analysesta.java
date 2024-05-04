package Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import entities.Analyses;

import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;


public class Analysesta {

    @FXML
    private PieChart imcPieChart;

    @FXML
    public void initialize() {
        calculerIMC(); // Cette méthode doit remplir une liste d'IMC dans le service IMC
        imcPieChart.setData(getChartData());
    }

    private static List<Integer> listeIMC = new ArrayList<>();

    // Méthode fictive pour simuler le calcul des IMC à partir des données de la base de données
    public static void calculerIMC() {
        String requete = "SELECT * FROM Analyses";
        // Supposons que vous ayez récupéré les IMC de la base de données et les avez stockés dans listeIMC
        try {
            Statement st = MyDB.getInstance().getCon().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {



                listeIMC.add(rs.getInt("imc"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<PieChart.Data> getChartData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Obésité", calculerObesite()));
        pieChartData.add(new PieChart.Data("Surpoids", calculerSurpoids()));
        pieChartData.add(new PieChart.Data("Poids normal", calculerPoidsNormal()));
        pieChartData.add(new PieChart.Data("Sous-poids", calculerSousPoids()));







        return pieChartData;
    }

    private static double calculerSousPoids() {
        double count = 0;
        for (int imc : listeIMC) {
            if (imc < 18.5) {
                count++;
            }
        }
        return (count / listeIMC.size()) * 100;
    }

    private static double calculerPoidsNormal() {
        double count = 0;
        for (int imc : listeIMC) {
            if (imc >= 18.5 && imc < 25) {
                count++;
            }
        }
        return (count / listeIMC.size()) * 100;
    }

    private static double calculerSurpoids() {
        double count = 0;
        for (int imc : listeIMC) {
            if (imc >= 25 && imc < 30) {
                count++;
            }
        }
        return (count / listeIMC.size()) * 100;
    }

    private static double calculerObesite() {
        double count = 0;
        for (int imc : listeIMC) {
            if (imc >= 30) {
                count++;
            }
        }
        return (count / listeIMC.size()) * 100;
    }
}
