package levels;

/**
 * Class used to store level data.
 */
public class Level {

    private int[][] levelData;

    public Level (int[][] levelData) {
        this.levelData = levelData;
    }

    public int[][] getLevelData() {
        return levelData;
    }
}
