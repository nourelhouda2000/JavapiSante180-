package entites;

public class Recette {

    private int id;
    private String nom;
    private String ingredient;
    private String category;
    private Nutritions nutritions;


    public void setId(int id) {
        this.id = id;
    }



    public Recette() {
    }

    public Recette(String nom, String ingredient, String category, Nutritions nutritions) {
        this.nom = nom;
        this.ingredient = ingredient;
        this.category = category;
        this.nutritions=nutritions;

    }
    public Recette( int id ,String nom, String ingredient, String category) {
        this.id = id;
        this.nom = nom;
        this.ingredient = ingredient;
        this.category = category;


    }

    public Recette(int id, String nom, String ingredient, String category, Nutritions nutritions) {
        this.id = id;
        this.nom = nom;
        this.ingredient = ingredient;
        this.category = category;
        this.nutritions=nutritions;

    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }


    public void setNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", category='" + category + '\'' +
                ", Nutritions='" + nutritions + '\'' +
                '}';
    }


}
