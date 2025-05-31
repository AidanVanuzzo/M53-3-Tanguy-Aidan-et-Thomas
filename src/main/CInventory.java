package main;

import interfaces.ICommand;
import java.util.List;
import java.util.Scanner;

public class CInventory implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    public CInventory(Game game) {
        this.game = game;
        this.description = "Manage your inventory. Type 'inventory', 'inspect <item>', or 'use <item>'.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        // [01.06.2025] Détection d'exécution simple (si tapé comme 'inventory' uniquement)
        List<Item> items = game.getPlayer().getInventory();
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return; // Ne rentre pas dans le menu
        } else {
            System.out.println("Inventory:");
            for (Item item : items) {
                System.out.println(" - " + item.getName());
            }
            return; // Stoppe ici sans afficher le menu
        }
    }

    // [01.06.2025] Méthode facultative si tu veux un menu avancé plus tard
    public void interactiveMenu() {
        System.out.println("Type 'inspect <item>' or 'use <item>' (type 'exit' to leave):");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("exit")) {
                break;
            } else if (input.startsWith("inspect ")) {
                String itemName = input.substring(8).trim();
                Item item = game.getPlayer().getItemByName(itemName);
                if (item != null) {
                    System.out.println(item.getDescription());
                } else {
                    System.out.println("You don’t have this item.");
                }
            } else if (input.startsWith("use ")) {
                String itemName = input.substring(4).trim();
                Item item = game.getPlayer().getItemByName(itemName);
                if (item != null) {
                    System.out.println("You try to use the " + item.getName() + "...");
                    System.out.println("Nothing happens. Maybe it doesn't work here.");
                } else {
                    System.out.println("You don’t have this item.");
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
