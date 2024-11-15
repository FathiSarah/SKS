package states;

import entities.NPCs;
import levels.LevelManager;

public class CombatNPC extends NPCs{
    
    public CombatNPC(int x, int y, String name, LevelManager levelManager) {
        super(x, y, 50,100, name, levelManager);
        nPC_type = NPC_type.COMBATIVE;
    }

    public void switchToAttack() {

    }

    public void perfAttack() {
        if (this.seesPlayer()) {
            // moveTowardsPlayer();
        }
    }

    // public void moveTowardsPlayer() {
    //     if (this.seesPlayer()) {
    //         if (player.getX() )
    //     }
    // }


}
