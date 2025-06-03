package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CTeleport implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
    private Game game;
    private Scanner scanner;

    //Constructeur
    public CTeleport(Game game) {
        this.description = "Enables you to move directly from one zone to another.";
        this.game = game;
        this.scanner = game.getScanner();
    }

    //Exécution de la commande 'teleport' (seulement si on possède l'item 'crystal')
    @Override
    public void execute() {

        //Vérifie que le joueur possède bien le crystal
        if (game.getPlayer().getItemByName("crystal") == null) {
            System.out.println("You're trying to access an unknown power.");
            return;
        }

        //Indication et entrée du joueur
        System.out.print("What's your destination ? ");
        String destination = scanner.nextLine().trim();

        //Récupère la carte
        Location[][] map = game.getWorldMap().getWorldMap();
        //Pour vérifier si la zone demandée existe dans la carte
        boolean found = false;

        //Parcourir la carte
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Location loc = map[y][x];
                if (loc != null && loc.getName().equalsIgnoreCase(destination)) {
                    found = true;
                    if (loc.isLocked()) {
                        //Ne permet pas de se téléporter dans une zone verrouillée
                        System.out.println("This area is locked. You cannot teleport there.\n");
                        return;
                    }
                    //Effectue la téléportation si la zone existe et n'est pas vérouillée
                    //Choix : Possible de se TP dans des zones non visitées, pour favoriser la rejouabilité
                    System.out.println("[You teleport in a flash of green light!]");
                    System.out.println();
                    game.movePlayer(x, y);
                    return;
                }
            }
        }

        //Si la zone n'existe pas
        if (!found) {
            System.out.println("No such location found. Try 'map'.\n");
        }
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }
}
