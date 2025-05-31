package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CSay implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    public CSay(Game game) {
        this.game = game;
        this.description = "Allows you to take objects or talk using 'take <name>'.";
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Type 'take <name>' to take or interact.");
        System.out.println();
        String input = scanner.nextLine().toLowerCase().trim();

        if (input.startsWith("take ")) {
            String itemName = input.substring(5).trim();
            Location current = game.getCurrentLocation();

            // === Cas spécial : PNJ avec énigme ===
            if (current.hasNpc() && current.isPuzzleActive()) {
                String npcName = current.getNpcName().toLowerCase();
                String npcFirst = npcName.split(" ")[0]; // ex: "tonton" depuis "Tonton Eleganza"

                if (itemName.equals(npcName) || itemName.equals(npcFirst)) {
                    System.out.println(current.getNpcIntro());

                    int target = (int) (Math.random() * 11);
                    boolean success = false;

                    while (!success) {
                        System.out.print("Your guess: ");
                        String guessInput = scanner.nextLine().trim();

                        try {
                            int guess = Integer.parseInt(guessInput);
                            if (guess == target) {
                                success = true;
                                System.out.println();
                                System.out.println("Tonton: Yeeeaaah! You truly are your uncle’s heir.");
                                System.out.println("Tonton: Take my card, go grab something to eat. It’s nearly lunchtime.");
                                System.out.println("Tonton: Go shine, my nephew. And always remember: Elegance is power!");
                                System.out.println();

                                Item reward = current.getRewardItem();
                                if (reward != null) {
                                    game.getPlayer().addItem(reward);
                                    System.out.println();
                                    System.out.println("[You received: " + reward.getName() + "]");
                                    System.out.println();
                                }

                                current.completePuzzle();
                            } else {
                                System.out.println();
                                System.out.println("Tonton: No, my dear, I was hiding " + target + ". But don’t worry, let’s try again.");
                                System.out.println();
                                target = (int) (Math.random() * 11); // Nouvelle tentative
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    return;
                }
            }

            // === Cas normal : prise d’objet dans la zone ===
            Item item = current.removeItemByName(itemName);
            if (item != null) {
                game.getPlayer().addItem(item);
                System.out.println();
                System.out.println("You picked up: " + item.getName());
                System.out.println();

                // Cas spécial : Massomo déverrouille Wizard's Lair
                if (item.getName().equalsIgnoreCase("massomo")) {
                    Location wizardLair = game.getWorldMap().getLocation(2, 2);
                    if (wizardLair != null && wizardLair.isLocked()) {
                        wizardLair.setLocked(false);
                        System.out.println("[The wise man disappeared, leaving a note: Meet me in my tower and I will teach you my power.]");
                        System.out.println();
                    }
                }

            } else {
                System.out.println("There is no such item or character here.");
            }
        } else {
            System.out.println("Invalid format. Use: take <name>");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
