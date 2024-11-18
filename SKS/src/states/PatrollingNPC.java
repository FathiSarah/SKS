package states;

import entities.NPCs;
import levels.LevelManager;
import main.Game;
import static utils.Methods.canMoveHere;
import static utils.Constants.EnemyConstants.*;
import static utils.Methods.isEntityOnFloor;

public class PatrollingNPC extends NPCs {

    public PatrollingNPC(float x, float y, int width, int height, String name, LevelManager levelManager) {
        super(x, y, width, height, name, levelManager);
        initHitBox(x, y, 18 * Game.SCALE * NPC_SCALE, 25 * Game.SCALE * NPC_SCALE);
    }

//    public void switchDirection() {
//        if (currentDirection == Direction.RIGHT) {
//            currentDirection = Direction.LEFT;
//        }
//        else if (currentDirection == Direction.LEFT) {
//            currentDirection = Direction.RIGHT;
//        }
//    }

    protected void updateMove() {
        while (!isEntityOnFloor(hitBox, levelManager.getCollisionMap())) {
            hitBox.y += 1;
        }
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("Current Action: " + getNPCAction() + ", Elapsed Time: " + elapsedTime);

        switch (getNPCAction()) {
            case IDLE:
                if (elapsedTime > 5000000000L) { // Wait for 3 seconds
                    switchDirection();
                    newState(WALK);
                    startTime = System.nanoTime();
                }
                break;
            case WALK:
                if (elapsedTime > 2000000000L) { // Patrol for 1 second
                    newState(IDLE);
                    startTime = System.nanoTime();
                } else {
                    if (currentDirection == Direction.RIGHT) {
                        if (canMoveHere(hitBox.x + speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                            hitBox.x += speed * 0.2;
                        } else {
                            switchDirection();
                            startTime = System.nanoTime();
                        }
                    } else {
                        if (canMoveHere(hitBox.x - speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                            hitBox.x -= speed * 0.2;
                        } else {
                            switchDirection();
                            startTime = System.nanoTime();
                        }
                    }
                }
                break;
        }
    }


}