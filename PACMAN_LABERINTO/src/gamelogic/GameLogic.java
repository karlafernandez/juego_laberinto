/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import figure.FlyweightFactory;
import figure.Pacman;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import main.Global;
import main.LabyrinthSingleton;

/**
 *
 * @author rgap
 */
public class GameLogic implements Runnable {

    /**
     * Reference to the flyweight
     */
    private Pacman pacman = FlyweightFactory.getPacman();

    /**
     * State is maintained by the client
     */
    private LabyrinthSingleton L;

    public GameLogic() {
        L = LabyrinthSingleton.getInstance();
        Global.gameState = 0; // 0 = nothing, 1 = win, 2 = gateSalto, 3 = gateBomba, 4 = lose
    }

    public void move() {
        if (termino(pacman.getNextY(), pacman.getNextX())) {
            Global.gameState = 1;
            System.out.println("LLego al Final");
        }
        if (enPasillo(pacman.getNextY(), pacman.getNextX())) {
            pacman.updateDirection();
        } else if (enPuerta(pacman.getNextY(), pacman.getNextX())) {
            pacman.updateDirection();
            gateAction(pacman.getNextY(), pacman.getNextX());
        }
    }

    public boolean enPasillo(int f, int c) {
        int countPared = 0;
        if ((f >= 0 && c >= 0) && (f < L.lab[0].length - PacmanGlobal.pacmanPixSize && c < L.lab.length - PacmanGlobal.pacmanPixSize)) {
            for (int i = 0; i < pacman.getPixSize(); ++i) {
                for (int j = 0; j < pacman.getPixSize(); ++j) {
                    if (L.lab[f + i][c + j] == L.PARED) {
                        countPared++;
                    }
                }
            }
            if (countPared > 25) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean termino(int f, int c) {
        // calculamos si llego a la meta
        //155--599
        int x, y = 0;
        x = f + PacmanGlobal.pacmanPixSize > Global.panelHeight ? Global.panelHeight - 5 : f + PacmanGlobal.pacmanPixSize;
        y = c + PacmanGlobal.pacmanPixSize > Global.panelWidth ? Global.panelWidth - 5 : c + PacmanGlobal.pacmanPixSize;

        //System.out.println("Inicio:"+f+"Final:"+c);
        boolean rest = false;
        for (int i = f - Global.pixSize; i < x; ++i) {
            for (int j = c - Global.pixSize; j < y; ++j) {
                if (i == Global.fin.y && j == Global.fin.x) {
                    rest = true;
                    break;
                }
            }
        }
        return rest;
    }

    public boolean enPuerta(int f, int c) {

        int contPx = 0;
        for (int i = f; i < f + PacmanGlobal.pacmanPixSize; ++i) {
            for (int j = c; j < c + PacmanGlobal.pacmanPixSize; ++j) {
                if (L.lab[i][j] == L.PUERTA_SALTO || L.lab[i][j] == L.PUERTA_BOMBA) {
                    contPx++;
                    Global.gameState = L.lab[i][j];
                }
            }
        }

        if (contPx >= PacmanGlobal.pacmanPixSize / 3) {
            return true;
        }
        return false;
    }

    public void gateAction(int f, int c) {
        if (Global.gameState == L.PUERTA_SALTO) {
            System.out.println("LLego puerta salto");
            Point p = L.getRandomPosition();

            int contX = 0;
            int contY = 0;
            for (int i = p.x; i < pacman.getPixSize(); ++i) {
                if (L.lab[f][i] == L.PARED || L.lab[f][i] == L.PUERTA_BOMBA || L.lab[f][i] == L.PUERTA_SALTO) {
                    if (i < (i + pacman.getPixSize()) / 2) {
                        contX--;
                    } else {
                        contX++;
                    }
                }
            }
            for (int j = p.y; j < pacman.getPixSize(); ++j) {
                if (L.lab[j][c] == L.PARED || L.lab[j][c] == L.PUERTA_BOMBA || L.lab[j][c] == L.PUERTA_SALTO) {
                    if (j < (j + pacman.getPixSize()) / 2) {
                        contY--;
                    } else {
                        contY++;
                    }
                }
            }

            setInitialPosition(p.x + contX, p.y + contY);

        } else {
            System.out.println("LLego puerta bomba");
            Global.gameState = 4;

        }
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
                Thread.sleep(pacman.getPixSize() * 10);
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
