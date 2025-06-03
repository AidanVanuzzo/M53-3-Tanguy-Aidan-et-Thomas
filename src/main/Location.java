package main;

import java.util.ArrayList;
import java.util.List;
import utils.IPrintable;

public class Location implements IPrintable {

    private String name;
    private String description;
    private boolean locked;
    private boolean discovered;

    //Objets présents dans la zone
    private List<Item> items;

    //PNJ et énigme associés à la zone
    private String npcName;
    private String npcIntro;
    private boolean puzzleActive;
    private Item rewardItem;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.locked = false;
        this.discovered = false;
        this.items = new ArrayList<>();
        this.npcName = null;
        this.npcIntro = null;
        this.puzzleActive = false;
        this.rewardItem = null;
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

    //Gestion des objets
    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item removeItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    //Gestion du PNJ et de l'énigme
    public void setNpcPuzzle(String npcName, String npcIntro, Item rewardItem) {
        this.npcName = npcName;
        this.npcIntro = npcIntro;
        this.rewardItem = rewardItem;
        this.puzzleActive = true;
    }

    public boolean hasNpc() {
        return npcName != null;
    }

    public String getNpcName() {
        return npcName;
    }

    public String getNpcIntro() {
        return npcIntro;
    }

    public boolean isPuzzleActive() {
        return puzzleActive;
    }

    public Item getRewardItem() {
        return rewardItem;
    }

    public void completePuzzle() {
        this.puzzleActive = false;
    }

    //Méthodes d'affichage de la carte
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

    //Save - retirer item
    public void removeItem(String itemName) {
    items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }    

}
