package main;

public class Main {

    /*
    Todo Dimanche
    1) Système d'objets (Ramasser/Inspecter) Ex : Récupérer un burger au bk pour le donner au crapaud de la rivière qui nous donne la clé du chateau
    2) Look : Modifier pour afficher les objets/énigmes de la zone courante
    3) Mécanisme pour dévérouiller les zones et si elles ont été visitée ou non (via le 2DPrinter si possible sinon en sout)
    4) Rendre le jeu plus guidé et jouable
    */

    public static void main(String[] args) {
        //Crée une instance de Game pour initialiser une nouvelle partie
        Game game = new Game();
        //Affiche les instructions et attend les commandes du joueur
        game.run();    
    }

}
