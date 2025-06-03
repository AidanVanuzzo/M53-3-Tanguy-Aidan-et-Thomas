package main;

import java.util.Scanner;

public class Main {

    /*
    +) Debug (tester le jeu et tous cas interdits)
    +) Commenter tout le code du main
    +) Améliorer la fluidité du texte de jeu autant que possible
    */

    public static void main(String[] args) {
    Game game = new Game();
    Scanner scanner = new Scanner(System.in);

    System.out.print("\n\nDo you want to load the last save or start a new game? (load/new): \n\n");
    String choice = scanner.nextLine().trim().toLowerCase();
    
    //ici

    if (choice.equals("load")) {
        game.setLoadRequested(true);
    }

    game.run();
    }

}
