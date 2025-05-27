package main;

public class Player {

    //Position actuelle du joueur
    private Location currentLocation;

    //Constructeur
    public Player(Location startLocation) {
        //Initialise la position de départ
        this.currentLocation = startLocation;
    }

    //Getter pour la position actuelle du joueur
    public Location getLocation() {
        return currentLocation;
    }

    //Setter pour mettre à jour la position actuelle du joueur
    public void setLocation(Location location) {
        this.currentLocation = location;
    }
}
