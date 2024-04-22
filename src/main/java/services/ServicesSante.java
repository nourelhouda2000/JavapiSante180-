package services;

import entities.Sante;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesSante {
    public Connection con;
    public Statement ste;
    public ServicesSante(){
        con= MyDB.getInstance().getCon();
    }

    public void add(Sante sante) {

        try {
            // Vérifier si la réclamation existe déjà dans la base de données

            // Ajouter la réclamation à la base de données s'il n'existe pas déjà
            String requete = "INSERT INTO Sante (maladie,medicament,calories) VALUES (?, ?, ?)";
            PreparedStatement pst = MyDB.getInstance().getCon().prepareStatement(requete);
            pst.setString(1, sante.getMaladie());
            pst.setString(2, sante.getMedicment());
            pst.setInt(3, sante.getCalories());



            pst.executeUpdate();
            System.out.println("Réclamation ajoutée avec succès !");
        }
        catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }}
    public void update(Sante sante) throws SQLException {


        String requete = "UPDATE sante set maladie=?, medicament=?,calories=? where id=?";
        try {
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(requete);

            pre.setString(1, sante.getMaladie());
            pre.setString(2,sante.getMedicment());
            pre.setInt(3,sante.getCalories());

            pre.setInt(4, sante.getId());
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reclamations Updated");
            } else {
                System.out.println("reclamations not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void delete(Sante sante) throws SQLException {
        String req = "delete from Sante where id=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,sante.getId());
        pre.executeUpdate();
    }
    public List<Sante> afficher() throws SQLException {

        List<Sante> data = new ArrayList<>();
        String requete = "SELECT * FROM Sante";
        try {
            Statement st = MyDB.getInstance().getCon().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Sante sante = new Sante();

                sante.setId(rs.getInt(1));

                sante.setMaladie(rs.getString("maladie"));
                sante.setMedicment(rs.getString("medicament"));
                sante.setCalories(rs.getInt("calories"));

                data.add(sante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
