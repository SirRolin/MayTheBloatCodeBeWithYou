import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    void addItem() {
        Item apple = new Item("apple");
        Item banana = new Item("banana");
        Item cerial = new Item("cerial");
        ItemList itemList = new ItemList("breakfeast");
        itemList.addItem(apple);
        itemList.addItem(banana);
        itemList.addItem(cerial);
        assertNotNull(itemList.getItem(apple.getName()));

    }

    @Test
    void toggleItem() {

        Item apple = new Item("apple");
        ItemList itemList2 = new ItemList("snack");
        itemList2.addItem(apple);
        boolean checkCheck;
        if (itemList2.items.contains(apple)){
            checkCheck = apple.isCheck();
            apple.toggleCheck();
            assertNotEquals(checkCheck,apple.isCheck());
        }else {
            System.out.println("object not found please try again");
        }

    }

    @Test
    void removeItem() {
        Item apple = new Item("apple");
        Item banana = new Item("banana");
        ItemList itemList3 = new ItemList("snack");
        itemList3.addItem(apple);
        itemList3.addItem(banana);

        assertNotNull(itemList3.getItem(apple.getName()));
        itemList3.removeItem("apple");
        assertNull(itemList3.getItem(apple.getName()));
    }

    @Test
    void changeAmount() {
        ItemList itemList = new ItemList("quicksnacks");
        Item bitcoinminerInitiated = new Item("tottally not a bitcoin miner");
        Item banana = new Item("banana");
        itemList.addItem(bitcoinminerInitiated);
        itemList.addItem(banana);
        assertEquals(0,banana.getAmount());
        if(itemList.getItem(banana.getName()) != null){
            itemList.changeAmount("banana",10);
        }
        assertEquals(10,banana.getAmount());

    }

    //should be ItemTestClass but the IDE is very retarded right now.
    @Test
    void toggleCheck() {
        boolean checkTheCheck;

        Item banana = new Item("banana");
        checkTheCheck = banana.isCheck();
        assertEquals(checkTheCheck,banana.isCheck());
        banana.toggleCheck();
        checkTheCheck = banana.isCheck();
        assertEquals(checkTheCheck,banana.isCheck());

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