package Classes;

import org.junit.Test;

import static Classes.Game.items;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ItemTest {

    Item testItem = new Item("testItem", 0, 1,2, true, "test");

    @Test
    public void containItem() {
        boolean a = false;

        ArrayList<Item> testList = new ArrayList<>();
        testList.add(testItem);

        assertFalse(a);

        for (Item item : testList) {
            if (testItem.getName().equals(item.getName())) {
                a = true;
            }
        }
        assertTrue(a);
    }

    @Test
    public void getItem() {
        boolean a = false;

        ArrayList<Item> testList = new ArrayList<>();
        testList.add(testItem);

        assertFalse(a);

        for (Item item : testList) {
            if (testItem.getName().equals(item.getName())) {
                a=true;
            }
        }

        assertTrue(a);

    }
}