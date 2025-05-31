package main;

import utils.IPrintable;
import java.util.ArrayList;
import java.util.List;

public class Location implements IPrintable {

    //Nom et description du lieu
    private String name;
    private String description;

    //Permet de gérer si un lieu est vérouillé et s'il a déjà été visité
    private boolean locked;
    private boolean discovered;

    // [01.06.2025] Liste des objets présents sur place
    private List<Item> items;

    //Constructeur
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.locked = false;
        this.discovered = false;
        this.items = new ArrayList<>();
    }

    //Getters et Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // [01.06.2025] Ajoute un objet à la zone
    public void addItem(Item item) {
        items.add(item);
    }

    // [01.06.2025] Renvoie la liste des objets dans la zone
    public List<Item> getItems() {
        return items;
    }

    // [01.06.2025] Retire un objet par son nom (utilisé par CSay)
    public Item removeItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    //Affichage sur la carte
    @Override
    public String getPrintableString() {
        return discovered ? name : "";
    }

    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }
}
