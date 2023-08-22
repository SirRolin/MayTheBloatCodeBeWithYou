import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ItemList implements Serializable {

    public Collection<Item> items = new ArrayList<>();

    public String name;

    public ItemList(String name){
        this.name = name;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem( String name){
        //seized and redistrubted from stackoverflow
        items.removeIf(item ->  item.getName().equals(name));
    }

    public void changeAmount(String name,int amount){
        //also seized and redistributed from stackoverflow
        items.forEach(item -> {
            if (name.contains(item.getName())) {
                item.setAmount(amount);
            }
        });
    }


}
