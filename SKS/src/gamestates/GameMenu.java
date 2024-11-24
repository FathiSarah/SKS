package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import ui.MenuButton;
import utils.LoadSave;

/**
 * GameMenu class is a subclass of State and implements StateMethods interface.
 * This class is responsible for displaying the main menu of the game.
 */
public class GameMenu extends State implements StateMethods {
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    private Clip menuMusic;

    /**
     * Constructor for GameMenu class.
     * @param game Game object
     */
    public GameMenu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        stopMusic();
        playMusic();
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
                    mb.applyGameState();
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

    public void playMusic() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./res/Free-Dramatic-Dramatic-Game-or-Menu-Music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            menuMusic = AudioSystem.getClip();
            menuMusic.open(audioStream);
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
    public void stopMusic() {
        if (menuMusic != null && menuMusic.isRunning()) {
            if (GameState.state != GameState.MENU) {
                menuMusic.stop();
            }

        }
    }
}
