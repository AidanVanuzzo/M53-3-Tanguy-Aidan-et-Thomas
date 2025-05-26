package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CMove implements ICommand {

    private String description;
    private Game game;

    CMove(Game game) {
        this.description = "Permet de se déplacer dans une direction cardinale (nord, sud, est, ouest).";
        this.game = game;
    }

    public void execute() {
        movePlayer();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void movePlayer() {

        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");

        Scanner scanner = new Scanner(System.in);
        int direction;

        do {
            direction = scanner.nextInt();
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
            System.out.println("Zone locked.");
        } else {
            game.setPlayerX(newX);
            game.setPlayerY(newY);
            System.out.println(nextLocation.getDescription());
        }
    }
}
