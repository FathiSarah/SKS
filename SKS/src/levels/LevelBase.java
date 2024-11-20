package levels;

import entities.Player;
import entities.interactables.HidingPlaces;

public interface LevelBase {

    public void initialize(Player player, LevelManager levelManager);
    public void update(Player player);
    public void render(java.awt.Graphics g);
    public void handleStairs(Player player);

    public HidingPlaces getHidingPlaces();
}
