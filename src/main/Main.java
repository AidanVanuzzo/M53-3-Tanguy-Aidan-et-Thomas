package main;

public class Main {

    /*
    1) Coder la quête (Win Alberto 10 Sec)
    3) Debug (Move wrong command, Multiple 'map', Inventory...)
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
