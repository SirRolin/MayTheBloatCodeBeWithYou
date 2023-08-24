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
}