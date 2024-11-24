package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import entities.interactables.Items;
import levels.LevelManager;
import main.Game;
import static utils.Constants.EnemyConstants.*;
import static utils.Methods.*;
import static utils.Methods.canMoveHere;

import utils.LoadSave;

/**
 * Child class of Entity, used to create NPCs in the game.
 */
public class NPCs extends Entity {

    protected enum NPC_state {
        IDLE,
        PATROL,
        CHASE,
    }

    public enum Direction {
        RIGHT,
        LEFT
    }

    protected Player player;

    protected NPC_state currentState = NPC_state.PATROL;
    public Direction currentDirection;

    private String name;

    protected long startTime = System.nanoTime();
    protected long elapsedTime = 0;

    private static float FIELD_OF_VIEW =  100 * Game.SCALE;

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

    private Items dropItem;

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
        if(!this.isAlive && dropItem != null){
            dropItem.setPickedUp(false);
            dropItem.setX(hitBox.x);
            dropItem.setY(hitBox.y);
        }
        directionSprites();
    }

    /**
     * Method that updates the movement of the NPC.
     * If the NPC is not on the floor, it will keep falling until it reaches the floor.
     * The NPC is set in Idle by default
     */
    protected void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }
        currentState = NPC_state.IDLE;
        NPCAction = NPC_IDLE;
    }

    /**
     * Method that updates the NPC's state.
     * @param newState New state of the NPC.
     */
    protected void newState(int newState) {
        this.NPCAction = newState;
        aniIndex = 0;
        aniTick = 0;
    }

    /**
     * Method that switches the direction the NPC is walking to.
     */
    public void switchDirection() {
        if (currentDirection == Direction.RIGHT) {
            currentDirection = Direction.LEFT;
        }
        else if (currentDirection == Direction.LEFT) {
            currentDirection = Direction.RIGHT;
        }
    }



    /**
     * Method that checks if the player is in the NPC's field of view.
     * @return True if the player is in the NPC's field of view, false otherwise.
     */
    public boolean inSight() {
        float playerX = levelManager.getGame().getPlaying().getPlayer().getHitBox().x;


        if (isOnSameLevel(hitBox, levelManager.getGame().getPlaying().getPlayer().getHitBox()) && !levelManager.getGame().getPlaying().getPlayer().isHidden()) {
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
     * Method that makes the NPC chase the player.
     */
    protected void chasePlayer() {
        var playerHitBox = levelManager.getGame().getPlaying().getPlayer().getHitBox();

        // Determine movement direction based on player's position
        if (playerHitBox.x < hitBox.x) {
            if (canMoveHere(hitBox.x - speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                System.out.println("detected");
                hitBox.x -= speed * 0.2;
                currentDirection = Direction.LEFT;
            }

        } else {
            if (canMoveHere(hitBox.x + speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                hitBox.x += speed * 0.2;
                currentDirection = Direction.RIGHT;
            }
        }
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

    /**
     * Method that changes the direction of the NPC's sprites.
     */
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


    public NPC_state getCurrentState() {
        return currentState;
    }
    public void setCurrentState(NPC_state currentState) {
        this.currentState = currentState;
    }
    public String getName() {
        return name;
    }
    public Items getDropItem() {
        return dropItem;
    }
    public void setFieldOfView(float fieldOfView) {
        FIELD_OF_VIEW = fieldOfView;
    }
}