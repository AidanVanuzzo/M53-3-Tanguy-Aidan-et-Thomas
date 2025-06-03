package main;

import interfaces.ICommand;

public class CSave implements ICommand {

    //Référence à l'instance actuelle du jeu
    private Game game;

    //Constructeur
    public CSave(Game game) {
        this.game = game;
    }

    //Exécution de la commande 'save' qui s'exécute depuis Game.java
    @Override
    public void execute() {
        game.saveState();
    }

    //Getter pour la description
    @Override
    public String getDescription() {
        return "Save your progress";
    }
}
