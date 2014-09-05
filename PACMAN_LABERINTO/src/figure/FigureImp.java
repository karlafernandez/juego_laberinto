/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author redarma
 */
public interface FigureImp  
{
    public void updatePosition(int newLocationX ,int newLocationY);
    public void draw(Graphics g);
    public int getPixSize();
    public Image getImage();
}
