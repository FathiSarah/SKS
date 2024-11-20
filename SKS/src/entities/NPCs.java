package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import levels.LevelManager;
import main.Game;
import static utils.Constants.EnemyConstants.*;
import utils.LoadSave;
import static utils.Methods.isEntityOnFloor;

/**
 * Child class of Entity, used to create NPCs in the game.
 */
public class NPCs extends Entity {

    protected enum NPC_state {
        IDLE,
        PATROL,
        CHASE,
    }

    protected enum NPC_type {
        COMBATIVE,
        FEARFUL
    }

    protected enum Direction {
        RIGHT,
        LEFT
    }

    protected Player player;

    protected NPC_state currentState;
    protected NPC_type nPC_type;
    protected Direction currentDirection;

    protected int nextAction = 1500;
    private String name;

    protected long startTime = System.nanoTime();
    protected long elapsedTime = 0;

    // Variables for animations and character rendering
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 30;
    private int NPCAction = WALK;
    private float xOffset = 8 * Game.SCALE;
    private float yOffset = 6 * Game.SCALE;
    public static final float NPC_SCALE = 1.8f;

    protected LevelManager levelManager;

    /**
     * Constructor for NPCs.
     * @param x x-coordinate of the NPC.
     * @param y y-coordinate of the NPC.
     * @param width Width of the NPC.
     * @param height Height of the NPC.
     * @param levelManager LevelManager object.
     */
    public NPCs(float x, float y, int width, int height, String name, LevelManager levelManager) {
        super(x, y, width, height);
        this.speed = 2;
        this.currentState = NPC_state.IDLE;
        this.currentDirection = Direction.RIGHT;
        this.levelManager = levelManager;
        this.name = name;
        loadAnimations();

    }

    /**
     * Method that updates everything related to the NPC.
     */
    public void update() {
        updateAnimationTick();
        updateMove();
    }

    protected void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }

        this.NPCAction = IDLE;
    }

    protected void newState(int newState) {
        this.NPCAction = newState;
        aniIndex = 0;
        aniTick = 0;
    }

    public void switchDirection() {
        if (currentDirection == Direction.RIGHT) {
            currentDirection = Direction.LEFT;
        }
        else if (currentDirection == Direction.LEFT) {
            currentDirection = Direction.RIGHT;
        }
    }

    /**
     * Method that renders the NPC on the screen.
     * @param g Graphics object.
     */
    public void render(Graphics g) {
        g.drawImage(animations[NPCAction][aniIndex], (int) (hitBox.x - xOffset), (int) (hitBox.y - yOffset), (int) width, (int) height, null);
        drawHitBox(g);
    }

    /**
     * Method that loads a set of animations for the NPC depending on the given name.
     */
    private void loadAnimations() {
        BufferedImage image = null;

        if(name.equals("NPC1")) {
            image = LoadSave.loadImage(LoadSave.ENEMY1_ATLAS);
        }
        if(name.equals("NPC2")) {
            image = LoadSave.loadImage(LoadSave.ENEMY2_ATLAS);
        }
        if(name.equals("NPC3")) {
            image = LoadSave.loadImage(LoadSave.ENEMY3_ATLAS);
        }




        animations = new BufferedImage[4][8];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j * 32, i * 34, 32, 34);
            }
        }
    }

    /**
     * Method that handles the rotation of sprites, depending on the animation and the number of sprites this animation have.
     */
    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(NPCAction)){
                aniIndex = 0;
            }
        }
    }

    public int getNPCAction() {
        return NPCAction;
    }
}