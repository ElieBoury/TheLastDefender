package Classes;

import java.util.ArrayList;

public class Item extends GameObject {

    private int changeMinBound;
    private int changeMaxBound;
    private int changeDice;
    private boolean toActivate;
    private boolean taken;

    /**
     * Constructor
     * @param name Name of the item
     * @param changeMinBound Change the minimum bound
     * @param changeMaxBound Change the maximum bound
     * @param changeDice Change the number of dices
     * @param toActivate Classes.Item is to activate or not
     * @param taken Classes.Item is taken or not
     * @param description Description of the item
     */
    public Item(String name, int changeMinBound, int changeMaxBound, int changeDice, boolean toActivate, boolean taken, String description) {
        super(name, description);
        this.changeMinBound = changeMinBound;
        this.changeMaxBound = changeMaxBound;
        this.changeDice = changeDice;
        this.toActivate = toActivate;
        this.taken = taken;
    }

    /**
     * changeMinBound getter
     * @return the influence on the minimum bound
     */
    public int getChangeMinBound() {
        return changeMinBound;
    }

    /**
     * changeMaxBound getter
     * @return the influence on the maximum bound
     */
    public int getChangeMaxBound() {
        return changeMaxBound;
    }

    /**
     * changeDice getter
     * @return the influence on the number of dice
     */
    public int getChangeDice() {
        return changeDice;
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
        return( getName()+" change minimum bound :" + getChangeMinBound()+", change maximum bound :"+
                getChangeMaxBound()+" change number of dice :" + getChangeDice()+
                ", is an activate item :"+isToActivate()+", is taken :"+isTaken()+
                ", his description : "+getDescription());
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
        //Name;changeMinBound;changeMaxBound;changeDice;toActivate;Taken;Description;
        String line =(getName()+";"+getChangeMinBound()+";"+ getChangeMaxBound()+";"+getChangeDice()+
                ";"+isToActivate()+";"+isTaken()+";"+getDescription()+";");
        return line;
    }

    public static void CSVToItem(String line){
        //Name;changeMinBound;changeMaxBound;changeDice;toActivate;Taken;Description;
        String[]values = line.split(";");
        Boolean activate =false;
        Boolean take=false;
        if(values[3].equals("true")){
            activate = true;
        }
        if(values[4].equals("true")){
            take=true;
        }
        Game.items.add(new Item(values[0],Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),Integer.parseInt(values[3]),activate, take, values[6]));
    }

}
