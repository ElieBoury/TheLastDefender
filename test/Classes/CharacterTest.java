package Classes;


import static org.junit.Assert.*;
import java.util.ArrayList;

public class CharacterTest {

    Room testRoom = new Room(0, "testRoom", "test");
    Item testItem = new Item("testItem", 0,1,2, true, "test");
    ArrayList<Item> testInventory = new ArrayList<>();
    Character testCharac = new Character("testCharac", false, "test",
            true, 1, 6, 1, testInventory);

    @org.junit.Test
    public void takeItem() {

        testRoom.getItems().add(testItem);
        testCharac.getInventory().add(testItem);
        testRoom.getItems().remove(testItem);

        assertTrue(testCharac.getInventory().contains(testItem));
        assertFalse(testRoom.getItems().contains(testItem));
    }

    @org.junit.Test
    public void activateItem() {
        testCharac.setLowerDice(testCharac.getLowerDice()+testItem.getChangeMinBound());
        testCharac.setUpperDice(testCharac.getNbDice() + testItem.getChangeMaxBound());
        testCharac.setNbDice(testCharac.getNbDice() + testItem.getChangeDice());

        testCharac.getInventory().remove(testItem);

        assertEquals(1, testCharac.getLowerDice());
        assertEquals(7, testCharac.getUpperDice());
        assertEquals(3, testCharac.getNbDice());
    }

    @org.junit.Test
    public void getCharacter() {
        boolean a = false;

        ArrayList<Character> testList = new ArrayList<>();
        testList.add(testCharac);

        assertFalse(a);

        for (Character myCharacter : testList) {
            if (testCharac.getName().equals(myCharacter.getName())) {
                a=true;
            }
        }

        assertTrue(a);
    }
}