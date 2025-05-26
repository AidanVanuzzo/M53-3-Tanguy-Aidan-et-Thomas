package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;


public class CommandRegistry {

    private Map<String, ICommand> commands;

    CommandRegistry() {
        commands = new HashMap<>();
        commands.put("move", new CMove());
        commands.put("map", new CMap());
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
    
        while (true) {
            System.out.print("Entrez une commande : ");
            String input = scanner.nextLine().toLowerCase();
    
            if (input.equals("quit")) {
                System.out.println("Au revoir Boss !");
                break;  // quitte la boucle et ferme le scanner
            }
    
            ICommand command = commands.get(input);
    
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Commande invalide, essayez 'help'");
            }
        }
    
        scanner.close();
    }
    
}
