package main;

public class Item {

    private String name;
    private String description;
    private String useZone;

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
