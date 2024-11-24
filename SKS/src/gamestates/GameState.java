package gamestates;

/**
 * GameState enum is used to keep track of the current state of the game.
 */
public enum GameState {
    PLAYING, MENU, OPTIONS, QUIT;
    public static GameState state = MENU;

}
