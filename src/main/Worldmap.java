package main;

public class WorldMap {

    //Tableau 2D pour la carte
    private Location[][] map;

    //Dimensions de la carte : est = largeur, nord = hauteur
    private int est;
    private int nord;

    //Référence à la partie actuelle
    private Game game;

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
        setLocation(0, 0, new Location("Bridge", "A calm wooden bridge above the river."));
        setLocation(1, 0, new Location("Farm", "A quiet farm with crops."));
        setLocation(2, 0, new Location("Cave", "A mysterious cave."));

        setLocation(0, 1, new Location("Burger King", "The local burger king of your small medieval village."));
        setLocation(1, 1, new Location("House", "Your cozy home"));
        setLocation(2, 1, new Location("Castle", "Lord Alberto's Castle"));

        setLocation(0, 2, new Location("Market", "A busy place full of merchants."));
        setLocation(1, 2, new Location("River", "A flowing river with a narrow bridge."));
        setLocation(2, 2, new Location("Wizard's Lair", "The tower of Massamo the great wizard."));

        map[1][1].setDiscovered(true); // [31.05.2025] La zone de départ est découverte par défaut.
    }

    //Méthode pour asigner un lieu à une position donnée sur la carte
    public void setLocation(int x, int y, Location location) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            map[y][x] = location;
        } else {
            //Gère le cas ou l'on ajouterait une position out of bound
            System.out.println("Impossible to move there, " + x + ", " + y + " are out of bounds.");
        }
    }

    //Getter permettant de récupérer un lieu à une position donnée
    public Location getLocation(int x, int y) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            return map[y][x];
        }
        //Retourne null si la position est out of bound
        return null;
    }

    //Getter pour récupérer la carte
    public Location[][] getWorldMap() {
        return map;
    }
}
