package main;

import javax.swing.JFrame;

public class GameWindow {
private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();

		jframe.setSize(800,600);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setTitle("SKS Game");
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null); // window in middle of screen


		jframe.setVisible(true);
	}
}
