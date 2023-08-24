import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return (TheSorter.settingsChecker("check") * Boolean.compare(item1.isCheck(), ((Item) item2).isCheck())) +
                (TheSorter.settingsChecker("name") * (int) Math.signum((item1.getName().compareTo(((Item) item2).getName())))) +
                (TheSorter.settingsChecker("amount") * (int) Math.signum((item1.getAmount() - ((Item) item2).getAmount())));
    }
}
