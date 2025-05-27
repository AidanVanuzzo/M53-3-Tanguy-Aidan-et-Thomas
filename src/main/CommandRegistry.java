package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;

public class CommandRegistry {

    private Map<String, ICommand> commands;

    CommandRegistry(Game game) {
        commands = new HashMap<>();
        commands.put("help", new CHelp(commands));
        commands.put("move", new CMove(game));
        commands.put("map", new CMap(game.getWorldMap()));
        commands.put("look", new CLook(game));
    }

    public void commandExecute() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input;
    
        while (true) {
            System.out.print("Enter a command (or type 'quit' to exit): ");
            input = scanner.nextLine().toLowerCase();
    
            if (input.equals("quit")) {
                System.out.println("Game over. See you next time :)");
                break;
            }
    
            ICommand command = commands.get(input);
    
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid command. Try 'help'.");
            }
        }
    
        scanner.close();
    }
    
}
