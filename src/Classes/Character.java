package Classes;

import java.util.ArrayList;

import javafx.scene.control.TextArea;

public class Character extends GameObject {
    private boolean wicked;
    private int lowerDice;
    private int upperDice;
    private int nbDice;
    private ArrayList<Item> inventory;
    private Room currentRoom;
    private String dialogue;


    /**
     * Constructor
     * @param name Name of the character
     * @param wicked haracter is wicked or not
     * @param description Description of the character
     * @param lowerDice Lower bound of the dice
     * @param upperDice Upper bound of the dice
     * @param nbDice Number of dice owned
     * @param inventory Inventory of the character
     */
    public Character(String name, boolean wicked, String dialogue, String description,
                     int lowerDice, int upperDice, int nbDice, ArrayList<Item> inventory) {
        super(name, description);
        this.wicked = wicked;
        this.dialogue = dialogue;
        this.lowerDice = lowerDice;
        this.upperDice = upperDice;
        this.nbDice = nbDice;
        this.inventory = inventory;
    }

    /**
     * wicked getter
     * @return If the character is wicked or not
     */
    public boolean isWicked() {
        return wicked;
    }

    /**
     * dialogue getter
     * @return  the dialogue of the character
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * inventory getter
     * @return the inventory of the character
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * currentRoom getter
     * @return The current room in which the character is
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * currentRoom setter
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * lowerDice getter
     * @return The lower bound of the dice of the character
     */
    public int getLowerDice() {
        return lowerDice;
    }

    /**
     * lowerDice setter
     * @param lowerDice
     */
    public void setLowerDice(int lowerDice) {
        this.lowerDice = lowerDice;
    }

    /**
     * upperDice getter
     * @return The upper bound of the dice of the character
     */
    public int getUpperDice() {
        return upperDice;
    }

    /**
     * upperDice setter
     * @param upperDice
     */
    public void setUpperDice(int upperDice) {
        this.upperDice = upperDice;
    }

    /**
     * nbDice getter
     * @return The number of dice owned by the character
     */
    public int getNbDice() {
        return nbDice;
    }

    /**
     * nbDice setter
     * @param nbDice
     */
    public void setNbDice(int nbDice) {
        this.nbDice = nbDice;
    }

    /**
     * Classes.Item recovery action from the character
     * @param item The item taken
     * @param console where text is showed
     */
    public void takeItem(Item item, TextArea console) {
        if (item == null) {
            console.appendText("\nClasses.Item null\n");
            return;
        }else {
                this.inventory.add(item);
                this.currentRoom.getItems().remove(item);
                console.appendText("\nVous venez de récupérer un " + item.getName() + " !\n" +
                        "Cet item est désormais dans votre inventaire.\n");
            }
    }

    @Override
    public String toString(){
        return (getName()+"is wicked:"+isWicked()+", his lowerDice is:"+getLowerDice()+
                ", his upperDice is:"+getUpperDice()+", his number of Dice is:"+getNbDice()+", his inventory is" +
                getInventory()+", and his current room is:"+getCurrentRoom());
    }

