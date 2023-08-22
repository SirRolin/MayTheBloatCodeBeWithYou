import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ItemList implements Serializable {

    public Collection<Item> items = new ArrayList<>();

    public String name;

    public ItemList(String name){
        this.name = name;
    }


}
