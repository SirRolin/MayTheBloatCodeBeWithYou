public class Settings {




    private int _amount = 0;

    private boolean _check = false;

    private String _firstSort = "check";

    private String _secondSort = "name";

    private String _thirdSort = "amount";






    public int getAmount() {
        return _amount;

    }

    public void setAmount(int amount) {
        _amount = amount;
    }

    public boolean isCheck() {
        return _check;
    }

    public void setCheck(boolean check) {
        _check = check;
    }




    public void createSettingsMenu(){}

    public String getFirstSort() {
        return _firstSort;
    }

    public void setFirstSort(String firstSort) {
        _firstSort = firstSort;
    }

    public String getSecondSort() {
        return _secondSort;
    }

    public void setSecondSort(String secondSort) {
        _secondSort = secondSort;
    }

    public String getThirdSort() {
        return _thirdSort;
    }

    public void setThirdSort(String thirdSort) {
        _thirdSort = thirdSort;
    }
}
