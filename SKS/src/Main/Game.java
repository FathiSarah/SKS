package Main;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    // Need to add the static variables for the size of the tiles, the scale and the size of the game window.

    /**
     * The constructor of the Game class.
     * It will initialize the classes needed for the game to run, create the game panel and window,
     * and start the game loop.
     */
    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    /**
     * Initialize the classes needed for the game to run. (level, player, etc)
     */
    private void initClasses() {
    }

    /**
     * Start the game loop by creating a new separate thread to run said game loop on.
     * That way the game loop run independently of the rest of the program.
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Update the game logic (player/player's hitbox position, animation sprites, etc).
     */
    public void update(){
    }

    /**
     * Render the game visuals.
     * @param g
     */
    public void render( Graphics g){

    }


    /**
     * The fixed time-step loop that will run the game.
     * It will update the game logic and render the game visuals at a set rate.
     */
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        //Only needed to print the UPS and FPS rate in the console
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF > 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            //Only needed to print the UPS and FPS rate in the console
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    //Will be called when the window loses focus, to stop the player from moving/pause the game or something similar
    public void WindowFocusLost() {
    }


}
