package main;

import interfaces.ICommand;
import utils.Array2Dprinter;
import utils.IPrintable;

public class CMap implements ICommand {

    private WorldMap worldMap;
    private String description;

    public CMap(WorldMap worldMap) {
        this.description = "Permet d'afficher la world map.";
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {
        // Récupère la map sous forme d'IPrintable[][]
        IPrintable[][] printableMap = worldMap.getWorldMap();

        // Récupère la position du joueur via la référence Game
        int playerX = worldMap.getGame().getPlayerX();
        int playerY = worldMap.getGame().getPlayerY();

        // Affiche la map en surlignant la position du joueur
        String result = Array2Dprinter.print2DArray(printableMap, playerY, playerX);

        System.out.println(result);
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
