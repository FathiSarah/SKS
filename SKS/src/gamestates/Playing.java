
package gamestates;

import entities.NPCs;
import entities.Player;
import entities.interactables.Key;
import entities.interactables.Knife;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import levels.LevelBase;
import levels.LevelFactory;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{
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

    private boolean gameOver = false;
    private long gameOverStart;
    private final long gameOverDuration = 3000;

    public Playing(Game game) {
        super(game);
        initClasses();
        startTimer();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(250 * Game.SCALE, 700 * Game.SCALE, (int)(128 * Game.SCALE), (int)(128 * Game.SCALE), levelManager, this);
        loadLevel(currentLevel);

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
        activeLevel.update(player);
        player.update();
        updateTimer();
        gameOver();
        allNPCsDead();

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
        }
        renderTimer(g);
        renderWalk(g);
        renderInteract(g);
        renderHide(g);
        renderAttack(g);
        renderFullscreen(g);
    }

    public void renderTimer(Graphics g) {
        g.setColor(Color.WHITE); // Set text color
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set font style and size
        g.drawString("Time: " + timeString, 10, 30); // Draw the timer at (10, 30)
    }

    public void renderWalk(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Walk: Q, D", 10, 70); 
    }

    public void renderInteract(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Interact: E", 10, 100); 
    }

    public void renderHide(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Hide/Stairs: R", 10, 130); 
    }

    public void renderAttack(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Attack: P", 10, 160); 
    }

    public void renderFullscreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Fullscreen: F", 10, 190); 
    }

    public void gameOver() {
        if (getPlayer().checkCollisionPlayerNPCs() && !getPlayer().isHidden()) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
            GameState.state = GameState.state.MENU;
        }
    }

    public void allNPCsDead() {
        if (getActiveLevel().getNPCs().isEmpty()) {
            GameState.state = GameState.state.MENU;
        }
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