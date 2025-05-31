package main;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    //Tableau 2D pour la carte
    private Location[][] map;

    //Dimensions de la carte : est = largeur, nord = hauteur
    private int est;
    private int nord;

    //Référence à la partie actuelle
    private Game game;

    // [31.05.2025] Map des descriptions alternatives pour les zones verrouillées
    private Map<String, String> lockedDescriptions = new HashMap<>();

    //Constructeur de WorldMap (définit la taille de la map et initialise les emplacements)
    public WorldMap() {
        this.est = 3;
        this.nord = 3;
        this.map = new Location[est][nord];
        initializeMap();
    }

    //Setter pour modifier la partie actuelle
    public void setGame(Game game) {
        this.game = game;
    }

    //Getter pour récupérer la partie actuelle
    public Game getGame() {
        return this.game;
    }

    //Méthode contenant les POI de la carte
    private void initializeMap() {
        setLocation(0, 0, new Location("Bridge", "[A calm wooden bridge above the river.]"));
        setLocation(1, 0, new Location("Farm", "[A quiet farm with crops.]"));
        setLocation(2, 0, new Location("Cave", "[A mysterious cave.]"));
    
        setLocation(0, 1, new Location("Burger King", "[The local burger king of your small medieval village.]"));
        setLocation(1, 1, new Location("House", "[Your cozy home]"));
        setLocation(2, 1, new Location("Castle", "[Lord Alberto's Castle]"));
    
        setLocation(0, 2, new Location("Market", "[A busy place full of merchants.]"));
        setLocation(1, 2, new Location("River", "[A flowing river with a narrow bridge.]"));
        setLocation(2, 2, new Location("Wizard's Lair", "[The tower of Massamo the great wizard.]"));
    
        // [31.05.2025] Zones verrouillées au départ (bien coordonnées)
        map[1][0].setLocked(true); // Burger King
        map[1][2].setLocked(true); // Castle
        map[2][2].setLocked(true); // Wizard's Lair
    
        // [31.05.2025] Descriptions alternatives pour zones verrouillées
        lockedDescriptions.put("Burger King", "[You try to enter, but the door is strangely sealed with a burger-shaped emblem.]");
        lockedDescriptions.put("Castle", "[The gates of Lord Alberto’s castle are locked tight. You’ll need a key.]");
        lockedDescriptions.put("Wizard's Lair", "[A magical barrier repels you. Perhaps the wizard is not home yet.]");
    
        // [31.05.2025] La zone de départ est découverte par défaut
        map[1][1].setDiscovered(true);

        // [01.06.2025] Objets de test ajoutés à différentes zones
        map[0][0].addItem(new Item("objet1", "sample descr 1", "Bridge"));   // Bridge
        map[2][1].addItem(new Item("objet2", "sample descr 2", "Castle"));   // Castle
        map[2][2].addItem(new Item("objet3", "sample descr 3", "Wizard's Lair")); // Wizard's Lair

        // [01.06.2025] Rencontre avec Massomo le Sorcier au Bridge pour débloquer Wizard's Lair
        map[0][0].addItem(new Item("massomo", "You see Massomo meditating above the river. His eyes open slowly when you approach.", "Wizard's Lair"));

    }
    

    // [31.05.2025] Récupère la description alternative d'une zone verrouillée
    public String getLockedDescription(String locationName) {
        return lockedDescriptions.getOrDefault(locationName, "You can’t go there yet.");
    }

    //Méthode pour assigner un lieu à une position donnée sur la carte
    public void setLocation(int x, int y, Location location) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            map[y][x] = location;
        } else {
            System.out.println("Impossible to move there, " + x + ", " + y + " are out of bounds.");
        }
    }

    //Getter permettant de récupérer un lieu à une position donnée
    public Location getLocation(int x, int y) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            return map[y][x];
        }
        return null;
    }

    //Getter pour récupérer la carte
    public Location[][] getWorldMap() {
        return map;
    }
}
