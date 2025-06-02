package main;

public class Main {

    /*
    1) Castle : Coder dialogue Alberto (Tue après 10 Sec) --> Win : Ajouer 'orbe' à l'inventaire
    2) End-Game donner orbe à Maman + Text & Crédits
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
