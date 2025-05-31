package main;

public class Main {

    /*
    1) Mécanisme pour dévérouiller 3 zones
    2) Système d'objets, Clé, Énigmes (Ramasser/Inspecter)
    3) CLook : Modifier pour afficher les objets/énigmes de la zone courante
    --
    4) Scénario Victoire (kill Alberto)
    5) Debug (Move wromg command, Multiple 'map', etc.)
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
