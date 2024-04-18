package test;

import entities.Rendezvous;
import entities.Rapport;
import services.RendezvousServices;
import services.RapportServices;
import utils.MyDB;

import java.util.List; // Importez la classe List depuis java.util

public class Main {
    public static void main(String[] args) {
        // Obtention d'une instance de MyDB
        System.out.println("////////////////////////////////Obtention d'une instance de MyDB///////////////////////");
        MyDB db = MyDB.getInstance();




        RendezvousServices rendezvousServices = new RendezvousServices();


        System.out.println("////////////////////////////////ajout  Rendezvous///////////////////////");

        int idUser = 5;
        Rendezvous rendezvousWithUser = new Rendezvous("2030-02-12", "09:00");
        rendezvousServices.addRendezvous2(rendezvousWithUser, idUser);



        System.out.println("////////////////////////////////la mise à jour Rendezvous///////////////////////");
        // Création d'un objet Rendezvous pour la mise à jour
        Rendezvous r = new Rendezvous("2024-04-29", "08:00");
        r.setIdR(81);
        // Appel de la méthode pour mettre à jour l'entité Rendezvous
        rendezvousServices.updateRendezvous(r);


        System.out.println("////////////////////////////////suppression Rendezvous///////////////////////");
        // Création d'un objet Rendezvous pour la suppression
        Rendezvous rdv = new Rendezvous();
        rdv.setIdR(69);
        // Appel de la méthode pour supprimer l'entité Rendezvous
        rendezvousServices.deleteRendezvous(rdv);



        System.out.println("////////////////////////////////toutes les données des rendez-vous( Affichage)///////////////////////");

        // Appel de la méthode pour récupérer toutes les données des rapports
        List<Rendezvous> rendezvousList = rendezvousServices.getAllDataRendezvous();
        // Affichage des rapports récupérés
        for (Rendezvous rendezvous1 : rendezvousList) {
            System.out.println(rendezvous1.toString());
        }







        System.out.println("********************************************************************************************************");
        RapportServices rapportServices = new RapportServices();



        System.out.println("//////////////////////////////// Ajout d'un nouveau rapport///////////////////////");
      // Ajout d'un nouveau rapport
        Rapport rapport = new Rapport("neeeeee");
        int idRendezvous = 83; // Remplacez 69 par l'ID du rendez-vous auquel vous souhaitez lier ce rapport
        rapportServices.addEntity2Rapport(rapport, idRendezvous);



        System.out.println("//////////////////////////////// Mise à jour du rapport///////////////////////");
// Mise à jour du rapport
        Rapport nouveauRapport = new Rapport("traaaaaaaaaah");
        nouveauRapport.setIdR(70); // Définir l'ID du rapport à mettre à jour
        rapportServices.updateRapport(nouveauRapport);


        System.out.println("//////////////////////////////// supprimer le rapport///////////////////////");
        //supp//

        // Création d'un objet Rapport et définition de son ID
     /*  // Création d'un objet Rapport et définition de son ID
Rapport rapportToDelete = new Rapport();
rapportToDelete.setId_rapport(33);

// Appel de la méthode pour supprimer le rapport
rapportServices.deleteRapport(rapportToDelete);
*/



        System.out.println("//////////////////////////////// Afficher rapport///////////////////////");



        // Appel de la méthode pour récupérer toutes les données des rapports
        List<Rapport> rapportList = rapportServices.getAllDataRapport();
        // Affichage des rapports récupérés
        for (Rapport rapport1 : rapportList) {
            System.out.println(rapport1.toString());
        }




    }
}
