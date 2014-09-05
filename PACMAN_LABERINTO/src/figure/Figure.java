/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import java.awt.Image;

/**
 *
 * @author rgap
 */
public class Figure {
    
    protected int pixSize;
    protected int x;
    protected int y;
    
    protected Image image;
    
    Figure()
    {
        pixSize = 10;
        x = 0;
        y = 0;
        image = null;
    }
    void setSize(int _pixSize)
    {
        this.x=pixSize;
    }
}
