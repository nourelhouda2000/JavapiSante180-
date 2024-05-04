package test;

import utils.MyDB;

public class main {
    public static void main(String[] args) {
        // Obtention d'une instance de MyDB
        MyDB db = MyDB.getInstance();
/*
        // Création d'une instance de RendezvousServices
        Reclamationsservices rec = new Reclamationsservices();
        Date daterec = Date.valueOf("2025-03-12");
        // Appel de la méthode d'ajout d'entité avec l'objet Rendezvous
        rec.addEntity(new Reclamations("rtestttttt", "eleve", daterec));
// Création d'un objet reponse pour la mise à jour
        Date daterec1= Date.valueOf("2026-03-12");
        Reclamations r = new Reclamations("rec", "excellente", daterec);
        r.setIdrec(13);

        // Appel de la méthode pour mettre à jour l'entité Reclamations
        rec.updateEntity(r);
        Reclamations r1= new Reclamations();
        r1.setIdrec(13);

        // Appel de la méthode pour supprimer l'entité Rendezvous
        rec.deleteEntity(r1);

        List<Reclamations> reclamations = rec.getAllData();

        // Affichage des rendez-vous récupérés

        for (Reclamations reclamationss : reclamations) {
            System.out.println(reclamationss.toString());
        }
        Reponsesservices rep=new Reponsesservices();
        rep.addEntity(new Reponses("reponsessssssssss", "mauvais", daterec));

        // Création d'un objet reponse pour la mise à jour
        Date daterec2= Date.valueOf("2026-03-12");
        Reponses repo = new Reponses("testeyyyyyyaaaa", "excellente", daterec2);
        repo.setIdrep(5);
        rep.updateEntity(repo);
        // Appel de la méthode pour mettre à jour l'entité Reclamations

// Création d'un objet Reponses avec l'ID à supprimer
        Reponses reponseto= new Reponses();
        reponseto.setIdrep(5); // Supposons que vous voulez supprimer la réponse avec l'ID 5

// Appel de la méthode pour supprimer l'entité Reponses
        rep.deleteEntity(reponseto);


        List<Reponses> reponses = rep.getAllData();

        // Affichage des rendez-vous récupérés
        System.out.println("************************** Afficheeeeeeeeeeeeeeeeeeeeeeeeeeeeeeer ***********************************");
        for (Reponses reponsess : reponses) {
            System.out.println(reponsess.toString());
        }
    }
/*
   Reclamationsservices rec = new Reclamationsservices();
        Date daterec = Date.valueOf("2025-03-12");

        // Ajout d'une réclamation
        rec.addEntity(new Reclamations("reclamation retard reponse", "Priorite", daterec));

        // Récupération de l'identifiant de la réclamation ajoutée
        int idRec = rec.getLastInsertedId(); // Supposons que vous avez une méthode pour obtenir le dernier identifiant inséré

        // Création d'une instance de Reponsesservices pour gérer les réponses
        Reponsesservices rep = new Reponsesservices();

        // Ajout d'une réponse associée à la réclamation
        rep.addEntity(new Reponses("reponses envoyé", "mauvais", daterec, idRec));

        // Récupération de toutes les réponses associées à cette réclamation
        List<Reponses> reponsesForReclamation = rep.getReponsesForReclamation(idRec);

        // Affichage des réponses associées à cette réclamation
        for (Reponses r : reponsesForReclamation) {
            System.out.println(r);
        }
*/
    }}
