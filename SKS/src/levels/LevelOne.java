package levels;

import entities.*;
import entities.interactables.Key;
import entities.interactables.Knife;
import entities.interactables.Stairs;
import utils.Methods;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static entities.NPCs.NPC_SCALE;
import static main.Game.SCALE;
import static utils.LoadSave.LEVEL_ONE;

public class LevelOne implements LevelBase {
    private Key key;
    private Knife knife;
    private NPCs npc1, npc2, npc3;

    private List<Stairs> stairs = new ArrayList<>();

    private LevelManager levelManager;

    @Override
    public void initialize(Player player, LevelManager levelManager) {
        key = new Key(250 * SCALE, 750 * SCALE, (int)(50 * SCALE), (int)(20 * SCALE), "Key");
        knife = new Knife(350 * SCALE, 750 * SCALE, (int)(50 * SCALE), (int)(20 * SCALE), "Knife");

        npc1 = new PatrollingNPC(800 * SCALE, 500 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC1", levelManager);
        npc2 = new PatrollingNPC(950 * SCALE, 670 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC2", levelManager);
        npc3 = new PatrollingNPC(400 * SCALE, 450 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC3", levelManager);

        Methods.setPlayer(player);

        player.setLevelManager(levelManager);
        this.levelManager = levelManager;
    }

    public void update(Player player) {
        player.update(key);
        player.update(knife);

        npc1.update();
        npc2.update();
        npc3.update();

        initStairs();
    }

    public void render(Graphics g) {
        levelManager.draw(g, LEVEL_ONE);

        if (key != null && key.isPickedUp() == false) {
            key.render(g);
        }
        if (knife != null && knife.isPickedUp() == false) {
            knife.render(g);
        }

        npc1.render(g);
        npc2.render(g);
        npc3.render(g);

    }

    private void initStairs() {
        stairs.add(new Stairs(990 * SCALE, 1100 * SCALE, 718 * SCALE, 782 * SCALE, 1050 * SCALE, 584 * SCALE));
        stairs.add(new Stairs(1050 * SCALE, 1100 * SCALE, 584 * SCALE, 648 * SCALE, 1025 * SCALE, 718 * SCALE));
        stairs.add(new Stairs(150 * SCALE, 220 * SCALE, 584 * SCALE, 648 * SCALE, 145 * SCALE, 459 * SCALE));
        stairs.add(new Stairs(140 * SCALE, 210 * SCALE, 459 * SCALE, 523 * SCALE, 205 * SCALE, 584 * SCALE));
    }

    public void handleStairs(Player player) {
        for (Stairs stair : stairs) {
            if(stair.isWithinBounds(player.getHitBox().x, player.getHitBox().y, player.getHitBox().width, player.getHitBox().height)) {
                System.out.println("Player is within bounds");
                player.setHitBox(stair.getTargetX(), stair.getTargetY(), player.getHitBox().width, player.getHitBox().height);
                break;
            }
        }
    }


    public Key getKey() {
        return key;
    }

    public Knife getKnife() {
        return knife;
    }

    public NPCs[] getNPCs() {
        return new NPCs[] {npc1, npc2, npc3};
    }
}
