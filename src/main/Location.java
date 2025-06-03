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

    //PNJ, énigme(s) et Item associés à la zone
    private String npcName;
    private String npcIntro;
    private boolean puzzleActive;
    private Item rewardItem;

    //Constructeur
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

    //Getters, Setters, Retour de l'état (locked, discovered)

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

    //Gestion des objets dans la zone

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
    //Supprime un objet de la zone quand on le ramasse
    public Item removeItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    //Gestion des PNJ et de leurs énigmes avec Getter, état, etc.

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

    //Méthodes d'affichage sur la carte d'une zone découverte
    @Override
    public String getPrintableString() {
        if (discovered) {
            return name;
        }
        return "";
    }

    //Affiche en grisé les zones trouvées mais vérouillées
    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }

    //Récupérer un objet (pour inspect, use, look...)
    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }  

    //Save - Retirer les Item déjà récupérés/interagis(PNJ)
    public void removeItem(String itemName) {
    items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }  

}
