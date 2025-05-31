package main;

import interfaces.ICommand;
import java.util.List;

public class CLook implements ICommand {

    //Je commenterai cette classe une fois qu'elle fonctionnera avec les items

    private String description;
    private Game game;

    public CLook(Game game) {
        this.description = "Look around the actual zone.";
        this.game = game;
    }

    @Override
    public void execute() {
        Location current = game.getCurrentLocation();

        if (current != null) {
            System.out.println();
            System.out.println("You look around...");
            System.out.println();
            System.out.println(current.getDescription());
            System.out.println();

            // [01.06.2025] Affiche les objets présents dans la zone
            List<Item> items = current.getItems();
            if (!items.isEmpty()) {
                System.out.println("You see the following items:");
                for (Item item : items) {
                    System.out.println(" - " + item.getName() + ": " + item.getDescription());
                }
                System.out.println();
            } else {
                System.out.println("There are no visible items here.");
                System.out.println();
            }
        } else {
            System.out.println("You are lost in the void.");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
