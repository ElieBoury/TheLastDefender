import java.util.*;

public class Salle extends Description {

    private ArrayList<Item> items;
    private ArrayList<Personnage> personnages;
    private boolean unlocked = false;

    public Salle(String nom, String description) {
        super(nom, description);
        this.items = new ArrayList<>();
        this.personnages = new ArrayList<>();
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(ArrayList<Personnage> personnages) {
        this.personnages = personnages;
    }

    static void présenterSalle(Salle salle) {
        System.out.println("Dans cette salle sont présents ces items: ");
        for (Item item : salle.items) {
            System.out.println("   " + item.getNom());
        }
        System.out.println("Dans cette salle sont présents ces personnages: ");
        for (Personnage personnages : salle.personnages) {
            System.out.println("   " + personnages.getNom());
        }
    }
}
