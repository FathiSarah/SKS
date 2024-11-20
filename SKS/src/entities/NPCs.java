package entities;

import levels.Level;
import levels.LevelManager;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static utils.Constants.EnemyConstants.*;

import static utils.Methods.isEntityOnFloor;
import static utils.Methods.isOnSameLevel;

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

    protected NPC_state currentState = NPC_state.PATROL;
    protected NPC_type nPC_type;
    protected Direction currentDirection;

    protected int nextAction = 1500;
    private String name;

    protected long startTime = System.nanoTime();
    protected long elapsedTime = 0;

    private static final float FIELD_OF_VIEW =  100 * Game.SCALE;

    // Variables for animations and character rendering
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 30;
    private int NPCAction = NPC_IDLE;
    private float xOffset = 8 * Game.SCALE;
    private float yOffset = 6 * Game.SCALE;
    public static final float NPC_SCALE = 1.8f;

    private int flipX = 0;
    private int flipW = 1;

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
        if(inSight()){
            System.out.println("Player is in sight");
        };
        directionSprites();
    }

    protected void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }

        currentState = NPC_state.IDLE;
        NPCAction = NPC_IDLE;
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

    public void directionSprites() {
        if (currentDirection == Direction.RIGHT) {
            flipX = 0;
            flipW = 1;
        }
        else if (currentDirection == Direction.LEFT) {
            flipX = (int)width;
            flipW = -1;
        }
    }

    public boolean inSight() {
        float playerX = levelManager.getGame().getPlayer().getHitBox().x;


        if (isOnSameLevel(hitBox, levelManager.getGame().getPlayer().getHitBox()) && !levelManager.getGame().getPlayer().isHidden()) {
            if (currentDirection == Direction.LEFT) {
                if (playerX >= hitBox.x - FIELD_OF_VIEW && playerX <= hitBox.x) {
                    System.out.println("Player is in sight (LEFT)");
                    return true;
                }
            } else if (currentDirection == Direction.RIGHT) {
                if (playerX >= hitBox.x && playerX <= hitBox.x + FIELD_OF_VIEW) {
                    System.out.println("Player is in sight (RIGHT)");
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Method that renders the NPC on the screen.
     * @param g Graphics object.
     */
    public void render(Graphics g) {
        g.drawImage(animations[NPCAction][aniIndex], (int) (hitBox.x - xOffset) + flipX, (int) (hitBox.y - yOffset), (int) width * flipW, (int) height, null);
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

    public void detectPlayer() {
        if (Math.abs(this.x - player.getX()) < 100) {
            currentState = NPC_state.CHASE;
        }
    }

    public boolean seesPlayer() {
        if (player.getX() > x && player.getX() < x + width) {

            return true;
        }
        return false;
    }

    public int getNPCAction() {
        return NPCAction;
    }

    public NPC_state getCurrentState() {
        return currentState;
    }

    public void setCurrentState(NPC_state currentState) {
        this.currentState = currentState;
    }

    public void setFlipX(int flipX) {
        this.flipX = flipX;
    }

    public void setFlipW(int flipW) {
        this.flipW = flipW;
    }

    public String getName() {
        return name;
    }
}