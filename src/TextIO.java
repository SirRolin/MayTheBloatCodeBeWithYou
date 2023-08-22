import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextIO implements IO{
    private boolean closeProgram = false;
    private Scene activeScene = Scene.mainMenu;
    public ArrayList<Predicate<String>> mainMenuCommands = new ArrayList<>();
    public ArrayList<Predicate<String>> settingsCommands = new ArrayList<>();
    public ArrayList<Predicate<String>> listCommands = new ArrayList<>();
    private String activeList = null;



    @Override
    public void initiate() {
        setupMainMenu();
        setupSettings();
    }

    private void setupMainMenu(){
        /* Go to Settings */
        mainMenuCommands.add(s -> {
            if(s.equalsIgnoreCase("settings")){
                activeScene = Scene.settings;
                System.out.println("Current Settings: ");
                System.out.println("Default Amount: " + Main.settings.getAmount());
                System.out.println("Default Checked: " + Main.settings.isCheck());
                System.out.println("When to Sort: "); /* needs sorting rules in settings done */
                System.out.println("First Sort: ");
                System.out.println("Second Sort: ");
                System.out.println("Third Sort: ");
                return true;
            }
            return false;
        });
        /* Quit */
        mainMenuCommands.add(s -> {
            if(findPattern(s, "(quit|exit)")){
                closeProgram = true;
                return true;
            }
            return false;
        });
        /* Create List */
        mainMenuCommands.add(s -> {
            final String PATTERN = "(create|new|create new) (?<list>[a-z0-9_]+)";
            if(findPattern(s, PATTERN)){
                activeList = returnPattern(s, PATTERN).group("list");
                activeScene = Scene.list;
                return true;
            }
            return false;
        });
        /* goto list */
        mainMenuCommands.add(s -> {
            final String PATTERN = "(list|goto|goto list) (?<list>[a-z0-9_]+)";
            if(findPattern(s, PATTERN)){
                activeList = returnPattern(s, PATTERN).group("list");
                activeScene = Scene.list;
                return true;
            }
            return false;
        });
    }

    private void setupSettings() {
        /* check */
        settingsCommands.add(s -> {
            final String PATTERN = "check ?(?<answer>[a-z0-9])?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                switch(answer.toLowerCase()){
                    case "yes", "true", "1" -> {
                        Main.settings.setCheck(true);
                    }
                    case "no", "false", "0" -> {
                        Main.settings.setCheck(false);
                    }
                    case "toggle" -> {
                        Main.settings.setCheck(!Main.settings.isCheck());
                    }
                    case "" -> {
                        System.out.println("Check is: " + Main.settings.isCheck());
                    }
                    default -> {
                        System.out.println("invalid argument - valid arguments: yes/no, true/false, 1/0, toggle");
                    }
                }
                return true;
            }
            return false;
        });
        /* amount */
        settingsCommands.add(s -> {
            final String PATTERN = "amount ?(?<answer>[\\-0-9]+)?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                if(answer.contains("-")){
                    System.out.println("Can only take positive numbers");
                } else if(!answer.equals("")) {
                    Main.settings.setAmount(Integer.parseInt(answer));
                } else {
                    System.out.println("default amount: " + Main.settings.getAmount());
                }
                return true;
            }
            return false;
        });
        /*  */
    }

    private boolean findPattern(String in, String find){
        Pattern quitting = Pattern.compile(find, Pattern.CASE_INSENSITIVE);
        return quitting.matcher(in).find();
    }

    private Matcher returnPattern(String in, String find){
        Pattern quitting = Pattern.compile(find, Pattern.CASE_INSENSITIVE);
        return quitting.matcher(in);
    }
}
