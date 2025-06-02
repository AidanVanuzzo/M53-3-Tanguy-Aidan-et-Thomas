package main;

import java.util.Scanner;

public class Game {

    private int playerX = 1;
    private int playerY = 1;
    private Scanner scanner = new Scanner(System.in);

    private WorldMap worldMap;
    private CommandRegistry registry;
    private Player player;
    private Location startingLocation;

    public Game() {
        this.worldMap = new WorldMap();
        this.worldMap.setGame(this);
        startingLocation = worldMap.getLocation(playerX, playerY);
        player = new Player(startingLocation);
    }

    public void run() {
        System.out.println();
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println("Welcome, brave warrior!");
        System.out.println("Time is running out...");
        System.out.println("You must stop Lord Alberto before his dark powers destroy our world!");
        System.out.println("/////////////////////////////////////////////////////////////////////");
        System.out.println();
        System.out.println("ZzzzzzZzzzzzZzzzzz...");
        System.out.println("Press Enter to wake up...");
        scanner.nextLine();
        System.out.println("Mom: Oh, you’re finally awake...");
        System.out.println("Mom: It’s very dark today, I hope there won’t be a thunderstorm.");
        System.out.println("Mom: Anyway...have a great day, my darling!");
        System.out.println();
        System.out.println("Mom: Oh, I almost forgot, I found your map in your pants before doing the laundry. What do you say?");
        System.out.print("You: ");
        String response;
        do {
            response = scanner.nextLine().toLowerCase().trim();
            if (!(response.equals("thanks") || response.equals("thank you"))) {
                System.out.println("Mom: Hmm? Don’t forget your manners, dear...");
                System.out.print("You: ");
            }
        } while (!(response.equals("thanks") || response.equals("thank you")));
        System.out.println();
        System.out.println("Mom: You're welcome! Be careful out there. [You leave the house]");
        System.out.println();
        System.out.println("Type 'help' to discover the commands (To exit, type 'quit').");

        registry = new CommandRegistry(this);
        registry.commandExecute();
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerX(int x) {
        updatePlayerLocation(x, this.playerY);
    }

    public void setPlayerY(int y) {
        updatePlayerLocation(this.playerX, y);
    }

    private void updatePlayerLocation(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        Location loc = worldMap.getLocation(x, y);
        if (loc != null) {
            loc.setDiscovered(true);
        }
        player.setLocation(loc);
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public Location getCurrentLocation() {
        return player.getLocation();
    }

    public void movePlayer(int newX, int newY) {
        Location target = worldMap.getLocation(newX, newY);
        if (target == null) {
            System.out.println("You can’t go that way.");
            return;
        }
    
        if (target.isLocked()) {
            for (Item item : player.getInventory()) {
                if (item.getUseZone() != null && item.getUseZone().equalsIgnoreCase(target.getName())) {
                    target.setLocked(false);
                    System.out.println("BEEP... The VIP card granted you access. The heavy door slides open with a hiss.");
                    System.out.println();
                    break;
                }
            }
        }
    
        if (target.isLocked()) {
            System.out.println(worldMap.getLockedDescription(target.getName()));
            return;
        }
    
        updatePlayerLocation(newX, newY);
        System.out.println(target.getDescription()); // Toujours afficher la description d'abord

        // === Fin du jeu si joueur retourne à la maison avec l’orbe rouge ===
        if (target.getName().equalsIgnoreCase("House") &&
        player.getItemByName("red orb") != null &&
        !target.getDescription().contains("Mom is waiting")) {

        System.out.println("\n[Mom is waiting for you. Oh...the orb turns turquoise!]");
        System.out.println("[You hand her the orb, and mom uses her powerful mage abilities to restore peace to the world.]");
        System.out.println("[She makes you a hot chocolate, and you enjoy your well-deserved weekend.]\n");

        System.out.println("////////////////////////////***EPILOGUE***////////////////////////////");
        System.out.println("Crappi Crappo will be hired as a cook at Burger King.");
        System.out.println("Chris will be promoted to Regional Assistant to the Burger King Manager.");
        System.out.println("Massamo will enjoy a peaceful retirement in the Balearic Islands.");
        System.out.println("Tonton will take over Alberto's castle and crown himself Supreme Leader of the kingdom,");
        System.out.println("deviously preparing his plan to steal the sacred orb.");
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println("\n[A game by Tanguy Vaucher, Thomas Delacétaz, Aidan Vanuzzo]\n");

        System.exit(0);
        }

        // === Boss final : Alberto dans Castle ===
        if (target.getName().equalsIgnoreCase("Castle") && target.isPuzzleActive()) {
            System.out.println("\n[As soon as you step into the castle hall, Alberto charges at you with his sword.]");
            System.out.println("Alberto: Evil one! I know why you are here. I will slay you!");

            System.out.print("\n>> ");

            java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
            java.util.concurrent.Future<String> future = executor.submit(() -> scanner.nextLine().trim().toLowerCase());

            try {
                String response = future.get(10, java.util.concurrent.TimeUnit.SECONDS);

                if (response.equals("rastapopoulos")) {
                    System.out.println("\nAlberto: BY THE GODS— I SHALL RE— BLAAARRRGHHKABOOM!!");
                    System.out.println("[Alberto explodes and disappears into the corridors of time. A shiny red orb rolls to your feet.]");
                    System.out.println("\n[You received: red orb]\nYou : Mom will surely know what to do with it.\n");

                    Item redOrb = target.getRewardItem();
                    if (redOrb != null && player.getItemByName(redOrb.getName()) == null) {
                        player.addItem(redOrb);
                    }

                    target.completePuzzle(); // Évite que le boss réapparaisse
                } else {
                    System.out.println("\n[Alberto struck you down with his sword. YOU ARE DEAD.]");
                    System.exit(0);
                }
            } catch (java.util.concurrent.TimeoutException e) {
                System.out.println("\n\n[You hesitated too long... Alberto struck you down with his sword. YOU ARE DEAD.]");
                System.exit(0);
            } catch (Exception e) {
                System.out.println("\n[An unexpected error occurred during the final battle.]");
                e.printStackTrace();
            } finally {
                executor.shutdownNow();
            }
        }
    }    

    public Player getPlayer() {
        return player;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
    
}
