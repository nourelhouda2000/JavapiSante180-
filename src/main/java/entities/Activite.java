package entities;

import java.util.Objects;

public class Activite {
    private int id;
    private String nom;
    private String description;
    private String categorie;
    private String niveau;

    public Activite() {
    }

    public Activite(int id, String nom, String description, String categorie, String niveau) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.niveau = niveau;
    }

    public Activite(String nom, String description, String categorie, String niveau) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Activite activite = (Activite) object;
        return id == activite.id && Objects.equals(nom, activite.nom) && Objects.equals(description, activite.description) && Objects.equals(categorie, activite.categorie) && Objects.equals(niveau, activite.niveau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, description, categorie, niveau);
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", niveau='" + niveau + '\'' +
                '}';
    }
}
