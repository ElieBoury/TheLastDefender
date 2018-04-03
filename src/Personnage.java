import java.util.ArrayList;

public class Personnage extends Description {
    Boolean joueur;
    int debutDes;
    int finDes;
    int nbDes;
    ArrayList<Item> inventaire;

    public Personnage(String nom, String description, Boolean joueur, int debutDes, int finDes, int nbDes, ArrayList<Item> inventaire) {
        super(nom, description);
        this.joueur = joueur;
        this.debutDes = debutDes;
        this.finDes = finDes;
        this.nbDes = nbDes;
        this.inventaire = inventaire;
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

    public ArrayList<Item> getInventaire() {
        return inventaire;
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

    public static boolean contientPerso (String nom, ArrayList<Personnage> persos){
        for(Personnage perso : persos){
            if(nom==perso.nom){
                return true;
            }
        }
        return false;
    }
    public static Personnage obtenirPerso (String nom, ArrayList<Personnage> persos){
        for(Personnage perso : persos){
            if(nom==perso.nom){
                return perso;
            }
        }
        return null;
    }

}
