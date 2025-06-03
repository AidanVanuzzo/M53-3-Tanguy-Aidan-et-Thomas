package main;

import java.util.Scanner;

public class Main {

    /*
    1) CSave (sauvegarde la progression du joueur) + Permet de rejouer ou recharger la partie précédente (exécute toutes les commandes à la suite)
    +) Debug (tester les cas interdits)
    +) Commenter le code
    +) Améliorer la fluidité du texte autant que possible
    */

    public static void main(String[] args) {
    Game game = new Game();
    Scanner scanner = new Scanner(System.in);

    System.out.print("Do you want to load the last save or start a new game? (load/new): ");
    String choice = scanner.nextLine().trim().toLowerCase();

    if (choice.equals("load")) {
        game.setLoadRequested(true);
    }

    game.run(); // la logique continue dans Game.java
}


}
