package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static utils.Constants.EnemyConstants.*;

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

    protected String name;
    protected int nextAction = 5000;

    protected long startTime = System.nanoTime();
    protected long elapsedTime = 0;

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 30;
    private int NPCAction = WALK;
    private float xOffset = 5 * Game.SCALE;
    private float yOffset = 6 * Game.SCALE;

    public NPCs(float x, float y, int width, int height, String name) {
        super(x, y, width, height);
        this.speed = 2;
        this.name = name;
        this.currentState = NPC_state.IDLE;
        this.currentDirection = Direction.RIGHT;
        this.nPC_type = NPC_type.FEARFUL;
        loadAnimations();
        initHitBox(x, y, 18 * Game.SCALE, 25 * Game.SCALE);
    }


    public void update() {
        updateAnimationTick();
    }

    public void render(Graphics g) {
        g.drawImage(animations[NPCAction][aniIndex], (int) (hitBox.x - xOffset), (int) (hitBox.y - yOffset), (int) width, (int) height, null);
        drawHitBox(g);
    }

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

    public void getNPC_type() {
        this.nPC_type = nPC_type;
    }
}