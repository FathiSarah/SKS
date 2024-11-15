package entities;

import entities.interactables.Key;
import entities.interactables.Knife;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import levels.LevelManager;
import main.Game;
import static utils.Constants.PlayerConstants.*;
import utils.LoadSave;
import static utils.Methods.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE_1;
    private boolean moving = false, attacking = false, jumping = false, running = false, crouching = false;
    private boolean left, right, up, down;
    private boolean equip = false;
    private float playerSpeed = 2.0f;
    private float xOffset = 48 * Game.SCALE;
    private float yOffset = 64 * Game.SCALE;

    private LevelManager levelManager;
  
    private boolean weaponInInventory = false;

    private Key key;
    private Knife knife;

    private int flipX = 0;
    private int flipW = 1;


    public Player(float x, float y, int width, int height, LevelManager levelManager) {
        super(x, y, width, height);
        this.levelManager = levelManager;
        loadAnimations();
        initHitBox(x, y, 35 * Game.SCALE, 64 * Game.SCALE);
    }

    public void update(Key key) {
        this.key = key;
        updatePos();
        setAnimation();
        updateAnimationTick();
        manageKeyPickup();
    }

    public void update(Knife knife) {
        this.knife = knife;
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)(hitBox.x - xOffset) + flipX, (int)(hitBox.y - yOffset), (int)width * flipW, (int)height, null);
        drawHitBox(g);
    }

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

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

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
            System.out.println("Collision detected at: (" + (x + xSpeed) + ", " + (y + ySpeed) + ")");
        }
    }

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

    public boolean killNPC() {
        if (weaponInInventory) {
            System.out.println("Player killed NPC");
            // removeWeapon();
            return true;
        }
        return false;
    }

    public void manageKeyPickup() {
        if (key != null && !key.isPickedUp && hitBox.intersects(key.getHitBox()) && equip) {
            System.out.println("Key picked up!");
            key.isPickedUp = true;
            key = null;
        }
        if (knife != null && !knife.isPickedUp && hitBox.intersects(knife.getHitBox()) && equip) {
            System.out.println("Knife picked up!");
            knife.isPickedUp = true;
            knife = null;
        }
    }
}