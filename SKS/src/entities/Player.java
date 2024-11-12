package entities;

import levels.LevelManager;
import main.Game;
import utils.LoadSave;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import static utils.Constants.PlayerConstants.*;
import static utils.Methods.canMoveHere;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE_1;
    private boolean moving = false, attacking = false, jumping = false, running = false, crouching = false;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    private float xOffset = 48 * Game.SCALE;
    private float yOffset = 64 * Game.SCALE;
    public static final float CHAR_SCALE = 0.9f;

    private LevelManager levelManager;
  
    private boolean weaponInInventory = false;
    private boolean keyInInventory = false;


    private int flipX = 0;
    private int flipW = 1;


    public Player(float x, float y, int width, int height, LevelManager levelManager) {
        super(x, y, width, height);
        this.levelManager = levelManager;
        loadAnimations();
        initHitBox(x, y, 35 * Game.SCALE * CHAR_SCALE, 64 * Game.SCALE * CHAR_SCALE);
    }

    public void update(){
        updatePos();
        setAnimation();
        updateAnimationTick();
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
  
    public void getWeapon(KeyEvent e) {
        e.getKeyCode();
        if (keyboardInputs.isEPressed()) {
            weaponInInventory = true;
        }
    }

    public void getKey(KeyEvent e) {
        e.getKeyCode();
        if (keyboardInputs.isEPressed()) {
            keyInInventory = true;
        }
    }

    public boolean killNPC() {
        if (weaponInInventory) {
            System.out.println("Player killed NPC");
            // removeWeapon();
            return true;
        }
        return false;
}
