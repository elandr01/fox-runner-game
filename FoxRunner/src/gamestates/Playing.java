package gamestates;

import java.awt.Graphics;
import java.awt.event.*;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{

    private Player player;
    private LevelManager levelManager;

    public Playing(Game game){
        super(game);
        initClasses();
    }

    private void initClasses(){
        levelManager = new LevelManager(game);
        player = new Player(200,200, (int) (32 * Game.SCALE), (int) (33 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();

    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1){
//            player.setRolling(true);
//        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.setResting(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.setResting(false);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_A -> player.setLeft(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
            case KeyEvent.VK_BACK_SPACE -> Gamestate.state = Gamestate.MENU;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }


   public void windowFocusLost() {
        player.resetDirBooleans();
   }

   public Player getPlayer() {return player;}



}

