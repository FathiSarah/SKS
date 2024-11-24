package Gamestates;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 * GameMenu class is a subclass of State and implements StateMethods interface.
 * This class is responsible for displaying the main menu of the game.
 */
public class GameMenu extends State implements StateMethods {
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    /**
     * Constructor for GameMenu class.
     * @param game Game object
     */
    public GameMenu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    /**
     * Loads the background image for the main menu.
     */
    private void loadBackground() {
        backgroundImg = LoadSave.loadImage(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);

    }

    /**
     * Loads the buttons for the main menu.
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (430 * Game.SCALE), 2, GameState.QUIT);

    }



    @Override
    public void update() {
        for(MenuButton mb : buttons) {
            mb.update();
        }

    }

    //public void draw(Graphics g, String level){
       // BufferedImage lvlToDraw = loadImage(level);

       // g.drawImage(lvlToDraw, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);


    /**
     * Draws the main menu.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        for(MenuButton mb : buttons) {
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons) {
            if(isIn(e,mb)){
                if(mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
        }
        resetButtons();



    }
    private void resetButtons(){
        for(MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons) {
            if(isIn(e,mb)){
                mb.setMousePressed(true);
            }
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons)
            mb.setMouseOver(false);


        for (MenuButton mb : buttons)
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
        }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("ENTER");
            GameState.state = GameState.PLAYING;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
