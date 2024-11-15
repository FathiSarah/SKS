package states;

import entities.NPCs;
import levels.LevelManager;

public class PatrollingNPC extends NPCs {
   
    public PatrollingNPC(int x, int y, String name, LevelManager levelManager) {
        super(x, y, 50, 100, name, levelManager);
        this.currentState = NPC_state.PATROL;
    }

    @Override
    public void update() {
        if (currentState == NPC_state.PATROL) {
            startPatrol();
        }
    }

    private void startPatrol() {
        elapsedTime = (System.nanoTime() - startTime) / 1000000;
        if (elapsedTime > nextAction) {
            patrolLR();
            switchDirection();
            switchState();
        }

    }

    public void switchDirection() {
        if (currentDirection == Direction.RIGHT) {
            currentDirection = Direction.LEFT;
        } 
        else if (currentDirection == Direction.LEFT) {
            currentDirection = Direction.RIGHT;
        }
    }

    public void switchState() {
        if (currentState == NPC_state.IDLE) {
            currentState = NPC_state.PATROL;
            isMoving = false;
            nextAction = 3000;
        }
        else if (currentState == NPC_state.PATROL) {
            currentState = NPC_state.IDLE;
            isMoving = true;
            nextAction = 4000;
        }
    }

    public void patrolLR() {
        if (currentState == NPC_state.PATROL) {
            if (currentDirection == Direction.RIGHT) {
            x += speed;
        } else {
            x -= speed;
        }
        }
    }
}