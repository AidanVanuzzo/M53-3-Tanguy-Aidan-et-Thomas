package main;

public class Main {

    /*
    1) Castle : Coder dialogue Alberto (Tue après 10 Sec --> Quit) --> Win : 'orbe' ajouté à l'inventaire
    2) End-Game donner orbe à Maman + Text final & Crédits --> Quit
    3) Cteleport ('teleport <lieu>' (sauf si locked) quand on a l'objet crystal la commande s'ajoute dans help)
    4) CSave (sauvegarde la progression du joueur) + Permet de rejouer ou recharger la partie précédente (exécute toutes les commandes à la suite)
    5) Debug (tester les cas interdits) + Commenter le code + Améliorer la fluidité du texte autant que possible
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
