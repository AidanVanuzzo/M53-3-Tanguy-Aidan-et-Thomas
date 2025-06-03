package main;

public class Main {

    /*
    1) Debug (tester le jeu et tous cas interdits)
    2) Améliorer la fluidité du texte de jeu autant que possible
    3) Commenter tout le code du main
    4) Rendu Rapport
    */

    public static void main(String[] args) {
        Game game = new Game();
        game.init();  // Save
        game.run();   // Lance le jeu
        game.closeScanner();  // Ferme le scanner à la fin
    }    

}
