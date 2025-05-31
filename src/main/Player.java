package main;

import java.util.ArrayList;
import java.util.List;

public class Player {

    //Position actuelle du joueur
    private Location currentLocation;

    // [01.06.2025] Inventaire du joueur
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

    // [01.06.2025] Ajoute un objet à l'inventaire
    public void addItem(Item item) {
        inventory.add(item);
    }

    // [01.06.2025] Récupère l'inventaire du joueur
    public List<Item> getInventory() {
        return inventory;
    }

    // [01.06.2025] Récupère un objet par son nom
    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    // Ajoute cette méthode dans ta classe Player

    public String getName() {
        return "Player"; // Ou retourne un vrai nom si tu veux le personnaliser plus tard
    }

}
