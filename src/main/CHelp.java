package main;

import interfaces.ICommand;
import java.util.Map;

public class CHelp implements ICommand {

    private String description;
    private Map<String, ICommand> commands;

    public CHelp(Map<String, ICommand> commands) {
        this.description = "Affiche la liste des commandes disponibles.";
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Commandes disponibles :");
        for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
            System.out.println(" - " + entry.getKey().toLowerCase() + " : " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
