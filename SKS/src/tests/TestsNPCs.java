package tests;

import entities.NPCs;
import levels.LevelManager;
import main.Game;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsNPCs {
    private LevelManager levelManager;

    @Test
    public void testSwitchDirection() {
        Game game = new Game();
        LevelManager levelManager = new LevelManager(game);
        NPCs npc = new NPCs(100, 100, 32, 34, "NPC1", levelManager);


        assertEquals(NPCs.Direction.RIGHT, npc.currentDirection, "NPC should initially face right.");

        npc.switchDirection();
        assertEquals(NPCs.Direction.LEFT, npc.currentDirection, "NPC should face left after switching.");

        npc.switchDirection();
        assertEquals(NPCs.Direction.RIGHT, npc.currentDirection, "NPC should face right after switching again.");
    }

}
