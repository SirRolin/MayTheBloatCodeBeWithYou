public class Main {
    public static Settings settings = new Settings();
    public static void main(String[] args) {
        IO io = new TextIO();
        io.message("initializing...");
        io.initiate();
        io.message("initialized");
        io.run();
    }
}