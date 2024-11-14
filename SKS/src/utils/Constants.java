package utils;

/**
 * Constants class to store all the constants used in the game
 */
public class Constants {

    /**
     * Constants to decide the player action and the according sprite
     */
    public static class PlayerConstants {

        public static final int ATTACK_1 = 0;
        public static final int ATTACK_2 = 1;
        public static final int DEAD = 2;
        public static final int HURT = 3;
        public static final int IDLE_1 = 4;
        public static final int IDLE_2 = 5;
        public static final int JUMP = 6;
        public static final int RUN = 7;
        public static final int SPECIAL = 8;
        public static final int WALK = 9;


        /**
         * Get the amount of sprites for the player action, to avoid looping through empty sprites
         * @param player_action
         * @return
         */
        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case JUMP:
                    return 16;
                case SPECIAL:
                    return 13;
                case IDLE_2:
                    return 11;
                case RUN:
                case WALK:
                    return 8;
                case IDLE_1:
                    return 6;
                case ATTACK_1:
                    return 5;
                case DEAD:
                    return 4;
                case ATTACK_2:
                case HURT:
                    return 3;
                default:
                    return 0;
            }
        }
    }

    public static class EnemyConstants {

        public static final int ATTACK = 0;
        public static final int DEATH = 1;
        public static final int IDLE = 2;
        public static final int WALK = 3;


        public static int GetSpriteAmount(int enemy_action) {
            switch (enemy_action) {
                case DEATH:
                    return 8;
                case WALK:
                    return 6;
                case ATTACK:
                case IDLE:
                    return 4;
                default:
                    return 0;
            }
        }
    }
}
