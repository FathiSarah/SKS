package entities.npc_list.lvl1;

import entities.NPCs;

public class Father extends NPCs {

    public Father(int x, int y, String name) {
        super(x, y, name);
        this.nPC_type = NPC_type.COMBATIVE;
        this.currentState = NPC_state.IDLE;
    }

    // turn around for 3 seconds each 5 seconds 
    public void turnAround() {
            elapsedTime = (System.nanoTime() - startTime) / 1000000;
            if (elapsedTime > 5000 && currentDirection == Direction.RIGHT) { // @TODO : check if he looks on the right for 5 seconds or not
                currentDirection = Direction.LEFT;
                startTime = System.nanoTime(); // reset timer
            } 
            else if (elapsedTime > 3000 && currentDirection == Direction.LEFT) {
                currentDirection = Direction.RIGHT;
                startTime = System.nanoTime(); 
            }
            if (seesPlayer()) {
                currentState = NPC_state.CHASE; // @TODO : implement Chasing logic in the states folder
            }
    }
}