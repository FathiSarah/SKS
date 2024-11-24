package utils;

import main.Game;

/**
 * Constants class to store all the constants used in the game
 */
public class Constants {

    /**
     * Constats for the User Interface
     */
    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE * 2);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE * 2);
        }
        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        public static class URMButtons{
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
        }
    }

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

    /**
     * Constants to decide the enemy action and the according sprite
     */
    public static class EnemyConstants {

        public static final int NPC_ATTACK = 0;
        public static final int NPC_DEATH = 1;
        public static final int NPC_IDLE = 2;
        public static final int NPC_WALK = 3;


        public static int GetSpriteAmount(int enemy_action) {
            switch (enemy_action) {
                case NPC_DEATH:
                    return 8;
                case NPC_WALK:
                    return 6;
                case NPC_ATTACK:
                case NPC_IDLE:
                    return 4;
                default:
                    return 0;
            }
        }
    }
}