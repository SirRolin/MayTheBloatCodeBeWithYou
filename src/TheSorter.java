import java.util.Collection;
import java.util.List;
import java.util.Collections;

public class TheSorter {

    public static int settingsChecker(String setting){

        if(Main.settings.getFirstSort().equals(setting) ){
            return 100;
        }

        else if(Main.settings.getSecondSort().equals(setting)){
            return 10;
        }

        else if(Main.settings.getThirdSort().equals(setting)){
            return 1;
        }

        else {
            return 0;
        }

    }


    public static ItemList itemSorter(ItemList itemList){
        /* Debugging */
//        for (Item item: itemList.items
//             ) {
//            System.out.println((item.isCheck() ? "[X] " : "[ ] ") + item.getName());
//        }
        /* Using ItemComparator we sort it */
        List<Item> list = new java.util.ArrayList<>(itemList.items.stream().toList());
        list.sort(new ItemComparator());

        /* Tried sorting this way, didn't like it */
//        itemList.items.sort((Item1, Item2) ->
//                (
//                        (settingsChecker("check") * Boolean.compare(((Item) Item1).isCheck(), ((Item) Item2).isCheck())) +
//                                (settingsChecker("name") * ((Item) Item1).getName().compareTo(((Item) Item2).getName()) +
//                                        (settingsChecker("amount") * (int) Math.signum(((Item) Item1).getAmount() - ((Item) Item2).getAmount())))
//                ));

        /* Debugging */
//        for (Item item: list
//        ) {
//            System.out.println((item.isCheck() ? "[X] " : "[ ] ") + item.getName());
//        }
        itemList.items = list;

        return itemList;
    }





}
