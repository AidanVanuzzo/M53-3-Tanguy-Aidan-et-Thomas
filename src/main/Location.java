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

    //Getter pour le nom
    public String getName() {
        return name;
    }

    //Getter pour la description
    public String getDescription() {
        return description;
    }

    //Indique si le lieu est découvert
    public boolean isDiscovered() {
        return discovered;
    }

    //Indique si le lieu est vérouillé
    public boolean isLocked() {
        return locked;
    }

    //Setter pour définir si le lieu est découvert
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    //Setter pour définir si le lieu est verrouillé
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    //Implémentation de la méthode de l'interface IPrintable
    //Retourne le nom du lieu à afficher sur la carte
    @Override
    public String getPrintableString() {
        return name;
    }

    //Indique si le lieu doit apparaître en grisé (verrouillé ou non découvert)
    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }

}
