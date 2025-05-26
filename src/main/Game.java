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
        this.worldMap.setGame(this);

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

    // Mise à jour de la position ET de la location du player
    public void setPlayerX(int x) {
        this.playerX = x;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }

    public void setPlayerY(int y) {
        this.playerY = y;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public Location getCurrentLocation() {
        return player.getLocation();
    }

    // Optionnel : méthode pratique pour bouger le joueur
    public void movePlayer(int newX, int newY) {
        this.playerX = newX;
        this.playerY = newY;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }
}
