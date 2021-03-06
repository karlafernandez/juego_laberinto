package main;
import figure.FlyweightFactory;
import figure.Gate;
import figure.Pacman;
import figure.Wall;
import figure.WallImp;
import gamelogic.PacmanLogic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LabPanel extends JPanel{ 

    private Image dbImage;
    private Graphics dbg;
    private PacmanLogic pacman;
    private LabyrinthSingleton L;
    boolean inicia=true;
    
    private Wall wall = FlyweightFactory.getWall();
    private Gate gate = FlyweightFactory.getGate();
    
    public LabPanel(){
        //Propiedades        
        setSize(500,500);
        setVisible(true);
        setBackground(Color.BLACK);
        
        L = LabyrinthSingleton.getInstance();
        L.mostrar_lab();

        pacman = new PacmanLogic();
        pacman.updatePosition(100, 100);
        
        //Hilos
        Thread t1 = new Thread(pacman);
        t1.start();
    }
    
    public void keyPressed(KeyEvent e){
        pacman.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
        pacman.keyReleased(e);
    }
    
    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void paintComponent(Graphics g){
        construirLaberinto(g);
        pacman.draw(g);
        repaint();
    }
    
    public void construirLaberinto(Graphics g){
                
        int x = 0;
        int y = 0;
        for(int i=0; i < L.FILAS; ++i){
            for(int j=0; j < L.COLUMNAS; ++j){
                if(L.lab[i][j]==L.PARED){
                    g.drawImage(wall.getImage(), x, y, wall.getPixSize(), wall.getPixSize(), this);
                    x += wall.getPixSize();
                }
                else
                    x += wall.getPixSize();
            }
            x = 0;
            y += wall.getPixSize();
        }
    }
    
}
