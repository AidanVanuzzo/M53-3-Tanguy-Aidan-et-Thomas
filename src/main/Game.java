package main;

public class Game {

    private int playerX = 1;
    private int playerY = 1;
    private WorldMap worldMap;
    private CommandRegistry registry;

    private Player player;
    private Location startingLocation;

    public Game() {
        this.worldMap = new WorldMap();
        this.worldMap.setGame(this);  // Lien WorldMap -> Game

        startingLocation = worldMap.getLocation(playerX, playerY);
        player = new Player(startingLocation);
    }

    public void run() {
        registry = new CommandRegistry(this);
        registry.commandExecute();
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerX(int x) {
        this.playerX = x;
    }

    public void setPlayerY(int y) {
        this.playerY = y;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public Location getCurrentLocation() {
        return player.getLocation();
    }
}
