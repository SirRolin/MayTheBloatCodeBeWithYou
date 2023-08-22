import java.util.List;
import java.util.Collections;

public class TheSorter {

    private static int settingsChecker(String setting){

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


    public static List<Item> itemSorter(List<Item> listToBeSortedByNumbers){


        Collections.sort(listToBeSortedByNumbers, (Item1, Item2) ->
                (
                        (settingsChecker("check") * Boolean.compare(Item1.isCheck(), Item2.isCheck())) +
                        (settingsChecker("name") *Item1.getName().compareTo(Item2.getName()) +
                        (settingsChecker("amount") * ((int)Math.signum(Item1.getAmount()-Item2.getAmount()))))


                ));

        return listToBeSortedByNumbers;
    }





}
