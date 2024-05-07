package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import entites.Reclamations;
import utils.MyDB;
import java.sql.ResultSet;



import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDB {
    private final String URL = "jdbc:mysql://localhost:3306/sprintjava";
    private final String USER = "root";
    private final String PWD = "";

    private static Connection cnx;
    private static MyDB instance;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connexion établie !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'établissement de la connexion : " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return cnx;
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }
}
