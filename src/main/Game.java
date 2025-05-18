package main;

public class Game {

    private CommandRegistry commandRegistry;
    private WorldMap worldMap;

    public Game() {
        System.out.println("Initializing game...");
        this.commandRegistry = new CommandRegistry();
        this.worldMap = new WorldMap();
    }

    public void run() {
        System.out.println("Running game...");
        // your runtime code here...
        commandRegistry.commandExecute();
        // end of game
    }

}