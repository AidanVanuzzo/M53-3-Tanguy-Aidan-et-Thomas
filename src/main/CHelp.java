package main;

import interfaces.ICommand;
import java.util.Map;

public class CHelp implements ICommand {
    
    private String description;
    private Map<String, ICommand> commands;

    public CHelp(Map<String, ICommand> commands) {
        this.description = "Displays the list of available commands.";
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Controls available:");
        System.out.println();

        //Ordre d'affichage
        printCommand("map");
        printCommand("move");
        printCommand("look");
        printCommand("say");
        printCommand("inventory");
        printCommand("teleport");
        System.out.println(" quit - Exit the game\n");
    }

    private void printCommand(String name) {
        ICommand cmd = commands.get(name);
        if (cmd != null) {
            System.out.println(" " + name + " - " + cmd.getDescription());
            System.out.println();
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
