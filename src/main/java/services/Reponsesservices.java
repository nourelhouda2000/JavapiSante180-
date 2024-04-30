package services;

import entites.Reclamations;
import entites.Reponses;
import utils.MyDB;
import utils.MyDB;
import java.sql.ResultSet;



import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Reponsesservices implements IReponses<Reponses> {
    public boolean entityExists(Reponses reponses) {
        try {
            // Vérifier si la réclamation existe dans la base de données en utilisant une requête SELECT
            String requete = "SELECT * FROM Reponses WHERE description = ? AND notereponse = ? AND daterec = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);

            pst.setString(1, reponses.getDescription());
            pst.setString(2, reponses.getNotereponse());
            pst.setDate(3, reponses.getDaterec());

            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (la réclamation existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de la vérification de l'existence de la réclamation : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }

    public void addEntity(Reponses reponses) {
        try {
            // Vérifier si la réclamation existe déjà dans la base de données
            if (entityExists(reponses)) {
                System.out.println("La repose existe déjà dans la base de données.");
            } else {
                // Ajouter la réclamation à la base de données s'il n'existe pas déjà
                String requete = "INSERT INTO Reponses (description, notereponse, daterec ,likes) VALUES (?, ?, ?,?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setString(1, reponses.getDescription());
                pst.setString(2, reponses.getNotereponse());
                pst.setDate(3, reponses.getDaterec());
                pst.setInt(4, 0);



                pst.executeUpdate();


                System.out.println("reponse ajoutée avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la reponse : " + e.getMessage());
        }
    }


    public void updateEntity(Reponses reponses) {
        String requete = "UPDATE Reponses SET description=?, notereponse=?, daterec=?, likes=? WHERE idrep=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);

            pst.setString(1, reponses.getDescription());
            pst.setString(2, reponses.getNotereponse());
            pst.setDate(3, reponses.getDaterec());
            pst.setInt(4, 0); // Vous pouvez changer cette valeur si nécessaire
            pst.setInt(5, reponses.getIdrep()); // Fournir la valeur de l'identifiant de la réponse

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Réponse mise à jour avec succès");
            } else {
                System.out.println("Réponse non trouvée");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la réponse : " + e.getMessage());
        }
    }

    public void deleteEntity(Reponses reponses) {
        String requete = "DELETE FROM Reponses WHERE idrep=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, reponses.getIdrep());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("reponse Deleted");
            } else {
                System.out.println("reponse not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Reponses> getAllData() {
        List<Reponses> data = new ArrayList<>();
        String requete = "SELECT * FROM Reponses";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reponses r = new Reponses();

                r.setIdrep(rs.getInt("idrep"));
                r.setDescription(rs.getString("description"));
                r.setNotereponse(rs.getString("notereponse"));
                r.setDaterec(rs.getDate("daterec"));
                data.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public List<Reponses> getReponsesForReclamation(int idRec) {
        List<Reponses> reponsesList = new ArrayList<>();
        String requete = "SELECT * FROM Reponses WHERE idrec = ?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, idRec);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reponses reponse = new Reponses();
                reponse.setIdrep(rs.getInt("idrep"));
                reponse.setDescription(rs.getString("description"));
                reponse.setNotereponse(rs.getString("notereponse"));
                reponse.setDaterec(rs.getDate("daterec"));
                // Vous pouvez ajouter d'autres colonnes si nécessaire
                reponsesList.add(reponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reponsesList;
    }
}

