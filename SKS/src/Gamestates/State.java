package Gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;
    public State(Game game) {
        this.game = game;

    }
//true if mouse in rectangle, false if isn't
    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }
}
