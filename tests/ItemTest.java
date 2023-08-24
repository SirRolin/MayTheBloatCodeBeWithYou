import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemTest {


    //should be ItemTestClass but the IDE is very retarded right now.
    @Test
    void toggleCheck() {
        boolean checkTheCheck;

        Item banana = new Item("banana");
        checkTheCheck = banana.isCheck();
        banana.toggleCheck();
        assertNotEquals(checkTheCheck,banana.isCheck());

    }

    @Test
    void testToString() {
        Item banana = new Item("banana");
        assertNotNull(banana.toString());

    }

    @Test
    void isCheck() {
        boolean checktheCheck;
        Item banana = new Item("banana");
        checktheCheck = banana.isCheck();
        assertEquals(checktheCheck,banana.isCheck());
    }

    @Test
    void setCheck() {
        boolean checktheCzcech;
        Item banana = new Item("banana");
        checktheCzcech = banana.isCheck();
        assertEquals(checktheCzcech,banana.isCheck());
        banana.setCheck(true);
        checktheCzcech = banana.isCheck();
        assertEquals(checktheCzcech,banana.isCheck());

    }

    @Test
    void getAmount() {
        int czechTheNumber;
        Item banana = new Item("banana");
        czechTheNumber = banana.getAmount();
        assertEquals(czechTheNumber,banana.getAmount());
    }

    @Test
    void setAmount() {
        int czechTheNumber;
        Item banana = new Item("banana");
        czechTheNumber = banana.getAmount();
        banana.setAmount(500);
        assertNotEquals(czechTheNumber,banana.getAmount());
        czechTheNumber = banana.getAmount();
        assertEquals(czechTheNumber,banana.getAmount());
    }

    @Test
    void getName() {
        String name;
        Item banana = new Item("banana");
        name = banana.getName();
        assertNotNull(name);
    }

    @Test
    void setName() {
        String oldname;
        String newName;
        Item fruit = new Item("banana");
        oldname = fruit.getName();
        fruit.setName("broccoli");
        newName = fruit.getName();
        assertNotEquals(oldname,newName);
    }
}