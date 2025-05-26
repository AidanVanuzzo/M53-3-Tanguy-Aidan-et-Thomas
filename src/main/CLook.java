package main;

import interfaces.ICommand;

public class CLook implements ICommand {

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
            System.out.println("You look around...");
            System.out.println(current.getDescription());
            // Ici tu pourras ajouter plus tard l'affichage des items s'il y en a
        } else {
            System.out.println("You are lost in the void.");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
