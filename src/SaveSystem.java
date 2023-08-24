import java.io.*;
import java.util.List;

public class SaveSystem  {

    private static String dataFolder = getWorkingDirectory()+"/checkListData/";
    private static String settingsName = "settings.set";

    private static String listFolder = "lists/";

    private static String getWorkingDirectory(){
        String workingDirectory;
        String OS = (System.getProperty("os.name")).toUpperCase();

        //stolen if else statements from stackoverflow very proud of it
        if (OS.contains("WIN"))
        {
            //it is simply the location of the "AppData" folder
            workingDirectory = System.getenv("AppData");
        }
//Otherwise, we assume Linux or Mac
        else
        {
            //in either case, we would start in the user's home directory
            workingDirectory = System.getProperty("user.home");
            //if we are on a Mac, we are not done, we look for "Application Support"
            workingDirectory += "/Library/Application Support";
        }

        return workingDirectory;

    }

    public static boolean saveSettingFunction(){
        try{
            new File(dataFolder).mkdirs();

            FileOutputStream file = new FileOutputStream(dataFolder + settingsName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(Main.settings);
            objectOutputStream.close();
            file.close();
            return true;
        }catch (IOException ex){
            System.out.println("IOEexcption is caught");
        }
        return false;

    }

    public static boolean loadSettingFunction(){

        try {
            new File(dataFolder).mkdirs();
            FileInputStream file = new FileInputStream(dataFolder + settingsName);
            ObjectInputStream in = new ObjectInputStream(file);

            Main.settings = (Settings) in.readObject();
            in.close();
            file.close();
            return true;
        }catch (IOException ex){
            System.out.println("caught an error");
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("class not found");
        }
        return false;

    }

    public static ItemList loadList(String listName){
        ItemList list = null;
        try {
            new File(dataFolder+listFolder).mkdirs();
            FileInputStream file = new FileInputStream(dataFolder + listFolder +listName+".denmark");
            ObjectInputStream in = new ObjectInputStream(file);

            list = (ItemList) in.readObject();
            in.close();
            file.close();
        }catch (IOException ex){
            System.out.println("caught an error");
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("class not found");
        }

        return list;
    }

    public static boolean saveListFunction(ItemList list){
        try{
            new File(dataFolder+listFolder).mkdirs();
            FileOutputStream file = new FileOutputStream(dataFolder+listFolder + list.name + ".denmark");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            file.close();
            return true;
        }catch (IOException ex){
            System.out.println("IOEexcption is caught");
        }

        return false;
    }

}
