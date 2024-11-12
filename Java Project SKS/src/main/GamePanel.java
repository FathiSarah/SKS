package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	private Game game;
	public GamePanel(Game game) { // initializing game variable to use position below
		this.game = game;
		this.setDoubleBuffered(true);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); 

		Graphics2D g2 = (Graphics2D)g; // type casting and G2D is more powerful

		g2.fillRect(game.getPlayerX(), game.getPlayerY(), 200, 50);
		g2.dispose();
	}
}
