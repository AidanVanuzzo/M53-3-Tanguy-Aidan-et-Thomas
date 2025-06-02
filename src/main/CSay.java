package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CSay implements ICommand {

    private String description;
    private Game game;
    private Scanner scanner;

    public CSay(Game game) {
        this.game = game;
        this.description = "Allows you to take objects or talk with the NPCs using 'take <name>'.";
        this.scanner = game.getScanner();
    }

    @Override
    public void execute() {
        System.out.println("Type 'take <name>' to take or interact: ");
        String input = scanner.nextLine().toLowerCase().trim();

        if (input.startsWith("take ")) {
            String itemName = input.substring(5).trim();
            Location current = game.getCurrentLocation();

            // === CHRIS Burger King ===
            if (itemName.equals("chris") && current.getName().equalsIgnoreCase("Burger King") && current.isPuzzleActive()) {
                System.out.println("\nChris: Hello, traveler! Would you like to try one of my delicious Whoppers? (yes/no)");
                System.out.print("Your answer: ");
                String reply = scanner.nextLine().trim().toLowerCase();


                if (reply.equals("no")) {
                    System.out.println("\nChris: Ahrrr, you don’t know what you’re missing, my friend.\n");
                    return;
                } else if (reply.equals("yes")) {
                    String[][] chrisRiddles = {
                        { "I’m green, light, sometimes crunchy, sometimes wilted, always here to pretend it's healthy.", "lettuce|salad" },
                        { "I melt in the heat, I’m yellow or orange, and without me, your burger is sad.", "cheese" },
                        { "I’m caught between two buns, I’ve got many layers, yet I stay compact. Who am I?", "burger" }
                    };

                    int rand = (int) (Math.random() * chrisRiddles.length);
                    String question = chrisRiddles[rand][0];
                    String[] validAnswers = chrisRiddles[rand][1].split("\\|");

                    System.out.println("\nChris: Okay, but first you'll have to successfully answer one of my riddles.");
                    System.out.println();
                    System.out.println("Chris: " + question);
                    System.out.print("Your answer: ");
                    String playerAnswer = scanner.nextLine().trim().toLowerCase();

                    boolean correct = false;
                    for (String ans : validAnswers) {
                        if (playerAnswer.equals(ans)) {
                            correct = true;
                            break;
                        }
                    }

                    if (correct) {
                        System.out.println("\nChris: BOOOOOOYYAAA! You’re worthy of my culinary talent. Here, take a Whopper, you’ve earned it.");
                        Item wooper = new Item(
                            "wooper",
                            "A Warm Juicy Wooper. It looks delicious.",
                            null
                        );
                        game.getPlayer().addItem(wooper);
                        System.out.println("\n[You received: Wooper]\n");
                        current.removeItemByName("chris");
                        current.completePuzzle();
                    } else {
                        System.out.println("Chris: Ah, that’s incorrect. You don’t know what you’re missing, my friend.");
                    }
                    return;
                } else {
                    System.out.println("Chris: I didn’t understand your answer.");
                    return;
                }
            }

            // === Cas spécial : PNJ (Tonton et Massomo) ===
            if (current.hasNpc() && current.isPuzzleActive() && !itemName.equals("crappi")) {
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
                                if (reward != null && game.getPlayer().getItemByName(reward.getName()) == null) {
                                    game.getPlayer().addItem(reward);
                                    System.out.println();
                                    System.out.println("[You received: " + reward.getName() + "]");
                                    System.out.println();
                                }

                                current.completePuzzle();

                                if (npcName.contains("tonton")) {
                                    current.removeItemByName("tonton");
                                } else if (npcName.contains("massomo")) {
                                    current.removeItemByName("massomo");
                                }

                            } else {
                                System.out.println();
                                System.out.println("Tonton: No, my dear, I was hiding " + target + ". But don’t worry, let’s try again.");
                                target = (int) (Math.random() * 11);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    return;
                }
            }

            // === Massomo - Wizard's Lair ===
            if (itemName.equals("massomo") && current.getName().equalsIgnoreCase("Wizard's Lair")) {
                String[][] questions = {
                    { "When I’m fresh, I’m hot. What am I?", "bread" },
                    { "A father and his son together are 36 years old. The father is 30 years older than the son. How old is the son?", "3" },
                    { "Yesterday I was, tomorrow I will be. What am I?", "today" }
                };

                System.out.println("\nMassomo: Welcome to my lair. If you pass your training, I'll give you access to the paralysis spell.");

                for (String[] q : questions) {
                    String answer;
                    do {
                        System.out.println();
                        System.out.println(q[0]);
                        System.out.print("Your answer: ");
                        answer = scanner.nextLine().toLowerCase().trim();

                        if (!answer.equals(q[1])) {
                            System.out.println("Massomo: No no, son. That’s not it. Let’s try again.");
                        }
                    } while (!answer.equals(q[1]));
                }

                System.out.println();
                System.out.println("Massomo: Well done. Listen carefully. I won't repeat myself...");
                System.out.println("\nMassomo: If you’re ever in danger, shout 'rastapopoulos' and your enemy will explode!\n");
                System.out.println("[Massomo disappeared in a flash of green light.]\n");

                current.removeItemByName("massomo");
                return;
            }

            // === Crappi Crapo - Cave ===
            if (itemName.equals("crappi") && current.getName().equalsIgnoreCase("Cave") && current.isPuzzleActive()) {
                System.out.println("\n[the animal lets out a chuckle...]\nCrappi Crapo: Hey Chef, it’s rough being a frog these days. I'm starving out here.");
                System.out.println("Crappi Crapo: You know what? I’ll give you this key if you bring me something to eat.");
                System.out.print("\nYour answer (yes/no): ");
                String reply = scanner.nextLine().trim().toLowerCase();

                if (reply.equals("yes")) {
                    if (game.getPlayer().getItemByName("wooper") != null) {
                        System.out.println("\nCrappi Crapo: Wow, you were fast, boss! Thanks, you've made my day. Take this key, you’ve earned it.");
                        System.out.println("\n[You received: Gold Key]\n");
                
                        // Supprimer le Wooper de l’inventaire
                        game.getPlayer().getInventory().removeIf(item -> item.getName().equalsIgnoreCase("wooper"));
                
                        // Ajouter la Gold Key si elle n’est pas déjà dans l’inventaire
                        Item goldKey = current.getRewardItem();
                        if (goldKey != null && game.getPlayer().getItemByName(goldKey.getName()) == null) {
                            game.getPlayer().addItem(goldKey);
                        }
                
                        // Supprimer Crappi de la zone et désactiver la quête
                        current.removeItemByName("crappi");
                        current.completePuzzle();
                
                        System.out.println("Crappi Crapo: Take care, boss. Next time, bring dessert, yeah?\n");
                    } else {
                        System.out.println("\nCrappi Crapo: Wait... you're playing games with me? No Wooper, no deal!");
                        System.out.println("Crappi Crapo: Come back when you have something juicy, boss.\n");
                    }
                } else {
                    System.out.println("\nCrappi Crapo: What? You tease me and then leave me? Not cool, boss.");
                    System.out.println("Crappi Crapo: Come back when you're serious about feeding a starving frog.\n");
                }
                
                return;                
            }

            // === Cas normal : prise d’objet ===
            if (game.getPlayer().getItemByName(itemName) != null) {
                System.out.println("You already have this item.");
                return;
            }

            Item item = current.removeItemByName(itemName);
            if (item != null) {
                String name = item.getName().toLowerCase();

                if (name.equals("tonton") || name.equals("massomo") || name.equals("chris")) {
                    System.out.println("You talked with " + item.getName() + ".");
                } else {
                    game.getPlayer().addItem(item);
                    System.out.println();
                    System.out.println("You picked up: " + item.getName());
                    System.out.println();
                }

                if (name.equals("massomo") && current.getName().equalsIgnoreCase("Bridge")) {
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
