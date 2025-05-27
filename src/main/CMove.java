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
        this.description = "Allows you to move in a cardinal direction (north, south, east, west).";
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    //Méthode execute() pour la commande 'move' qui lance appel la méthode de déplacement du joueur
    public void execute() {
        movePlayer();
    }

    //Getter pour récupérer la description de la commande 'move'
    @Override
    public String getDescription() {
        return this.description;
    }

    //Méthode pour déplacer le joueur (appelée par execute()) selon la direction choisie (scanner)
    public void movePlayer() {
        //Indication au joueur pour le choix de la direction
        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");
        //Variable pour stocker le choix
        int direction;
        //Appel du scanner dans une boucle pour s'assurer que le joueur entre un nombre valide
        do {
            direction = scanner.nextInt();
            if (direction > 4 || direction < 1) {
                System.out.println("You need to type a number between 1 - 4");
            }
        } while (direction > 4 || direction < 1);
        //Récupère la position actuelle du joueur
        int x = game.getPlayerX();
        int y = game.getPlayerY();
        //Variables pour stocker la nouvelle position
        int newX = x;
        int newY = y;
        //Mise à jour de la position selon la direction choisie
        switch (direction) {
            case 1 -> newY--; // North
            case 2 -> newY++; // South
            case 3 -> newX++; // East
            case 4 -> newX--; // West
        }
        //Récupère l'emplacement suivant sur la carte
        Location nextLocation = game.getWorldMap().getLocation(newX, newY);
        //Gestion out of bound ou zone vérouillée
        if (nextLocation == null) {
            System.out.println("You can't go that way.");
        } else if (nextLocation.isLocked()) {
            System.out.println("Zone locked.");
        } else {
            //Mise à jour de la position
            game.setPlayerX(newX);
            game.setPlayerY(newY);
            //Affichage de la description du nouveau POI
            System.out.println(nextLocation.getDescription());
        }
    }
}
