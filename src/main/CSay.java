package main;

import interfaces.ICommand;
import java.util.Scanner;

public class CSay implements ICommand {

    private String description;
    //Référence à l'instance actuelle du jeu
    private Game game;
    private Scanner scanner;

    //Constructeur
    public CSay(Game game) {
        this.game = game;
        this.description = "Allows you to take objects or talk with the NPCs using 'take <name>'.";
        this.scanner = game.getScanner();
    }

    //Méthode d'exécution pour la commande 'say'
    @Override
    public void execute() {
        //Instructions et entrée
        System.out.println("Type 'take <name>' to take or interact: ");
        String input = scanner.nextLine().toLowerCase().trim();

        //Gestion de 'take <object>' avec les interractions de PNJ
        if (input.startsWith("take ")) {
            String itemName = input.substring(5).trim();
            Location current = game.getCurrentLocation();

            //=== Chris à Burger King ===
            if (itemName.equals("chris") && current.getName().equalsIgnoreCase("Burger King") && current.isPuzzleActive()) {
                System.out.println("\nChris: Hello, traveler! Would you like to try one of my delicious Woopers?");
                System.out.print("Your answer (yes/no): ");
                String reply = scanner.nextLine().trim().toLowerCase();

                //Cas pour les deux réponses possibles
                if (reply.equals("no")) {
                    System.out.println("\nChris: Ahrrr, you don’t know what you’re missing, my friend.\n");
                    return;
                } else if (reply.equals("yes")) {
                    //Énigmes de Chris (une seule par partie)
                    String[][] chrisRiddles = {
                        { "I’m green, light, sometimes crunchy, sometimes wilted, always here to pretend it's healthy.", "lettuce|salad" },
                        { "I melt in the heat, I’m yellow or orange, and without me, your burger is sad.", "cheese" },
                        { "I’m caught between two buns, I’ve got many layers, yet I stay compact. Who am I?", "burger" }
                    };
                    //Pose une énigme aléatoirement
                    int rand = (int) (Math.random() * chrisRiddles.length);
                    String question = chrisRiddles[rand][0];
                    String[] validAnswers = chrisRiddles[rand][1].split("\\|");
                    //Gestion question/réponse
                    System.out.println("\nChris: Okay, but first you'll have to successfully answer one of my riddles.");
                    System.out.println();
                    System.out.println("Chris: " + question);
                    System.out.print("Your answer: ");
                    String playerAnswer = scanner.nextLine().trim().toLowerCase();
                    // Vérifie si la réponse est correcte
                    boolean correct = false;
                    for (String ans : validAnswers) {
                        if (playerAnswer.equals(ans)) {
                            correct = true;
                            break;
                        }
                    }

                    //Cas réponse correcte - Donne le Wooper
                    if (correct) {
                        System.out.println("\nChris: BOOOOOOYYAAA! You’re worthy of my culinary talent. Here, take a Woopper, you’ve earned it.");
                        Item wooper = new Item(
                            "wooper",
                            "A Warm Juicy Wooper. It looks delicious.",
                            null
                        );
                        game.getPlayer().addItem(wooper);
                        System.out.println("\n[You received: Wooper]\n");
                        current.removeItemByName("chris");
                        current.completePuzzle();

                    //Cas incorrect (arrête l'interaction avec Chris)
                    } else {
                        System.out.println("\nChris: Ah, that’s incorrect. You don’t know what you’re missing, my friend.");
                    }
                    return;
                } else {
                    System.out.println("\nChris: I didn’t understand your answer.\n");
                    return;
                }
            }

            //=== Interaction avec Tonton ou Massomo de Bridge ===
            if (current.hasNpc() && current.isPuzzleActive() && !itemName.equals("crappi")) {

                //Récupère le PNJ
                String npcName = current.getNpcName().toLowerCase();
                String npcFirst = npcName.split(" ")[0];

                if (itemName.equals(npcName) || itemName.equals(npcFirst)) {
                    System.out.println(current.getNpcIntro());
                    //Génère un nombre entre 0 et 10 pour l'énigme de Tonton
                    int target = (int) (Math.random() * 11);
                    boolean success = false;
                    //Boucle jusqu'à ce que le joueur trouve le bon nombre
                    while (!success) {
                        System.out.print("Your guess: ");
                        String guessInput = scanner.nextLine().trim();

                        try {
                            int guess = Integer.parseInt(guessInput);
                            if (guess == target) {
                                //Cas le joueur devine le bon nombre
                                success = true;
                                System.out.println();
                                System.out.println("Tonton: Yeeeaaah! You truly are your uncle’s heir.");
                                System.out.println("Tonton: Take my card, go grab something to eat. It’s nearly lunchtime.");
                                System.out.println("Tonton: Go shine, my nephew. And always remember: Elegance is power!");
                                //Reçoit l'objet
                                Item reward = current.getRewardItem();
                                if (reward != null && game.getPlayer().getItemByName(reward.getName()) == null) {
                                    game.getPlayer().addItem(reward);
                                    System.out.println();
                                    System.out.println("[You received: " + reward.getName() + "]");
                                    System.out.println();
                                }

                                //Marque l’énigme comme terminée
                                current.completePuzzle();

                                //Supprime le PNJ de la zone une fois l'énigme résolue
                                if (npcName.contains("tonton")) {
                                    current.removeItemByName("tonton");
                                } else if (npcName.contains("massomo")) {
                                    current.removeItemByName("massomo");
                                }
                            
                            //Cas le joueur n'as pas deviné correctement
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

            //=== Massomo à Wizard's Lair ===
            if (itemName.equals("massomo") && current.getName().equalsIgnoreCase("Wizard's Lair")) {
                //Les 3 énigmes de Massomo (il les pose toutes)
                String[][] questions = {
                    { "When I’m fresh, I’m hot. What am I?", "bread" },
                    { "A father and his son together are 36 years old. The father is 30 years older than the son. How old is the son?", "3" },
                    { "Yesterday I was, tomorrow I will be. What am I?", "today" }
                };

                System.out.println("\nMassomo: Welcome to my lair. If you pass your training, I'll give you access to the paralysis spell.");

                //Boucle pour poser les énigmes
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

                //Texte de réussite
                System.out.println();
                System.out.println("Massomo: Well done. Listen carefully. I won't repeat myself...");
                System.out.println("\nMassomo: If you’re ever in danger, shout 'rastapopoulos' and your enemy will explode!\n");
                System.out.println("[Massomo disappeared in a flash of green light.]\n");

                //Save - On reçoit un objet invisible pour marquer la réussite et faire disparaitre massomo si on a déjà fait sa quête
                Item token = new Item("massomo token", "", null);
                game.getPlayer().addItem(token);
                current.removeItemByName("massomo");
                return;
            }

            //=== Crappi Crapo à Cave ===
            if (itemName.equals("crappi") && current.getName().equalsIgnoreCase("Cave") && current.isPuzzleActive()) {
                //Introduction et scanner
                System.out.println("\n[The animal lets out a chuckle...]\nCrappi Crapo: Hey Chef, it’s rough being a frog these days. I'm starving out here.");
                System.out.println("Crappi Crapo: You know what? I’ll give you this key if you bring me something to eat.");
                System.out.print("\nYour answer (yes/no): ");
                String reply = scanner.nextLine().trim().toLowerCase();
                //Si on dit oui
                if (reply.equals("yes")) {
                    if (game.getPlayer().getItemByName("wooper") != null) {
                        System.out.println("\nCrappi Crapo: Wow, you were fast, boss! Thanks, you've made my day. Take this key, you’ve earned it.");
                        System.out.println("\n[You received: Gold Key]\n");
                
                        //Supprimer le Wooper de l’inventaire
                        game.getPlayer().getInventory().removeIf(item -> item.getName().equalsIgnoreCase("wooper"));
                
                        //Ajouter la Gold Key à l’inventaire
                        Item goldKey = current.getRewardItem();
                        if (goldKey != null && game.getPlayer().getItemByName(goldKey.getName()) == null) {
                            game.getPlayer().addItem(goldKey);
                        }
                
                        //Supprime Crappi de la zone
                        current.removeItemByName("crappi");
                        current.completePuzzle();
                
                        System.out.println("Crappi Crapo: Take care, boss. Next time, bring dessert, yeah?\n");
                    } else {
                        //Si on dit oui et qu'on a pas le wooper
                        System.out.println("\nCrappi Crapo: Wait... you're playing games with me? No Wooper, no deal!");
                        System.out.println("Crappi Crapo: Come back when you have something juicy, boss.\n");
                    }
                } else {
                    //Si on dit non
                    System.out.println("\nCrappi Crapo: What? You tease me and then leave me? Not cool, boss.");
                    System.out.println("Crappi Crapo: Come back when you're serious about feeding a starving frog.\n");
                }
                
                return;                
            }

            //=== Cas normal : prise d’objet ===
            if (game.getPlayer().getItemByName(itemName) != null) {
                //Normalement impossible : gère le cas ou l'on tenterait de prendre un Item déjà dans l'inventaire
                System.out.println("You already have this item.");
                return;
            }

            //Ajoute l'objet sélectionné à l'inventaire, sauf si c'est un PNJ + Textes en fonction de la circonstance
            Item item = current.removeItemByName(itemName);
            if (item != null) {
                String name = item.getName().toLowerCase();

                if (name.equals("tonton") || name.equals("massomo") || name.equals("chris")) {
                    System.out.println("\nYou talked with " + item.getName() + ".");
                } else {
                    game.getPlayer().addItem(item);
                    System.out.println();

                    if (name.equals("crystal")) {
                        System.out.println("[You piked up: crystal]");
                        System.out.println("You : Mmmh, maybe I should ask some help to use that");
                    } else {
                        System.out.println("[You picked up: " + item.getName() + "]");
                    }

                    System.out.println();
                }

                //Débloque Wizard's Lair et laisse un message secret au sol (accessible via look) quand on parle à Massomo (Bridge)
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

            //Si le joueur entre une mauvaise saisie

            } else {
                System.out.println("There is no such item or character here.\n");
            }
        } else {
            System.out.println("Invalid format. Use: 'take <name>' next time.\n");
        }
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return this.description;
    }
}
