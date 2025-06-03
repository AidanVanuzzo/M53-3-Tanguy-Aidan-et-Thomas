package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CTeleport implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    public CTeleport(Game game) {
        this.description = "Enables you to move directly from one zone to another.";
        this.game = game;
        this.scanner = game.getScanner();
    }

    @Override
    public void execute() {
        // Vérifie que le joueur possède le crystal
        if (game.getPlayer().getItemByName("crystal") == null) {
            System.out.println("You don't have the crystal. You cannot teleport.");
            return;
        }

        System.out.print("What's your destination ? ");
        String destination = scanner.nextLine().trim();

        Location[][] map = game.getWorldMap().getWorldMap();
        boolean found = false;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Location loc = map[y][x];
                if (loc != null && loc.getName().equalsIgnoreCase(destination)) {
                    found = true;
                    if (loc.isLocked()) {
                        System.out.println("This area is locked. You cannot teleport there.\n");
                        return;
                    }

                    System.out.println("[You teleport in a flash of green light!]");
                    System.out.println();
                    game.movePlayer(x, y);
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("No such location found. Try 'map'.\n");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
