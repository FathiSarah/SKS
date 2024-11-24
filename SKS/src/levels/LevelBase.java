package levels;

import entities.NPCs;
import entities.Player;
import entities.interactables.HidingPlaces;
import java.util.List;

/**
 * Interface used to define the methods that a level must implement.
 */
public interface LevelBase {

    public void initialize(Player player, LevelManager levelManager);
    public void update(Player player);
    public void render(java.awt.Graphics g);
    public void handleStairs(Player player);

    public List<HidingPlaces> getHidingPlaces();

    public List<NPCs> getNPCs();
}
