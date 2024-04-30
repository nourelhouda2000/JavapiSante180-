package entites;

import java.sql.Date; // Import du type de données Date correct
import java.util.Objects;

public class Reclamations {
    private int idrec; // Modification : supprimer le mot-clé static
    private String description; // Modification : supprimer le mot-clé static
    private String Priorite; // Modification : supprimer le mot-clé static
    private Date daterec; // Modification : supprimer le mot-clé static et mettre le bon import

    public Reclamations() {
    }

    public Reclamations(int idrec, String description, String Priorite, Date daterec) {
        this.idrec = idrec;
        this.description = description;
        this.Priorite = Priorite;
        this.daterec = daterec;
    }

    public Reclamations(String description, String Priorite, Date daterec) {
        this.description = description;
        this.Priorite = Priorite;
        this.daterec = daterec;
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriorite() {
        return Priorite;
    }

    public void setPriorite(String priorite) {
        Priorite = priorite;
    }

    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    @Override
    public String toString() {
        return "l id est " + idrec + ", le description " + description + ", Priorite " + Priorite + ", daterec " + daterec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamations that = (Reclamations) o;
        return idrec == that.idrec && Objects.equals(description, that.description) && Objects.equals(Priorite, that.Priorite) && Objects.equals(daterec, that.daterec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrec, description, Priorite, daterec);
    }
}
