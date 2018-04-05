import java.util.ArrayList;

public class Item extends GameObject {

    private int bonus; // - signifie une augmentation de la borne max, + signifie une augmentation du nombre de dès
    private int malus; // - signifie une diminution de la borne max, + signifie une diminution du nombre de dès
    private boolean toActivate;
    private boolean taken;

    public Item(String nom, int bonus, int malus, boolean toActivate, boolean taken, String description) {
        super(nom, description);
        this.bonus = bonus;
        this.malus = malus;
        this.toActivate = toActivate;
        this.taken = taken;
    }

    public int getBonus() {
        return bonus;
    }

    public int getMalus() {
        return malus;
    }

    public boolean isToActivate() {
        return toActivate;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public static boolean containItem(String nom, ArrayList<Item> items) {
        for (Item item : items) {
            if (nom.equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public static Item getItem(String nom, ArrayList<Item> items) {
        for (Item item : items) {
            if (nom.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

}
