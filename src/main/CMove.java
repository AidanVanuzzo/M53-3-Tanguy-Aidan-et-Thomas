package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CMove implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
    private Game game;
    private Scanner scanner;

    //Constructeur
    CMove(Game game) {
        this.description = "Allows you to move in a cardinal direction.";
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }

    //Exécution de la commande 'move' via la méthode ci-dessous
    @Override
    public void execute() {
        movePlayer();
    }

    public void movePlayer() {
        //Instructions au joueur
        System.out.println();
        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");

        //Boucle jusqu’à ce que le joueur saisisse une direction valide (1 à 4)
        int direction = -1;

        do {
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();
            System.out.println();
            try {
                direction = Integer.parseInt(input);
                if (direction < 1 || direction > 4) {
                    System.out.println("You need to type a number between 1 - 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 - 4.");
            }
        } while (direction < 1 || direction > 4);

        //Récupère de la position actuelle
        int x = game.getPlayerX();
        int y = game.getPlayerY();

        //Stockage du changement de position
        int newX = x;
        int newY = y;
        switch (direction) {
            case 1 -> newY--; //Nord
            case 2 -> newY++; //Sud
            case 3 -> newX++; //Est
            case 4 -> newX--; //Ouest
        }

        //Récupère la prochaine zone
        Location nextLocation = game.getWorldMap().getLocation(newX, newY);

        //Exécution
        if (nextLocation == null) {
            //Hors de la carte, le joueur ne bouge pas
            System.out.println("You can't go that way.");
        } else if (nextLocation.isLocked()) {
            //Si vérouillé, découvert via 'map', mais on ne peut pas y entrer + Description alternative du lieu
            nextLocation.setDiscovered(true);
            String lockedMsg = game.getWorldMap().getLockedDescription(nextLocation.getName());
            System.out.println(lockedMsg);
            game.getWorldMap().getWorldMap()[newY][newX] = nextLocation;
        } else {
            //Si la zone existe et n'est pas vérouillée, le joueur change de position
            game.movePlayer(newX, newY);
        }
    }
}
