package main;

import interfaces.ICommand;
import java.util.List;
import java.util.Scanner;

public class CInventory implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
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

        // Filtre : items visibles seulement
        List<Item> visibleItems = items.stream()
            .filter(item -> !item.getName().equalsIgnoreCase("massomo token"))
            .toList();

        if (visibleItems.isEmpty()) {
            System.out.println();
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println();
            System.out.println("Your inventory:");
            for (Item item : visibleItems) {
                System.out.println();
                System.out.println(" - " + item.getName());
            }
            System.out.println();

            // Lance le menu automatiquement
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
                    System.out.println("[" + item.getDescription() + "]");
                    System.out.println();
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
                                System.out.println("\n[BEEP... The VIP card granted you access. The heavy door slides open with a hiss.]\n");
                            } else {
                                System.out.println("The Burger King is already unlocked.");
                            }
                        } else {
                            System.out.println("\nThis object cannot be used here.\n");
                        }
    
                    } else if (item.getName().equalsIgnoreCase("gold key")) {
                        int x = game.getPlayerX();
                        int y = game.getPlayerY();
                        WorldMap map = game.getWorldMap();
    
                        boolean isNearCastle =
                            (x == 1 && y == 1) || // House
                            (x == 2 && y == 0) || // Cave
                            (x == 1 && y == 2);   // River
    
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
    
                    } else {
                        System.out.println("You try to use the " + item.getName() + "...");
                        System.out.println("Nothing happens. Maybe it doesn't work here.\n");
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
