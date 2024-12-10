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

    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;

        InputStream is = LoadSave.class.getResourceAsStream("/textures/" + fileName);
        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException e){e.printStackTrace();}
        }

        return img;
    }//end of getPlayerAtlas

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
