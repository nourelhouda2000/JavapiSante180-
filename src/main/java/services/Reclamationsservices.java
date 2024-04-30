package services;

import entites.Reclamations;
import utils.MyDB;
import java.sql.ResultSet;



import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reclamationsservices implements IReclamations<Reclamations> {
    //verf ex///
    public boolean entityExists(Reclamations reclamations) {
        try {
            // Vérifier si la réclamation existe dans la base de données en utilisant une requête SELECT
            String requete = "SELECT * FROM Reclamations WHERE description = ? AND Priorite = ? AND daterec = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, reclamations.getDescription());
            pst.setString(2, reclamations.getPriorite());
            pst.setDate(3, reclamations.getDaterec());

            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (la réclamation existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de la vérification de l'existence de la réclamation : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }



    @Override
    public void addEntity(Reclamations reclamations) {
        try {
            // Vérifier si la réclamation existe déjà dans la base de données
            if (entityExists(reclamations)) {
                System.out.println("La réclamation existe déjà dans la base de données.");
            } else {
                // Ajouter la réclamation à la base de données s'il n'existe pas déjà
                String requete = "INSERT INTO Reclamations (description, Priorite, daterec) VALUES (?, ?, ?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setString(1, reclamations.getDescription());
                pst.setString(2, reclamations.getPriorite());
                pst.setDate(3, reclamations.getDaterec());

                pst.executeUpdate();
                System.out.println("Réclamation ajoutée avec succès !");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }
    }
   public void updateEntity(Reclamations rec) {
        String requete = "UPDATE Reclamations SET description=?, Priorite=? , daterec=? WHERE idrec=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);

            pst.setString(1, rec.getDescription());
            pst.setString(2, rec.getPriorite());
            pst.setDate(3, rec.getDaterec());
            pst.setInt(4, rec.getIdrec());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reclamations Updated");
            } else {
                System.out.println("reclamations not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteEntity(Reclamations reclamations) {
        String requete = "DELETE FROM Reclamations WHERE idrec=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, reclamations.getIdrec());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reclamations Deleted");
            } else {
                System.out.println("reclamations not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Reclamations> getAllData() {
        List<Reclamations> data = new ArrayList<>();
        String requete = "SELECT * FROM Reclamations";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamations r = new Reclamations();

                r.setIdrec(rs.getInt("idrec"));
                r.setDescription(rs.getString("description"));
                r.setPriorite(rs.getString("Priorite"));
                r.setDaterec(rs.getDate("daterec"));
                data.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }



}
