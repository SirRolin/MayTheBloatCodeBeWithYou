import java.util.List;
import java.util.Collections;

public class TheSorter {


    public TheSorter (){

    }

    private int settingsChecker(String isChecked){

        if(Settings.getFirstSort().equals(isChecked) ){
            return 100;
        }

        else if(Settings.getSecondSort().equals(isChecked)){
            return 10;
        }

        else if(Settings.getThirdSort().equals(isChecked)){
            return 1;
        }

        else {
            return 0;
        }

    }

    public List<Item> nameSorter(List<Item> listToBeNameSorted){

         Collections.sort(listToBeNameSorted, (Item1, Item2) -> (Item1.getName().compareTo(Item2.getName())));

        return listToBeNameSorted;
    }


    public List<Item> numberSorter(List<Item> listToBeSortedByNumbers){


        Collections.sort(listToBeSortedByNumbers, (Item1, Item2) ->
                (
                        (10 * Boolean.compare(Item1.isCheck(), Item2.isCheck())) + (Item1.getName().compareTo(Item2.getName()))
                ));

        return listToBeSortedByNumbers;
    }





}
