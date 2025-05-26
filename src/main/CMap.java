package main;

import interfaces.ICommand;
import utils.Array2Dprinter;
import utils.IPrintable;

public class CMap implements ICommand {
private WorldMap worldMap;
    // private String verb;
    private String description;

    CMap(WorldMap worldMap)  {
        this.description = "Permet d'afficher la world map.";
         this.worldMap = worldMap;
    }

  



   @Override
   public void execute() {
   /*       int est = 3;
        int nord = 3;
        IPrintable[][] printableMap = new IPrintable[est][nord];

        for (int x = 0; x < est; x++) {
            for (int y = 0; y < nord; y++) {
                printableMap[x][y] = worldMap.getLocation(x, y);
            }
        }*/

        // surligne la position du player 
        String result = Array2Dprinter.print2DArray(worldMap.getworldMap());
        System.out.println(result);
    }






    @Override
    public String getDescription() {
        return this.description;
    }





    
}
