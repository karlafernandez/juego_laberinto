package main;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    
    static LabPanel P = new LabPanel();

    public MainFrame(){
        setTitle("PACMAN");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
                
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("Inicio");
        menuBar.add(menu);

        menuItem = new JMenuItem("Reiniciar Juego");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Cargar Mapa");
        menuItem.getInputMap();
        menu.add(menuItem);
        
        menu = new JMenu("Ayuda");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Acerca del juego");
        menu.add(menuItem);

        setJMenuBar(menuBar);
        
        addKeyListener(new AL());
        
    }
    
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            P.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            P.keyReleased(e);
        }
    }
    
    public static void main(String[] args) {
        //Frame
        MainFrame frame = new MainFrame();
        
        frame.setVisible(true);
        frame.add(P);
        frame.setSize(P.size());
    } 
}
