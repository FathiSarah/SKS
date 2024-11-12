package main;

import inputs.KeyboardInputs;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private KeyboardInputs keyboardInputs;

	private Thread gameThread;
	// Player player = new Player(this.keyboardInputs);

	private final int FPS = 60;

	private int playerX = 100, playerY = 100;
	private int playerSpeed = 4;

	public Game() {
		gamePanel = new GamePanel(this); // give access to Game object
		gameWindow = new GameWindow(gamePanel);
		keyboardInputs = new KeyboardInputs(gamePanel);
		gamePanel.addKeyListener(keyboardInputs);
		gamePanel.requestFocus(); // makes user input heard by the game in priority
		startGameThread();
	}

	public void startGameThread() { // allows the game to be responsive

		gameThread = new Thread(this); // this = GamePanel (class)
		gameThread.start();
	}

	// @Override
	// public void run() { // update info, draw updated info

	// 	double timePerFrame = 1000000000 / FPS; // 1 second
	// 	double nextDrawTime = System.nanoTime() + timePerFrame;
	// 	while (gameThread != null) {
	// 		long currentTime = System.nanoTime();
	// 		update();
	// 		gamePanel.repaint();
			
	// 		try {
	// 			double remainingTime = nextDrawTime - System.nanoTime();
	// 			remainingTime = remainingTime/1000000; // converted to millis

	// 			if (remainingTime < 0) {
	// 				remainingTime = 0;
	// 			}
	// 			Thread.sleep((long) remainingTime);

	// 			nextDrawTime += timePerFrame; // important to control game

	// 		} catch (InterruptedException e) {
	// 			// TODO Auto-generated catch block
	// 			e.printStackTrace();
	// 		}
		// }
	// }

	@Override
	public void run() {
		double timePerFrame = 1000000000 / FPS; // 1 second
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / timePerFrame;

			lastTime = currentTime;

			if(delta >= 1) {
				update();
				gamePanel.repaint();
				delta--;
			}
		}
	}


	public void update(){
		if (keyboardInputs.isUpPressed()) {
			playerY -= playerSpeed;
		}
		if (keyboardInputs.isDownPressed()) {
			playerY += playerSpeed;
		}
		if (keyboardInputs.isLeftPressed()) {
			playerX -= playerSpeed;
		}
		if (keyboardInputs.isRightPressed()) {
			playerX += playerSpeed;
		}
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
}
