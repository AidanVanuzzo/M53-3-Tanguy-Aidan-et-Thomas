package main;

import interfaces.ICommand;

public class CLook implements ICommand {

    private String description;
    private CMove move;

    public CLook(CMove move) {
        this.description = "Permet d'observer la zone actuelle.";
        this.move = move;
    }

    @Override
    public void execute() {
        Location current = move.getCurrentLocation();
        if (current != null) {
            System.out.println("Vous regardez autour de vous...");
            System.out.println(current.getDescription());
        } else {
            System.out.println("Vous êtes perdu dans le néant.");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
