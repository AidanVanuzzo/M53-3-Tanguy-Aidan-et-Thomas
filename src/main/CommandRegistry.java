package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;

public class CommandRegistry {

    //Collection pour stocker les commandes
    private Map<String, ICommand> baseCommands;
    //Référence à l'instance actuelle du jeu
    private Game game;

    //Constructeur
    public CommandRegistry(Game game) {
        this.game = game;
        baseCommands = new HashMap<>();
        //Commandes de niveau 1
        baseCommands.put("help", new CHelp(baseCommands));
        baseCommands.put("map", new CMap(game.getWorldMap()));
        baseCommands.put("move", new CMove(game));
        baseCommands.put("look", new CLook(game));
        baseCommands.put("say", new CSay(game));
        baseCommands.put("save", new CSave(game));
        baseCommands.put("inventory", new CInventory(game));
    }

    //Exécution des commandes tapées par le joueur
    public void commandExecute() {
        //Entrés clavier
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input;

        while (true) {
            //Génère les commandes à chaque tour
            Map<String, ICommand> currentCommands = new HashMap<>(baseCommands);

            //Si le joueur a le crystal, on ajoute 'teleport'
            if (game.getPlayer().getItemByName("crystal") != null) {
                currentCommands.put("teleport", new CTeleport(game));
            }

            //Met à jour la commande 'help' pour qu’elle affiche les commandes actuelles
            currentCommands.put("help", new CHelp(currentCommands));

            //Champ de saisie des commandes
            System.out.print("Enter a command : ");
            input = scanner.nextLine().toLowerCase();

            //Permet de quitter la partie
            if (input.equals("quit")) {
                System.out.println("\n[See you next time.]\n");
                break;
            }

            //Cherche et lance la commande
            ICommand command = currentCommands.get(input);
            if (command != null) {
                command.execute();
            } else {
                //Rappel de 'help' en cas de mauvaise saisie
                System.out.println("Invalid command. Try 'help'.");
            }
        }

        scanner.close();
    }
}
