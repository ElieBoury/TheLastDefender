import java.util.HashSet;

public class Personnage {
    String nom;
    String description;
    Boolean joueur;
    int debutDes;
    int finDes;
    int nbDes;

    public Personnage(String nom, String description, Boolean joueur, int debutDes, int finDes, int nbDes) {
        this.nom = nom;
        this.description = description;
        this.joueur = joueur;
        this.debutDes = debutDes;
        this.finDes = finDes;
        this.nbDes = nbDes;

    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getJoueur() {
        return joueur;
    }

    public int getDebutDes() {
        return debutDes;
    }

    public int getFinDes() {
        return finDes;
    }

    public int getNbDes() {
        return nbDes;
    }

    public void setDescription(String description) {
        description = description;
    }

    public void setDebutDes(int debutDes) {
        this.debutDes = debutDes;
    }

    public void setFinDes(int finDes) {
        this.finDes = finDes;
    }

    public void setNbDes(int nbDes) {
        this.nbDes = nbDes;
    }

    public void changeDeSalle(Salle salle){

    }

}
