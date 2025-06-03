package main;

public class Main {

    /*
    +) Debug (tester le jeu et tous cas interdits)
    +) Commenter tout le code du main
    +) Améliorer la fluidité du texte de jeu autant que possible
    --> Rendu Rapport
    */

    public static void main(String[] args) {
        Game game = new Game();
        game.init();  // Save
        game.run();   // Lance le jeu
        game.closeScanner();  // Ferme le scanner à la fin
    }    

}
