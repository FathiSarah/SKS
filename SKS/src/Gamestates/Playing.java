
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


public class Playing extends State implements Statemethods{
    private Player player;
    private Key key;
    private Knife knife;
    private LevelManager levelManager;
    private NPCs npc, npc2, npc3;

    private LevelBase activeLevel;
    private int currentLevel = 1;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(250 * Game.SCALE, 700 * Game.SCALE, (int)(128 * Game.SCALE), (int)(128 * Game.SCALE), levelManager, this);
        loadLevel(currentLevel);

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

    }

    @Override
    public void draw(Graphics g) {
        activeLevel.render(g);
        player.render(g);


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


