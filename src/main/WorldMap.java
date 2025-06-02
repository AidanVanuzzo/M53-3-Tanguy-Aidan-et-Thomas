package main;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    private Location[][] map;
    private int est;
    private int nord;
    private Game game;

    //Descriptions alternatives pour les zones verrouillées
    private Map<String, String> lockedDescriptions = new HashMap<>();

    public WorldMap() {
        this.est = 3;
        this.nord = 3;
        this.map = new Location[est][nord];
        initializeMap();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    private void initializeMap() {
        // === Création des zones ===
        setLocation(0, 0, new Location("Bridge", "[A calm wooden bridge above the river.]"));
        setLocation(1, 0, new Location("Farm", "[A quiet farm with crops.]"));
        setLocation(2, 0, new Location("Cave", "[A mysterious cave.]"));

        setLocation(0, 1, new Location("Burger King", "[The local burger king of your small medieval village.]"));
        setLocation(1, 1, new Location("House", "[Your cozy home]"));
        setLocation(2, 1, new Location("Castle", "[Lord Alberto's Castle]"));

        setLocation(0, 2, new Location("Market", "[A busy place full of merchants.]"));
        setLocation(1, 2, new Location("River", "[A relaxing flowing river.]"));
        setLocation(2, 2, new Location("Wizard's Lair", "[The tower of Massomo the great wizard.]"));

        // === Zones verrouillées par défaut ===
        getLocation(0, 1).setLocked(true); // Burger King
        getLocation(2, 1).setLocked(true); // Castle
        getLocation(2, 2).setLocked(true); // Wizard's Lair

        // === Descriptions alternatives ===
        lockedDescriptions.put("Burger King", "[You try to enter, but the door is strangely sealed with a burger-shaped emblem.]\n");
        lockedDescriptions.put("Castle", "[The gates of Lord Alberto’s castle are locked tight. You’ll need a key.]");
        lockedDescriptions.put("Wizard's Lair", "[A magical barrier repels you. Perhaps the wizard is not home yet.]");

        // === Zone de départ découverte ===
        getLocation(1, 1).setDiscovered(true); // House

        // === Massomo, déverrouille Wizard's Lair ===
        getLocation(0, 0).addItem(new Item("massomo", "You see Massomo meditating above the river. His eyes open slowly when you approach.", "Wizard's Lair"));

        // === Tonton Eleganza Market ===
        getLocation(0, 2).addItem(new Item("tonton", "An elegant man in a turquoise silk suit holding a melon. He looks at you with flair.", "Market"));

        // === VIP Card de Tonton ===
        Item vipCard = new Item("vip card", "\nThis shiny VIP card gives you access to unlimited burgers.\n", "Burger King");

        String tontonIntro = "\nTonton: Hello young man, how are you?\n" +
                "[A strange card is sticking out of his pocket.]\n" +
                "\n[Tonton, annoyed, exclaims...]\n" +
                "Tonton: I found my old VIP pass that lets me eat for free at Burger King...\n" +
                "Tonton: I’ll give it to you, but first you have to guess how many fingers I’m hiding behind my back! \n";

        getLocation(0, 2).setNpcPuzzle("Tonton Eleganza", tontonIntro, vipCard);

        // === Objets distracteurs ===
        Item farmJunk = new Item(
            "clean pitchfork",
            "Oddly shiny and completely clean. Nobody dares to touch it.",
            "Farm"
        );
        getLocation(1, 0).addItem(farmJunk);

        Item riverPhoto = new Item(
            "picture of tonton",
            "The strange-looking man wears a big red hat and a green suit.",
            "River"
        );
        getLocation(1, 2).addItem(riverPhoto);

        // === Massomo dans la Wizard's Lair (avec énigme)
        Item lairMassomo = new Item("massomo", "Massomo stands silently by his bookshelf. His eyes sparkle with ancient knowledge.", "Wizard's Lair");
        getLocation(2, 2).addItem(lairMassomo);

        // === Chris au Burger King ===
        Item wooper = new Item(
            "wooper",
            "A Warm Juicy Wooper. It looks delicious.",
            "Burger King"
        );

        String chrisIntro = """
        \nChris: Hello, traveler! Would you like to try one of my delicious Whoppers? (yes/no)
        """;

        getLocation(0, 1).setNpcPuzzle("Chris", chrisIntro, wooper);
        getLocation(0, 1).addItem(new Item("chris", "A friendly cook with a paper hat and a spatula in hand.", "Burger King"));
        
        // === Crappi Crapo dans la Cave ===
        Item crappi = new Item("crappi", "You see a big toad with golden rings and a chef’s hat. He croaks loudly.", "Cave");
        getLocation(2, 0).addItem(crappi); // Ajout du PNJ visuel

        Item goldKey = new Item("gold key", "A large golden key adorned with diamonds", "Castle");
        String crappiIntro = """
        Hey Chef, it’s rough being a frog these days. I'm starving out here.
        You know what? I’ll give you this key if you bring me something to eat.
        """;
        getLocation(2, 0).setNpcPuzzle("Crappi Crapo", crappiIntro, goldKey);

        // === Boss final : Alberto dans Castle ===
        Item redOrb = new Item("red orb", "A mysterious red orb pulsing with energy.", null);
        getLocation(2, 1).setNpcPuzzle(
            "Alberto",
            "As soon as you step into the castle hall, Alberto charges at you with his sword.",
            redOrb
        );

    }

    public String getLockedDescription(String locationName) {
        return lockedDescriptions.getOrDefault(locationName, "You can’t go there yet.");
    }

    public void setLocation(int x, int y, Location location) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            map[y][x] = location;
        } else {
            System.out.println("Impossible to move there, " + x + ", " + y + " are out of bounds.");
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            return map[y][x];
        }
        return null;
    }

    public Location[][] getWorldMap() {
        return map;
    }
}
