package main;

public class Main {

    /*
    1) Commenter le code du main
    2) Rapport --> Rendu Cyberlearn
    */

    public static void main(String[] args) {
        Game game = new Game();
        game.init();  // Save
        game.run();   // Lance le jeu
        game.closeScanner();  // Ferme le scanner à la fin
    }    

}
