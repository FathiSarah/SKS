package levels;

import entities.*;
import static entities.NPCs.NPC_SCALE;
import entities.interactables.Key;
import entities.interactables.Knife;
import entities.interactables.Stairs;

import utils.Methods;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static main.Game.SCALE;
import static utils.LoadSave.*;

public class LevelOne implements LevelBase {
    private Key key;
    private Knife knife;
    private List<HidingPlaces> hidingPlaces = new ArrayList<>();

    private List<Stairs> stairs = new ArrayList<>();
    private List<NPCs> npcs = new ArrayList<>();

    private LevelManager levelManager;

    @Override
    public void initialize(Player player, LevelManager levelManager) {
        hidingPlaces.add(new HidingPlaces(500 * SCALE, 424 * SCALE, (int) (85 * SCALE), (int) (100 * SCALE), WARDROBE));
        hidingPlaces.add(new HidingPlaces(500 * SCALE, 550 * SCALE, (int) (65 * SCALE), (int) (100 * SCALE), FRIDGE));
        hidingPlaces.add(new HidingPlaces(500 * SCALE, 730 * SCALE, (int) (112 * SCALE), (int) (53 * SCALE), COUCH));
        hidingPlaces.add(new HidingPlaces(800 * SCALE, 470 * SCALE, (int) (107 * SCALE), (int) (56 * SCALE), BED));

        key = new Key(250 * SCALE, 750 * SCALE, (int) (50 * SCALE), (int) (20 * SCALE), "Key");
        knife = new Knife(520 * SCALE, 610 * SCALE, (int) (50 * SCALE), (int) (20 * SCALE), "Knife");

        npcs.add(new PatrollingNPC(530 * SCALE, 500 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC1", levelManager));
        npcs.add(new PatrollingNPC(550 * SCALE, 670 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC2", levelManager));
        npcs.add(new PatrollingNPC(400 * SCALE, 450 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC3", levelManager));

        initStairs();

        Methods.setPlayer(player);

        player.setLevelManager(levelManager);
        this.levelManager = levelManager;
    }

    public void update(Player player) {
        player.update(key);
        player.update(knife);

        player.update(hidingPlaces);

        for (NPCs npc : npcs) {
            npc.update();
        }


    }

    public void render(Graphics g) {
        levelManager.draw(g, LEVEL_ONE);

        for (HidingPlaces hidingPlace : hidingPlaces) {
            hidingPlace.render(g, hidingPlace.getName());
        }

        if (key != null && key.isPickedUp() == false) {
            key.render(g);
        }
        if (knife != null && knife.isPickedUp() == false) {
            knife.render(g);
        }

        for (NPCs npc : npcs) {
            if (npc.Alive()) {
                npc.render(g);
            }
        }

    }

    private void initStairs() {
        stairs.add(new Stairs(990 * SCALE, 1100 * SCALE, 718 * SCALE, 782 * SCALE, 1050 * SCALE, 584 * SCALE));
        stairs.add(new Stairs(1050 * SCALE, 1100 * SCALE, 584 * SCALE, 648 * SCALE, 1025 * SCALE, 718 * SCALE));
        stairs.add(new Stairs(150 * SCALE, 220 * SCALE, 584 * SCALE, 648 * SCALE, 145 * SCALE, 459 * SCALE));
        stairs.add(new Stairs(140 * SCALE, 210 * SCALE, 459 * SCALE, 523 * SCALE, 205 * SCALE, 584 * SCALE));
    }

    public void handleStairs(Player player) {
        for (Stairs stair : stairs) {
            if (stair.isWithinBounds(player.getHitBox().x, player.getHitBox().y, player.getHitBox().width, player.getHitBox().height)) {
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

    public List<NPCs> getNPCs() {
        return npcs;
    }

    public List<HidingPlaces> getHidingPlaces() {
        return hidingPlaces;
    }


}
