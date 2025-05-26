package main;

public class Player {
    private Location currentLocation;

    public Player(Location startLocation) {
        this.currentLocation = startLocation;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public void setLocation(Location location) {
        this.currentLocation = location;
    }
}