    /**
     * Activation of an item owned by the character
     * @param item the item activated
     * @param console where the text is showed
     * @param bonusmalus if the item has to be activate as a bonus, a malus, or both
     */
    public void activateItem(Item item, TextArea console, String bonusmalus) {
        if (item.isToActivate()) {
            switch (bonusmalus) {
                case "bonus":
                    if (item.getChangeMinBound() > 0) {
                        if (this.getUpperDice() < this.getLowerDice() + item.getChangeMinBound()) {
                            this.setLowerDice(this.getUpperDice());
                        } else {
                            this.lowerDice += item.getChangeMinBound();
                        }
                        console.appendText("\nVotre borne inférieure vient d'augmenter de " +
                                item.getChangeMinBound() + " !\n" + "Elle est maintenant de " + this.lowerDice + ".\n");
                    }
                    if (item.getChangeMaxBound() > 0) {
                        this.upperDice += item.getChangeMaxBound();
                        console.appendText("\nVotre borne supérieure vient d'augmenter de " +
                                item.getChangeMaxBound() + " !\n" + "Elle est maintenant de " + this.upperDice + ".\n");
                    }
                    if (item.getChangeDice() > 0) {
                        this.nbDice += item.getChangeDice();
                        console.appendText("\nVotre nombre de dés vient d'augmenter de " +
                                item.getChangeDice() + " !\n" +
                                "Votre kit de dés est maintenant au nombre de " + this.nbDice + ".\n");
                    }
                    break;
                case "malus":
                    if (item.getChangeMinBound() < 0) {
                        this.lowerDice += item.getChangeMinBound();
                        console.appendText("\nLa borne inférieure de vos dés vient de diminuer de " +
                                item.getChangeMinBound() * -1 + " !\n" + "Elle est maintenant de " +
                                this.lowerDice + ".\n");
                    }
                    if (item.getChangeMaxBound() < 0) {
                        if (this.getLowerDice() > this.getUpperDice() + item.getChangeMaxBound()) {
                            this.setUpperDice(this.getLowerDice());
                        } else {
                            this.upperDice += item.getChangeMaxBound();
                        }
                        console.appendText("\nLa borne supérieure de vos dés vient de diminuer de " +
                                item.getChangeMinBound() * -1 + " !\n" + "Elle est maintenant de " +
                                this.upperDice + ".\n");
                    }
                    if (item.getChangeDice() < 0) {
                        if (1 > this.getNbDice() + item.getChangeDice()) {
                            console.appendText("\nVous n'avez plus de dés !!!" +
                                    "\n---GAME OVER---");
                        }else {
                            this.nbDice += item.getChangeDice();
                            console.appendText("\nVotre nombre de dés vient de diminuer de  " +
                                    item.getChangeDice() * -1 + " !\n" +
                                    "Votre kit de dés est maintenant au nombre de " + this.nbDice + ".\n");
                        }
                    }
                    break;
                case "all":
                    //Manage minimum bound
                    if (this.getUpperDice() < this.getLowerDice() + item.getChangeMinBound()) {
                        this.setLowerDice(this.getUpperDice());
                    } else {
                        this.lowerDice += item.getChangeMinBound();
                    }
                    if (item.getChangeMinBound() > 0) {
                        console.appendText("\nVotre borne inférieure vient d'augmenter de " +
                                item.getChangeMinBound() + " !\n" + "Il est maintenant de " + this.lowerDice + ".\n");
                    } else if (item.getChangeMinBound() < 0) {
                        console.appendText("\nLa borne inférieure de vos dés vient de diminuer de " +
                                item.getChangeMinBound() * -1 + " !\n" + "Elle est maintenant de " +
                                this.lowerDice + ".\n");
                    }
                    //Manage maximum bound
                    if (this.getLowerDice() > this.getUpperDice() + item.getChangeMaxBound()) {
                        this.setUpperDice(this.getLowerDice());
                    } else {
                        this.upperDice += item.getChangeMaxBound();
                    }
                    if (item.getChangeMaxBound() > 0) {
                        console.appendText("\nVotre borne supérieure vient d'augmenter de " +
                                item.getChangeMaxBound() + " !\n" + "Il est maintenant de " + this.upperDice + ".\n");
                    } else if (item.getChangeMaxBound() < 0) {
                        console.appendText("\nLa borne supérieure de vos dés vient de diminuer de " +
                                item.getChangeMinBound() * -1 + " !\n" + "Elle est maintenant de " +
                                this.upperDice + ".\n");
                    }
                    //Manage number of dices
                    if (1 > this.getNbDice() + item.getChangeDice()) {
                        console.appendText("\nVous n'avez plus de dés !!!" +
                                "\n---GAME OVER---");
                    } else {
                        this.nbDice += item.getChangeDice();
                        if (item.getChangeDice() > 0) {
                            console.appendText("\nVotre nombre de dés vient d'augmenter de " +
                                    item.getChangeDice() + " !\n" +
                                    "Votre kit de dés est maintenant au nombre de " + this.nbDice + ".\n");
                        } else if (item.getChangeDice() < 0) {
                            console.appendText("\nVotre nombre de dés vient de diminuer de  " +
                                    item.getChangeDice() * -1 + " !\n" +
                                    "Votre kit de dés est maintenant au nombre de " + this.nbDice + ".\n");
                        }

                        this.inventory.remove(item);
                    }
                    break;
            }


        } else {
            console.appendText("\nCet item ne peut pas être activé.");
        }
    }

    /**
     * Get a character from an arraylist from his name
     * @param name the name of the character
     * @param characters the arraylist
     * @return the character
     */
   public static Character getCharacter(String name, ArrayList<Character> characters) {
        for (Character myCharacter : characters) {
            if (name.equals(myCharacter.getName())) {
                return myCharacter;
            }
        }
        return null;
    }

    /**
     * Translate properties of a character in .csv
     * @return the line .csv corresponding to this character
     */
    public String characterToCSV(){
        //Name;Wicked;Dialogue;Description;LowerDice;UpperDice;nbDice;Items;
        StringBuilder line =  new StringBuilder();
        line.append(getName()+";"+ isWicked()+";"+getDialogue()+";"+getDescription()+";"+getLowerDice()+";"+
                getUpperDice()+";"+getNbDice()+";");
        if (this.inventory.isEmpty()){
            line.append("null;");
        }else{
            for (Item myItem: inventory) {
                if(inventory.get(inventory.size()-1)==myItem){
                    line.append(myItem.getName());
                }else{
                    line.append(myItem.getName()+"/");
                }
            }
            line.append(";");
        }
        return line.toString();
    }

    /**
     * Create a character from a .csv
     * @param line the line .csv of the character
     */
    public static void CSVToCharacter(String line){
        //Name;Wicked;Dialogue;Description;LowerDice;UpperDice;nbDice;Items;
        String[]values = line.split(";");
        ArrayList<Item> myInventory = new ArrayList<>();
        Boolean isWicked =false;
        if(values[1].equals("true")){
            isWicked = true;
        }
        if (!values[7].equals("null")) {
            String[] oneItem = values[7].split("/");
            for (String myItem:oneItem) {
                myInventory.add(Item.getItem(myItem,Game.items));
            }
        }
        Game.characters.add(new Character(values[0],isWicked,values[2],values[3],
                Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),myInventory));
    }
}
