package utils;

import main.Game;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Spritesheet.png";
    public static final String LEVEL1_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ATLAS = "Tileset.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";


	public static BufferedImage getSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

   public static int[][] getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++){
            for(int i = 0; i < Game.TILES_IN_WIDTH; i++){
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        }
        return lvlData;
   }



}