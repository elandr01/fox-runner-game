package levels;

import java.awt.*;
import java.awt.image.*;
import main.Game;
import utils.LoadSave;

import static main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData());

    }
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL1_ATLAS);
        levelSprite = new BufferedImage[48];
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 12; ++j){
                int index = i * 12 + j;
                levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);

            }
        }
    }

    public void draw(Graphics g){
        for(int j = 0; j < Game.TILES_IN_HEIGHT; ++j){
            for(int i = 0; i < Game.TILES_IN_WIDTH; ++i){
                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }

    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelOne;
    }



}
