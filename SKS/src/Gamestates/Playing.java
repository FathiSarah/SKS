
package Gamestates;

import levels.LevelFactory;
import main.Game;
import entities.NPCs;
import entities.Player;
import entities.interactables.Key;
import entities.interactables.Knife;
import levels.LevelBase;
import levels.LevelManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Playing class is a subclass of State and implements StateMethods interface.
 * This class is responsible for handling the game state when the player is playing the game.
 */
public class Playing extends State implements StateMethods {
    private Player player;
    private Key key;
    private Knife knife;
    private LevelManager levelManager;
    private NPCs npc, npc2, npc3;

    private LevelBase activeLevel;
    private int currentLevel = 1;

    private long startTime;  // To store when the timer started
    private int elapsedTime; // Time in seconds
    int minutes = elapsedTime / 60;
    int seconds = elapsedTime % 60;
    String timeString = String.format("%02d:%02d", minutes, seconds);

    /**
     * Constructor for Playing class.
     * @param game Game object
     */
    public Playing(Game game) {
        super(game);
        initClasses();
        startTimer();
    }

    /**
     * Initializes the classes required for the Playing state.
     */
    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(250 * Game.SCALE, 650 * Game.SCALE, (int)(128 * Game.SCALE), (int)(128 * Game.SCALE), levelManager, this);
        loadLevel(currentLevel);
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        startTime = System.currentTimeMillis(); // Record the current time in milliseconds
    }


    /**
     * Loads the level based on the level number.
     * @param LevelNumber Level number
     */
    public void loadLevel(int LevelNumber) {
        activeLevel = LevelFactory.createLevel(LevelNumber);
        activeLevel.initialize(player, levelManager);
    }


    public Player getPlayer(){
        return player;
        }

    public LevelBase getActiveLevel() {
        return activeLevel;
    }

    /**
     * Updates the game state.
     */
    @Override
    public void update() {
        activeLevel.update(player);
        player.update();
        updateTimer();

    }

    /**
     * Updates the timer.
     */
    public void updateTimer() {
        long currentTime = System.currentTimeMillis(); // Current time in milliseconds
        elapsedTime = (int) ((currentTime - startTime) / 1000); // Elapsed time in seconds

        // Update the time string dynamically
        int minutes = elapsedTime / 60;
        int seconds = elapsedTime % 60;
        timeString = String.format("%02d:%02d", minutes, seconds);
    }


    /**
     * Draws the game state.
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g) {
        activeLevel.render(g);
        if(!getPlayer().isHidden()){
            player.render(g);
        }
        renderTimer(g);

    }

    /**
     * Renders the timer.
     * @param g Graphics object
     */
    public void renderTimer(Graphics g) {
        g.setColor(Color.WHITE); // Set text color
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set font style and size
        g.drawString("Time: " + timeString, 10, 30); // Draw the timer at (10, 30)
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            player.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            player.setAttacking(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            player.setJumping(true);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_SHIFT) {
            player.setRunning(true);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q){
            player.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            player.setAttacking(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            player.setJumping(false);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_SHIFT) {
            player.setRunning(false);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_E) {
            player.setEquip(false);
        }


    }

    public Playing getInstance(){
        return this;
    }
}


