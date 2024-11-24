package entities;

import levels.LevelManager;
import main.Game;

import static utils.Constants.EnemyConstants.*;
import static utils.Methods.*;

/**
 * A class for static NPCs that switch direction periodically and chase the player when in sight.
 */
public class StaticNPC extends NPCs {

    // Time tracking for direction switching
    private long startTime;
    private long elapsedTime;

    /**
     * Constructor for the StaticNPC class.
     *
     * @param x            The x-coordinate of the NPC.
     * @param y            The y-coordinate of the NPC.
     * @param width        The width of the NPC.
     * @param height       The height of the NPC.
     * @param name         The name of the NPC.
     * @param levelManager The level manager to access the game world.
     */
    public StaticNPC(float x, float y, int width, int height, String name, LevelManager levelManager) {
        super(x, y, width, height, name, levelManager);
        initHitBox(x, y, 18 * Game.SCALE * NPC_SCALE, 25 * Game.SCALE * NPC_SCALE);
        setSpeed(0);
        setFieldOfView(200 * Game.SCALE);

        setCurrentState(NPC_state.IDLE);
        currentDirection = Direction.RIGHT;

        startTime = System.nanoTime();
    }

    /**
     * Updates the NPC's behavior.
     */
    public void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }
        elapsedTime = System.nanoTime() - startTime;

        switch (getCurrentState()) {
            case IDLE:
                if (inSight()) {
                    setCurrentState(NPC_state.CHASE);
                    newState(NPC_WALK);
                    setSpeed(1.5f); // Set speed when chasing
                } else if (elapsedTime > 2000000000L) { // Switch direction every 2 seconds
                    switchDirection();
                    startTime = System.nanoTime();
                }
                break;

            case CHASE:
                chasePlayer();
                break;
        }
    }
}
