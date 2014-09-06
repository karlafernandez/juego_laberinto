/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamelogic;

import figure.FlyweightFactory;
import figure.Pacman;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import main.LabyrinthSingleton;

/**
 *
 * @author rgap
 */
public class PacmanLogic implements Runnable {
    
    /**
    * Reference to the flyweight
    */
    private Pacman pacman = FlyweightFactory.getPacman();
    
    /**
    * State is maintained by the client 
    */
    
    private LabyrinthSingleton L;
    
    public PacmanLogic(){
        this.L = LabyrinthSingleton.getInstance();
    }
    
    public void move(){
        if(estaDentro(pacman.getNextY(), pacman.getNextX())){
            pacman.updateDirection();
        }
    }
    
    public boolean estaDentro(int f, int c){
        if(L.lab[f][c] == L.PARED)
            return false;
        return true;
    }
    public boolean termino(int f,int c)
    {
        if(L.fin.x ==f& L.fin.y==c)
            return true;
        return false;
    }
    public boolean puerta(int f, int c) 
    {
       if(L.lab[f][c] == L.PUERTA)
            return true;
        return false; 
    }
    
    public void updatePosition(int newLocationX, int newLocationY){
        pacman.updatePosition(newLocationX, newLocationY);
    }
    
    public void draw(Graphics g){
        pacman.draw(g);
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(200);
            }
        }
        catch(Exception e){
            System.out.println("ERROR PACMAN");
        }
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_UP)
            pacman.setYDirection(-pacman.getPixSize());
        if(keyCode == e.VK_DOWN)
            pacman.setYDirection(+pacman.getPixSize());
        if(keyCode == e.VK_RIGHT)
            pacman.setXDirection(+pacman.getPixSize());
        if(keyCode == e.VK_LEFT)
            pacman.setXDirection(-pacman.getPixSize());
    }
    
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_UP)
            pacman.setYDirection(0);
        if(keyCode == e.VK_DOWN)
            pacman.setYDirection(0);
        if(keyCode == e.VK_RIGHT)
            pacman.setXDirection(0);
        if(keyCode == e.VK_LEFT)
            pacman.setXDirection(0);
    }    
}
