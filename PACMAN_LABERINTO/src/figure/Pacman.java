/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figure;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Flyweight Interface
 *
 */
public interface Pacman {

    public void updateDirection();

    public void updatePosition(int newLocationX, int newLocationY);

    public void setXDirection(int dir);

    public void setYDirection(int dir);

    public void draw(Graphics g);

    public int getPixSize();

    public int getNextY();

    public int getNextX();

    public Image getImage();
}
