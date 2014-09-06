/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rgap
 */
public class WallImp extends Figure implements FigureAbstract{
        
    /**
    * Intrinsic State
    */
        
    WallImp(){
        String currentDir = System.getProperty("user.dir");
        image = new ImageIcon(currentDir+"/recursos/wall.jpg").getImage();
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
    public int getPixSize() {
        return pixSize;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
}
