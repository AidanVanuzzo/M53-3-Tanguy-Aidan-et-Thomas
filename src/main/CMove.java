package main;

import interfaces.ICommand;

public class CMove implements ICommand {

    // private String verb;
    private String description;

    CMove() {
        this.description = "Permet de se déplacer dans une direction cardinale (nord, sud, est, ouest).";
    }

    public void execute() {

    }

    public String getDescription() {
        return this.description;
    }
}
