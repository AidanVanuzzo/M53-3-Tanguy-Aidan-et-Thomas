package main;

import interfaces.ICommand;
import utils.Array2Dprinter;
import utils.IPrintable;

public class CMap implements ICommand {

    //Carte du jeu
    private WorldMap worldMap;
    //Description de map
    private String description;

    //Constructeur qui reçoit la carte du monde
    public CMap(WorldMap worldMap) {
        this.description = "Displays the world map.";
        this.worldMap = worldMap;
    }

    //Méthode execute() pour la commande 'map' qui affiche la carte
    @Override
    public void execute() {
        //Récupère la map sous forme de tableau 2D d'objets
        IPrintable[][] printableMap = worldMap.getWorldMap();
        //Récupère la position actuelle du joueur
        int playerX = worldMap.getGame().getPlayerX();
        int playerY = worldMap.getGame().getPlayerY();
        //Utilise la classe Array2Dprinter pour afficher la carte avec position du joueur
        String result = Array2Dprinter.print2DArray(printableMap, playerY, playerX);
        //Affiche le résultat
        System.out.println(result);
    }

    //Getter pour récupérer la description de la commande 'map'
    @Override
    public String getDescription() {
        return this.description;
    }
}
