package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.*;
import javax.swing.JPanel;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 * This class creates a JPanel that we'll use to display our game.
 * It sets the size of the panel and enables keyboard and mouse inputs.
 * It is also the class that will render the game visuals.
 */
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    /**
     * Constructor for the GamePanel class.
     * @param game The game object that will be rendered on the panel.
     */
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs();
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(GamePanel.this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Set the size of the panel.
     */
    public void setPanelSize() {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    /**
     * Get the game object.
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }
}
