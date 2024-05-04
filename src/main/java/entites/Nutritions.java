package entites;

public class Nutritions {

    private int id;
    private int calories;
    private int glucides;
    private int lipides;
    private int fibres;
    private int proteines;

    public Nutritions() {
    }

    public Nutritions(int calories, int glucides, int lipides, int fibres, int proteines) {
        this.calories = calories;
        this.glucides = glucides;
        this.lipides = lipides;
        this.fibres = fibres;
        this.proteines = proteines;
    }

    public Nutritions(int id, int calories, int glucides, int lipides, int fibres, int proteines) {
        this.id = id;
        this.calories = calories;
        this.glucides = glucides;
        this.lipides = lipides;
        this.fibres = fibres;
        this.proteines = proteines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getGlucides() {
        return glucides;
    }

    public void setGlucides(int glucides) {
        this.glucides = glucides;
    }

    public int getLipides() {
        return lipides;
    }

    public void setLipides(int lipides) {
        this.lipides = lipides;
    }

    public int getFibres() {
        return fibres;
    }

    public void setFibres(int fibres) {
        this.fibres = fibres;
    }

    public int getProteines() {
        return proteines;
    }

    public void setProteines(int proteines) {
        this.proteines = proteines;
    }

    @Override
    public String toString() {
        return "Nutritions{" +
                "id=" + id +
                ", calories=" + calories +
                ", glucides=" + glucides +
                ", lipides=" + lipides +
                ", fibres=" + fibres +
                ", proteines=" + proteines +
                '}';
    }
}


