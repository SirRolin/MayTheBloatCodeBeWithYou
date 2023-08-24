import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheSorterTest {
    @BeforeAll
    static void setupMain(){
        Main.settings = new Settings();
    }

    @Test
    void itemSorter() {
        ItemList list = new ItemList("Unit Test");
        list.addItem(new Item("pizza"));
        list.addItem(new Item("apple"));
        list.toggleItem("apple");
        list.addItem(new Item("Test"));
        TheSorter.itemSorter(list);
    }
}