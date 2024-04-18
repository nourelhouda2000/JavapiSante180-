package entities;

public class Rendezvous {
    //private int id_r;
    private int idR;
    private String date_r;
    private String heur;
    private int idUser;
    private int idRapport;
    private  String nomuser;
    private  String email;
    private String rapport;
    // Constructeurs, getters et setters

    // Constructeur sans paramètre
    public Rendezvous() {
    }

    // Constructeur avec date et heure
    public Rendezvous(String date_r, String heur) {
        this.date_r = date_r;
        this.heur = heur;
    }

    // Constructeur avec tous les attributs
    public Rendezvous(int idR, String date_r, String heur, int idUser, int idRapport) {
        this.idR = idR;
        this.date_r = date_r;
        this.heur = heur;
        this.idUser = idUser;
        this.idRapport = idRapport;
    }

    // Getters et setters pour chaque attribut
    // Notez qu'ils ne sont plus statiques

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getDate_r() {
        return date_r;
    }

    public void setDate_r(String date_r) {
        this.date_r = date_r;
    }
    public String getNomuser() {
        return nomuser;
    }

    public String getEmail() {
        return email;
    }
    public  void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }
    public String getHeur() {
        return heur;
    }

    public void setHeur(String heur) {
        this.heur = heur;
    }
    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(int idRapport) {
        this.idRapport = idRapport;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Rendezvous{" +
                "Id_Rendez-vous='" + idR + '\'' +
                ", Id_User='" + idUser + '\'' +
                ", Id_Rapport='" + idRapport + '\'' +
                ", Date Rendez-vous='" + date_r + '\'' +
                ", Nom_User='" + nomuser + '\'' +
                ", email='" + email + '\'' +
                ", Rapport='" + rapport + '\'' +
                ", Heure Rendez-vous='" + heur + '\'' +
                '}';
    }

}
