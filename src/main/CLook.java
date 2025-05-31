package main;

import interfaces.ICommand;

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
        } else {
            System.out.println("You are lost in the void.");
        }
    }

    //Getter pour récupérer la description de la commande 'look'
    @Override
    public String getDescription() {
        return this.description;
    }
}
