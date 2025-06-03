package main;

import java.util.ArrayList;
import java.util.List;

public class Player {

    //Position actuelle du joueur
    private Location currentLocation;

    //Inventaire du joueur
    private List<Item> inventory;

    //Constructeur
    public Player(Location startLocation) {
        this.currentLocation = startLocation;
        this.inventory = new ArrayList<>();
    }

    //Getter pour la position actuelle du joueur
    public Location getLocation() {
        return currentLocation;
    }

    //Setter pour mettre à jour la position actuelle du joueur
    public void setLocation(Location location) {
        this.currentLocation = location;
    }

    //Ajout d'un objet à l'inventaire
    public void addItem(Item item) {
        inventory.add(item);
    }

    //Récupère l'inventaire du joueur
    public List<Item> getInventory() {
        return inventory;
    }

    //Récupère un objet par son nom dans l'inventaire (pour inspect, use, etc.)
    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    //Getter pour récupérer le nom du joueur (toujours 'Player')
    public String getName() {
        return "Player";
    }

}
