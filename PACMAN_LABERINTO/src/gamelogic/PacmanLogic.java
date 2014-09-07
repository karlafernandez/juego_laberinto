/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import figure.FlyweightFactory;
import figure.Pacman;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Global;
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

    public PacmanLogic() {
        this.L = LabyrinthSingleton.getInstance();
    }

    public void move() {
        if (enPasillo(pacman.getNextY(), pacman.getNextX())) {
            pacman.updateDirection();
        }
        if (termino(pacman.getNextY(), pacman.getNextX()))
            System.out.print("Gano");
    }

    public boolean enPasillo(int f, int c) {
        if ((f >= 0 && c >= 0) && (f < L.lab[0].length-Global.pacmanPixSize && c < L.lab.length-Global.pacmanPixSize)) {
            for (int i = 0; i < pacman.getPixSize(); ++i) {
                for (int j = 0; j < pacman.getPixSize(); ++j) {
                    if (L.lab[f + i][c + j] == L.PARED) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean termino(int f, int c) {
       // calculamos si llego a la meta
        int x ,y=0;
        
        x= f+Global.pacmanPixSize>Global.panelHeight?Global.panelHeight-5:f+Global.pacmanPixSize;
        y= c+Global.pacmanPixSize>Global.panelWidth?Global.panelWidth-5:c+Global.pacmanPixSize;
        System.out.println(x+"-"+y);
        boolean rest=false;
       for (int i = f; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    if (f==j && c==i) {
                        rest= true;
                        break;
                    }
                }
            } 
       return rest;
    }

    public boolean puerta(int f, int c) {
        return L.lab[f][c] == L.PUERTA;
    }

    public void setInitialPosition(int newLocationX, int newLocationY) {
        if ((newLocationX > 0 || newLocationY > 0) && (newLocationX < Global.panelWidth && newLocationY < Global.panelHeight)) {
            pacman.updatePosition(newLocationX, newLocationY);
        }
    }

    public void draw(Graphics g) {
        pacman.draw(g);
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(pacman.getPixSize()*10);
            }
        } catch (Exception e) {
            System.out.println("ERROR THREAD");
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_UP) {
            pacman.setYDirection(-pacman.getPixSize());
        }
        if (keyCode == e.VK_DOWN) {
            pacman.setYDirection(+pacman.getPixSize());
        }
        if (keyCode == e.VK_RIGHT) {
            pacman.setXDirection(+pacman.getPixSize());
        }
        if (keyCode == e.VK_LEFT) {
            pacman.setXDirection(-pacman.getPixSize());
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_UP) {
            pacman.setYDirection(0);
        }
        if (keyCode == e.VK_DOWN) {
            pacman.setYDirection(0);
        }
        if (keyCode == e.VK_RIGHT) {
            pacman.setXDirection(0);
        }
        if (keyCode == e.VK_LEFT) {
            pacman.setXDirection(0);
        }
    }
}
