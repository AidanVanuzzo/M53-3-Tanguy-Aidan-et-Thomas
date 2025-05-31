package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CSay implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    // [01.06.2025] Commande 'take <objet>' pour ramasser un objet dans la zone
    public CSay(Game game) {
        this.game = game;
        this.description = "Allows you to take objects using 'take <object name>'.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Type 'take <object name>':");
        String input = scanner.nextLine().toLowerCase().trim();

        if (input.startsWith("take ")) {
            String itemName = input.substring(5).trim();
            Location current = game.getCurrentLocation();
            Item item = current.removeItemByName(itemName);
            if (item != null) {
                game.getPlayer().addItem(item); //erruer
                System.out.println("You picked up: " + item.getName());
            } else {
                System.out.println("There is no such item here.");
            }
        } else {
            System.out.println("Invalid format. Use 'take <object name>'.");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
