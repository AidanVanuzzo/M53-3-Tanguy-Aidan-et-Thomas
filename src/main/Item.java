package main;

// [01.06.2025] Classe représentant un objet du jeu (clé, item, etc.)
public class Item {

    private String name;
    private String description;
    private String useZone; // Optionnel : zone où l’objet peut être utilisé

    public Item(String name, String description, String useZone) {
        this.name = name;
        this.description = description;
        this.useZone = useZone;
    }

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
