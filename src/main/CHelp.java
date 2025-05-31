package main;

import interfaces.ICommand;
import java.util.Map;

public class CHelp implements ICommand {
    
    //Description de CHelp
    private String description;

    //Liste des commandes disponibles (nom + objets commande)
    private Map<String, ICommand> commands;

    //Constructeur qui reçoit la liste des commandes
    public CHelp(Map<String, ICommand> commands) {
        this.description = "Displays the list of available commands.";
        this.commands = commands;
    }

    //Méthode execute() pour la commande 'help' via une boucle for
    @Override
    public void execute() {
        System.out.println();
        System.out.println("Controls available:");
        System.out.println();
        for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
            String commandName = entry.getKey().toLowerCase();
            boolean isHelpCommand = commandName.equals("help");
            //Condition pour ne pas afficher 'help'
            if (!isHelpCommand) {
                System.out.println(" " + commandName + " - " + entry.getValue().getDescription());
                System.out.println();
            }
        }
    }

    //Getter pour récupérer la description de la commande 'help'
    @Override
    public String getDescription() {
        return this.description;
    }
}
