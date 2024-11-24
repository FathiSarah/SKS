package entities;

import levels.LevelManager;
import main.Game;

import static utils.Constants.EnemyConstants.*;
import static utils.Methods.*;

/**
 * A class for NPCs that react to a detection zone.
 */
public class TriggeredNPC extends NPCs {

    private static final int DETECTION_WIDTH = 100;
    private static final int DETECTION_HEIGHT = 100;

    public TriggeredNPC(float x, float y, int width, int height, String name, LevelManager levelManager) {
        super(x, y, width, height, name, levelManager);
        initHitBox(x, y, 18 * Game.SCALE * NPC_SCALE, 25 * Game.SCALE * NPC_SCALE);
        setSpeed(1.5f);

        setCurrentState(NPC_state.IDLE);
        currentDirection = Direction.RIGHT;
    }

    /**
     * Updates the NPC's behavior.
     */
    public void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }

        switch (getCurrentState()) {
            case IDLE:
                if (playerDetected()) {
                    setCurrentState(NPC_state.CHASE);
                    newState(NPC_WALK);
                }
                break;

            case CHASE:
                chasePlayer();
                break;
        }
    }

    /**
     * Checks if the player is within the detection zone behind the NPC.
     *
     * @return true if the player is detected, false otherwise
     */
    private boolean playerDetected() {
        var playerHitBox = levelManager.getGame().getPlaying().getPlayer().getHitBox();

        float detectionX = currentDirection == Direction.RIGHT ? hitBox.x - DETECTION_WIDTH : hitBox.x + hitBox.width;
        float detectionY = hitBox.y;
        float detectionWidth = DETECTION_WIDTH;
        float detectionHeight = DETECTION_HEIGHT;

        return playerHitBox.intersects(detectionX, detectionY, detectionWidth, detectionHeight);
    }
}
