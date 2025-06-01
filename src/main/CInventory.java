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
        this.description = "Manage your inventory. Use 'inspect <item>', or 'use <item>'.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        List<Item> items = game.getPlayer().getInventory();
        if (items.isEmpty()) {
            System.out.println();
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println();
            System.out.println("Your inventory:");
            for (Item item : items) {
                System.out.println();
                System.out.println(" - " + item.getName());
            }
            System.out.println();
            //Lance le menu automatiquement
            interactiveMenu();
        }
    }


    public void interactiveMenu() {
        while (true) {
            System.out.print("Inventory (type 'inspect <object>' 'use <object>' or 'exit') : ");
            String input = scanner.nextLine().toLowerCase().trim();
            System.out.println();
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
                    if (item.getName().equalsIgnoreCase("vip card")) {
                        int x = game.getPlayerX();
                        int y = game.getPlayerY();
                        WorldMap map = game.getWorldMap();

                        boolean isNearBurgerKing =
                            (x == 0 && y == 0) || // Bridge
                            (x == 1 && y == 1) || // House
                            (x == 0 && y == 2);   // Market

                        Location burgerKing = map.getLocation(0, 1);
                        if (isNearBurgerKing) {
                            if (burgerKing != null && burgerKing.isLocked()) {
                                burgerKing.setLocked(false);
                                System.out.println("\nBEEP... The VIP card granted you access. The heavy door slides open with a hiss.\n");
                            } else {
                                System.out.println("The Burger King is already unlocked.");
                            }
                        } else {
                            System.out.println("\nThis object cannot be used here.\n");
                        }
                    } else {
                        System.out.println("You try to use the " + item.getName() + "...");
                        System.out.println("Nothing happens. Maybe it doesn't work here.");
                    }
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
