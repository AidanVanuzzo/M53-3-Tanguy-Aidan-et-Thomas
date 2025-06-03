package main;

import interfaces.ICommand;
import java.util.List;

public class CLook implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
    private Game game;

    //Constructeur
    public CLook(Game game) {
        this.description = "Look around the actual zone.";
        this.game = game;
    }

    //Exécution de la commande 'look'
    @Override
    public void execute() {

        //Position actuelle du joueur
        Location current = game.getCurrentLocation();

        if (current != null) {
            //Affichage d'un texte d'ambiance et de la description de la zone courante
            System.out.println();
            System.out.println("You look around...");
            System.out.println();
            System.out.println(current.getDescription());
            System.out.println();
            //Récupère et stocke les items (objets, clés, PNJ) présents dans la zone
            List<Item> items = current.getItems();
            //Si il y'a un ou des Items
            if (!items.isEmpty()) {
                System.out.println("You see around you:");
                //Affiche les Items
                for (Item item : items) {
                    System.out.println();
                    System.out.println(" - " + item.getName());
                }
                System.out.println();
            } else {
                //Cas si la zone est vide (ou si tout a été déjà ramassé)
                System.out.println("Nothing interesting here...");
                System.out.println();
            }
        } else {
            //Plus prudent, mais n'arrive jamais car CMove empêche de quitter la carte 
            System.out.println("You are lost in the void.");
        }
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }
}
