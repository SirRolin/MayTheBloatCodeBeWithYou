public class Settings {

    private int amount;

    private boolean check;


    public Settings (int amount,boolean check){
        this.amount = amount;
        this.check = check;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void createSettingsMenu(){}

}
