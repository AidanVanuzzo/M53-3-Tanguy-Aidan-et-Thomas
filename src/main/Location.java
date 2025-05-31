package main;

import utils.IPrintable;

public class Location implements IPrintable { 

    //Nom et description du lieu
    private String name;
    private String description;

    //Permet de gérer si un lieu est vérouillé et s'il a déjà été visité
    private boolean locked;
    private boolean discovered;

    //Constructeur
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.locked = false;
        this.discovered = false;
    }

    //Méthodes, Getters et Setters -->

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

    // [31.05.2025] Affiche le nom si la zone est découverte, même si elle est verrouillée
    @Override
    public String getPrintableString() {
        return discovered ? name : "";
    }

    //Indique si le lieu doit apparaître en grisé (verrouillé ou non découvert)
    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }
}
