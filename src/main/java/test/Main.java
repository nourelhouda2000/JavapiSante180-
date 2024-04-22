package test;

import entities.Sante;
import services.ServicesSante;
import utils.MyDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Sante p1 =new Sante("eya","chouk",21);


        ServicesSante sp = new ServicesSante();


            sp.add(p1);
            System.out.println(sp.afficher());
    }
}
