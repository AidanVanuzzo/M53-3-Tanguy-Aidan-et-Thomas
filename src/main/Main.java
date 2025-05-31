package main;

public class Main {

    /*
    1) Mécanisme pour dévérouiller 3 zones --> CSay : take <nom_de_l’objet>
    2) CInventaire : Clés, Énigmes ('Inspect'/'Use'). Random chagement énigmes
    3) CLook : Modifier pour afficher les objets/énigmes de la zone courante
    --
    4) Scénario Victoire (kill Alberto qui vous attaque (10 sec pour se défendre, sinon 'quit'))
    5) Debug (Move wrong command, Multiple 'map', etc.)
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
