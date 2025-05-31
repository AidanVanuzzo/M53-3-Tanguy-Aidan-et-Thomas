package main;

public class Main {

    /*

    Market : Tonton Eleganza --> Enigme --> Carte d'accès Burger King
    Burger King : Chris --> Enigme --> Wooper
    Cave : Wooper --> Crapeau énigme --> Clé Alberto
    Farm (Objet) : Enigme : Qui s'attaque au tyran sans pouvoirs magique sera tué (lettre de sang)
    River (objet) : Photo de Tonton Eleganza qu'il a perdu (distracteur)



    1) Coder la quête (Win Alberto 10 Sec)
    3) Debug (Move wrong command, Multiple 'map', Inventory...)
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
