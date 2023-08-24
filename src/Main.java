public class Main {
    public static Settings settings;
    public static void main(String[] args) {
        if(!SaveSystem.loadSettingFunction()){
            settings = new Settings();
            SaveSystem.saveSettingFunction();
        }
        IO io = new TextIO();
        io.message("initializing...");
        io.initiate();
        io.message("initialized");
        io.run();
    }
}