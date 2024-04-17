package entities;

public class User {

    private int idUser;
    private  String nomuser;
    private  String Prenomuser;
    private  String ageuser;
    private String sexe;
    private  String email;
    private  String mdp;
    private  int role;



    private String roleAsString;
    public User() {
    }


    public  int getIdUser() {
        return idUser;
    }

    public  String getNomuser() {
        return nomuser;
    }

    public  String getPrenomuser() {
        return Prenomuser;
    }

    public  String getAgeuser() {
        return ageuser;
    }

    public  String getSexe() {
        return sexe;
    }

    public  String getEmail() {
        return email;
    }

    public  String getMdp() {
        return mdp;
    }

    public int getRole() {
        return role;
    }


    public  void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public  void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public  void setPrenomuser(String prenomuser) {
        this.Prenomuser = prenomuser;
    }

    public  void setAgeuser(String ageuser) {
        this.ageuser = ageuser;
    }

    public  void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public  void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(int role) {
        this.role = role;
    }


    public String getRoleAsString() {
        return roleAsString;
    }

    public void setRoleAsString(String roleAsString) {
        this.roleAsString = roleAsString;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id_User='" + idUser + '\'' +
                "NOM='" + nomuser + '\'' +
                "Prenomuser='" + Prenomuser + '\'' +
                "age='" + ageuser + '\'' +
                "sexe='" + sexe + '\'' +
                "roleee='" + roleAsString + '\'' +
                "email'" + email + '\'' +
                "\n}";
    }



}
