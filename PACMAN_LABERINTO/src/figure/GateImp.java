/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import main.Global;

/**
 *
 * @author rgap
 */
public class GateImp extends Figure implements FigureAbstract {
    
    /**
    * Intrinsic State
    */
        
    GateImp(){
        String currentDir = System.getProperty("user.dir");
        image = new ImageIcon(currentDir+"/recursos/gate.png").getImage();
    }
    
    @Override
    public void updatePosition(int newLocationX, int newLocationY) {
        x = newLocationX;
        y = newLocationY;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, Global.pixSize, Global.pixSize, null);
    }

    @Override
    public int getPixSize() {
        return Global.pixSize;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
}
