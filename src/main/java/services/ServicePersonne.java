package services;

import entities.Personne;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements IServices<Personne> {

    public Connection con;
    public Statement ste;


    public ServicePersonne(){
        con= MyDB.getInstance().getConnection();
    }
    @Override
    public void add(Personne personne) throws SQLException {
        String req = "insert into personne (nom,prenom,age) values('"+ personne.getNom()+"','"+personne.getPrenom()+"',"+personne.getAge()+")";
        // req avec preparedStement - >    String req2="insert into personne (nom,prenom,age) values (?,?,?)";
        ste = con.createStatement();
        ste.executeUpdate(req);

    }



    @Override
    public void update(Personne personne) throws SQLException {

        String req = "update personne set nom=?,prenom=?,age=? where id=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,personne.getNom());
        pre.setString(2,personne.getPrenom());
        pre.setInt(3,personne.getAge());
        pre.setInt(4,personne.getId());
        pre.executeUpdate();

    }

    @Override
    public void delete(Personne personne) throws SQLException {
        String req = "delete from personne where id=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,personne.getId());
        pre.executeUpdate();
    }

    @Override
    public List<Personne> afficher() throws SQLException {
        List<Personne> pers = new ArrayList<>();
        String req = "select * from personne";
        ste = con.createStatement();
        ResultSet res =ste.executeQuery(req);
        while (res.next()){
           int id = res.getInt(1);
           String nom =res.getString("nom");
           String prenom =res.getString(3);
           int age =res.getInt("age");
           Personne p = new Personne(id,nom,prenom,age);
           pers.add(p);
        }
        return pers;
    }
}
