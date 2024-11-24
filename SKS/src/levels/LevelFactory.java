package levels;

/**
 * Class used to create levels.
 */
public class LevelFactory {

    /**
     * Creates a level based on the level number.
     * @param levelNumber the level number
     * @return the level
     */
    public static LevelBase createLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return new LevelOne();
            default:
                throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        }
    }
}
