package main;

public class Game {

    //Position par défaut du joueur (centre de la carte)
    private int playerX = 1;
    private int playerY = 1;

    //Appel des éléments nécessaires au jeu
    private WorldMap worldMap;
    private CommandRegistry registry;
    private Player player;
    private Location startingLocation;

    //Constructeur de Game
    public Game() {
        //Création de la carte du monde
        this.worldMap = new WorldMap();
        //Lie la carte à la partie actuelle
        this.worldMap.setGame(this);
        //Récupération de la position de départ
        startingLocation = worldMap.getLocation(playerX, playerY);
        //Création du joueur avec sa position de départ
        player = new Player(startingLocation);
    }
    
    //Méthodes avec getters et setters

    //Affiche les instructions et attend les commandes du joueur
    public void run() {
        registry = new CommandRegistry(this);
        registry.commandExecute();
    }

    //Retourne la position X actuelle du joueur
    public int getPlayerX() {
        return playerX;
    }

    //Retourne la position Y actuelle du joueur
    public int getPlayerY() {
        return playerY;
    }

    //Met à jour la position X du joueur
    public void setPlayerX(int x) {
        this.playerX = x;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }

    //Met à jour la position Y du joueur
    public void setPlayerY(int y) {
        this.playerY = y;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }

    //Retourne la carte
    public WorldMap getWorldMap() {
        return worldMap;
    }

    //Retourne la position actuelle du joueur
    public Location getCurrentLocation() {
        return player.getLocation();
    }

    //Déplace le joueur à une nouvelle position X/Y et met à jour sa position
    public void movePlayer(int newX, int newY) {
        this.playerX = newX;
        this.playerY = newY;
        player.setLocation(worldMap.getLocation(playerX, playerY));
    }
}
