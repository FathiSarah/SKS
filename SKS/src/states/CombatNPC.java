package states;

import entities.NPCs;

public class CombatNPC extends NPCs{
    
    public CombatNPC(int x, int y, String name) {
        super(x, y, 50,100, name);
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
