package main;

public class Game {

    //Position par défaut du joueur (centre de la carte)
    private int playerX = 1;
    private int playerY = 1;

    //Éléments nécessaires au fonctionnement du jeu
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

    // [31.05.2025] Remplacé par appel à la méthode centralisée avec découverte de la zone
    public void setPlayerX(int x) {
        updatePlayerLocation(x, this.playerY);
    }

    // [31.05.2025] Remplacé par appel à la méthode centralisée avec découverte de la zone
    public void setPlayerY(int y) {
        updatePlayerLocation(this.playerX, y);
    }

    // [31.05.2025] Méthode centralisée pour mettre à jour position + découverte automatique
    private void updatePlayerLocation(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        Location loc = worldMap.getLocation(x, y);
        if (loc != null) {
            loc.setDiscovered(true); // Découvre la zone visitée
        }
        player.setLocation(loc);
    }

    //Retourne la carte
    public WorldMap getWorldMap() {
        return worldMap;
    }

    //Retourne la position actuelle du joueur
    public Location getCurrentLocation() {
        return player.getLocation();
    }

    // [31.05.2025] Utilise maintenant updatePlayerLocation() pour centraliser la logique
    public void movePlayer(int newX, int newY) {
        updatePlayerLocation(newX, newY);
    }
}
