/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

import java.awt.Image;
import main.Global;

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
        pixSize = Global.pixSize;
        x = 0;
        y = 0;
        image = null;
    }
}
