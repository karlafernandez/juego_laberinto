
package figure;

import figure.Pacman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PacmanImp extends Figure implements Pacman{
    
    /**
    * Intrinsic State
    */

    private int xDirection;
    private int yDirection;
    
    public PacmanImp(){
        String currentDir = System.getProperty("user.dir");
        image = new ImageIcon(currentDir+"/recursos/pacman.gif").getImage();
    }
    
    @Override
    public void updatePosition(int newLocationX, int newLocationY) {
        if (newLocationX>=0 &&newLocationX<500)
            x = newLocationX;
        if (newLocationY>=0 &&newLocationY<500)
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
