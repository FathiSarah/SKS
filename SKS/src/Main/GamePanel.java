package Main;

import Inputs.MouseInputs;
import Inputs.KeyBoardInputs;

import javax.swing.JPanel;
import java.awt.*;

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
        addKeyListener(new KeyBoardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Set the size of the panel.
     */
    public void setPanelSize() {
        setPreferredSize(new Dimension(1280, 720));

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
