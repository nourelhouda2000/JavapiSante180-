package entities;
public class Rapport {
    private int id_rapport;

    private String rapport;
    private int idR;

    public Rapport() {
    }

    public Rapport(String rapport) {
        this.rapport = rapport;

    }

    public Rapport(int id_rapport, String rapport, int idR) {
        this.id_rapport = id_rapport;
        this.rapport = rapport;
        this.idR = idR;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getId_rapport() {
        return id_rapport;
    }


    public void setId_rapport(int id_rapport) {
        this.id_rapport = id_rapport;
    }


    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id_rapport='" + id_rapport + '\'' +
                ", rapport='" + rapport + '\'' +
                ", id Rendezvous='" + idR + '\'' +
                '}';
    }

    public void deleteEntity1(Rapport rapport) {
        // Logique pour supprimer l'entité Rapport
    }
}
