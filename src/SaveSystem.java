import java.io.*;

public class SaveSystem  {

    private String dataFolder = getWorkingDirectory()+"checkListData/";
    private String settingsName = "settings.set";

    private String getWorkingDirectory(){
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

    public void saveSettingFunction(){
        try{
            FileOutputStream file = new FileOutputStream(dataFolder + settingsName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(Main.settings);
            objectOutputStream.close();
            file.close();

        }catch (IOException ex){
            System.out.println("IOEexcption is caught");
        }


    }

    public void loadSettingFunction(){

        try {
            FileInputStream file = new FileInputStream(dataFolder + settingsName);
            ObjectInputStream in = new ObjectInputStream(file);

            Main.settings = (Settings) in.readObject();
            in.close();
            file.close();
        }catch (IOException ex){
            System.out.println("caught an error");
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("class not found");
        }


    }




}
