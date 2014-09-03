/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import figure.Gate;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rgap
 */
public class GateImp extends FlyweightFigure implements Gate  {
    
    /**
    * Intrinsic State
    */
    
    GateImp(){
        image = new ImageIcon("/home/rgap/Escritorio/GAME/juego_laberinto/PACMAN_LABERINTO/recursos/gate.jpg").getImage();
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
