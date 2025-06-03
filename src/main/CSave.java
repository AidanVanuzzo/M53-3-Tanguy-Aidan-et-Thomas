package main;

import interfaces.ICommand;

public class CSave implements ICommand {

    private Game game;

    public CSave(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.saveState();
    }

    @Override
    public String getDescription() {
        return "Save your progress";
    }
}
