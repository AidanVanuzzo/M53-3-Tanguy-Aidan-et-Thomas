package main;

public class Main {

    /*
    1) Cave : Wooper --> Crapeau énigme --> Donne Clé Alberto
    2) Castle : Coder la quête finale (Alberto tue après 15 Sec)
    +) Commentaires + Debug
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
