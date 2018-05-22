package Classes;

import Editor.EditorController;

import javafx.scene.control.TextArea;
import java.util.*;

public class Room extends GameObject {

    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private boolean unlocked = false;
    private int id;
    private ArrayList<Item> lockedItems;
    private ArrayList<Character> lockedCharacters;

    public ArrayList<Item> getLockedItems() {
        return lockedItems;
    }

    public void setLockedItems(ArrayList<Item> lockedItems) {
        this.lockedItems = lockedItems;
    }

    public ArrayList<Character> getLockedCharacters() {
        return lockedCharacters;
    }

    public void setLockedCharacters(ArrayList<Character> lockedCharacters) {
        this.lockedCharacters = lockedCharacters;
    }

    /**
     * Constructor
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(int id, String name, String description) {
        super(name, description);
        this.id = id;
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.lockedItems = new ArrayList<>();
        this.lockedCharacters = new ArrayList<>();
    }

    /**
     * id getter
     * @return the id of the room
     */
    public int getId() {
        return id;
    }

    /**
     * unlocked getter
     * @return If the access to the next room is unlocked or not
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * unlocked setter
     * @param unlocked
     */
    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    /**
     * items getter
     * @return The list of the items being in the room
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * items setter
     * @param items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * characters getter
     * @return The list of the character being in the room
     */
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * characters setter
     * @param characters
     */
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    /**
     * Present the room : tell what is in it
     */
    public void presentRoom(TextArea console) {
        console.appendText("\nVous êtes dans la " + this.getName() + "\n");
        console.appendText("Description : " + this.getDescription() + "\n");
        if(this.getItems().size()!=0) {
            console.appendText("Dans cette salle sont présents ces items: \n");
            for (Item item : this.items) {
                console.appendText("   " + item.getName() + "\n");
            }
        }else{
            console.appendText("Aucun item présent dans cette salle.\n");
        }
        if(this.getCharacters().size()!=0) {
            console.appendText("Dans cette salle sont présents ces personnages: \n");
            for (Character personnages : this.characters) {
                console.appendText("   " + personnages.getName() + "\n");
            }
        }else{
            console.appendText("Aucun personnage présent dans cette salle.\n");
        }
    }

    /**
     * Tell if a character, from his name, is in an ArrayList or not
     * @param name the name of the character
     * @param persos the ArrayList in which we are looking for
     * @return yes if the character is in the ArrayList, no if not
     */
    public static boolean containCharac(String name, ArrayList<Character> persos) {
        for (Character perso : persos) {
            if (name.equals(perso.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Give a character, given by his name, in an ArrayList
     * @param name the name of the character
     * @param persos the ArrayList in which we are looking for
     * @return the item, null if the name is not found
     */
    public static Character getCharac(String name, ArrayList<Character> persos) {
        for (Character perso : persos) {
            if (name.equals(perso.getName())) {
                return perso;
            }
        }
        return null;
    }

    /**
     * Remove a character, given by his name, in an ArrayList
     * @param name the name of the character
     * @param persos the ArrayList in which we are looking for
     */
    public static void removeCharac(String name, ArrayList<Character> persos){
        for (Character perso : persos) {
            if (name.equals(perso.getName())) {
                Game.characters.get(0).getCurrentRoom().getCharacters().remove(perso);
            }
        }
    }

    public String roomToCSV(){
        //ID;Name; Description;IsUnlocked; Items; Characters; LockedCharacters; lockedItems;
        StringBuilder line =  new StringBuilder();
        line.append(getId()+";"+getName()+";"+ getDescription()+";"+isUnlocked()+";");
        for (Item myItem: items) {
            if (items.get(items.size()-1)==myItem){
                line.append(myItem.getName());
            }else{
                line.append(myItem.getName()+"/");
            }
        }
        if (items.isEmpty()){
            line.append("null");
        }
        line.append(";");
        for (Character myCharacter: characters) {
            if(characters.get(characters.size()-1)==myCharacter){
                line.append(myCharacter.getName());
            }else{
                line.append(myCharacter.getName()+"/");
            }
        }
        if (characters.isEmpty()){
            line.append("null");
        }
        line.append(";");
        for (Character myCharacter: lockedCharacters) {
            if(lockedCharacters.get(lockedCharacters.size()-1)==myCharacter){
                line.append(myCharacter.getName());
            }else{
                line.append(myCharacter.getName()+"/");
            }
        }
        if (lockedCharacters.isEmpty()){
            line.append("null");
        }
        line.append(";");
        for (Item myItem: lockedItems) {
            if (lockedItems.get(lockedItems.size()-1)==myItem){
                line.append(myItem.getName());
            }else{
                line.append(myItem.getName()+"/");
            }
        }
        if (lockedItems.isEmpty()){
            line.append("null");
        }
        line.append(";");
        return line.toString();
    }

    public static void CSVToRoom(String line){
        //ID;Name; Description;IsUnlocked; Items; Characters; LockedCharacters; lockedItems;
        String[]values = line.split(";");
        Boolean isUnlocked =false;
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Character> lockedCharacters = new ArrayList<>();
        ArrayList<Item> lockedItems = new ArrayList<>();
        Room maRoom = new Room(Integer.parseInt(values[0]),values[1],values[2]);
        if(values[3].equals("true")){
            isUnlocked=true;
            maRoom.setUnlocked(isUnlocked);
        }
        if (!values[4].equals("null")) {
            String[] oneItem = values[4].split("/");
            for (String myItem:oneItem) {
                items.add(Item.getItem(myItem,Game.items));
            }
            maRoom.setItems(items);
        }
        if (!values[5].equals("null")) {
            String[] oneCharacter = values[5].split("/");
            for (String myCharacter:oneCharacter) {
                characters.add(Character.getCharacter(myCharacter, Game.characters));
                for (Character character:Game.characters){
                    if(myCharacter==character.getName()){
                        character.setCurrentRoom(maRoom);
                    }
                }
            }
            maRoom.setCharacters(characters);
        }
        if (!values[6].equals("null")) {
            String[] oneCharacter = values[5].split("/");
            for (String myCharacter:oneCharacter) {
                lockedCharacters.add(Character.getCharacter(myCharacter, Game.characters));
            }
            maRoom.setLockedCharacters(lockedCharacters);
        }
        if (!values[4].equals("null")) {
            String[] oneItem = values[4].split("/");
            for (String myItem:oneItem) {
                lockedItems.add(Item.getItem(myItem,Game.items));
            }
            maRoom.setLockedItems(lockedItems);
        }
        Game.rooms.add(maRoom);
    }
}