import java.io.File;
import java.lang.reflect.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveSystemTest {
    @BeforeAll
    static void setupMain(){
        Main.settings = new Settings();
    }

    @Test
    void saveSettingFunction() {

        assertTrue(SaveSystem.saveSettingFunction());

    }

    @Test
    void loadSettingFunction() {
        if(!new File(System.getenv("AppData")+"/checkListData/settings.set").exists()){
            saveSettingFunction();
        };

        assertTrue(SaveSystem.loadSettingFunction());
    }


    @Test
    void saveListFunction() {
        ItemList rolinsFavoriteBands = new ItemList("rolinsFavoriteBands");
        assertTrue(SaveSystem.saveListFunction(rolinsFavoriteBands));
    }

    @Test
    void loadList() {
        String filename = "rolinsFavoriteBands";
        ItemList rolinsFavoriteBands = new ItemList(filename);

        SaveSystem.saveListFunction(rolinsFavoriteBands);

        assertTrue(SaveSystem.loadList(filename).name.equals(filename));
    }

}
