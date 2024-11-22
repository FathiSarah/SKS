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
        mouseInputs = new MouseInputs(GamePanel.this);
        this.game = game;

        setPanelSize(getWidth(), getHeight());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Set the size of the panel.
     */
    public void setPanelSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calculate scale factors
        float scaleX = (float) getWidth() / Game.GAME_WIDTH;
        float scaleY = (float) getHeight() / Game.GAME_HEIGHT;

        // Cast Graphics to Graphics2D for scaling
        Graphics2D g2d = (Graphics2D) g;

        // Apply scaling
        g2d.scale(scaleX, scaleY);

        // Render the game
        game.render(g2d);

        // Reset scaling to avoid affecting other operations
        g2d.scale(1 / scaleX, 1 / scaleY);
    }

    /**
     * Get the game object.
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }
}
