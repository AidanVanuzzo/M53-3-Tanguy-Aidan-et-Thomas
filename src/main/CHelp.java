package main;

import interfaces.ICommand;
import java.util.Map;

public class CHelp implements ICommand {
    
    private String description;
    //Liste des commandes
    private Map<String, ICommand> commands;

    //Constructeur
    public CHelp(Map<String, ICommand> commands) {
        this.description = "Displays the list of available commands.";
        this.commands = commands;
    }

    //Exécution de la commande 'help'
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
        printCommand("save");
        System.out.println(" quit - Exit the game\n");
    }

    //Affiche les commandes avec leur description
    private void printCommand(String name) {
        ICommand cmd = commands.get(name);
        if (cmd != null) {
            System.out.println(" " + name + " - " + cmd.getDescription());
            System.out.println();
        }
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }
}
