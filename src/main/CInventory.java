package main;

import interfaces.ICommand;
import java.util.List;
import java.util.Scanner;

public class CInventory implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
    private Game game;
    private Scanner scanner;

    //Constructeur
    public CInventory(Game game) {
        this.game = game;
        this.description = "Manage your inventory. Use 'inspect <item>', or 'use <item>'.";
        this.scanner = new Scanner(System.in);
    }

    //Exécution de la commande 'inventory'
    @Override
    public void execute() {
        //Récupération des items
        List<Item> items = game.getPlayer().getInventory();

        List<Item> visibleItems = items.stream()
            //Save - Ignore l'Item 'massomo token' uniquement utile pour la sauvegarde
            .filter(item -> !item.getName().equalsIgnoreCase("massomo token"))
            .toList();

        if (visibleItems.isEmpty()) {
            //Cas inventaire vide
            System.out.println();
            System.out.println("Your inventory is empty.");
        } else {
            //Affichage de l'inventaire
            System.out.println();
            System.out.println("Your inventory:");
            for (Item item : visibleItems) {
                System.out.println();
                System.out.println(" - " + item.getName());
            }
            System.out.println();

            //Lance le menu de l'inventaire
            interactiveMenu();
        }
    }

    //Menu pour 'inspect' ou 'use' des objets
    public void interactiveMenu() {
        //S'exécute en boucle jusqu'à ce qu'on entre 'exit'
        while (true) {
            //Consignes et entrée utilisateur
            System.out.print("Inventory (type 'inspect <object>' 'use <object>' or 'exit') : ");
            String input = scanner.nextLine().toLowerCase().trim();
            System.out.println();
            //Condition de sortie
            if (input.equals("exit")) {
                break;
            //Pour gérer 'inspect'
            } else if (input.startsWith("inspect ")) {
                String itemName = input.substring(8).trim();
                Item item = game.getPlayer().getItemByName(itemName);
                if (item != null) {
                    System.out.println("[" + item.getDescription() + "]");
                    System.out.println();
                } else {
                    System.out.println("You don’t have this item.");
                }
            //Pour gérer 'use'
            } else if (input.startsWith("use ")) {
                String itemName = input.substring(4).trim();
                Item item = game.getPlayer().getItemByName(itemName);
                
                if (item != null) {
                    //=== VIP Card ===
                    if (item.getName().equalsIgnoreCase("vip card")) {
                        int x = game.getPlayerX();
                        int y = game.getPlayerY();
                        WorldMap map = game.getWorldMap();
                        //Vérifie que l'on est bien sur une case adjascente
                        boolean isNearBurgerKing =
                            (x == 0 && y == 0) || //Bridge
                            (x == 1 && y == 1) || //House
                            (x == 0 && y == 2);   //Market
                        //Affichage du texte d'ouverture et gestion des exceptions + Déverrouillage
                        Location burgerKing = map.getLocation(0, 1);
                        if (isNearBurgerKing) {
                            if (burgerKing != null && burgerKing.isLocked()) {
                                burgerKing.setLocked(false);
                                System.out.println("\n[BEEP... The VIP card granted you access. The heavy door slides open with a hiss.]\n");
                            } else {
                                System.out.println("The Burger King is already unlocked.");
                            }
                        } else {
                            System.out.println("\nThis object cannot be used here.\n");
                        }
                    //=== Gold Key ===
                    } else if (item.getName().equalsIgnoreCase("gold key")) {
                        int x = game.getPlayerX();
                        int y = game.getPlayerY();
                        WorldMap map = game.getWorldMap();
                        //Vérifie que l'on est bien sur une case adjascente
                        boolean isNearCastle =
                            (x == 1 && y == 1) || //House
                            (x == 2 && y == 0) || //Cave
                            (x == 2 && y == 2);   //Wizard
                        //Affichage du texte d'ouverture et gestion des exceptions + Déverrouillage
                        Location castle = map.getLocation(2, 1);
                        if (isNearCastle) {
                            if (castle != null && castle.isLocked()) {
                                castle.setLocked(false);
                                System.out.println("\n[The gate creaks open with a sinister groan.]\n");
                            } else {
                                System.out.println("The Castle is already unlocked.");
                            }
                        } else {
                            System.out.println("\nThis object cannot be used here.\n");
                        }
                    //Si un item est utilisé au mauvais endroit
                    } else {
                        System.out.println("You try to use the " + item.getName() + "...");
                        System.out.println("Nothing happens. Maybe it doesn't work here.\n");
                    }
                //Si l'Item en question n'est pas possédé par le joueur
                } else {
                    System.out.println("You don’t have this item.");
                }
            //Si l'on entre une mauvaise commande
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }    

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }
}
