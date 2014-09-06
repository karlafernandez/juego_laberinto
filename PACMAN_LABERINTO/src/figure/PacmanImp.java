package figure;

import figure.Pacman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import main.Global;

public class PacmanImp extends Figure implements Pacman {

    /**
     * Intrinsic State
     */
    private int xDirection;
    private int yDirection;

    public PacmanImp() {
        String currentDir = System.getProperty("user.dir");
        image = new ImageIcon(currentDir + "/recursos/pacman.gif").getImage();
    }

    @Override
    public void updatePosition(int newLocationX, int newLocationY) {
        System.out.println(newLocationX + " " + newLocationY);
                
        if (newLocationX >= 0 && newLocationX < Global.panelWidth) {
            x = newLocationX;
        }
        if (newLocationY >= 0 && newLocationY < Global.panelHeight) {
            y = newLocationY;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, Global.pacmanPixSize, Global.pacmanPixSize, null);
    }

    @Override
    public void setXDirection(int dir) {
        xDirection = dir;
    }

    @Override
    public void setYDirection(int dir) {
        yDirection = dir;
    }

    @Override
    public int getPixSize() {
        return Global.pacmanPixSize;
    }

    @Override
    public int getNextY() {
        return (y + yDirection);
    }

    @Override
    public int getNextX() {
        return (x + xDirection);
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
