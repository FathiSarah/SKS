package levels;

import entities.NPCs;
import entities.Player;
import java.util.List;

public interface LevelBase {

    public void initialize(Player player, LevelManager levelManager);
    public void update(Player player);
    public void render(java.awt.Graphics g);
    public void handleStairs(Player player);
    public List<NPCs> getNPCs();
}
