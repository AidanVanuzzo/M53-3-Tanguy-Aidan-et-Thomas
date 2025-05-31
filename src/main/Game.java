package main;

import java.util.Scanner;

public class Game {

    //Position par défaut du joueur (centre de la carte)
    private int playerX = 1;
    private int playerY = 1;

    private Scanner scanner = new Scanner(System.in); // [31.05.2025] Scanner partagé pour tout le jeu

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

    // [31.05.2025] Affichage de l'intro du jeu + ambiance
    System.out.println();
    System.out.println("/////////////////////////////////////////////////////////////////////");
    System.out.println("Welcome, brave warrior!");
    System.out.println("Time is running out...");
    System.out.println("You must stop Lord Alberto before his dark powers destroy our world!");
    System.out.println("/////////////////////////////////////////////////////////////////////");
    System.out.println();
    System.out.println("ZzzzzzZzzzzzZzzzzz...");
    // [31.05.2025] Pause pour simuler le réveil : appuyer sur une touche
    System.out.println("Press Enter to wake up...");
    scanner.nextLine();
    System.out.println("Mom: Oh, you’re finally awake...");
    System.out.println("Mom: It’s very dark today, I hope there won’t be a thunderstorm.");
    System.out.println("Mom: Anyway...have a great day, my darling!");
    System.out.println();
    System.out.println("Mom: Oh, I almost forgot, I found your map in your pants before doing the laundry. What do you say?");

    // [31.05.2025] Le joueur doit dire merci pour continuer (thanks ou thank you)
    String response;
    System.out.print("You: ");
    do {
        response = scanner.nextLine().toLowerCase().trim();
        if (!(response.equals("thanks") || response.equals("thank you"))) {
            System.out.println("Mom: Hmm? Don’t forget your manners, dear...");
            System.out.print("You: ");
        }
    } while (!(response.equals("thanks") || response.equals("thank you")));
    System.out.println();
    System.out.println("Mom: You're welcome! Be careful out there. [You leave the house]");
    System.out.println();
    System.out.println("Type 'help' to discover the commands (To exit, type 'quit').");

    // [31.05.2025] Lancement normal du jeu après l'intro
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
