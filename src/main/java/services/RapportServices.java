package services;

import entities.Rapport;
import javafx.scene.control.Alert;
import utils.MyDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RapportServices implements IservicesRapport<Rapport> {

    public boolean entityExists(Rapport rapport) {
        try {
            String requete = "SELECT * FROM Rapport WHERE rapport = ? AND idR = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, rapport.getRapport());
            pst.setInt(2, rapport.getIdR());
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du rapport : " + e.getMessage());
            return false;
        }
    }
    public boolean entityExistsInRendezvous(int idRendezvous) {
        try {
            String requete = "SELECT * FROM Rapport WHERE idR = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, idRendezvous);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du rapport dans ce rendez-vous : " + e.getMessage());
            return false;
        }
    }


    public int getIdRapportFromDatabase(Rapport rapport) {
        int id_rapport = -1;
        try {
            String requete = "SELECT id_rapport FROM Rapport WHERE rapport = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, rapport.getRapport());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id_rapport = rs.getInt("id_rapport");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'ID du rapport depuis la base de données : " + e.getMessage());
        }
        return id_rapport;
    }

    public void updateRendezvousWithRapportId(int idR, int idRapport) {
        try {
            String requete = "UPDATE Rendezvous SET idRapport = ? WHERE id_r = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, idRapport);
            pst.setInt(2, idR);
            pst.executeUpdate();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Rendezvous mis à jour avec l'ID du rapport !");
            successAlert.showAndWait();
           // System.out.println("Rendezvous mis à jour avec l'ID du rapport !");
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la mise à jour du rendezvous avec l'ID du rapport : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la mise à jour du rendezvous avec l'ID du rapport : " + e.getMessage());
        }
    }


    public void addEntity2Rapport(Rapport rapport, int idR) {
        try {
            // Vérifier si le champ Rapport est vide
            if (rapport.getRapport().isEmpty()) {
                System.out.println("Le champ rapport fergh............");
                return;
            }

            if (entityExists(rapport)) {
                System.out.println("Rapport mawjouuuuud .... ");
            } else {
                String requete = "INSERT INTO Rapport (rapport, idR) VALUES (?, ?)";
                PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
                pst.setString(1, rapport.getRapport());
                pst.setInt(2, idR);
                pst.executeUpdate();
                System.out.println("Rapport ajouté avec succès !");

                int idRapport = getIdRapportFromDatabase(rapport);
                updateRendezvousWithRapportId(idR, idRapport);
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
                System.out.println(" Le rapport existe déjà dans la base de données.");
            } else {
                System.out.println("Erreur lors de l'ajout du rapport : " + e.getMessage());
            }
        }
    }




    public Rapport getRapportByRendezvousId(int idRendezvous) {
        Rapport rapport = null;
        try {
            String requete = "SELECT * FROM Rapport WHERE idR = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, idRendezvous);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                rapport = new Rapport();
                rapport.setId_rapport(rs.getInt("id_rapport"));
                rapport.setRapport(rs.getString("rapport"));
                rapport.setIdR(rs.getInt("idR"));
            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la récupération du rapport par identifiant de rendez-vous : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la récupération du rapport par identifiant de rendez-vous : " + e.getMessage());
        }
        return rapport;
    }











    public boolean entityExistsById(int idRapport) {
        try {
            String requete = "SELECT * FROM Rapport WHERE id_rapport = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, idRapport);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du rapport par identifiant : " + e.getMessage());
            return false;
        }
    }



    public void updateRapport2(Rapport rapport, int idR) {
        try {
            // Vérifier si le rapport existe dans la base de données en utilisant l'identifiant fourni
            if (!entityExistsById(idR)) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Aucun rapport trouvé avec cet identifiant.");
                errorAlert.showAndWait();
                return;
            }

            // Mettre à jour le rapport dans la base de données
            String requete = "UPDATE Rapport SET rapport=? WHERE id_rapport=?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, rapport.getRapport());
            pst.setInt(2, idR);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rapport mis à jour avec succès !");
                successAlert.showAndWait();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Erreur lors de la mise à jour du rapport.");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Erreur lors de la mise à jour du rapport : " + e.getMessage());
            errorAlert.showAndWait();
        }
    }



    public void updateRapport(Rapport rapport) {
        try {
            // Vérifier si le champ Rapport est vide
            if (rapport.getRapport().isEmpty()) {
                System.out.println("Le champ Rapport fergh");
                return;
            }

            String requete = "UPDATE Rapport SET rapport=? WHERE idR=?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requete);

            pst.setString(1, rapport.getRapport());
            pst.setInt(2, rapport.getIdR());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Rapport mis à jour avec succès !");
                successAlert.showAndWait();
               // System.out.println("Rapport mis à jour avec succès !");
            } else {
                // Vérifier si le rapport existe dans la base de données avec l'identifiant donné
                if (entityExists(rapport)) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Le rapport a déjà été mis à jour.");
                    successAlert.showAndWait();
                   // System.out.println("Le rapport a déjà été mis à jour.");
                } else {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Message");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Aucun rapport trouvé avec cet identifiant.");
                    successAlert.showAndWait();
                    //System.out.println("Aucun rapport trouvé avec cet identifiant.");
                }
            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la mise à jour du rapport : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la mise à jour du rapport : " + e.getMessage());
        }
    }













    public void deleteRapport(Rapport rapport) {
        try {
            // Supprimer les rendezvous associés à ce rapport
            String deleteRendezvousQuery = "DELETE FROM Rendezvous WHERE idRapport = ?";
            PreparedStatement pstDeleteRendezvous = MyDB.getInstance().getConnection().prepareStatement(deleteRendezvousQuery);
            pstDeleteRendezvous.setInt(1, rapport.getId_rapport());
            pstDeleteRendezvous.executeUpdate();

            // Ensuite, supprimer le rapport
            String deleteRapportQuery = "DELETE FROM Rapport WHERE id_rapport = ?";
            PreparedStatement pstDeleteRapport = MyDB.getInstance().getConnection().prepareStatement(deleteRapportQuery);
            pstDeleteRapport.setInt(1, rapport.getId_rapport());
            pstDeleteRapport.executeUpdate();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Rapport supprimé avec succès !");
            successAlert.showAndWait();
           // System.out.println("Rapport supprimé avec succès !");
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la suppression du rapport : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la suppression du rapport : " + e.getMessage());
        }
    }


    public List<Rapport> getAllDataRapport() {
        List<Rapport> rapportList = new ArrayList<>();
        String requete = "SELECT * FROM Rapport";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Rapport rapport = new Rapport();
                rapport.setId_rapport(rs.getInt("id_rapport"));
                rapport.setRapport(rs.getString("rapport"));
                rapport.setIdR(rs.getInt("idR"));
                rapportList.add(rapport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rapportList;
    }

}
