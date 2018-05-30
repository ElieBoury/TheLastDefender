package Classes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RoomTest {

    ArrayList<Item> testInventory = new ArrayList<>();
    Character testCharac = new Character("testCharac", false, "test",
            true, 1, 6, 1, testInventory);

    @Test
    public void containCharac() {
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

    @Test
    public void getCharac() {
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

    @Test
    public void removeCharac() {
        ArrayList<Character> testList = new ArrayList<>();
        testList.add(testCharac);

        assertTrue(testList.contains(testCharac));

        List<Character> t = testList.stream()
                .filter(c -> testCharac.getName().equals(c.getName()))
                .collect(Collectors.toList());

        t.forEach(testList::remove);

        assertFalse(testList.contains(testCharac));
    }

    @Test
    public void removeItem() {
        Item testItem = new Item("testItem", 0, 1,-2, true, false, "test");
        ArrayList<Item> testList = new ArrayList<>();
        testList.add(testItem);

        assertTrue(testList.contains(testItem));

        List<Item> t = testList.stream()
                .filter(c -> testItem.getName().equals(c.getName()))
                .collect(Collectors.toList());

        t.forEach(testList::remove);

        assertFalse(testList.contains(testItem));
    }
}