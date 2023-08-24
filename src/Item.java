import java.io.Serializable;

public class Item implements Serializable, Comparable {

    private boolean check;
    private int amount;

    private String name;


    public Item(String name){
        this.check = false;
        this.amount = 0;
        this.name = name;
    }


    public boolean toggleCheck(){
        check = !check;
        return check;
    }
    @Override
    public String toString(){
        return "check: " +check + " amount: "+amount+ " name: "+name;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object Item2) {
        return (TheSorter.settingsChecker("check") * Boolean.compare(this.isCheck(), ((Item) Item2).isCheck())) +
                (TheSorter.settingsChecker("name") * (this.getName().compareTo(((Item) Item2).getName()))) +
                (TheSorter.settingsChecker("amount") * (int) Math.signum((this.getAmount() - ((Item) Item2).getAmount())));
    }
}
