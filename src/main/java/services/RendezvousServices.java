package services;


import entities.Rendezvous;
import javafx.scene.control.Alert;
import utils.MyDB;
import java.sql.ResultSet;



import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RendezvousServices implements IservicesRendezvous<Rendezvous> {

//verf ex///
    public boolean entityExists(Rendezvous rendezvous) {
        try {
            // Vérifier si le rendez-vous existe dans la base de données en utilisant une requête SELECT
            String requete = "SELECT * FROM Rendezvous WHERE date_r = ? AND heur = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, rendezvous.getDate_r());
            pst.setString(2, rendezvous.getHeur());
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (le rendez-vous existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur l'existence du rendez-vous : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }
    public void addRendezvous(Rendezvous Rendezvous) {


    }





    public void addRendezvous2(Rendezvous rendezvous, int idUser) {
        try {
            // Obtenir la date actuelle
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedCurrentDate = currentDate.format(formatter);

            // Vérifier si le rendez-vous existe déjà dans la base de données
            if (entityExists(rendezvous)) {
                //System.out.println("Le rendez-vous existe déjà dans la base de données.");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Le rendez-vous existe déjà .");
                successAlert.showAndWait();
            } else {
                // Vérifier le format de la date
                if (!isValidDateFormat(rendezvous.getDate_r())) {
                    //System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Format de date invalide. Utilisez le format YYYY-MM-DD.!");
                    successAlert.showAndWait();


                    return;
                }

                // Vérifier le format de l'heure et si elle est entre 08:00 et 17:00
                if (!isValidTimeFormat(rendezvous.getHeur()) || !isTimeInRange(rendezvous.getHeur())) {
                   // System.out.println("Format d'heure invalide. Utilisez le format HH:00 et assurez-vous que l'heure est entre 08:00 et 17:00.");

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Format d'heure invalide. Utilisez le format HH:00 et assurez-vous que l'heure est entre 08:00 et 17:00.!");
                    successAlert.showAndWait();


                    return;
                }

                // Convertir la date du rendez-vous en objet LocalDate
                LocalDate rendezvousDate = LocalDate.parse(rendezvous.getDate_r(), formatter);

                // Vérifier si la date du rendez-vous est aujourd'hui
                if (rendezvousDate.equals(currentDate)) {
                    //System.out.println("Impossible d'ajouter un rendez-vous pour aujourd'hui.");

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Impossible d'ajouter un rendez-vous pour aujourd'hui.");
                    successAlert.showAndWait();
                    return;
                }

                // Vérifier si la date du rendez-vous est avant la date actuelle
                if (rendezvousDate.isBefore(currentDate)) {

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Impossible d'ajouter un rendez-vous avant la date actuelle.");
                    successAlert.showAndWait();

                    return;
                }

                // Ajouter le rendez-vous à la base de données s'il n'existe pas déjà
                String requete = "INSERT INTO Rendezvous (date_r, heur, IdUser) VALUES (?, ?, ?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setString(1, rendezvous.getDate_r());
                pst.setString(2, rendezvous.getHeur());
                pst.setInt(3, idUser); // Utilisation de idUser passé en paramètre
                pst.executeUpdate();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rendezvous added successfully!");
                successAlert.showAndWait();
            }
        } catch (SQLException e) {

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de l'ajout du rendezvous : " + e.getMessage());
            successAlert.showAndWait();
            // Gérer les exceptions SQLException
           // System.out.println("Erreur lors de l'ajout du rendezvous : " + e.getMessage());
        }
    }


    // Méthode pour valider le format de la date
    private boolean isValidDateFormat(String date) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    // Méthode pour valider le format de l'heure
    private boolean isValidTimeFormat(String time) {
        String regex = "([01]?[0-9]|2[0-3]):00";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    // Méthode pour vérifier si l'heure est dans la plage spécifiée
    private boolean isTimeInRange(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        return (hour >= 8 && hour < 17);
    }


    public void updateRendezvous(Rendezvous rendezvous) {
        try {
            // Obtenir la date actuelle
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedCurrentDate = currentDate.format(formatter);

            // Vérifier le format de la date
            if (!isValidDateFormat(rendezvous.getDate_r())) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Format de date invalide. Utilisez le format YYYY-MM-DD.!");
                successAlert.showAndWait();
               // System.out.println("Format de date invalide. Utilisez le format YYYY-MM-DD.");
                return;
            }

            // Vérifier le format de l'heure et si elle est entre 08:00 et 17:00
            if (!isValidTimeFormat(rendezvous.getHeur()) || !isTimeInRange(rendezvous.getHeur())) {
               // System.out.println("Format d'heure invalide. Utilisez le format HH:00 et assurez-vous que l'heure est entre 08:00 et 17:00.");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Format d'heure invalide. Utilisez le format HH:00 et assurez-vous que l'heure est entre 08:00 et 17:00.");
                successAlert.showAndWait();

                return;
            }

            // Convertir la date du rendez-vous en objet LocalDate
            LocalDate rendezvousDate = LocalDate.parse(rendezvous.getDate_r(), formatter);

            // Vérifier si la date du rendez-vous est aujourd'hui
            if (rendezvousDate.equals(currentDate)) {
                //System.out.println("Impossible de mettre à jour un rendez-vous pour aujourd'hui.");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Impossible de mettre à jour un rendez-vous pour aujourd'hui.");
                successAlert.showAndWait();
                return;
            }

            // Vérifier si la date du rendez-vous est avant la date actuelle
            if (rendezvousDate.isBefore(currentDate)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Impossible de mettre à jour un rendez-vous avant la date actuelle.");
                successAlert.showAndWait();

                return;
            }

            // Exécuter la mise à jour du rendez-vous
            String requete = "UPDATE Rendezvous SET date_r=?, heur=? WHERE id_r=?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, rendezvous.getDate_r());
            pst.setString(2, rendezvous.getHeur());
            pst.setInt(3, rendezvous.getIdR());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rendez-vous mis à jour avec succès!");
                successAlert.showAndWait();

            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rendez-vous non trouvé.!");
                successAlert.showAndWait();

            }
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la mise à jour du rendez-vous : " + e.getMessage());
            successAlert.showAndWait();

        }
    }




    public void deleteRendezvous(Rendezvous rendezvous) {
        String requete = "DELETE FROM Rendezvous WHERE id_r=?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, rendezvous.getIdR());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rendez-vous Deleted");
                successAlert.showAndWait();

            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rendez-vous not found");
                successAlert.showAndWait();

            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Il n'est pas autorisé de supprimer tous les rendez-vous avec des rapports et tous les rendez-vous avec rapport enregistré ");
            successAlert.showAndWait();

        }
    }




    public List<Rendezvous> getAllDataRendezvous() {
        List<Rendezvous> rendezvousList = new ArrayList<>();
        String requete = "SELECT r.*, u.nomuser, u.email, rp.rapport FROM Rendezvous r " +
                "LEFT JOIN User u ON r.idUser = u.id_user " +
                "LEFT JOIN Rapport rp ON r.idRapport = rp.id_rapport";

        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setIdR(rs.getInt("id_r"));
                rendezvous.setDate_r(rs.getString("date_r"));
                rendezvous.setHeur(rs.getString("heur"));
                rendezvous.setNomuser(rs.getString("nomuser"));
                rendezvous.setIdUser(rs.getInt("idUser"));
                rendezvous.setEmail(rs.getString("email"));
                rendezvous.setIdRapport(rs.getInt("idRapport"));
                if (rs.getString("rapport") != null) {
                    rendezvous.setRapport(rs.getString("rapport"));
                } else {
                    rendezvous.setRapport("Aucun rapport");
                }
                rendezvousList.add(rendezvous);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rendezvousList;
    }






    public Map<String, Integer> getRDVStatisticsByMonth() {
        Map<String, Integer> appointmentCountsByMonth = new HashMap<>();

        String query = "SELECT DATE_FORMAT(date_r, '%Y-%m') AS month, COUNT(*) AS count " +
                "FROM Rendezvous " +
                "GROUP BY month";

        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String month = rs.getString("month");
                int count = rs.getInt("count");
                appointmentCountsByMonth.put(month, count);
            }
        } catch (SQLException e) {
            // Gérer les exceptions
            e.printStackTrace();
        }

        return appointmentCountsByMonth;
    }


 /*   public List<Rendezvous> searchRendezvous(Rendezvous searchCriteria) {
        List<Rendezvous> searchResults = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Rendezvous WHERE 1=1");

        if (searchCriteria.getDate_r() != null && !searchCriteria.getDate_r().isEmpty()) {
            queryBuilder.append(" AND date_r = ?");
        }
        if (searchCriteria.getHeur() != null && !searchCriteria.getHeur().isEmpty()) {
            queryBuilder.append(" AND heur = ?");
        }
        if (searchCriteria.getNomuser() != null && !searchCriteria.getNomuser().isEmpty()) {
            queryBuilder.append(" AND nomuser = ?");
        }
        if (searchCriteria.getEmail() != null && !searchCriteria.getEmail().isEmpty()) {
            queryBuilder.append(" AND email = ?");
        }
        // Ajoutez des conditions pour d'autres attributs si nécessaire

        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(queryBuilder.toString());

            int parameterIndex = 1;
            if (searchCriteria.getDate_r() != null && !searchCriteria.getDate_r().isEmpty()) {
                pst.setString(parameterIndex++, searchCriteria.getDate_r());
            }
            if (searchCriteria.getHeur() != null && !searchCriteria.getHeur().isEmpty()) {
                pst.setString(parameterIndex++, searchCriteria.getHeur());
            }
            if (searchCriteria.getNomuser() != null && !searchCriteria.getNomuser().isEmpty()) {
                pst.setString(parameterIndex++, searchCriteria.getNomuser());
            }
            if (searchCriteria.getEmail() != null && !searchCriteria.getEmail().isEmpty()) {
                pst.setString(parameterIndex, searchCriteria.getEmail());
            }
            // Ajoutez des paramètres pour d'autres attributs si nécessaire

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setIdR(rs.getInt("id_r"));
                rendezvous.setDate_r(rs.getString("date_r"));
                rendezvous.setHeur(rs.getString("heur"));
                rendezvous.setNomuser(rs.getString("nomuser"));
                rendezvous.setEmail(rs.getString("email"));
                rendezvous.setIdUser(rs.getInt("idUser"));
                rendezvous.setIdRapport(rs.getInt("idRapport"));
                searchResults.add(rendezvous);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }*/













    public List<Rendezvous> rechercheParHeure(String heure) {
        ResultSet rs = null;
        List<Rendezvous> listeRendezvous = new ArrayList<>();
        try {
            String query = "SELECT R.*, U.nomuser, U.email, RP.rapport " +
                    "FROM Rendezvous R " +
                    "INNER JOIN User U ON R.idUser = U.id_user " +
                    "LEFT JOIN Rapport RP ON R.idRapport = RP.id_rapport " +
                    "WHERE R.heur LIKE ?";


            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, "%" + heure + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setIdR(rs.getInt("id_r"));
                rendezvous.setDate_r(rs.getString("date_r"));
                rendezvous.setHeur(rs.getString("heur"));
                rendezvous.setNomuser(rs.getString("nomuser"));
                rendezvous.setEmail(rs.getString("email"));
                rendezvous.setRapport(rs.getString("rapport"));


                // Ajoutez d'autres attributs au besoin

                listeRendezvous.add(rendezvous);
            }
        } catch (SQLException e) {
            // Gérez l'exception selon les besoins de votre application
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listeRendezvous;
    }

//////////////////////////calendr/////


    public List<Rendezvous> getRdvByDate(LocalDate dateSelectionnee) {
        List<Rendezvous> rdvs = new ArrayList<>();
        String formattedDate = dateSelectionnee.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "SELECT * FROM Rendezvous WHERE date_r = ?";
        try {
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, formattedDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setIdR(rs.getInt("id_r"));
                rendezvous.setDate_r(rs.getString("date_r"));
                rendezvous.setHeur(rs.getString("heur"));
                // Ajoutez d'autres attributs au besoin
                rdvs.add(rendezvous);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rdvs;
    }

}
