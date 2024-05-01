package entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exercice {

@Id
    private int id;
    private String nom;
    private String description ;
    private String niveau;
    private int nombre_repetition;
    private int likes;
    private String videoUrl;
@ManyToOne
    private Activite activite;



    public Exercice() {

    }

    public Exercice(int id, String nom, String description, String niveau, int nombre_repetition, int likes , Activite activite ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
        this.nombre_repetition = nombre_repetition;
        this.likes = likes;
        this.activite=activite;
       // this.videoUrl=videoUrl;
    }

    public Exercice( String nom, String description, String niveau, int nombre_repetition, int likes , Activite activite ) {
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
        this.nombre_repetition = nombre_repetition;
        this.likes = likes;
        this.activite=activite;
       // this.videoUrl=videoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getNombre_repetition() {
        return nombre_repetition;
    }

    public void setNombre_repetition(int nombre_repetition) {
        this.nombre_repetition = nombre_repetition;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Exercice exercice = (Exercice) object;
        return id == exercice.id && nombre_repetition == exercice.nombre_repetition && likes == exercice.likes && Objects.equals(nom, exercice.nom) && Objects.equals(description, exercice.description) && Objects.equals(niveau, exercice.niveau) && Objects.equals(activite, exercice.activite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, description, niveau, nombre_repetition, likes, activite);
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", niveau='" + niveau + '\'' +
                ", nombre_repetition=" + nombre_repetition +
                ", likes=" + likes +
                ", activite=" + activite +
                '}';
    }


     //

    // Méthode pour vérifier si une vidéo est associée à l'exercice
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public boolean hasVideo() {
        // Vérifier si l'URL de la vidéo est présente
        return videoUrl != null && !videoUrl.isEmpty();
    }

}

