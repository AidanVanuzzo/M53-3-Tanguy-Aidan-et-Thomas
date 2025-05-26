package main;

import interfaces.ICommand;

public class CMap implements ICommand {

    // private String verb;
    private String description;

    CMap() {
        this.description = "Permet d'afficher la world map.";
    }

    public void execute() {

    }

    public String getDescription() {
        return this.description;
    }

}
