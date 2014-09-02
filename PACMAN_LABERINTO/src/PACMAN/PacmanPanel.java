package PACMAN;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PacmanPanel extends JPanel{ 

    private Image dbImage;
    private Graphics dbg;
    Pacman pacman;
    Laberinto L;
    boolean inicia=true;
    
    public PacmanPanel(){
        //Propiedades        
        setSize(1000,650);
        setVisible(true);
        setBackground(Color.BLACK);
        
        L=new Laberinto();
        L.mostrar_lab();

        pacman = new Pacman(400,550);
        
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
        for(int i=0; i<L.FILAS; ++i){
            for(int j=0; j<L.COLUMNAS; ++j){
                if(L.lab[i][j]==L.PARED){
                    g.drawImage(new ImageIcon("C:\\Users\\jorge\\Documents\\GitHub\\MaestriaPW\\PACMAN_LABERINTO\\recursos\\wall.jpg").getImage(), x, y, 50, 50, this);
                    x+=50;
                }
                else
                    x+=50;
            }
            x=0;
            y+=50;
        }
    }
    
}
