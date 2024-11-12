package entities.npc_list.lvl1;

import entities.NPCs;

public class Mother extends NPCs{

    public Mother(int x, int y, String name) {
        super(x, y, name);
        nPC_type = NPC_type.FEARFUL;
        currentState = NPC_state.IDLE;
        currentDirection = Direction.LEFT;
    }

    // @Override
    // public void isAlive() {
    //     if 
    // }

    



    
}
