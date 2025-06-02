package main;

public class Main {

    /*
    1) End-Game donner orbe à Maman + Text final & Crédits --> Quit
    2) Cteleport ('teleport <lieu>' (sauf si locked) quand on a l'objet crystal la commande s'ajoute dans help)
    3) CSave (sauvegarde la progression du joueur) + Permet de rejouer ou recharger la partie précédente (exécute toutes les commandes à la suite)
    4) Debug (retester les cas interdits) + Commenter le code + Améliorer la fluidité du texte autant que possible
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
