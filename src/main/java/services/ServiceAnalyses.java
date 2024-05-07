package services;

import entities.Analyses;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAnalyses implements AnalysesServices<Analyses> {

    public Connection con;
    public Statement ste;


    public ServiceAnalyses(){
        con= MyDB.getInstance().getConnection();
    }
    @Override
    public void add(Analyses analyse) {

        try {
            // Vérifier si la réclamation existe déjà dans la base de données

                // Ajouter la réclamation à la base de données s'il n'existe pas déjà

            String req = "insert into Analyses (poids,taille,poidsideal,imc,taux,santeid) values('"+ analyse.getPoids()+"','"+analyse.getTaille()+"','"+analyse.getPoidsideal()+"','"+analyse.getImc()+"','"+analyse.getTaux()+"','"+analyse.getSanteid()+"')";
            // req avec preparedStement - >    String req2="insert into personne (nom,prenom,age) values (?,?,?)";
            ste = con.createStatement();
            ste.executeUpdate(req);



                System.out.println("Réclamation ajoutée avec succès !");
            }
           catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }}








    @Override
    public void update(Analyses analyses) throws SQLException {


        String requete = "UPDATE analyses set poids=?, taille=?,poidsideal=?,imc=?,taux=?,santeid=? where id=?";
        try {
            PreparedStatement pre = MyDB.getInstance().getConnection().prepareStatement(requete);

            pre.setInt(1,analyses.getPoids());
            pre.setInt(2,analyses.getTaille());
            pre.setInt(3,analyses.getPoidsideal());
            pre.setInt(4,analyses.getImc());
            pre.setInt(5,analyses.getTaux());
            pre.setInt(6,analyses.getSanteid());
            pre.setInt(7,analyses.getId());
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

    @Override
    public void delete(Analyses analyses) throws SQLException {
        String req = "delete from Analyses where id=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,analyses.getId());
        pre.executeUpdate();
    }

    @Override
    public List<Analyses> afficher() throws SQLException {

        List<Analyses> data = new ArrayList<>();
        String requete = "SELECT * FROM Analyses";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Analyses analyse = new Analyses();

                analyse.setId(rs.getInt(1));

                analyse.setPoids(rs.getInt("poids"));
                analyse.setTaille(rs.getInt("taille"));
                analyse.setPoidsideal(rs.getInt("poidsideal"));
                analyse.setImc(rs.getInt("imc"));
                analyse.setTaux(rs.getInt("taux"));
                analyse.setSanteid(rs.getInt("santeid"));
                data.add(analyse);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
