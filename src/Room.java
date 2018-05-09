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
    public void presentRoom() {
        System.out.println("Vous êtes dans la " + this.getName());
        System.out.println("Description :\n " + this.getDescription());
        if(this.getItems().size()!=0) {
            System.out.println("Dans cette room sont présents ces items: ");
            for (Item item : this.items) {
                System.out.println("   " + item.getName());
            }
        }else{
            System.out.println("Aucun item présent dans cette salle.");
        }
        if(this.getCharacters().size()!=0) {
            System.out.println("Dans cette room sont présents ces characters: ");
            for (Character personnages : this.characters) {
                System.out.println("   " + personnages.getName());
            }
        }else{
            System.out.println("Aucun personnage présent dans cette salle.");
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
                persos.remove(perso);
            }
        }
    }

    public String roomToCSV(){
        StringBuilder line =  new StringBuilder();
        line.append(getId()+";"+getName()+";"+ getDescription()+";"+isUnlocked()+";");
        for (Item myItem: items) {
            if (items.get(items.size()-1)==myItem){
                line.append(myItem.getName());
            }else{
                line.append(myItem.getName()+"/");
            }
        }
        line.append(";");
        for (Character myCharacter: characters) {
            if(characters.get(characters.size()-1)==myCharacter){
                line.append(myCharacter.getName());
            }else{
                line.append(myCharacter.getName()+"/");
            }
        }
        line.append(";");
        for (Character myCharacter: lockedCharacters) {
            if(lockedCharacters.get(lockedCharacters.size()-1)==myCharacter){
                line.append(myCharacter.getName());
            }else{
                line.append(myCharacter.getName()+"/");
            }
        }
        line.append(";");
        for (Item myItem: lockedItems) {
            if (lockedItems.get(lockedItems.size()-1)==myItem){
                line.append(myItem.getName());
            }else{
                line.append(myItem.getName()+"/");
            }
        }
        line.append(";");
        return line.toString();
    }
}
