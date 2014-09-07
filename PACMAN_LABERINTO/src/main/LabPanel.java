package main;

import figure.FigureAbstract;
import figure.FlyweightFactory;
import gamelogic.PacmanLogic;
import imageProcessing.MapProcessing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class LabPanel extends JPanel {

    private Image dbImage;
    private Graphics dbg;
    private PacmanLogic pacman;
    private LabyrinthSingleton L;
    boolean inicia = true;

    private FigureAbstract wall = FlyweightFactory.getWall();
     private FigureAbstract gate = FlyweightFactory.getGate();

    public LabPanel() {
        setSize(Global.panelWidth, Global.panelHeight);
        setVisible(true);
        setBackground(Color.BLACK);

        L = LabyrinthSingleton.getInstance();
        //L.mostrar_lab();        
    }

    public void keyPressed(KeyEvent e) {
        pacman.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        pacman.keyReleased(e);
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        construirLaberinto(g);
        pacman.draw(g);
        repaint();
    }

    public void updatePanel(String path) {
        L.buildMap(path);

        pacman = new PacmanLogic();
        pacman.setInitialPosition( Global.inicio.x,Global.inicio.y);

        //Movimiento de Pacman
        Thread pacmanThread = new Thread(pacman);
        pacmanThread.start();
    }

    public void construirLaberinto(Graphics g) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < L.FILAS; ++i) {
            for (int j = 0; j < L.COLUMNAS; ++j) {
                if (L.lab[i][j] == L.PARED) {
                    g.drawImage(wall.getImage(), x, y, wall.getPixSize(), wall.getPixSize(), this);
                } else if (L.lab[i][j] == L.PUERTA) {
                    g.drawImage(gate.getImage(), x, y, gate.getPixSize(), gate.getPixSize(), this);
                }
                x += wall.getPixSize();
            }
            x = 0;
            y += wall.getPixSize();
        }
    }
}
