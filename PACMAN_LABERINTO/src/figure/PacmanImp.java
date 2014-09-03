
package figure;

import figure.Pacman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PacmanImp extends FlyweightFigure implements Pacman{
    
    /**
    * Intrinsic State
    */

    private int xDirection;
    private int yDirection;
    
    public PacmanImp(){
        image = new ImageIcon("/home/rgap/Escritorio/GAME/juego_laberinto/PACMAN_LABERINTO/recursos/pacman.gif").getImage();
    }
    
    @Override
    public void updatePosition(int newLocationX, int newLocationY) {
        x = newLocationX;
        y = newLocationY;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, pixSize, pixSize, null);
    }
    
    @Override
    public void setXDirection(int dir){
        xDirection = dir;
    }
    
    @Override
    public void setYDirection(int dir){
        yDirection = dir;
    }

    @Override
    public int getPixSize() {
        return pixSize;
    }

    @Override
    public int getNextY() {
        return (y + yDirection)/pixSize;
    }

    @Override
    public int getNextX() {
        return (x + xDirection)/pixSize;
    }

    @Override
    public void updateDirection() {
        x += xDirection;
        y += yDirection;
    }
   
    @Override
    public Image getImage() {
        return image;
    }
}
