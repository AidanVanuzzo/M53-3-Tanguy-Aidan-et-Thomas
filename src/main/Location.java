package main;

import java.util.ArrayList;
import java.util.List;
import utils.IPrintable;

public class Location implements IPrintable {

    private String name;
    private String description;
    private boolean locked;
    private boolean discovered;

    // [01.06.2025] Liste des objets dans cette zone
    private List<Item> items;

    // Constructeur
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.locked = false;
        this.discovered = false;
        this.items = new ArrayList<>();
    }

    // Getters et setters
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

    // [01.06.2025] Ajoute un objet à cette zone
    public void addItem(Item item) {
        items.add(item);
    }

    // [01.06.2025] Retourne la liste des objets présents dans la zone
    public List<Item> getItems() {
        return items;
    }

    // [01.06.2025] Retire un objet par son nom et le retourne (sinon null)
    public Item removeItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    // Méthodes de l’interface IPrintable
    @Override
    public String getPrintableString() {
        if (discovered) {
            return name;
        }
        return "";
    }

    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }
}
