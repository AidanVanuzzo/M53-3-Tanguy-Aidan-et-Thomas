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
        game.init();  // Gère le menu ici (load/new)
        game.run();   // Lance le jeu
        game.closeScanner();  // Ferme à la toute fin
    }    

}
