package main;

public class Item {

    //Les Items comprennent : clés, PNJ et distracteurs (inutiles, mais on peut les inspecter)
    
    //En fonction du besoin, description et useZone (position de l'objet sur la carte) peuvent être null
    private String name;
    private String description;
    private String useZone;

    //Constructeur
    public Item(String name, String description, String useZone) {
        this.name = name;
        this.description = description;
        this.useZone = useZone;
    }

    //Getters pour les trois attributs
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUseZone() {
        return useZone;
    }
}
