package services;


import entities.Activite;


import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Exercice;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class Exerciceservice implements IExercice<Exercice> {

    static Connection cnx = MyDB.getInstance().getConnection();



    public boolean entityExists(Exercice exercices) {
        try {
            // Vérifier si la réclamation existe dans la base de données en utilisant une requête SELECT
            String requete = "SELECT * FROM exercice WHERE nom = ? AND description = ? AND niveau = ?  AND nombre_repetition= ? AND likes= ?  ";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, exercices.getNom());
            pst.setString(2, exercices.getDescription());
            pst.setString(3, exercices.getNiveau());
            pst.setInt(4, exercices.getNombre_repetition());
            pst.setInt(5, exercices.getLikes());

            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (exercice existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de la vérification de l'existence de l'exercice : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }



    public void addEntity(Exercice exercice) {
        try {
            // Vérifier si l'exercice existe déjà dans la base de données
            if (entityExists(exercice)) {
                System.out.println("L'exercice existe déjà dans la base de données.");
            } else {
                // Ajouter l'exercice à la base de données s'il n'existe pas déjà
                String requete = "INSERT INTO exercice (nom, description, niveau, nombre_repetition, likes, activite_id) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setString(1, exercice.getNom());
                pst.setString(2, exercice.getDescription());
                pst.setString(3, exercice.getNiveau());
                pst.setInt(4, exercice.getNombre_repetition());
                pst.setInt(5, exercice.getLikes());
                pst.setInt(6, exercice.getActivite().getId()); // Utilisation de l'identifiant de l'activité

                pst.executeUpdate();
                System.out.println("Exercice ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'exercice : " + e.getMessage());
        }
    }

    public void updateEntity(Exercice exercice) {
        String requete = "UPDATE exercice SET nom = ?, description = ?, niveau = ?, nombre_repetition = ?, likes = ?, activite_id = ? WHERE id=?";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, exercice.getNom());
            pst.setString(2, exercice.getDescription());
            pst.setString(3, exercice.getNiveau());
            pst.setInt(4, exercice.getNombre_repetition());
            pst.setInt(5, exercice.getLikes());
            pst.setInt(6, exercice.getActivite().getId()); // Utilisation de l'identifiant de l'activité
            pst.setInt(7, exercice.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Exercice Updated");
            } else {
                System.out.println("Exercice not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEntity(Exercice exercice) {
        String requete = "DELETE FROM exercice WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, exercice.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Exercice Deleted");
            } else {
                System.out.println("Exercice not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Exercice> getAllData() {
        List<Exercice> exercices = new ArrayList<>();

        try {
            String requete = "SELECT e.*, a.nom AS activite_nom FROM exercice e JOIN activite a ON e.activite_id = a.id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("e.id");
                String nom = rs.getString("e.nom");
                String description = rs.getString("e.description");
                String niveau = rs.getString("e.niveau");
                int nombre_repetition = rs.getInt("e.nombre_repetition");
                int likes = rs.getInt("e.likes");

                // Récupération du nom de l'activité associée à l'exercice
                String activiteNom = rs.getString("activite_nom");

                // Création de l'objet Activite correspondant
                Activite activite = new Activite();
                activite.setNom(activiteNom);

                // Création de l'objet Exercice avec l'objet Activite associé
                Exercice exercice = new Exercice(id, nom, description, niveau, nombre_repetition, likes, activite);

                // Ajout de l'exercice à la liste
                exercices.add(exercice);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des exercices : " + e.getMessage());
        }

        return exercices;
    }



    public List<Exercice> rechercherExercices(String nom, String description, String niveau) {
        List<Exercice> exercices = new ArrayList<>();
        try {
            // Requête SQL pour rechercher des exercices par nom, description et niveau
            String requete = "SELECT * FROM exercice WHERE nom LIKE ? AND description LIKE ? AND niveau LIKE ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            // Paramétrer les critères de recherche avec des '%' pour rechercher des correspondances partielles
            pst.setString(1, "%" + nom + "%");
            pst.setString(2, "%" + description + "%");
            pst.setString(3, "%" + niveau + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Récupérer les détails de l'exercice à partir du résultat de la requête
                int id = rs.getInt("id");
                String nomExercice = rs.getString("nom");
                String descriptionExercice = rs.getString("description");
                String niveauExercice = rs.getString("niveau");
                int nombreRepetition = rs.getInt("nombre_repetition");
                int likes = rs.getInt("likes");
                // Créer un objet Exercice avec les détails récupérés
                Exercice exercice = new Exercice(id, nomExercice, descriptionExercice, niveauExercice, nombreRepetition, likes, null);
                // Ajouter l'exercice à la liste des exercices
                exercices.add(exercice);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des exercices : " + e.getMessage());
        }
        return exercices;
    }

    public static void updateLikes(int exerciceId, int newLikesCount) {
        try {
            // Préparer la requête de mise à jour du nombre de likes
            String query = "UPDATE exercice SET likes = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, newLikesCount);
            pst.setInt(2, exerciceId);

            // Exécuter la mise à jour
            int rowsAffected = pst.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffected > 0) {
                System.out.println("Nombre de likes mis à jour avec succès pour l'exercice avec l'ID : " + exerciceId);
            } else {
                System.out.println("Échec de la mise à jour du nombre de likes pour l'exercice avec l'ID : " + exerciceId);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du nombre de likes pour l'exercice avec l'ID " + exerciceId + " : " + e.getMessage());
        }
    }





    public void updateDislikes(int exerciceId, int newDislikesCount) {
        try {
            // Préparer la requête de mise à jour du nombre de dislikes
            String query = "UPDATE exercice SET dislikes = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, newDislikesCount);
            pst.setInt(2, exerciceId);

            // Exécuter la mise à jour
            int rowsAffected = pst.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffected > 0) {
                System.out.println("Nombre de dislikes mis à jour avec succès pour l'exercice avec l'ID : " + exerciceId);
            } else {
                System.out.println("Échec de la mise à jour du nombre de dislikes pour l'exercice avec l'ID : " + exerciceId);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du nombre de dislikes pour l'exercice avec l'ID " + exerciceId + " : " + e.getMessage());
        }
    }




    public List<Exercice> getOffresByPage(int pageNumber, int itemsPerPage) {
        EntityManager em = Persistence.createEntityManagerFactory("UtriPU").createEntityManager();
        Query query = em.createQuery("SELECT e FROM Exercice e ORDER BY e.id DESC");

        int startPosition = (pageNumber - 1) * itemsPerPage;
        query.setFirstResult(startPosition);
        query.setMaxResults(itemsPerPage);

        List<Exercice> offres = query.getResultList();
        em.close();
        return offres;
    }
}













