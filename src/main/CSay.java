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
                String npcFirst = npcName.split(" ")[0];

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

                                Item reward = current.getRewardItem();
                                if (reward != null) {
                                    String rewardName = reward.getName().toLowerCase();
                                    if (rewardName.equals("tonton") || rewardName.equals("massomo")) {
                                        System.out.println("[You talked with " + reward.getName() + ", but they are not an item to take.]");
                                    } else {
                                        if (game.getPlayer().getItemByName(reward.getName()) == null) {
                                            game.getPlayer().addItem(reward);
                                            System.out.println();
                                            System.out.println("[You received: " + reward.getName() + "]");
                                            System.out.println();
                                        } else {
                                            System.out.println("[You already have the " + reward.getName() + ".]");
                                        }
                                    }
                                }

                                current.completePuzzle();

                                // Supprimer Tonton ou Massomo de la zone après interaction
                                if (npcName.equals("tonton eleganza") || npcFirst.equals("tonton")) {
                                    current.removeItemByName("tonton");
                                } else if (npcName.equals("massomo")) {
                                    current.removeItemByName("massomo");
                                }

                            } else {
                                System.out.println();
                                System.out.println("Tonton: No, my dear, I was hiding " + target + ". But don’t worry, let’s try again.");
                                System.out.println();
                                target = (int) (Math.random() * 11);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    return;
                }
            }

            // === Cas normal : prise d’objet dans la zone ===
            if (game.getPlayer().getItemByName(itemName) != null) {
                System.out.println("You already have this item.");
                return;
            }

            Item item = current.removeItemByName(itemName);
            if (item != null) {
                String name = item.getName().toLowerCase();

                if (name.equals("tonton") || name.equals("massomo")) {
                    System.out.println("You talked with " + item.getName() + ", but they are not an item to carry.");
                } else {
                    game.getPlayer().addItem(item);
                    System.out.println();
                    System.out.println("You picked up: " + item.getName());
                    System.out.println();
                }

                // Cas spécial : Massomo -> déverrouille la tour + spawn du parchemin
                if (name.equals("massomo")) {
                    Location wizardLair = game.getWorldMap().getLocation(2, 2);
                    if (wizardLair != null && wizardLair.isLocked()) {
                        wizardLair.setLocked(false);
                        System.out.println();
                        System.out.println("[The wise man disappeared in a flash of smoke, whispering: \"Find me...\"]\n");
                    }

                    Location bridge = game.getWorldMap().getLocation(0, 0);
                    if (bridge != null) {
                        Item parchment = new Item(
                            "[There is a message painted on the floor: Meet me in my tower and I will teach you the ultimate power.]",
                            "",
                            "Bridge"
                        );
                        bridge.addItem(parchment);
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
