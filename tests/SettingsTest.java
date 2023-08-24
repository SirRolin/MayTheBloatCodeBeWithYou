import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
public class SettingsTest {

    @BeforeAll
    static void setupMain(){
        Main.settings = new Settings();
    }

    @Test
    void getAmount() {

        assertNotNull(Main.settings.getAmount());
    }

    @Test
    void setAmount() {
        int czechTheNumber = Main.settings.getAmount();
        Main.settings.setAmount(50000);
        assertNotEquals(czechTheNumber,Main.settings.getAmount());
    }

    @Test
    void isCheck() {
        assertNotNull(Main.settings.isCheck());
    }

    @Test
    void setCheck() {
        boolean czechTheCzech = Main.settings.isCheck();
        Main.settings.setCheck(true);
        assertNotEquals(czechTheCzech,Main.settings.isCheck());
    }


    @Test
    void getFirstSort() {
        assertNotNull(Main.settings.getFirstSort());
    }

    @Test
    void setFirstSort() {
        String czechTheFirstSort = Main.settings.getFirstSort();
        Main.settings.setFirstSort("amount");
        assertNotEquals(czechTheFirstSort,Main.settings.getFirstSort());

    }

    @Test
    void getSecondSort() {
        assertNotNull(Main.settings.getSecondSort());
    }

    @Test
    void setSecondSort() {
        String czechTheSecondSort = Main.settings.getSecondSort();
        Main.settings.setSecondSort("check");
        assertNotEquals(czechTheSecondSort,Main.settings.getSecondSort());
    }

    @Test
    void getThirdSort() {
        assertNotNull(Main.settings.getThirdSort());
    }

    @Test
    void setThirdSort() {
        String czechTheThirdSort = Main.settings.getThirdSort();
        Main.settings.setThirdSort("name");
        assertNotEquals(czechTheThirdSort,Main.settings.getThirdSort());
    }
}
