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
public interface Wall {
        
    public void updatePosition(int newLocationX ,int newLocationY);
    public void draw(Graphics g);
    public int getPixSize();
    public Image getImage();
}
