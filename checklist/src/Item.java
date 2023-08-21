public class Item {

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
}
