import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextIO implements IO{
    public enum Scene {
        mainMenu,
        settings,
        list
    }
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

    @Override
    public void run() {
        message("Hi, this is the command prototype of CheckList (Name Pending)");
        message("Please type something underneath to enter your commands.");
        message("the following commands are available: settings, help, ?, create, create new, new, quit, back, exit, list, goto, goto list");
        Scanner sc = new Scanner(System.in);
        while(!closeProgram){
            System.out.print("command: ");
            String outInput = sc.nextLine();
            switch(activeScene){
                case mainMenu -> TestOnList(mainMenuCommands, outInput);
                case list -> TestOnList(listCommands, outInput);
                case settings -> TestOnList(settingsCommands, outInput);
            }
        }
    }

    private void TestOnList(ArrayList<Predicate<String>> commandList, String outInput) {
        boolean accompliced = false;
        for (Predicate<String> comm : commandList) {
            if (comm.test(outInput)) {
                accompliced = true;
                break;
            }
        }
        if(!accompliced) message("Command " + outInput + " was not recognised.");
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
                System.out.println("goto, list, goto list <list name> - opens list with the name <list name> (only accepts letters, numbers and underscores)");
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
            final String PATTERN = "(create|new|create new) (?<list>[A-z0-9_]+)$";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                activeList = new ItemList(match.group("list"));
                activeScene = Scene.list;
                return true;
            }
            return false;
        });
        /* goto list */
        mainMenuCommands.add(s -> {
            final String PATTERN = "(list|goto|goto list) (?<list>[A-z0-9_]+)$";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String listName = match.group("list");
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
            final String PATTERN = "check ?(?<answer>[A-z0-9])?$";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String answer = match.group("list");
                switch(answer.toLowerCase()){
                    case "yes", "true", "1" -> Main.settings.setCheck(true);
                    case "no", "false", "0" -> Main.settings.setCheck(false);
                    case "toggle" -> Main.settings.setCheck(!Main.settings.isCheck());
                    case "" -> System.out.println("Check is: " + Main.settings.isCheck());
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
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String answer = match.group("answer");
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
            final String PATTERN = "add (?<answer>[A-z_]+)?";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String answer = match.group("answer");
                activeList.addItem(new Item(answer));
                return true;
            }
            return false;
        });
        /* delete item */
        listCommands.add(s -> {
            final String PATTERN = "(delete|remove|d|del|rem) (?<answer>[A-z_]+)?";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String answer = match.group("answer");
                activeList.removeItem(answer);
                return true;
            }
            return false;
        });
        /* toggle check on item */
        listCommands.add(s -> {
            final String PATTERN = "(check|uncheck|c|toggle) (?<answer>[A-z_]+)?";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                final String answer = match.group("answer");
                if(answer.isEmpty()) {
                    message("try: toggle <amount>");
                } else {
                    activeList.toggleItem(answer);
                    return true;
                }
            }
            return false;
        });
        /* change amount on item */
        listCommands.add(s -> {
            final String PATTERN = "^(change )?(a|amount) ?(?<item>[a-z_()])? ?(?<answer>[0-9]+)?$";
            Matcher match = returnPattern(s, PATTERN);
            if(match.find()){
                String item = match.group("item");
                String answer = match.group("answer");
                if(item.isBlank() || answer.isBlank()) {
                    message("try: amount <item> <amount>");
                } else {
                    try {
                        activeList.changeAmount(item, Integer.parseInt(answer));
                        return true;
                    } catch (Exception ignored) {
                        message("Can't parse \"" + answer + "\" must be an error on our side, sorry.");
                    }
                }
            }
            return false;
        });
        /* go back */
        listCommands.add(s -> {
            final String PATTERN = "back";
            if(findPattern(s, PATTERN)){
                activeList = TheSorter.itemSorter(activeList);
                SaveSystem.saveListFunction(activeList);
                activeList = null;
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
