package services;


import entites.Nutritions;
import entites.Recette;
import entites.Reclamations;
import entites.Reponses;
import utils.MyDB;

import java.sql.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class RecetteServices implements Iservices<Recette> {


    //verf ex///
    public boolean entityExists(Recette recette) {
        try {
            // Vérifier si le recette existe dans la base de données en utilisant une requête SELECT
            String requete = "SELECT * FROM Recette WHERE  nom = ? ingredient = ? AND category = ? AND nutrition_id = ? ";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, recette.getNom());
            pst.setString(2, recette.getIngredient());
            pst.setString(3, recette.getCategory());
            pst.setString(4, String.valueOf(recette.getNutritions()));
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (le recette existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de la vérification de l'existence du recette : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }


    public void addEntity2(Recette recette) {
        try {
            // Vérifier si le rendez-vous existe déjà dans la base de données
            if (entityExists(recette)) {
                System.out.println("La recette existe déjà dans la base de données.");
            } else {
                // Ajouter la recette à la base de données s'il n'existe pas déjà
                String requete = "INSERT INTO Recette ( nom ,ingredient, category, nutrition_id) VALUES (?, ?, ?,?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setString(1, recette.getNom());
                pst.setString(2, recette.getIngredient());
                pst.setString(3, recette.getCategory());
                pst.setInt(4, recette.getNutritions().getId());
                pst.executeUpdate();
                System.out.println("Recette ajoutée avec succès !");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de l'ajout du recette : " + e.getMessage());
        }
    }

    public void updateEntity(Recette recette) {
        String requete = "UPDATE Recette SET nom=?, ingredient=?, category=?, nutrition_id=? WHERE id=?";

        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);

            pst.setString(1, recette.getNom());
            pst.setString(2, recette.getIngredient());
            pst.setString(3, recette.getCategory());
            pst.setInt(4, recette.getNutritions().getId());
            pst.setInt(5, recette.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Recette Updated");
            } else {
                System.out.println("Recette not found");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la recette : " + e.getMessage());
        }
    }


    public void deleteEntity(Recette recette) {

        String requete = "DELETE FROM Recette WHERE id=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, recette.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("recetteDeleted");
            } else {
                System.out.println("recette not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Recette> getAllData() {
        List<Recette> listeRecette = new ArrayList<>();

        try {
            String requete = "SELECT r.id, r.nom, r.ingredient, r.category, n.id AS nutrition_id, n.fibres, n.calories, n.glucides, n.proteines, n.lipides " +
                    "FROM recette r JOIN nutritions n ON r.nutrition_id = n.id";

            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String ingredient = rs.getString("ingredient");
                String category = rs.getString("category");
                int nutritionId = rs.getInt("nutrition_id");
                int fibres = rs.getInt("fibres");
                int calories = rs.getInt("calories");
                int glucides = rs.getInt("glucides");
                int proteines = rs.getInt("proteines");
                int lipides = rs.getInt("lipides");

                // Créer un nouvel objet Nutritions avec les données récupérées
                Nutritions nutritions = new Nutritions(nutritionId, fibres, calories, glucides, proteines, lipides);

                // Créer un nouvel objet Recette avec les données récupérées
                Recette recette = new Recette(id, nom, ingredient, category, nutritions);

                // Ajouter l'objet Recette à la liste
                listeRecette.add(recette);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réponses : " + e.getMessage());
        }

        return listeRecette;
    }


    public List<Recette> searchByCriteria(String nom, String category) {
        List<Recette> result = new ArrayList<>();
        String query = "SELECT * FROM Recette WHERE nom LIKE ? AND category LIKE ?";

        try (PreparedStatement statement = MyDB.getInstance().getConnection().prepareStatement(query)) {
            // Paramétrer les valeurs des critères de recherche dans la requête
            statement.setString(1, "%" + nom + "%");
            statement.setString(2, "%" + category + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Récupérer les données de chaque recette correspondante et les ajouter à la liste de résultats
                int id = resultSet.getInt("id");
                String Nom = resultSet.getString("nom");
                String ingredient = resultSet.getString("ingredient");
                String categoryy = resultSet.getString("category");

                result.add(new Recette(id, Nom, ingredient, categoryy));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


}