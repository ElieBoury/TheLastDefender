package Classes;


import static org.junit.Assert.*;
import java.util.ArrayList;

public class CharacterTest {

    Room testRoom = new Room(0, "testRoom", "test");
    Item testItem = new Item("testItem", 1,-2, true, false, "test");
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
        if (testItem.getBonus() != 0) {
            if (testItem.getBonus() > 0) {
                testCharac.setNbDice(testCharac.getNbDice()+testItem.getBonus());
            } else {
                testCharac.setUpperDice(testCharac.getUpperDice()+(testItem.getBonus() * -1));
            }
        }
        if (testItem.getMalus() != 0) {
            if (testItem.getMalus() > 0) {
                testCharac.setNbDice(testCharac.getNbDice() - testItem.getMalus());
            } else {
                testCharac.setUpperDice(testCharac.getUpperDice()-(testItem.getMalus() * -1));
            }
        }
        testCharac.getInventory().remove(testItem);

        assertEquals(2, testCharac.getNbDice());
        assertEquals(4, testCharac.getUpperDice());
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