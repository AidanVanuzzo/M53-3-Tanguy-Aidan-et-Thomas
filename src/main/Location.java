package main;

public class Location {

<<<<<<< HEAD
private String name;
private String description;
private boolean locked;
private boolean discovered;

public Location (String name, String description) {
    this.name = name;
    this.description = description;
    this.locked = false;
    this.discovered = false;
}


  public String getName() {
        return name;
    }

=======
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
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59

    public String getDescription() {
        return description;
    }

<<<<<<< HEAD

=======
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59
    public boolean isDiscovered() {
        return discovered;
    }

<<<<<<< HEAD

=======
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59
    public boolean isLocked() {
        return locked;
    }

<<<<<<< HEAD

=======
>>>>>>> f29a576bfb48c367b6e5e877e51f4d948b992f59
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
