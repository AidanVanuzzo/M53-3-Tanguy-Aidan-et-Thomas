package main;

public class Main {

    /*
    Todo Dimanche
    1) Système d'objets (Ramasser/Inspecter)
    2) Look : Modifier pour afficher les objets/énigmes de la zone
    3) Mécanisme pour dévérouiller les zones
    4) Rendre le jeu plus guidé et jouable
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
