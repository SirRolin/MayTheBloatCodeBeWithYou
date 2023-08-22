import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextIO implements IO{
    private boolean closeProgram = false;
    private Scene activeScene = Scene.mainMenu;
    private final ArrayList<Predicate<String>> mainMenuCommands = new ArrayList<>();
    private final ArrayList<Predicate<String>> settingsCommands = new ArrayList<>();
    private final ArrayList<Predicate<String>> listCommands = new ArrayList<>();
    private ItemList activeList = null;



    @Override
    public void initiate() {
        activeScene = Scene.mainMenu;
        setupMainMenu();
        setupSettings();
        setupListFunctions();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while(!closeProgram){
            String outInput = sc.nextLine();
            switch(activeScene){
                case mainMenu -> {
                    TestOnList(mainMenuCommands, outInput);
                }
                case list -> {
                    TestOnList(settingsCommands, outInput);
                }
                case settings ->{
                    TestOnList(listCommands, outInput);
                }
            }
        }
    }

    private void TestOnList(ArrayList<Predicate<String>> mainMenuCommands, String outInput) {
        boolean accompliced = false;
        for (Predicate<String> comm : mainMenuCommands) {
            if (comm.test(outInput)) {
                accompliced = true;
                break;
            }
        }
        if(!accompliced) {
            message("Command " + outInput + " was not recognised.");
        }
    }

    @Override
    public void message(String msg) {
        System.out.println(msg);
    }

    private void setupMainMenu(){
        /* Go to Settings */
        mainMenuCommands.add(s -> {
            if(s.trim().equalsIgnoreCase("settings")){
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
        /* Help */
        mainMenuCommands.add(s -> {
            if(findPattern(s.trim(), "(\\?|help)")){
                System.out.println("commands:");
                System.out.println("Settings - goes to settings");
                System.out.println("quit, exit, back - stops the program");
                System.out.println("create, new, create new <list name> - creates a new list with the name <list name> (only accepts letters, numbers and underscores)");
                return true;
            }
            return false;
        });
        /* Quit */
        mainMenuCommands.add(s -> {
            if(findPattern(s, "(quit|exit|back)")){
                closeProgram = true;
                return true;
            }
            return false;
        });
        /* Create List */
        mainMenuCommands.add(s -> {
            final String PATTERN = "(create|new|create new) (?<list>[a-z0-9_]+)$";
            if(findPattern(s, PATTERN)){
                activeList = new ItemList(returnPattern(s, PATTERN).group("list"));
                activeScene = Scene.list;
                return true;
            }
            return false;
        });
        /* goto list */
        mainMenuCommands.add(s -> {
            final String PATTERN = "(list|goto|goto list) (?<list>[a-z0-9_]+)$";
            if(findPattern(s, PATTERN)){
                String listName = returnPattern(s, PATTERN).group("list");
                activeList = SaveSystem.loadList(listName);
                if(activeList != null) {
                    activeScene = Scene.list;
                } else {
                    System.out.println("List " + listName + " not found.");
                }
                return true;
            }
            return false;
        });
    }

    private void setupSettings() {
        /* check */
        settingsCommands.add(s -> {
            final String PATTERN = "check ?(?<answer>[a-z0-9])?$";
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
                        return false;
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
                    System.out.println("Default amount: " + Main.settings.getAmount());
                }
                return true;
            }
            return false;
        });
        /* leave settings*/
        settingsCommands.add(s -> {
            if(findPattern(s.trim(), "^(back|leave)")){
                activeScene = Scene.mainMenu;
                return true;
            }
            return false;
        });
        /* Quit program */
        settingsCommands.add(s -> {
            if(findPattern(s, "^(quit|exit)")){
                activeScene = Scene.mainMenu;
                closeProgram = true;
                return true;
            }
            return false;
        });
    }

    private void setupListFunctions() {
        /* add item */
        listCommands.add(s -> {
            final String PATTERN = "add (?<answer>[a-z_]+)?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                /* TODO add item to active list */
            }
            return false;
        });
        /* delete item */
        listCommands.add(s -> {
            final String PATTERN = "(delete|remove|d|del|rem) (?<answer>[a-z_]+)?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                /* TODO remove item from active list */
            }
            return false;
        });
        /* toggle check on item */
        listCommands.add(s -> {
            final String PATTERN = "(check|uncheck|c|toggle) (?<answer>[a-z_]+)?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("list");
                /* TODO Toggle item from active list */
            }
            return false;
        });
        /* change amount on item */
        listCommands.add(s -> {
            final String PATTERN = "(change )?(a|amount) (?<answer>[0-9]+)?";
            if(findPattern(s, PATTERN)){
                String answer = returnPattern(s, PATTERN).group("answer");
                /* TODO Toggle item from active list */
            }
            return false;
        });
        /* go back */
        listCommands.add(s -> {
            final String PATTERN = "back";
            if(findPattern(s, PATTERN)){
                /* TODO Save the List */
                activeScene = Scene.mainMenu;
                return true;
            }
            return false;
        });
    }

    private boolean findPattern(String in, String find){
        Pattern patt = Pattern.compile(find, Pattern.CASE_INSENSITIVE);
        return patt.matcher(in).find();
    }

    private Matcher returnPattern(String in, String find){
        Pattern patt = Pattern.compile(find, Pattern.CASE_INSENSITIVE);
        return patt.matcher(in);
    }
}
