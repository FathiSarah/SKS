package levels;

import entities.*;
import static entities.NPCs.NPC_SCALE;
import entities.interactables.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static main.Game.SCALE;
import static utils.LoadSave.*;
import utils.Methods;

/**
 * Class that defines the first level of the game.
 */
public class LevelOne implements LevelBase {

    private Key key;
    private Knife knife;
    private List<HidingPlaces> hidingPlaces = new ArrayList<>();
    private List<Doors> doors = new ArrayList<>();

    private List<Stairs> stairs = new ArrayList<>();
    private List<NPCs> npcs = new ArrayList<>();

    private NPCs npc1, npc2, npc3, npc4;

    private LevelManager levelManager;

    /**
     * Method used to initialize the level.
     * @param player
     * @param levelManager
     */
    @Override
    public void initialize(Player player, LevelManager levelManager) {
        hidingPlaces.add(new HidingPlaces(350 * SCALE, 430 * SCALE, (int) (85 * SCALE), (int) (100 * SCALE), WARDROBE));
        hidingPlaces.add(new HidingPlaces(550 * SCALE, 550 * SCALE, (int) (65 * SCALE), (int) (100 * SCALE), FRIDGE));
        hidingPlaces.add(new HidingPlaces(500 * SCALE, 730 * SCALE, (int) (112 * SCALE), (int) (53 * SCALE), COUCH));
        hidingPlaces.add(new HidingPlaces(800 * SCALE, 470 * SCALE, (int) (107 * SCALE), (int) (56 * SCALE), BED));

        doors.add(new Doors(293 * SCALE, 675 * SCALE, (int) (65 * SCALE), (int) (125 * SCALE), CLOSED_DOOR));
        doors.add(new Doors(757 * SCALE, 555 * SCALE, (int) (60 * SCALE), (int) (100 * SCALE), OPENED_DOOR));
        doors.add(new Doors(920 * SCALE, 690 * SCALE, (int) (60 * SCALE), (int) (100 * SCALE), OPENED_DOOR));

        key = new Key(250 * SCALE, 750 * SCALE, (int) (50 * SCALE), (int) (20 * SCALE), "Key");

        knife = new Knife(500 * SCALE, 610 * SCALE, (int) (50 * SCALE), (int) (20 * SCALE), "Knife");

        npc1 = new StaticNPC(400 * SCALE, 500 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC1", levelManager);
        npc2 = new PatrollingNPC(750 * SCALE, 670 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC2", levelManager);
        npc3 = new TriggeredNPC(950 * SCALE, 450 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC3", levelManager);
        npc4 = new PatrollingNPC(575 * SCALE, 450 * SCALE, (int) (31 * SCALE * NPC_SCALE), (int) (29 * SCALE * NPC_SCALE), "NPC3", levelManager);

        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);

        initStairs();

        Methods.setPlayer(player);
        player.setLevelManager(levelManager);
        this.levelManager = levelManager;
    }


    /**
     * Method used to update the level.
     * @param player
     */
    public void update(Player player) {
        player.update(key);
        player.update(knife);

        player.update(hidingPlaces);

        for (NPCs npc : npcs) {
            npc.update();
            if (!npc.Alive() && npc.getDropItem() != null) {
                Items droppedItem = npc.getDropItem();
                if (!droppedItem.isPickedUp()) {
                    player.manageKeyPickup();
                }
            }
        }
    }

    /**
     * Method used to render the level.
     * @param g
     */
    public void render(Graphics g) {
        levelManager.draw(g, LEVEL_ONE);

        for (HidingPlaces hidingPlace : hidingPlaces) {
            hidingPlace.render(g, hidingPlace.getName());
        }

        for (Doors door : doors) {
            if(key.isPickedUp == true && door.getName().equals(CLOSED_DOOR)){
                door.setName(OPENED_DOOR);
                door.setX(315 * SCALE);
                door.setY(687 * SCALE);
                door.setWidth((int)(60 * SCALE));
                door.setHeight((int)(100 * SCALE));
            }
            door.render(g);
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

    /**
     * Method used to initialize the stairs.
     */
    private void initStairs() {
        stairs.add(new Stairs(990 * SCALE, 1100 * SCALE, 718 * SCALE, 782 * SCALE, 1050 * SCALE, 584 * SCALE));
        stairs.add(new Stairs(1050 * SCALE, 1100 * SCALE, 584 * SCALE, 648 * SCALE, 1025 * SCALE, 718 * SCALE));
        stairs.add(new Stairs(150 * SCALE, 220 * SCALE, 584 * SCALE, 648 * SCALE, 145 * SCALE, 459 * SCALE));
        stairs.add(new Stairs(140 * SCALE, 210 * SCALE, 459 * SCALE, 523 * SCALE, 205 * SCALE, 584 * SCALE));
    }

    /**
     * Method used to handle the use of stairs.
     * @param player
     */
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