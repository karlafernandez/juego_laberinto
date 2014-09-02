package PACMAN;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Pacman implements Runnable {
    
    int x,y,xDirection,yDirection;
    Image pacman;
    
    Laberinto L;
    
    public Pacman(int x, int y){
        this.x= x;
        this.y= y;
        L = new Laberinto();
        
        pacman = new ImageIcon("C:\\Users\\jorge\\Documents\\GitHub\\MaestriaPW\\PACMAN_LABERINTO\\recursos\\pacman.gif").getImage();
    }
    
    public boolean estaDentro(int f, int c){
        if(L.lab[f][c]==L.PARED)
            return false;
        return true;
    }
    
    public void draw(Graphics g){
        g.drawImage(pacman, x, y, null);
    }
    
    public void setXDirection(int dir){
        xDirection = dir;
    }
    
    public void setYDirection(int dir){
        yDirection = dir;
    }
    
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_UP)
            setYDirection(-50);
        if(keyCode == e.VK_DOWN)
            setYDirection(+50);
        if(keyCode == e.VK_RIGHT)
            setXDirection(+50);
        if(keyCode == e.VK_LEFT)
            setXDirection(-50);
    }
    
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_UP)
            setYDirection(0);
        if(keyCode == e.VK_DOWN)
            setYDirection(0);
        if(keyCode == e.VK_RIGHT)
            setXDirection(0);
        if(keyCode == e.VK_LEFT)
            setXDirection(0);
    }
    
    public void move(){
        if(estaDentro((y+yDirection)/50,(x+xDirection)/50)){
            x+=xDirection;
            y+=yDirection;
        }
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
}
