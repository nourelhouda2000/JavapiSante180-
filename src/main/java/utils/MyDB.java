package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    private final String URL = "jdbc:mysql://localhost:3306/projetpi";
    private final String USER = "root";
    private final String PWD = "";

    private Connection cnx;
    private static MyDB instance;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connexion établie !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'établissement de la connexion : " + e.getMessage());
            // Vous pourriez lancer une exception personnalisée ici ou enregistrer l'erreur dans un fichier journal.
        }
    }

    public Connection getConnection() {
        return cnx;
    }

    public void closeConnection() {
        if (cnx != null) {
            try {
                cnx.close();
                System.out.println("Connexion fermée !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }
}
