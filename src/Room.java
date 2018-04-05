import java.util.*;

public class Room extends GameObject {

    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private boolean unlocked = false;

    public Room(String name, String description) {
        super(name, description);
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
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

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    static void presentRoom(Room room) {
        System.out.println("Dans cette room sont présents ces items: ");
        for (Item item : room.items) {
            System.out.println("   " + item.getName());
        }
        System.out.println("Dans cette room sont présents ces characters: ");
        for (Character personnages : room.characters) {
            System.out.println("   " + personnages.getName());
        }
    }
}
