package utils;

import main.Game;

public class Constants {

    public static class UI {

        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

    }

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        // THIS IS BASED ON THE SPRITE SHEET OF THE FOX
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int CLIMBING = 2;
        public static final int CROUCHING = 3;
        public static final int HURTING = 4;
        public static final int JUMPING = 5;
        public static final int WALL_GRAB = 6;
        public static final int HURTING2 = 7; //contains 1 image of that row within the sprite sheet
        public static final int DIZZY = 8;
        public static final int ROLLING = 9;
        public static final int LOOKING_UP = 10; //contains 1 image within that row of the sprite sheet
        public static final int VICTORY = 11; //contains 1 image of that row within the sprite sheet

        public static int getSpriteAmount(int player_action){
            return switch(player_action){
                case IDLE,ROLLING,CLIMBING -> 4;
                case RUNNING,DIZZY -> 6;
                case CROUCHING -> 3;
                case HURTING,JUMPING,WALL_GRAB -> 2;
                default -> 1;
            };

        }
    }

}
