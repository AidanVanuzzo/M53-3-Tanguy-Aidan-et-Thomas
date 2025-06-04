package main;

//Imports nécessaires au système de sauvegarde
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Game {

    //Coordonnées par défaut
    private int playerX = 1;
    private int playerY = 1;

    private Scanner scanner = new Scanner(System.in);

    //Carte, commandes et joueur
    private WorldMap worldMap;
    private CommandRegistry registry;
    private Player player;
    private Location startingLocation;

    //Save - Pour savoir s'il on recharge la dernière sauvegarde
    private boolean loadRequested = false;

    public void setLoadRequested(boolean loadRequested) {
        this.loadRequested = loadRequested;
    }

    //Constructeur
    public Game() {
        this.worldMap = new WorldMap();
        this.worldMap.setGame(this);
        startingLocation = worldMap.getLocation(playerX, playerY);
        player = new Player(startingLocation);
    }

    //Méthode de lancement du jeu
    public void run() {
        //Save - Chargement de la sauvegarde si demandé
        if (loadRequested) {
            loadState();
            registry = new CommandRegistry(this);
            System.out.println("\n--Save loaded successfully!--\n");
            //Affiche la description du lieu ou réapparait le joueur
            System.out.println(getCurrentLocation().getDescription()); 
            registry.commandExecute();
            return;
        }

        //=== Maman - Introduction du jeu (si nouvelle partie)
        System.out.println("\n\n\n\n\n\n");
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

        //Énigme de Maman
        do {
            response = scanner.nextLine().toLowerCase().trim();
            if (!(response.equals("thanks") || response.equals("thank you"))) {
                //Aide le joueur s'il échoue à l'énigme
                System.out.println("Mom: Hmm? Don’t forget your manners, dear...");
                System.out.print("You: ");
            }
        } while (!(response.equals("thanks") || response.equals("thank you")));
        System.out.println();
        System.out.println("Mom: You're welcome! Be careful out there. [You leave the house]");
        System.out.println();
        //Incite le joueur à entrer 'help' pour découvrir les commandes du jeu
        System.out.println("Advice: Type 'help' to discover the commands.");
        //Démarre la boucle de commandes
        registry = new CommandRegistry(this);
        registry.commandExecute();
    }

    //Getters et setters pour la position du joueur

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

    //Mise à jour de la position du joueur
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

    //Déplacement du joueur dans la carte
    public void movePlayer(int newX, int newY) {
        Location target = worldMap.getLocation(newX, newY);

        //Empêche de sortir de la carte
        if (target == null) {
            System.out.println("You can’t go that way.");
            return;
        }
    
        //Déverrouillage des zones
        if (target.isLocked()) {
            for (Item item : player.getInventory()) {
                if (item.getUseZone() != null && item.getUseZone().equalsIgnoreCase(target.getName())) {
                    target.setLocked(false);
                    //Pour le Burger King
                    System.out.println("BEEP... The VIP card granted you access. The heavy door slides open with a hiss.");
                    System.out.println();
                    break;
                }
            }
        }
    
        //Affiche la description alternative d'un lieu verrouillé
        if (target.isLocked()) {
            System.out.println(worldMap.getLockedDescription(target.getName()));
            return;
        }
        
        //Si le lieu n'est pas verrouillé, on modifie la position du joueur
        updatePlayerLocation(newX, newY);
        //Chaque fois qu'on arrive dans un nouveau lieu, on affiche sa description
        System.out.println(target.getDescription());

        //=== Fin du jeu si le joueur retourne à la maison avec l’orbe rouge ===
        if (target.getName().equalsIgnoreCase("House") &&
        player.getItemByName("red orb") != null &&
        !target.getDescription().contains("Mom is waiting")) {
        
        //Scénario du endgame
        System.out.println("\n[Mom is waiting for you...Oh!...the orb turns blue!]");
        System.out.println("[You hand her the orb, and mom uses her powerful mage abilities to restore peace to the world.]");
        System.out.println("[She makes you a hot chocolate, and you enjoy your well-deserved weekend.]\n");

        System.out.println("////////////////////////////***EPILOGUE***////////////////////////////");
        System.out.println("Crappi Crappo will be hired as a cook at Burger King.");
        System.out.println("Chris will be promoted to Regional Assistant to the Burger King Manager.");
        System.out.println("Massomo will enjoy a peaceful retirement in the Balearic Islands.");
        System.out.println("Tonton will take over Alberto's castle and crown himself Supreme Leader of the kingdom...");
        System.out.println("...deviously preparing his plan to steal the sacred orb.");
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println("\n[THE END - A game by Tanguy Vaucher, Thomas Delacétaz, Aidan Vanuzzo]\n");
        
        //Arrêt de l'exécution
        System.exit(0);
        }

        //=== Boss final - Alberto dans Castle ===
        if (target.getName().equalsIgnoreCase("Castle") && target.isPuzzleActive()) {
            
            //Indications au joueur
            System.out.println("\n[As soon as you step into the castle hall, Alberto charges at you with his sword.]");
            System.out.println("Alberto: Evil one! I know why you are here. I will slay you!");

            System.out.print("\n>> ");

            //Champ pour le timer de 10 secondes
            java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
            java.util.concurrent.Future<String> future = executor.submit(() -> scanner.nextLine().trim().toLowerCase());

            try {
                //Timer de 10 secondes
                String response = future.get(10, java.util.concurrent.TimeUnit.SECONDS);

                if (response.equals("rastapopoulos")) {
                    //Cas réussite, on reçoit l'orbe rouge
                    System.out.println("\nAlberto: BY THE GODS— I SHALL RE— BLAAARRRGHHKABOOM!!");
                    System.out.println("[Alberto explodes and disappears into the corridors of time. A shiny red orb rolls to your feet.]");
                    System.out.println("\n[You received: red orb]\nYou : Mom will surely know what to do with it.\n");

                    Item redOrb = target.getRewardItem();
                    if (redOrb != null && player.getItemByName(redOrb.getName()) == null) {
                        player.addItem(redOrb);
                    }

                    target.completePuzzle();
                } else {
                    //Si l'on entre une mauvaise réponse ou après 10 secondes, le programme s'arrête
                    System.out.println("\n[Alberto struck you down with his sword. YOU ARE DEAD.]\n");
                    System.exit(0);
                }
            } catch (java.util.concurrent.TimeoutException e) {
                System.out.println("\n\n[You hesitated too long... Alberto struck you down with his sword. YOU ARE DEAD.]\n");
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

    //Save - Sauvegarde de la partie dans savegame.txt
    public void saveState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("savegame.txt"))) {

            //Restaurer la position
            writer.write("position:" + playerX + "," + playerY);
            writer.newLine();

            //Restaurer l'inventaire
            for (Item item : player.getInventory()) {
                writer.write("item:" + item.getName());
                writer.newLine();
            }

            //Restaurer les zones découvertes, déverrouillées, énigmes résolues
            for (int y = 0; y < worldMap.getWorldMap().length; y++) {
                for (int x = 0; x < worldMap.getWorldMap()[y].length; x++) {
                    Location loc = worldMap.getLocation(x, y);
                    if (loc != null) {
                        if (loc.isDiscovered()) {
                            writer.write("discovered:" + x + "," + y);
                            writer.newLine();
                        }
                        if (!loc.isLocked()) {
                            writer.write("unlocked:" + x + "," + y);
                            writer.newLine();
                        }
                        if (!loc.isPuzzleActive()) {
                            writer.write("puzzle:" + x + "," + y);
                            writer.newLine();
                        }
                    }
                }
            }
            //Indications au joueur (succès/échec)
            System.out.println("\n[Game saved successfully!]");
        } catch (IOException e) {
            System.out.println("[Failed to save game state]");
            e.printStackTrace();
        }
    }

    //Chargement de la dernière sauvegarde depuis le fichier "savegame.txt"
    public void loadState() {
        try {
            //Lit toutes les lignes du fichier de sauvegarde
            List<String> lines = Files.readAllLines(Paths.get("savegame.txt"));
            //Parcours chaque ligne pour restaurer les données
            for (String line : lines) {
                //Position
                if (line.startsWith("position:")) {
                    String[] coords = line.substring(9).split(",");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    setPlayerX(x);
                    setPlayerY(y);
                //Inventaire
                } else if (line.startsWith("item:")) {
                    String itemName = line.substring(5).trim();
                    Item item = worldMap.createRestoredItem(itemName);
                    if (item != null) {
                        player.addItem(item);
                        //Supprime les items restaurés de la carte
                        for (int y = 0; y < worldMap.getWorldMap().length; y++) {
                            for (int x = 0; x < worldMap.getWorldMap()[y].length; x++) {
                                Location loc = worldMap.getLocation(x, y);
                                if (loc != null) {
                                    loc.removeItem(itemName);
                                }
                            }
                        }
                    }
                //Zones découvertes
                } else if (line.startsWith("discovered:")) {
                    String[] coords = line.substring(11).split(",");
                    Location loc = worldMap.getLocation(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    if (loc != null) loc.setDiscovered(true);
                //Zones déverrouillées
                } else if (line.startsWith("unlocked:")) {
                    String[] coords = line.substring(9).split(",");
                    Location loc = worldMap.getLocation(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    if (loc != null) loc.setLocked(false);
                //Énigmes résolues
                } else if (line.startsWith("puzzle:")) {
                    String[] coords = line.substring(7).split(",");
                    Location loc = worldMap.getLocation(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    if (loc != null) loc.completePuzzle();
                }
            }
    
            //=== Cas spéciaux pour les PNJ ===
            for (int y = 0; y < worldMap.getWorldMap().length; y++) {
                for (int x = 0; x < worldMap.getWorldMap()[y].length; x++) {
                    Location loc = worldMap.getLocation(x, y);
                    if (loc == null) continue;
    
                    //Supprime Tonton si le joueur a déjà la VIP card
                    if (loc.getName().equalsIgnoreCase("Market") &&
                        player.getItemByName("vip card") != null) {
                        loc.removeItem("tonton");
                    }
    
                    //Supprime Chris si le joueur a déjà le Wooper ou la clé en or
                    if (loc.getName().equalsIgnoreCase("Burger King") &&
                        (player.getItemByName("wooper") != null || player.getItemByName("gold key") != null)) {
                        loc.removeItem("chris");
                    }
    
                    //Supprime Crappi Crapo si le joueur a la clé en or
                    if (loc.getName().equalsIgnoreCase("Cave") &&
                        player.getItemByName("gold key") != null) {
                        loc.removeItem("crappi");
                    }
    
                    //Empêcher la réapparition d'Alberto si on a l'orbe rouge
                    if (loc.getName().equalsIgnoreCase("Castle") &&
                        player.getItemByName("red orb") != null) {
                        loc.completePuzzle();
                    }

                    //Supprime Massomo à Bridge si la zone Wizard's Lair est déverrouillée
                    if (loc.getName().equalsIgnoreCase("Bridge")) {
                        Location wizardLair = worldMap.getLocation(2, 2);
                        if (wizardLair != null) {
                            boolean wizardUnlocked = !wizardLair.isLocked();
                            boolean massomoPresent = loc.getItemByName("massomo") != null;
                            boolean messagePresent = loc.getItemByName("[There is a message painted on the floor: Meet me in my tower and I will teach you the ultimate power.]") != null;

                            if (wizardUnlocked) {
                                if (massomoPresent) {
                                    loc.removeItem("massomo");
                                }
                                //Afficher le message secret de Massomo à Bridge
                                if (!messagePresent) {
                                    Item parchment = new Item(
                                        "[There is a message painted on the floor: Meet me in my tower and I will teach you the ultimate power.]",
                                        "",
                                        "Bridge"
                                    );
                                    loc.addItem(parchment);
                                }
                            } else {
                                //Si la zone Wizard's Lair est verrouillée : on remet Massomo et on retire le message secret
                                if (!massomoPresent) {
                                    Item massomo = new Item("massomo", "You see Massomo meditating above the river. His eyes open slowly when you approach.", "Bridge");
                                    loc.addItem(massomo);
                                }
                                if (messagePresent) {
                                    loc.removeItem("[There is a message painted on the floor: Meet me in my tower and I will teach you the ultimate power.]");
                                }
                            }
                        }
                    }

                    //Supprime Massomo à Wizard's Lair (l'Item invisible "massomo token" permet au système de savoir si on déjà résolu l'énigme)
                    if (loc.getName().equalsIgnoreCase("Wizard's Lair") &&
                        player.getItemByName("massomo token") != null) {
                        loc.removeItem("massomo");
                    }
                    
                }
            }
    
        } catch (IOException e) {
            //Si erreur de chargement
            System.out.println("[Failed to load game state]");
            e.printStackTrace();
        }
    }

    //Donne le choix au joueur de charger une partie ou non
    public void init() {
        System.out.print("\n\nDo you want to load the last save or start a new game? (load/new): ");
        String choice = scanner.nextLine().trim().toLowerCase();
    
        if (choice.equals("load")) {
            //Lance la récupération
            setLoadRequested(true);
        }
    }
    
    //Fermeture scanner
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }    
    
}
