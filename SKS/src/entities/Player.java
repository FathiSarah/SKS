package entities;

import entities.interactables.*;
import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import levels.*;
import main.Game;
import static utils.Constants.PlayerConstants.*;
import utils.LoadSave;
import static utils.Methods.*;

/**
 * Child class of Entity, used to create the player in the game.
 */
public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE_1;
    private boolean moving = false, attacking = false, jumping = false, running = false, crouching = false, action = false, hidden = false;
    private boolean left, right, up, down;
    private boolean equip = false;
    private float playerSpeed = 2.0f;
    private float xOffset = 48 * Game.SCALE;
    private float yOffset = 64 * Game.SCALE;

    private LevelManager levelManager;
    private Playing playing;

    private boolean weaponInInventory = false;

    private Key key;
    private Knife knife;

    private List<HidingPlaces> hidingPlaces = new ArrayList<>();

    private Weapons currentWeapon;
    private Rectangle attackHitBox;
    private boolean attackHitBoxStatus = false;

    private Clip deathSound;

    private int flipX = 0;
    private int flipW = 1;

    /**
     * Constructor for Player.
     *
     *
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
        hiding();
        takeStairs(playing.getActiveLevel());

        hitboxLR();
        checkCollisionPlayerNPCs();
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

    public void update(List<HidingPlaces> hidingPlaces) {
        this.hidingPlaces = hidingPlaces;
    }

    /**
     * Method that renders the player on the screen.
     *
     *
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xOffset) + flipX, (int) (hitBox.y - yOffset), (int) width * flipW, (int) height, null);
        drawHitBox(g);
        if (attackHitBox != null && attackHitBoxStatus == true) {
            g.setColor(Color.GREEN);
            g.drawRect(attackHitBox.x, attackHitBox.y, attackHitBox.width, attackHitBox.height);
        }
    }

    /**
     * Method that handles the rotation of sprites, depending on the animation and the number of sprites this animation have.
     */
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Method that sets the animation of the player depending on the player's actions.
     */
    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            playerAction = WALK;
        } else {
            playerAction = IDLE_1;
        }

        if (attacking) {
            playerAction = ATTACK_2;
        }

        if (jumping) {
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

        if (!left && !right && !up && !down) {
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if (left && !right && !isHidden()) {
            xSpeed -= playerSpeed;
            flipX = (int) width;
            flipW = -1;
        }
        if (!left && right && !isHidden()) {
            xSpeed += playerSpeed;

            flipX = 0;
            flipW = 1;
        }
        if (up && !down) {
            ySpeed -= playerSpeed;
        }
        if (!up && down) {
            ySpeed += playerSpeed;
        }


        BufferedImage collisionMap = levelManager.getCollisionMap();


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

    public void setAction(boolean action) {
        this.action = action;
    }

    public boolean getKeyIsPickedUp() {
        return key.isPickedUp;
    }

    /**
     * Method that checks if the player is attacking and if the attack hitbox intersects with an NPC.
     * If so, the NPC is removed from the list of NPCs in the level.
     *
     * @return
     */
    public boolean killNPC() {
        if (weaponInInventory && attacking) {
            if (knife != null && knife.getAttacking()) {
                List<NPCs> npcs = playing.getActiveLevel().getNPCs();
                for (int i = 0; i < npcs.size(); i++) {
                    NPCs npc = npcs.get(i);
                    if (attackHitBox.intersects(npc.getHitBox())) {
                        npcs.remove(i);
                        npc.setAlive(false);
                        this.performAttack();
                        deathSound();
                        System.out.println("Player killed NPC");
                        knife.setResetAttack();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void deathSound() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./res/mixkit-game-blood-pop-slide-2363.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            deathSound = AudioSystem.getClip();
            deathSound.open(audioStream);
            deathSound.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void manageKeyPickup() {
        if (key != null && !key.isPickedUp && hitBox.intersects(key.getHitBox()) && equip) {
            System.out.println("Key picked up!");
            key.isPickedUp = true;
            key.keyPickUpSound();
            key = null;
        }
        if (knife != null && !knife.isPickedUp() && hitBox.intersects(knife.getHitBox()) && equip) {
            System.out.println("Knife picked up!");
            knife.isPickedUp = true;
            knife.knifeEquipSound();
            equipWeapon(knife);
        }
    }

    /**
     * Method that equips the player with a weapon.
     *
     * @param weapon
     */
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

    /**
     * Method that sets the position of the attack hitbox depending on the player's direction.
     */
    public void hitboxLR() {
        if (attackHitBox != null && attackHitBoxStatus) {
            if (flipW == -1) {
                attackHitBox.x = (int) hitBox.x - 35;
                attackHitBox.y = (int) hitBox.y + 7;

            } else {
                attackHitBox.x = (int) hitBox.x + 35;
                attackHitBox.y = (int) hitBox.y + 7;
            }
        }
    }

    public boolean checkCollisionPlayerNPCs() {
        for (NPCs npc : playing.getActiveLevel().getNPCs()) {
            if (hitBox.intersects(npc.getHitBox())) {
                System.out.println("Player-NPC Collision");
                return true;
            }
        }
        return false;
    }

    // public Weapons getCurrentWeapon() {
    //     return currentWeapon;
    // }

    /**
     * Method used to trigger the attack action of the player.
     *
     * @return
     */
    public void performAttack() {
        if (currentWeapon != null) {
            currentWeapon.setAttacking(true);
        }
    }

    /**
     * Method that triggers the use of the stairs when the action button is pressed.
     *
     * @param currentLevel
     */
    private void takeStairs(LevelBase currentLevel) {
        if (action) {
            currentLevel.handleStairs(this); // Call to level-specific logic
            setAction(false); // Reset action after use
        }
    }

    /**
     * Method that checks if the player is colliding with a hiding place and if the player is in the hiding place.
     * If so, the player is hidden.
     */
    private void hiding() {
        if (action) {
            for (HidingPlaces hidingPlace : hidingPlaces) {
                if (hitBox.intersects(hidingPlace.getHitBox())) {
                    if (!hidden) {
                        setHidden(true);
                    } else {
                        setHidden(false);
                    }
                }
            }
        }
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public void setHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }
}

