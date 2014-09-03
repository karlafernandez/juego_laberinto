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
public class FlyweightFigure {
    
    protected int pixSize;
    protected int x;
    protected int y;
    
    protected Image image;
    
    FlyweightFigure(){
        pixSize = 10;
        x = 0;
        y = 0;
        image = null;
    }
}
