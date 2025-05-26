package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CMove implements ICommand {

    // private String verb;
    private String description;

    CMove() {
        this.description = "Permet de se déplacer dans une direction cardinale (nord, sud, est, ouest).";
    }

    public void execute() {
        movePlayer();
    }

    public String getDescription() {
        return this.description;
    }

    //Default Location
    private int x = 1;
    private int y = 1;
    private WorldMap worldMap = new WorldMap();

    public void movePlayer() {

        System.out.println("To go North, type 1");
        System.out.println("To go South, type 2");
        System.out.println("To go East, type 3");
        System.out.println("To go West, type 4");

        Scanner scanner = new Scanner(System.in);
        int direction = 0;

        do {
            direction = scanner.nextInt();
            if (direction > 4 || direction < 1) { 
               System.out.println("You need to type a number beetween 1 - 4");
            }
        } while (direction > 4 || direction < 1);

        int newX = x;
        int newY = y;

        switch (direction) {
            case 1 -> newY--; // North
            case 2 -> newY++; // South
            case 3 -> newX++; // East
            case 4 -> newX--; // West
        }

        Location nextLocation = worldMap.getLocation(newX, newY);

        if (nextLocation == null) {
            System.out.println("You can't go that way."); //OOB
        } else if (nextLocation.isLocked()) {
            System.out.println("Zone locked."); //Locked
        } else {
            x = newX;
            y = newY;
            System.out.println(nextLocation.getDescription());
        }
    }
}
