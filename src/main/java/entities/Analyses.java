package entities;

public class Analyses {
    int id,taille,poids,poidsideal,imc,taux;
    int santeid;

    public Analyses() {

    }
    public Analyses(int id, int taille, int poids, int poidsideal , int imc, int taux, int santeid) {
        this.id=id;
        this.taille=taille;
        this.poids=poids;
        this.poidsideal=poidsideal;
        this.imc=imc;
        this.taux=taux;
        this.santeid=santeid;

    }
    public Analyses(int poids, int taille, int poidsideal , int imc, int taux, int santeid) {

        this.taille=taille;
        this.poids=poids;
        this.poidsideal=poidsideal;
        this.imc=imc;
        this.taux=taux;
        this.santeid=santeid;

    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaille() {
        return taille;
    }


    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPoidsideal() {
        return poidsideal;
    }

    public int getImc() {
        return imc;
    }

    public void setImc(int imc) {
        this.imc = imc;
    }

    public Integer getSanteid() {
        return santeid;
    }

    public void setSanteid(Integer santeid) {
        this.santeid = santeid;
    }

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }



    public void setPoidsideal(int poidsideal) {
        this.poidsideal = poidsideal;
    }

    @Override
    public String toString() {
        return "    Analyses{" +

                ", poids='" + poids + '\'' +
                ", taille='" + taille + '\'' +
                ", poids ideal='" + poidsideal + '\'' +
                ", imc='" + imc + '\'' +
                ", taux='" + taux + '\'' +

                '}';
    }
}
