
package Gamestates;

import levels.LevelFactory;
import main.Game;
import entities.NPCs;
import entities.Player;
import entities.interactables.Key;
import entities.interactables.Knife;
import levels.LevelBase;
import levels.LevelManager;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Playing extends State implements Statemethods{
    private Player player;
    private Key key;
    private Knife knife;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private NPCs npc, npc2, npc3;
    private boolean paused = false;

    private LevelBase activeLevel;
    private int currentLevel = 1;

    private long startTime;  // To store when the timer started
    private int elapsedTime; // Time in seconds
    int minutes = elapsedTime / 60;
    int seconds = elapsedTime % 60;
    String timeString = String.format("%02d:%02d", minutes, seconds);

    public Playing(Game game) {
        super(game);
        initClasses();
        startTimer();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(250 * Game.SCALE, 700 * Game.SCALE, (int)(128 * Game.SCALE), (int)(128 * Game.SCALE), levelManager, this);
        loadLevel(currentLevel);
        pauseOverlay = new PauseOverlay(this);

    }
    public void startTimer() {
        startTime = System.currentTimeMillis(); // Record the current time in milliseconds
    }


    public void loadLevel(int LevelNumber) {
        activeLevel = LevelFactory.createLevel(LevelNumber);
        activeLevel.initialize(player, levelManager);
    }
    //Will be called when the window loses focus, to stop the player from moving/pause the game or something similar
//    public void WindowFocusLost() {
//    }

    /**
     * Get the player object.
     * @return
     */
    public Player getPlayer(){
        return player;
        }

    public LevelBase getActiveLevel() {
        return activeLevel;
    }

    @Override
    public void update() {
        if(!paused) {
            activeLevel.update(player);
            player.update();

        }else {
            updateTimer();
            pauseOverlay.update();
        }
    }

    public void updateTimer() {
        long currentTime = System.currentTimeMillis(); // Current time in milliseconds
        elapsedTime = (int) ((currentTime - startTime) / 1000); // Elapsed time in seconds

        // Update the time string dynamically
        int minutes = elapsedTime / 60;
        int seconds = elapsedTime % 60;
        timeString = String.format("%02d:%02d", minutes, seconds);
    }


    @Override
    public void draw(Graphics g) {
        activeLevel.render(g);
        if(!getPlayer().isHidden()){
            player.render(g);

            if(paused)
                pauseOverlay.draw(g);
        }
        renderTimer(g);

    }

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
        if (paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused)
            pauseOverlay.mousePressed(e);


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseMoved(e);
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
        if (e.getKeyCode() ==  KeyEvent.VK_ESCAPE) {
            paused = !paused;

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

    public void unpauseGame(){
        paused = false;
    }

    public Playing getInstance(){
        return this;
    }
}


