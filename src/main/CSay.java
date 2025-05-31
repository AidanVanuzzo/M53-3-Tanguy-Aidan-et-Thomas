package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CSay implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    public CSay(Game game) {
        this.game = game;
        this.description = "Allows you to take objects using 'take <object name>'.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Type 'take <name>':");
        String input = scanner.nextLine().toLowerCase().trim();

        if (input.startsWith("take ")) {
            String itemName = input.substring(5).trim();
            Location current = game.getCurrentLocation();
            Item item = current.removeItemByName(itemName);
            if (item != null) {
                game.getPlayer().addItem(item);
                System.out.println();
                System.out.println("You picked up: " + item.getName());
                System.out.println();

                // [01.06.2025] Déverrouille la tour du sorcier si on prend massomo
                if (item.getName().equalsIgnoreCase("massomo")) {
                    Location wizardLair = game.getWorldMap().getLocation(2, 2); // Wizard's Lair coordonnée (2,2)
                    if (wizardLair != null && wizardLair.isLocked()) {
                        wizardLair.setLocked(false);
                        System.out.println("[The wise man disappeared, leaving a note: Meet me in my tower and I will teach you my power.]");
                        System.out.println();
                    }
                }

            } else {
                System.out.println("There is no such item here.");
            }
        } else {
            System.out.println("Invalid format.");
            System.out.println();
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
