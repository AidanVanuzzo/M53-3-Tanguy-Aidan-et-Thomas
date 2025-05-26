package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;


public class CommandRegistry {

    /*
    CMap map;
    CMove move;
    */
    private Map<String, ICommand> commands;


    /*CommandRegistry() {
        this.map = new CMap();
        this.move = new CMove();
    }*/

    CommandRegistry(WorldMap worldMap) {
        commands = new HashMap<>();
        commands.put("move", new CMove());
        commands.put("map", new CMap(worldMap));
        commands.put("help", new CHelp(commands));
    }
    
    /*
    public void commandExecute() {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Entrez une commande : ");
        String command = scanner.nextLine();

        switch (command) {
            case "Move":

                move.execute();

                break;

            case "Map":

                map.execute();

                break;

            default:

                System.out.println("commande invalide, essayez la commande 'Help'");

                break;
        }

        scanner.close();

    }*/

    public void commandExecute() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
    
        System.out.print("Entrez une commande : ");
        String input = scanner.nextLine().toLowerCase();
    
        ICommand command = commands.get(input);
    
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Commande invalide, essayez 'help'");
        }
    
        scanner.close();
    }
    
}
