package Classes;

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
     * @param toActivate Classes.Item is to activate or not
     * @param taken Classes.Item is taken or not
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

    @Override
    public String toString(){
        return( getName()+" have a bonus :" + getBonus()+", have a malus :"+getMalus()+", is an activate item :"+isToActivate()+", is taken :"+isTaken()+", his description : "+getDescription());
    }

    /**
     * Tell if an item, from his name, is in an ArrayList or not
     * @param name the name of the item
     * @param items the ArrayList in which we are looking for
     * @return yes if the item is in the ArrayList, no if not
     */
    public static boolean containItem(String name, ArrayList<Item> items) {
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
    public static Item getItem(String name, ArrayList<Item> items) {
        for (Item item : items) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    public String itemToCSV(){
        //Name;Bonus;Malus;toActivate;Taken;Description;
        String line =(getName()+";"+ getBonus()+";"+getMalus()+";"+isToActivate()+";"+isTaken()+";"+getDescription()+";");
        return line;
    }

    public static void CSVToItem(String line){
        //Name;Bonus;Malus;toActivate;Taken;Description;
        String[]values = line.split(";");
        Boolean activate =false;
        Boolean take=false;
        if(values[3].equals("true")){
            activate = true;
        }
        if(values[4].equals("true")){
            take=true;
        }
        Game.items.add(new Item(values[0],Integer.parseInt(values[1]),Integer.parseInt(values[2]),activate, take, values[5]));
    }

}
