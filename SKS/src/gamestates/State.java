package gamestates;

import java.awt.event.MouseEvent;
import main.Game;
import ui.MenuButton;

/**
 * State class is a superclass for all the game states.
 */
public class State {
    protected Game game;

    /**
     * Constructor for State class.
     * @param game Game object
     */
    public State(Game game) {
        this.game = game;
    }


    /**
     * Checks if the mouse is in the rectangle.
     * @param e MouseEvent object
     * @param mb MenuButton object
     * @return true if mouse is in the rectangle, false if it isn't
     */
    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }
}
