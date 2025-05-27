package main;

import interfaces.ICommand;
import utils.Array2Dprinter;
import utils.IPrintable;

public class CMap implements ICommand {

    private WorldMap worldMap;
    private String description;

    public CMap(WorldMap worldMap) {
        this.description = "Displays the world map.";
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {

        IPrintable[][] printableMap = worldMap.getWorldMap();

        int playerX = worldMap.getGame().getPlayerX();
        int playerY = worldMap.getGame().getPlayerY();

        String result = Array2Dprinter.print2DArray(printableMap, playerY, playerX);

        System.out.println(result);
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
