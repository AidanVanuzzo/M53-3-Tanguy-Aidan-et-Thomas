package main;

public class Main {

    /*
    1) Coder la quête (jusqu'au Boss) et Cleen
    --
    2) Scénario Victoire (kill Alberto qui vous attaque (10 sec pour se défendre, sinon 'quit'))
    3) Debug (Move wrong command, Multiple 'map', Inventory, etc.)
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
