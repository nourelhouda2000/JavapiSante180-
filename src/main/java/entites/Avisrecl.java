package entites;

public class Avisrecl {
    private int idrating ;
    private int idUser;
    private int idrep;
    private double rating;

    public Avisrecl(int idrating, int idUser, int idrep, double rating) {
        this.idrating = idrating;
        this.idUser = idUser;
        this.idrep = idrep;
        this.rating = rating;
    }
    public Avisrecl( int idUser, int idrep, double rating) {

        this.idUser = idUser;
        this.idrep = idrep;
        this.rating = rating;
    }

    public int getIdrating() {
        return idrating;
    }

    public void setIdrating(int idrating) {
        this.idrating = idrating;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdrep() {
        return idrep;
    }

    public void setIdrec(int idrec) {
        this.idrep = idrec;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Avisrecl{" +
                "idrating=" + idrating +
                ", idUser=" + idUser +
                ", idrep=" + idrep +
                ", rating=" + rating +
                '}';
    }
}
