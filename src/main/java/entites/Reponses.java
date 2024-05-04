package entites;
import java.sql.Date;
import java.util.Objects;

public class Reponses {
    private int idrep;
    private String description;
    private String notereponse ;
    private Date daterec;
    private int likes;
   private double rating;
    private Reclamations reclamations;

    public Reponses(int idrep) {
        this.idrep=idrep;    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Reclamations getReclamations() {
        return reclamations;
    }

    public void setReclamations(Reclamations reclamations) {
        this.reclamations = reclamations;
    }

    public Reponses() {

    }

    public Reponses(int idrep, String description, String notereponse,int likes, Date daterec,Reclamations reclamations) {
        this.idrep = idrep;
        this.description = description;
        this.notereponse = notereponse;
        this.daterec = daterec;
        this.likes=likes;
        this.reclamations=reclamations;
    }
    public Reponses(int idrep, String description, String notereponse, int likes, Date daterec, double rating, Reclamations reclamations) {
        this.idrep = idrep;
        this.description = description;
        this.notereponse = notereponse;
        this.likes = likes;
        this.daterec = daterec;
        this.rating=rating;
        this.reclamations = reclamations;
        this.rating = rating;
    }
    public Reponses( String description, String notereponse, Date daterec,Reclamations reclamations,int likes) {

        this.description = description;
        this.notereponse = notereponse;
        this.daterec = daterec;
        this.likes=likes;
        this.reclamations=reclamations;
    }
    public  int getIdrep() {
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
                ", reclamations=" + reclamations +
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


    public void setRating(double rating) {
        this.rating=rating;
    }

    public double getRating() {
        return rating;
    }

}