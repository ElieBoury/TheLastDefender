import java.util.ArrayList;

public class Item extends GameObject {

    private int bonus; // - signifie une augmentation de la borne max, + signifie une augmentation du namebre de dès
    private int malus; // - signifie une diminution de la borne max, + signifie une diminution du namebre de dès
    private boolean toActivate;
    private boolean taken;

    /**
     * Constructor
     * @param name Name of the item
     * @param bonus Bonus given by the item
     * @param malus Malus given by the item
     * @param toActivate Item is to activate or not
     * @param taken Item is taken or not
     * @param description Description of the item
     */
    public Item(String name, int bonus, int malus, boolean toActivate, boolean taken, String description) {
        super(name, description);
        this.bonus = bonus;
        this.malus = malus;
        this.toActivate = toActivate;
        this.taken = taken;
    }

    /**
     * Bonus getter
     * @return Bonus given by the item
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Malus getter
     * @return Malus given by the item
     */
    public int getMalus() {
        return malus;
    }

    /**
     * toActivate getter
     * @return if the item is to activate or not
     */
    public boolean isToActivate() {
        return toActivate;
    }

    /**
     * taken getter
     * @return if the item is taken or not
     */
    public boolean isTaken() {
        return taken;
    }

    /**
     * taken setter
     * @param taken
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    /**
     * Tell if an item, from his name, is in an ArrayList or not
     * @param name the name of the item
     * @param items the ArrayList in which we are looking for
     * @return yes if the item is in the ArrayList, no if not
     */
    static boolean containItem(String name, ArrayList<Item> items) {
        for (Item item : items) {
            if (name.equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Give an item, given by his name, in an ArrayList
     * @param name the name of the item
     * @param items the ArrayList in which we are looking for
     * @return the item, null if the name is not found
     */
    static Item getItem(String name, ArrayList<Item> items) {
        for (Item item : items) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

}
