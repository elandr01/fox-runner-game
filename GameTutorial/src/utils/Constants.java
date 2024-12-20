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
        
        public static class PauseButtons {
        public static final int SOUND_SIZE_DEFAULT = 42;
        public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        public static class VolumeButtons 
        {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
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

        public static int getSpriteAmount(int player_action)
        {
            switch(player_action)
            {
                case IDLE:
                case ROLLING:
                case CLIMBING:
                    return 4;
                case RUNNING:
                case DIZZY:
                    return 6;
                case CROUCHING:
                    return 3;
                case HURTING: 
                case JUMPING:
                case WALL_GRAB: 
                    return 2;
                default:
                    return 1;
            }

        }

    }

}
