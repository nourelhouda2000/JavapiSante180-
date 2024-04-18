package test;


import entities.Rendezvous;
import entities.User;
import services.UserServices;
import utils.MyDB;

import java.util.List;

public class MainN {
    public static void main(String[] args) {
        System.out.println("////////////////////////////////Obtention d'une instance de MyDB///////////////////////");
        MyDB db = MyDB.getInstance();
        System.out.println("////////////////////////////////ajouter///////////////////////");
        // Création d'un nouvel utilisateur
        User newUser = new User();
        newUser.setNomuser("nour");
        newUser.setPrenomuser("nes");
        newUser.setAgeuser("20");
        newUser.setSexe("femme");
        newUser.setEmail("nnnnnnnnnnnnnnnn@esprit.com");
        newUser.setMdp("password");
        newUser.setRole(1);


        UserServices userServices = new UserServices();
        userServices.addUser(newUser);




///////////////////////update/////////////

        User userToUpdate = new User();
        userToUpdate.setIdUser(8);
        userToUpdate.setNomuser("555");
        userToUpdate.setPrenomuser("N");
        userToUpdate.setAgeuser("13");
        userToUpdate.setSexe("homme");
        userToUpdate.setEmail("nouveauemail@gmail.com");
        userToUpdate.setMdp("nouveaumotdepasse");
        userToUpdate.setRole(2);
        userServices.updateUser(userToUpdate);





///////////////////////////////////////////supprimerr/
        System.out.println("suppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
        User user = new User();
        user.setIdUser(13);
        userServices.deleteUser(user);




        System.out.println("////////////////////////////////toutes les données des user( Affichage)///////////////////////");

        // Appel de la méthode pour récupérer toutes les données des rapports
        List<User> userList = userServices.getAllUsersWithRoleNames();
        // Affichage des rapports récupérés
        for (User user1 : userList) {
            System.out.println(user1.toString());

        }
    }
}
