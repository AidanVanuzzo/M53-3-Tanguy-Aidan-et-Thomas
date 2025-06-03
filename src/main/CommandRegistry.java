package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;

public class CommandRegistry {

    private Map<String, ICommand> baseCommands; // Commandes permanentes
    private Game game;

    public CommandRegistry(Game game) {
        this.game = game;
        baseCommands = new HashMap<>();

        // Commandes de base
        baseCommands.put("help", new CHelp(baseCommands));
        baseCommands.put("map", new CMap(game.getWorldMap()));
        baseCommands.put("move", new CMove(game));
        baseCommands.put("look", new CLook(game));
        baseCommands.put("say", new CSay(game));
        baseCommands.put("save", new CSave(game));
        baseCommands.put("inventory", new CInventory(game));
    }

    public void commandExecute() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input;

        while (true) {
            // Génère les commandes dynamiques à chaque tour
            Map<String, ICommand> currentCommands = new HashMap<>(baseCommands);

            // Si le joueur a le crystal, on ajoute "teleport"
            if (game.getPlayer().getItemByName("crystal") != null) {
                currentCommands.put("teleport", new CTeleport(game));
            }

            // Met à jour la commande help pour qu’elle affiche les commandes actuelles
            currentCommands.put("help", new CHelp(currentCommands));

            System.out.print("Enter a command : ");
            input = scanner.nextLine().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("\n[See you next time.]\n");
                break;
            }

            ICommand command = currentCommands.get(input);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid command. Try 'help'.");
            }
        }

        scanner.close();
    }
}
