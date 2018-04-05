import java.util.ArrayList;

public class Item extends Description {

    private int bonus; // - signifie une augmentation de la borne max, + signifie une augmentation du nombre de dès
    private int malus; // - signifie une diminution de la borne max, + signifie une diminution du nombre de dès
    private boolean activable;
    private boolean estRecupéré;

    public Item(String nom, int bonus, int malus, boolean activable, boolean estRecupéré, String description) {
        super(nom, description);
        this.bonus = bonus;
        this.malus = malus;
        this.activable = activable;
        this.estRecupéré = estRecupéré;
    }

    public int getBonus() {
        return bonus;
    }

    public int getMalus() {
        return malus;
    }

    public boolean isActivable() {
        return activable;
    }

    public boolean isEstRecupéré() {
        return estRecupéré;
    }

    public void setEstRecupéré(boolean estRecupéré) {
        this.estRecupéré = estRecupéré;
    }

    public static boolean contientItem(String nom, ArrayList<Item> items) {
        for (Item item : items) {
            if (nom.equals(item.getNom())) {
                return true;
            }
        }
        return false;
    }

    public static Item obtenirItem(String nom, ArrayList<Item> items) {
        for (Item item : items) {
            if (nom.equals(item.getNom())) {
                return item;
            }
        }
        return null;
    }

}
