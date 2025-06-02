package main;

public class Main {

    /*
    0) Bug Push WorldMap.java
    1) Castle : Coder dialogue Alberto (Tue après 10 Sec --> Quit) --> Win : 'orbe' ajouté à l'inventaire
    2) End-Game donner orbe à Maman + Text final & Crédits --> Quit
    3) Devoir 6
    4) Commentaires + Debug
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
