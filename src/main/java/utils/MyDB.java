package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import entites.Reclamations;
import utils.MyDB;
import java.sql.ResultSet;



import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDB {
    private final String URL = "jdbc:mysql://localhost:3306/projetpix";
    private final String USER = "root";
    private final String PWD = "";

    private Connection cnx;
    private static MyDB instance;
//1rendre le constructeur prive
    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connexion établie !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'établissement de la connexion : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return cnx;
    }
//2  creation instance static
    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public void closeConnection() {
        if (cnx != null) {
            try {
                cnx.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}