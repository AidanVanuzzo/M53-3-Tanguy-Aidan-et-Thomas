package main;

public class Game {

    private CommandRegistry commandRegistry;
    private WorldMap worldMap;

    public Game() {
        System.out.println("Initializing game...");
        this.worldMap = new WorldMap();
        this.commandRegistry = new CommandRegistry(worldMap);
        
    }

    public void run() {
        System.out.println("Running game...");
        // your runtime code here...
        commandRegistry.commandExecute();
        // end of game
    }

}