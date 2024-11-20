package entities;

import Gamestates.Playing;
import entities.interactables.Key;
import entities.interactables.Knife;
import entities.interactables.Weapons;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import levels.*;
import main.Game;
import static utils.Constants.PlayerConstants.*;
import utils.LoadSave;
import static utils.Methods.*;

/**
 * Child class of Entity, used to create the player in the game.
 */
public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE_1;
    private boolean moving = false, attacking = false, jumping = false, running = false, crouching = false, action = false;
    private boolean left, right, up, down;
    private boolean equip = false;
    private float playerSpeed = 2.0f;
    private float xOffset = 48 * Game.SCALE;
    private float yOffset = 64 * Game.SCALE;
    private LevelBase currentlevel = new LevelOne();

    private LevelManager levelManager;
    private Playing playing;
  
    private boolean weaponInInventory = false;

    private Key key;
    private Knife knife;
    private Weapons currentWeapon;
    private NPCs npc;
    private Rectangle attackHitBox;
    private boolean attackHitBoxStatus = false;
    private LevelOne levelOne;

    private int flipX = 0;
    private int flipW = 1;

    /**
     * Constructor for Player.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param levelManager
     */
    public Player(float x, float y, int width, int height, LevelManager levelManager, Playing playing) {
        super(x, y, width, height);
        this.levelManager = levelManager;
        this.playing = playing;
        loadAnimations();
        initHitBox(x, y, 35 * Game.SCALE, 64 * Game.SCALE);
    }

    /**
     * Method that updates everything related to the player (it's position, animation, etc.)
     */
    public void update() {
        updatePos();
        setAnimation();
        updateAnimationTick();
        manageKeyPickup();

        takeStairs(playing.getActiveLevel());
       
        hitboxLR();
        if (attacking) {
            performAttack();
            killNPC();
        }

    }

    public void update(Knife knife) {
        this.knife = knife;
    }

    public void update(Key key) {
        this.key = key;
    }

    /**
     * Method that renders the player on the screen.
     * @param g
     */
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)(hitBox.x - xOffset) + flipX, (int)(hitBox.y - yOffset), (int)width * flipW, (int)height, null);
        drawHitBox(g);
        if (attackHitBox != null && attackHitBoxStatus == true) {
            g.setColor(Color.GREEN);
            g.drawRect(attackHitBox.x, attackHitBox.y, attackHitBox.width, attackHitBox.height);
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
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }

    /**
     * Method that sets the animation of the player depending on the player's actions.
     */
    private void setAnimation(){
        int startAni = playerAction;

        if(moving){
            playerAction = WALK;
        } else {
            playerAction = IDLE_1;
        }

        if(attacking){
            playerAction = ATTACK_2;
        }

        if(jumping){
            playerAction = JUMP;
        }

        if (running) {
            playerAction = RUN;
            playerSpeed = 4.0f;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }




    }

    /**
     * Method that resets the animation tick and index. Used when the player changes animation.
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Method that updates the player's position.
     */
    private void updatePos() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }
        moving = false;

        if(!left && !right && !up && !down) {
            return ;
        }

        float xSpeed = 0, ySpeed = 0;

        if(left && !right){
            xSpeed -= playerSpeed;
            flipX = (int)width;
            flipW = -1;
        }
        if (!left && right) {
            xSpeed += playerSpeed;

            flipX = 0;
            flipW = 1;
        } 
        if (up && !down) { ySpeed -= playerSpeed; }
        if (!up && down) { ySpeed += playerSpeed; }


        BufferedImage collisionMap = levelManager.getCollisionMap();
//        if (canMoveHere(x + xSpeed, y + ySpeed, width, height, collisionMap)) {
//            this.x =  x + xSpeed;
//            this.y = y + ySpeed;
//            moving = true;
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, collisionMap)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        } else {
            System.out.println("Collision detected at: (" + (hitBox.x + xSpeed) + ", " + (hitBox.y + ySpeed) + ")");
        }
    }

    /**
     * Method that loads the different animations of the player into an array.
     */
    private void loadAnimations() {

        BufferedImage image = LoadSave.loadImage(LoadSave.CHARACTER_ATLAS);

        animations = new BufferedImage[10][16];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j * 128, i * 128, 128, 128);
            }
        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }


    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if (!running) {
            playerSpeed = 2.0f;
        }
    }

    public void setCrouching(boolean crouching) {
        this.crouching = crouching;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setEquip(boolean equip) {
        this.equip = equip;
    }

    public void setAction(boolean action) { this.action = action; }

    public boolean getKeyIsPickedUp() {
        return key.isPickedUp;
    }

    public boolean killNPC() {
        if (weaponInInventory && attacking)  {
            if (knife != null && knife.getAttacking()) {
                List<NPCs> npcs = game.getActiveLevel().getNPCs();
                for (int i = 0; i < npcs.size(); i++) {
                    NPCs npc = npcs.get(i);
                    if (attackHitBox.intersects(npc.getHitBox())) {
                        npcs.remove(i);
                        npc.setAlive(false);
                        this.performAttack();
                        System.out.println("Player killed NPC");
                        knife.setResetAttack();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void manageKeyPickup() {
        if (key != null && !key.isPickedUp && hitBox.intersects(key.getHitBox()) && equip) {
            System.out.println("Key picked up!");
            key.isPickedUp = true;
            key = null;
        }
        if (knife != null && !knife.isPickedUp() && hitBox.intersects(knife.getHitBox()) && equip) {
            System.out.println("Knife picked up!");
            knife.isPickedUp = true;
            equipWeapon(knife);
        }
    }

    public void equipWeapon(Weapons weapon) {
        this.currentWeapon = weapon;
        weaponInInventory = true;
        attackHitBox = new Rectangle((int) this.getHitBox().x + 5, (int) this.getHitBox().y + 5, 35, 50);
        attackHitBoxStatus = true;
    }

    public void unequipWeapon(Weapons weapon) {
        this.currentWeapon = null;
        weaponInInventory = false;
        attackHitBox = null;
        attackHitBoxStatus = false;
    }

    public void hitboxLR() {
        if (attackHitBox != null && attackHitBoxStatus) {
            if (flipW == -1) {
                attackHitBox.x = (int) hitBox.x - 35;
            } 
            else {
                attackHitBox.x = (int) hitBox.x + 35;
            }
        }
    }

    // public Weapons getCurrentWeapon() {
    //     return currentWeapon;
    // }

    public void performAttack() {
        if (currentWeapon != null) {
            currentWeapon.setAttacking(true);
        }
    }

    private void takeStairs(LevelBase currentLevel) {
        if (action) {
            System.out.println("Action triggered for stairs.");
            System.out.println("hitBox.x: " + hitBox.x + ", hitBox.y: " + hitBox.y);
            System.out.println("hitBox.width: " + (hitBox.x + hitBox.width) + ", hitBox.height: " + (hitBox.y+hitBox.height));
            currentLevel.handleStairs(this); // Call to level-specific logic
            setAction(false); // Reset action after use
        }
    }



    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public void setHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }
}