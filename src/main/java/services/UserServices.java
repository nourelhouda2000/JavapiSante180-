package services;
import entities.User;
import javafx.scene.control.Alert;
import utils.MyDB;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class UserServices implements IservicesUser <User>{
    // Méthode pour valider l'âge de l'utilisateur
    private boolean isValidAge(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue > 0; // L'âge doit être positif
        } catch (NumberFormatException e) {
            return false; // Échec de la conversion en entier
        }
    }

    // Méthode pour valider l'e-mail de l'utilisateur
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Méthode pour valider le rôle de l'utilisateur
    private boolean isValidRole(int role) {
        return role >= 0 && role <= 2;
    }

    private boolean isValidSexe(String sexe) {
        return sexe.equalsIgnoreCase("Homme") || sexe.equalsIgnoreCase("Femme");
    }
    public boolean entityExists(User user) {
        try {
            // Vérifier si l'utilisateur existe dans la base de données en utilisant une requête SELECT
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, user.getEmail());
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Renvoie true si le résultat de la requête n'est pas vide (l'utilisateur existe déjà), sinon false
        } catch (SQLException e) {
            // Gérer les exceptions SQLException
            System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + e.getMessage());
            return false; // En cas d'erreur, retourner false par défaut
        }
    }
    // Méthode pour ajouter user
    public void addUser(User user) {
        try {

            if (entityExists(user)) {
               // System.out.println("user existe déjà dans la base de données");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("user existe déjà dans la base de données");
                successAlert.showAndWait();


                return;
            }
            // Validation des données de l'utilisateur
            if (!isValidAge(user.getAgeuser())) {

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("age doit être un entier positif");
                successAlert.showAndWait();
               // System.out.println("age doit être un entier positif");
                return;
            }

            if (!isValidEmail(user.getEmail())) {

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("email invalide");
                successAlert.showAndWait();
                //System.out.println("email invalide");
                return;
            }
            if (!isValidSexe(user.getSexe())) {
                //System.out.println("Homme ou Femme ???????????");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Homme ou Femme ???????????");
                successAlert.showAndWait();
                return;
            }


            if (!isValidRole(user.getRole())) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("role invalide...roles valides sont '0' pour 'admin', '1' pour 'doctor' et '2' pour 'patient'");
                successAlert.showAndWait();
               // System.out.println("role invalide...roles valides sont '0' pour 'admin', '1' pour 'doctor' et '2' pour 'patient'");
                return;
            }

            // Préparation de la requête SQL pour insérer l'utilisateur dans la base de données
            String query = "INSERT INTO user (nomuser, prenomuser, ageuser, sexe, email, mdp, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);

            // Paramètres pour l'insertion
            pst.setString(1, user.getNomuser());
            pst.setString(2, user.getPrenomuser());
            pst.setString(3, user.getAgeuser());
            pst.setString(4, user.getSexe());
            pst.setString(5, user.getEmail());
            pst.setString(6, user.getMdp());
            pst.setInt(7, user.getRole());

            // Exécution de la requête
            pst.executeUpdate();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Utilisateur ajouté avec succès");
            successAlert.showAndWait();
           // System.out.println("Utilisateur ajouté avec succès");
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            successAlert.showAndWait();
           // System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour un utilisateur
    public boolean updateUser(User user) {
        try {
            // Validation des données de l'utilisateur
            if (!isValidAge(user.getAgeuser())) {

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("age doit être un entier positif");
                successAlert.showAndWait();
                // System.out.println("age doit être un entier positif");
                return false;
            }

            if (!isValidEmail(user.getEmail())) {

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("email invalide");
                successAlert.showAndWait();
                //System.out.println("email invalide");
                return false;
            }
            if (!isValidSexe(user.getSexe())) {
                //System.out.println("Homme ou Femme ???????????");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Homme ou Femme ???????????");
                successAlert.showAndWait();
                return false;
            }


            if (!isValidRole(user.getRole())) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("role invalide...roles valides sont '0' pour 'admin', '1' pour 'doctor' et '2' pour 'patient'");
                successAlert.showAndWait();
                // System.out.println("role invalide...roles valides sont '0' pour 'admin', '1' pour 'doctor' et '2' pour 'patient'");
                return false;
            }

            // Préparation de la requête SQL pour la mise à jour des informations de l'utilisateur
            String query = "UPDATE user SET nomuser = ?, prenomuser = ?, ageuser = ?, sexe = ?, email = ?, role = ? WHERE id_user = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);

            // Paramètres pour la mise à jour
            pst.setString(1, user.getNomuser());
            pst.setString(2, user.getPrenomuser());
            pst.setString(3, user.getAgeuser());
            pst.setString(4, user.getSexe());
            pst.setString(5, user.getEmail());

            pst.setInt(6, user.getRole());
            pst.setInt(7, user.getIdUser());

            // Exécution de la requête
            int rowsUpdated = pst.executeUpdate();

            // Vérification si la mise à jour a réussi
            if (rowsUpdated > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("user mis à jour avec succès !");
                successAlert.showAndWait();
               // System.out.println("user mis à jour avec succès !");
                return true;
            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Aucun user mis à jour.");
                successAlert.showAndWait();
               // System.out.println("Aucun user mis à jour.");
                return false;
            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            return false;
        }
    }




    public boolean updateUserpwdprofil(User user) {
        try {
            // Validation des données de l'utilisateur

            // Préparation de la requête SQL pour la mise à jour des informations de l'utilisateur
            String query = "UPDATE user SET nomuser = ?, prenomuser = ?, mdp = ? WHERE id_user = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);

            // Paramètres pour la mise à jour
            pst.setString(1, user.getNomuser());
            pst.setString(2, user.getPrenomuser());

            pst.setString(3, user.getMdp());

            pst.setInt(4, user.getIdUser());

            // Exécution de la requête
            int rowsUpdated = pst.executeUpdate();

            // Vérification si la mise à jour a réussi
            if (rowsUpdated > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("user mis à jour avec succès !");
                successAlert.showAndWait();
                // System.out.println("user mis à jour avec succès !");
                return true;
            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Aucun user mis à jour.");
                successAlert.showAndWait();
                // System.out.println("Aucun user mis à jour.");
                return false;
            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            return false;
        }
    }


    public boolean deleteUser(User user) {
        try {
            // Préparation de la requête SQL pour supprimer l'utilisateur de la base de données
            String query = "DELETE FROM user WHERE id_user = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            pst.setInt(1, user.getIdUser());
            // Paramètre pour la suppression


            // Exécution de la requête
            int rowsDeleted = pst.executeUpdate();

            // Vérification si la suppression a réussi
            if (rowsDeleted > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("user supprimé avec succès");
                successAlert.showAndWait();
                //System.out.println("user supprimé avec succès");
                return true;
            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Aucun user n'a été supprimé");
                successAlert.showAndWait();
                //System.out.println("Aucun user n'a été supprimé");
                return false;
            }
        } catch (SQLException e) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            successAlert.showAndWait();
            //System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    private String mapRoleToString(int role) {
        switch (role) {
            case 0:
                return "admin";
            case 1:
                return "doctor";
            case 2:
                return "patient";
            default:
                return "manareech";
        }
    }

////affichage:////////
    public List<User> getAllUsersWithRoleNames() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setNomuser(rs.getString("nomuser"));
                user.setPrenomuser(rs.getString("prenomuser"));
                user.setAgeuser(rs.getString("ageuser"));
                user.setSexe(rs.getString("sexe"));
                user.setEmail(rs.getString("email"));
                user.setMdp(rs.getString("mdp"));
                user.setRole(rs.getInt("role"));
                user.setRoleAsString(mapRoleToString(user.getRole()));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }



    public boolean loginUser(String email, String mdp) {
        try {
            // Query to verify login credentials
            String query = "SELECT * FROM user WHERE email = ? AND mdp = ?";
            try (PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query)) {
                pst.setString(1, email);
                pst.setString(2, mdp);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // User authenticated successfully
                        return true;
                    } else {
                        // Invalid credentials
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Log the exception for debugging
            // Display a generic error message to the user
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while logging in. Please try again later.");
            errorAlert.showAndWait();
            return false;
        }
    }

    // Method to get the name of the user based on email
    public User getUserByEmail(String email) {
        try {
            // Query to retrieve the user based on email
            String query = "SELECT id_user, nomuser, prenomuser, ageuser, sexe, email, mdp, role FROM user WHERE email = ?";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setNomuser(rs.getString("nomuser"));
                user.setPrenomuser(rs.getString("prenomuser"));
                user.setAgeuser(rs.getString("ageuser"));
                user.setSexe(rs.getString("sexe"));
                user.setEmail(rs.getString("email"));
                user.setMdp(rs.getString("mdp"));
                user.setRole(rs.getInt("role"));
                return user;
            } else {
                return null; // If no user corresponding to the email is found, return null
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Log the exception for debugging
            // Display a generic error message to the user
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while retrieving user information. Please try again later.");
            errorAlert.showAndWait();
            return null;
        }
    }



}
