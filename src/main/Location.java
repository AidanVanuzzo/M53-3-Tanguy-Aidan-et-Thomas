package main;

import utils.IPrintable;

public class Location implements IPrintable { 

    private String name;
    private String description;
    private boolean locked;
    private boolean discovered;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.locked = false;
        this.discovered = false;
    }

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

    @Override
    public String getPrintableString() {
        return name;
    }
    
    @Override
    public boolean isGrayedOut() {
        return locked || !discovered;
    }
    

}
