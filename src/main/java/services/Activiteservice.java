package services;

import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Activite;
import entities.Exercice;


public class Activiteservice implements IActivite<Activite> {

        Connection cnx = MyDB.getInstance().getConnection();

        //verf ex///
        public boolean entityExists(Activite activites) {
            try {
                // Vérifier si la réclamation existe dans la base de données en utilisant une requête SELECT
                String requete = "SELECT * FROM activite WHERE nom = ? AND description = ? AND categorie = ?  AND niveau = ?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setString(1, activites.getNom());
                pst.setString(2, activites.getDescription());
                pst.setString(3, activites.getCategorie());
                pst.setString(4, activites.getNiveau());

                ResultSet rs = pst.executeQuery();
                return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (la réclamation existe déjà), sinon false
            } catch (SQLException e) {
                // Gérer les exceptions SQLException
                System.out.println("Erreur lors de la vérification de l'existence de la réclamation : " + e.getMessage());
                return false; // En cas d'erreur, retourner false par défaut
            }
        }



        @Override
        public void addEntity(Activite activites) {
            try {
                // Vérifier si la réclamation existe déjà dans la base de données
                if (entityExists(activites)) {
                    System.out.println("L 'activite existe déjà dans la base de données.");
                } else {
                    // Ajouter la réclamation à la base de données s'il n'existe pas déjà
                    String requete = "INSERT INTO activite (nom,description, categorie, niveau) VALUES (?, ?, ?,?)";
                    PreparedStatement pst = cnx.prepareStatement(requete);
                    pst.setString(1, activites.getNom());
                    pst.setString(2, activites.getDescription());
                    pst.setString(3, activites.getCategorie());
                    pst.setString(4, activites.getNiveau());

                    pst.executeUpdate();
                    System.out.println("Activite ajoutée avec succès !");
                }
            } catch (SQLException e) {
                // Gérer les exceptions SQLException
                System.out.println("Erreur lors de l'ajout de l'activite : " + e.getMessage());
            }
        }
        public void updateEntity(Activite act) {
            String requete = "UPDATE activite SET nom = ?, description = ?, categorie = ?, niveau = ? WHERE id=?";

            try {
                PreparedStatement pst = cnx.prepareStatement(requete);

                pst.setString(1, act.getNom());
                pst.setString(2, act.getDescription());
                pst.setString(3, act.getCategorie());
                pst.setString(4, act.getNiveau());
                pst.setInt(5, act.getId());

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Activite Updated");
                } else {
                    System.out.println("Activite not found");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        public void deleteEntity(Activite activite) {
            String requete = "DELETE FROM activite WHERE id=?";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, activite.getId());

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Activite Deleted: " + activite.getId());
                } else {
                    System.out.println("Activite not found: " + activite.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error deleting activity: " + e.getMessage());
            }
        }


        public static List<Activite> getAllData() {
            List<Activite> data = new ArrayList<>();
            String requete = "SELECT * FROM activite";
            try {
                Statement st = MyDB.getInstance().getConnection().createStatement();
                ResultSet rs = st.executeQuery(requete);
                while (rs.next()) {
                    Activite a = new Activite();

                    a.setId(rs.getInt("id"));
                    a.setNom(rs.getString("nom"));
                    a.setDescription(rs.getString("description"));
                    a.setCategorie(rs.getString("categorie"));
                    a.setNiveau(rs.getString("niveau"));
                    data.add(a);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return data;
        }



        public List<Exercice> getExercicesForActivite(Activite activite) {
            List<Exercice> exercices = new ArrayList<>();
            String requete = "SELECT * FROM exercice WHERE activite_id = ?";
            try {
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, activite.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Exercice exercice = new Exercice();
                    exercice.setId(rs.getInt("id"));
                    exercice.setNom(rs.getString("nom"));
                    exercice.setDescription(rs.getString("description"));
                    exercice.setNiveau(rs.getString("niveau"));
                    // Ajoutez d'autres attributs d'exercice si nécessaire

                    exercices.add(exercice);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des exercices pour l'activité : " + e.getMessage());
            }
            return exercices;
        }



    }




