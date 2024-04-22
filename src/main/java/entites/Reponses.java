package entites;
import java.sql.Date;
import java.util.Objects;

public class Reponses {
    private int idrep;
    private String description;
    private String notereponse ;
    private Date daterec;
    private int likes;

    public Reponses() {

    }

    public Reponses(int idrep, String description, String notereponse, Date daterec) {
        this.idrep = idrep;
        this.description = description;
        this.notereponse = notereponse;
        this.daterec = daterec;
    }

    public Reponses( String description, String notereponse, Date daterec) {

        this.description = description;
        this.notereponse = notereponse;
        this.daterec = daterec;
    }
    public int getIdrep() {
        return idrep;
    }

    public void setIdrep(int idrep) {
        this.idrep = idrep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotereponse() {
        return notereponse;
    }

    public void setNotereponse(String notereponse) {
        this.notereponse = notereponse;
    }

    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    @Override
    public String toString() {
        return "Reponses{" +
                "idrep=" + idrep +
                ", description='" + description + '\'' +
                ", notereponse='" + notereponse + '\'' +
                ", daterec=" + daterec +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reponses reponses = (Reponses) o;
        return idrep == reponses.idrep && Objects.equals(description, reponses.description) && Objects.equals(notereponse, reponses.notereponse) && Objects.equals(daterec, reponses.daterec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrep, description, notereponse, daterec);
    }
}
