package entities;

import levels.LevelManager;
import main.Game;


import static utils.Constants.EnemyConstants.*;
import static utils.Methods.*;

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

        switch (getCurrentState()) {
            case IDLE:
                if(inSight()) {
                    setCurrentState(NPC_state.CHASE);
                    newState(NPC_WALK);
                }
                if (elapsedTime > 5000000000L) { // Wait for 3 seconds
                    switchDirection();
                    setCurrentState(NPC_state.PATROL);
                    newState(NPC_WALK);
                    startTime = System.nanoTime();
                }
                break;
            case PATROL:
                if(inSight()) {
                    setCurrentState(NPC_state.CHASE);
                    newState(NPC_WALK);
                }
                if (elapsedTime > 3000000000L) {
                    setCurrentState(NPC_state.IDLE);// Patrol for 1 second
                    newState(NPC_IDLE);
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
            case CHASE:
                System.out.println("Chasing player");
//                if (hidden or something like that) {
//                    setCurrentState(NPC_state.PATROL);
//                    newState(NPC_WALK);
//                } else {
                if(!isOnSameLevel(this.hitBox, levelManager.getGame().getPlaying().getPlayer().getHitBox())) {
                    setCurrentState(NPC_state.PATROL);
                    newState(NPC_WALK);
                }

                if (levelManager.getGame().getPlaying().getPlayer().getHitBox().x < hitBox.x) {

                    if (canMoveHere(hitBox.x - speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                        hitBox.x -= speed * 0.2;
                        currentDirection = Direction.LEFT;
                    }
                } else {
                    if (canMoveHere(hitBox.x + speed, hitBox.y, hitBox.width, hitBox.height, levelManager.getCollisionMap())) {
                        hitBox.x += speed * 0.2;
                        currentDirection = Direction.RIGHT;
                    }

                }
                break;
        }
    }


}