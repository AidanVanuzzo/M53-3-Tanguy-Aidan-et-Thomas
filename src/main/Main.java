package main;

public class Main {

    /*
    1) CSave (sauvegarde la progression du joueur) + Permet de rejouer ou recharger la partie précédente (exécute toutes les commandes à la suite)
    +) Debug (retester les cas interdits) + Commenter le code + Améliorer la fluidité du texte autant que possible
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
