package main;

import figure.FigureAbstract;
import figure.FlyweightFactory;
import figure.Pacman;
import gamelogic.GameLogic;
import gamelogic.PacmanGlobal;
import imageProcessing.MapProcessing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LabPanel extends JPanel {

    private MainFrame frame;
    private Image dbImage;
    private Graphics dbg;
    private GameLogic game;
    private LabyrinthSingleton L;
    boolean inicia = true;

    private FigureAbstract wall = FlyweightFactory.getWall();
    private FigureAbstract gate = FlyweightFactory.getGate();

    public LabPanel(MainFrame frame) {
        this.frame = frame;
        setSize(Global.panelWidth, Global.panelHeight);
        setVisible(true);
        setBackground(Color.BLACK);

        L = LabyrinthSingleton.getInstance();
        //L.mostrar_lab();        
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }

    public void paint(Graphics g) {
        if(Global.gameState == 1 || Global.gameState == 4){
            PacmanGlobal.pacmanThread.interrupt();
            if (Global.gameState == 1) {
                JOptionPane.showMessageDialog(this, "Ganaste");
            } else if (Global.gameState == 4) {
                JOptionPane.showMessageDialog(this, "Perdiste");
            }
            frame.dispose();
        }
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        construirLaberinto(g);
        game.draw(g);
        repaint();
    }

    public void updatePanel(String path) {
        L.buildMap(path);
        
        game = new GameLogic();
        game.setInitialPosition(Global.inicio.x, Global.inicio.y);

        //Movimiento de Pacman
        PacmanGlobal.pacmanThread = new Thread(game);
        PacmanGlobal.pacmanThread.start();
    }

    public void construirLaberinto(Graphics g) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < L.FILAS; ++i) {
            for (int j = 0; j < L.COLUMNAS; ++j) {
                if (L.lab[i][j] == L.PARED) {
                    g.drawImage(wall.getImage(), x, y, wall.getPixSize(), wall.getPixSize(), this);
                } else if (L.lab[i][j] == L.PUERTA_SALTO || L.lab[i][j] == L.PUERTA_BOMBA) {
                    g.drawImage(gate.getImage(), x, y, gate.getPixSize(), gate.getPixSize(), this);
                }
                x += wall.getPixSize();
            }
            x = 0;
            y += wall.getPixSize();
        }
    }
}
