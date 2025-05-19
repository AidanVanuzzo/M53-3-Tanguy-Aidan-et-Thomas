package main;

import java.util.List;

<<<<<<< HEAD
public class Worldmap {
=======
public class WorldMap {
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59

    private Location[][] map;
    private int est;
    private int nord;

<<<<<<< HEAD
    public Worldmap(int est, int nord) {
        this.est = est;
        this.nord = nord;
        this.map = new Location[est][nord];
     initializeMap();
     }

   private void initializeMap() {
        // C'est pour setup les location j'ai fait une petite map de 3x3 pour le début mais c'est adaptable, j'ai mis des location au pif


        
    // Ligne 0
    Location bridge = new Location("Bridge", "A calm wooden bridge above the river.");
    setLocation(0, 0, bridge);

    Location farm = new Location("Farm", "A quiet farm with crops.");
    setLocation(1, 0, farm);

    Location cave = new Location("Cave", "A mysterious cave.");
    cave.setLocked(true); // faut répondre à l'enigme du crapeau pour entrer
    setLocation(2, 0, cave);

    // Ligne 1
    Location burgerKing = new Location("Burger King", "The local burger king of your small medieval village.");
    setLocation(0, 1, burgerKing); 

    Location house = new Location("House", "Your cozy home");
    house.setDiscovered(true); // Point de départ de l'aventure je l'ai set au millieu de la map 3x3
    setLocation(1, 1, house);

    Location castle = new Location("Castle", "Lord Gabor's Castle");
    castle.setLocked(true); //il faut la foreign key de Gabor pour rentrer
    setLocation(2, 1, castle);

    // Ligne 2
    Location market = new Location("Market", "A busy place full of merchants.");
    setLocation(0, 2, market);

    Location river = new Location("River", "A flowing river with a narrow bridge.");
    setLocation(1, 2, river);

    Location wizzard = new Location("Wizzard's Lair", "The tower of Massamo the great wizzard.");
    wizzard.setLocked(true); // Massamo doit verifier qu'on en est digne avant de nous laisser rentrer
    setLocation(2, 2, wizzard);
} 

public void setLocation(int x, int y, Location location){
    if (isInBounds(x,y)) {
        map[x][y] = location;
    } else {
        System.out.println("the coordinates are out of bounds, " + x + " + " + y + " are out of bounds.");
    }
}

 public Location getLocation(int x, int y) {
=======
    public WorldMap(/* int est, int nord */) {
        /*
         * this.est = est;
         * this.nord = nord;
         */
        this.map = new Location[est][nord];
        initializeMap();
    }

    private void initializeMap() {
        // C'est pour setup les location j'ai fait une petite map de 3x3 pour le début
        // mais c'est adaptable, j'ai mis des location au pif

        // Ligne 0
        Location bridge = new Location("Bridge", "A calm wooden bridge above the river.");
        setLocation(0, 0, bridge);

        Location farm = new Location("Farm", "A quiet farm with crops.");
        setLocation(1, 0, farm);

        Location cave = new Location("Cave", "A mysterious cave.");
        cave.setLocked(true); // faut répondre à l'enigme du crapeau pour entrer
        setLocation(2, 0, cave);

        // Ligne 1
        Location burgerKing = new Location("Burger King", "The local burger king of your small medieval village.");
        setLocation(0, 1, burgerKing);

        Location house = new Location("House", "Your cozy home");
        house.setDiscovered(true); // Point de départ de l'aventure je l'ai set au millieu de la map 3x3
        setLocation(1, 1, house);

        Location castle = new Location("Castle", "Lord Gabor's Castle");
        castle.setLocked(true); // il faut la foreign key de Gabor pour rentrer
        setLocation(2, 1, castle);

        // Ligne 2
        Location market = new Location("Market", "A busy place full of merchants.");
        setLocation(0, 2, market);

        Location river = new Location("River", "A flowing river with a narrow bridge.");
        setLocation(1, 2, river);

        Location wizzard = new Location("Wizzard's Lair", "The tower of Massamo the great wizzard.");
        wizzard.setLocked(true); // Massamo doit verifier qu'on en est digne avant de nous laisser rentrer
        setLocation(2, 2, wizzard);
    }

    public void setLocation(int x, int y, Location location) {
        if (isInBounds(x, y)) {
            map[x][y] = location;
        } else {
            System.out.println("the coordinates are out of bounds, " + x + " + " + y + " are out of bounds.");
        }
    }

    public Location getLocation(int x, int y) {
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59
        if (isInBounds(x, y)) {
            return map[x][y];
        }
        return null;
    }
<<<<<<< HEAD
    private boolean isInBounds(int x, int y){
         return x >= 0 && x < est && y >= 0 && y < nord;
    }

    public void discoverLocation(int x, int y) {
    Location loc = getLocation(x, y);
    if (loc != null) {
        if (!loc.isDiscovered()) {
            loc.setDiscovered(true);
            System.out.println("It's your first time here.");
        } 
}
}
}


=======

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < est && y >= 0 && y < nord;
    }

    public void discoverLocation(int x, int y) {
        Location loc = getLocation(x, y);
        if (loc != null) {
            if (!loc.isDiscovered()) {
                loc.setDiscovered(true);
                System.out.println("It's your first time here.");
            }
        }
    }
}
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59
