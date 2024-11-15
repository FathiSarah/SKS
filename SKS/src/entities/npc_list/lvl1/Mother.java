package entities.npc_list.lvl1;

import entities.NPCs;
import levels.LevelManager;

public class Mother extends NPCs{

    public Mother(int x, int y, String name, LevelManager levelManager) {
        super(x, y, 50 , 100, name, levelManager);
        nPC_type = NPC_type.FEARFUL;
        currentState = NPC_state.IDLE;
        currentDirection = Direction.LEFT;
    }

    // @Override
    // public void isAlive() {
    //     if 
    // }

    



    
}
