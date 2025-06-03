package main;

public class Main {

    /***(WorldMap > Inventory > Game > Say)***/

    public static void main(String[] args) {
        //Création d'une nouvelle partie
        Game game = new Game();
        //Gestion de la sauvegarde (new ou load)
        game.init();
        //Lancement du jeu
        game.run();
        //Fermeture des scanners
        game.closeScanner();
    }    

}
