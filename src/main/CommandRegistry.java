package main;

import java.util.Map;
import java.util.HashMap;
import interfaces.ICommand;

public class CommandRegistry {

    //Liste des commandes disponibles (nom + objets commande)
    private Map<String, ICommand> commands;

    //Constructeur qui initialise la map des commandes et insert les commandes du jeu
    CommandRegistry(Game game) {
        commands = new HashMap<>();
        commands.put("help", new CHelp(commands));
        commands.put("map", new CMap(game.getWorldMap()));
        commands.put("move", new CMove(game));
        commands.put("look", new CLook(game));
    }

    //Méthode pour l'exécution des comandes
    public void commandExecute() {
        //Scanner pour la saisie
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        //Stockage de la saisie
        String input;
        //Boucle qui permet la saisie de commande tant que la partie est active
        while (true) {
            System.out.print("Enter a command : ");
            input = scanner.nextLine().toLowerCase();
            //Si l'utilisateur entre 'quit', on quitte la boucle et termine le jeu
            if (input.equals("quit")) {
                System.out.println("Game over. See you next time :)");
                break;
            }
            //Appelle la commande correspondante dans la map
            ICommand command = commands.get(input);
            //Exécute la commande si elle existe, sinon affiche un message d'erreur
            if (command != null) {
                command.execute();
            } else {
                //Incite l'utilisateur à recevoir de l'aide
                System.out.println("Invalid command. Try 'help'.");
            }
        }
        //Ferme le scanner en fin de partie
        scanner.close();
    }
    
}
