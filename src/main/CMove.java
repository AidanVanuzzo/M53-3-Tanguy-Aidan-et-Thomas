package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CMove implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    CMove(Game game) {
        this.description = "Allows you to move in a cardinal direction.";
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        movePlayer();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void movePlayer() {
        System.out.println();
        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");

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
            nextLocation.setDiscovered(true);
            String lockedMsg = game.getWorldMap().getLockedDescription(nextLocation.getName());
            System.out.println(lockedMsg);
            game.getWorldMap().getWorldMap()[newY][newX] = nextLocation;
        } else {
            game.movePlayer(newX, newY);
        }
    }
}
