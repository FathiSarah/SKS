package levels;

public class LevelFactory {

    public static LevelBase createLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return new LevelOne();
            default:
                throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        }
    }
}
