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
        SetupSettings();
    }

    private void setupMainMenu(){
        /* Go to Settings */
        mainMenuCommands.add(s -> {
            if(s.equalsIgnoreCase("settings")){
                activeScene = Scene.settings;
                System.out.println("current settings: ");
                System.out.println("default amount: " + settings.getAmount());
                System.out.println("default checked: " + settings.isCheck());
                System.out.println("First Sort:"); /* needs sorting rules in settings done */
                System.out.println("Second Sort:");
                System.out.println("Third Sort:");
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

    private void SetupSettings() {
        /* check */
        settingsCommands.add(s -> {
            final String PATTERN = "check ?(?<answer>[a-z0-9])?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                switch(answer.toLowerCase()){
                    case "yes", "true", "1" -> {
                        settings.setCheck(true);
                    }
                    case "no", "false", "0" -> {
                        settings.setCheck(false);
                    }
                    case "toggle" -> {
                        settings.setCheck(!settings.isCheck());
                    }
                    case "" -> {
                        System.out.println("Check is: " + settings.isCheck());
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
                    settings.setAmount(Integer.parseInt(answer));
                } else {
                    System.out.println("default amount: " + settings.getAmount());
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
