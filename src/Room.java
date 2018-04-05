import java.util.*;

public class Room extends GameObject {

    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private boolean unlocked = false;

    /**
     * Constructor
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(String name, String description) {
        super(name, description);
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
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
}
