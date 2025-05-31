package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CMove implements ICommand {

    //Description de la commande move
    private String description;
    //Instance du jeu pour accéder à la position du joueur et à la carte
    private Game game;
    //Scanner pour la saisie de la direction
    private Scanner scanner;

    //Constructeur de CMove
    CMove(Game game) {
        this.description = "Allows you to move in a cardinal direction.";
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    //Méthode execute() pour la commande 'move'
    public void execute() {
        movePlayer();
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }

    // [31.05.2025] Gère le déplacement avec vérification des zones verrouillées
    public void movePlayer() {
        System.out.println();
        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");

        int direction;
        do {
            direction = scanner.nextInt();
            System.out.println();
            if (direction > 4 || direction < 1) {
                System.out.println("You need to type a number between 1 - 4");
            }
        } while (direction > 4 || direction < 1);

        int x = game.getPlayerX();
        int y = game.getPlayerY();
        int newX = x;
        int newY = y;

        switch (direction) {
            case 1 -> newY--; // North
            case 2 -> newY++; // South
            case 3 -> newX++; // East
            case 4 -> newX--; // West
        }

        Location nextLocation = game.getWorldMap().getLocation(newX, newY);

        if (nextLocation == null) {
            System.out.println("You can't go that way.");
        } else if (nextLocation.isLocked()) {
            // [31.05.2025] Zone verrouillée : découverte manuellement car on ne bouge pas
            nextLocation.setDiscovered(true);
            String lockedMsg = game.getWorldMap().getLockedDescription(nextLocation.getName());
            System.out.println(lockedMsg);
        
            // [31.05.2025] Mise à jour manuelle de la map pour forcer l'affichage
            game.getWorldMap().getWorldMap()[newY][newX] = nextLocation;
        } else {
            // Zone accessible → on se déplace normalement
            game.setPlayerX(newX);
            game.setPlayerY(newY);
            System.out.println(nextLocation.getDescription());
            System.out.println();
        }
    }
}
