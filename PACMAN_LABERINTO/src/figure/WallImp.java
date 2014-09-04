/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import figure.Wall;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rgap
 */
public class WallImp extends Figure implements Wall{
        
    /**
    * Intrinsic State
    */
        
    WallImp(){
        image = new ImageIcon("C:\\Users\\jorge\\Documents\\GitHub\\juego_laberinto\\PACMAN_LABERINTO\\recursos\\wall.jpg").getImage();
    }
    
    @Override
    public void updatePosition(int newLocationX, int newLocationY) {
        x = newLocationX;
        y = newLocationY;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, null);
    }

    @Override
    public int getPixSize() {
        return pixSize;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
}
