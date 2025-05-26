package main;

public class WorldMap {

    private Location[][] map;
    private int est;
    private int nord;

    private Game game;  // Référence vers le game

    public WorldMap() {
        this.est = 3;
        this.nord = 3;
        this.map = new Location[est][nord];
        initializeMap();
    }

    // **Ajoute cette méthode sinon erreur undefined setGame**
    public void setGame(Game game) {
        this.game = game;
    }

    // **Ajoute cette méthode pour récupérer le game**
    public Game getGame() {
        return this.game;
    }

    private void initializeMap() {
        // Remplir ta map ici, par exemple :
        setLocation(0, 0, new Location("Bridge", "A calm wooden bridge above the river."));
        setLocation(1, 0, new Location("Farm", "A quiet farm with crops."));
        setLocation(2, 0, new Location("Cave", "A mysterious cave."));

        setLocation(0, 1, new Location("Burger King", "The local burger king of your small medieval village."));
        setLocation(1, 1, new Location("House", "Your cozy home"));
        setLocation(2, 1, new Location("Castle", "Lord Alberto's Castle"));

        setLocation(0, 2, new Location("Market", "A busy place full of merchants."));
        setLocation(1, 2, new Location("River", "A flowing river with a narrow bridge."));
        setLocation(2, 2, new Location("Wizard's Lair", "The tower of Massamo the great wizard."));
    }

    public void setLocation(int x, int y, Location location) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            map[x][y] = location;
        } else {
            System.out.println("Impossible to move there, " + x + ", " + y + " are out of bounds.");
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < est && y >= 0 && y < nord) {
            return map[x][y];
        }
        return null;
    }

    // **Attention : la méthode doit retourner IPrintable[][] pour Array2Dprinter**
    public Location[][] getWorldMap() {
        return map;
    }
}
