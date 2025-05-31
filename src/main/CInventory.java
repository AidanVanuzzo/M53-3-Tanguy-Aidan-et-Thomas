package main;

import interfaces.ICommand;
import java.util.List;
import java.util.Scanner;

public class CInventory implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    // [01.06.2025] Commande pour inspecter et utiliser les objets de l'inventaire
    public CInventory(Game game) {
        this.game = game;
        this.description = "Manage your inventory: inspect <item> or use <item>.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Type 'inventory' to see your items, or 'inspect <item>', or 'use <item>':");
        String input = scanner.nextLine().toLowerCase().trim();

        if (input.equals("inventory")) {
            List<Item> items = game.getPlayer().getInventory();
            if (items.isEmpty()) {
                System.out.println("Your inventory is empty.");
            } else {
                System.out.println("You have:");
                for (Item item : items) {
                    System.out.println(" - " + item.getName());
                }
            }
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
                // [01.06.2025] Utilisation simulée, à spécialiser selon la zone
                System.out.println("You try to use the " + item.getName() + "...");
                System.out.println("Nothing happens. Maybe it doesn't work here.");
            } else {
                System.out.println("You don’t have this item.");
            }
        } else {
            System.out.println("Invalid input. Try again.");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
