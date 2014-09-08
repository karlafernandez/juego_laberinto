package main;

import imageProcessing.MapProcessing;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements ActionListener {

    public static LabPanel P = null;

    JFileChooser fc;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem_resetGame;
    private JMenuItem menuItem_loadMap;
    private JMenuItem menuItem_about;

    public MainFrame() {
        setTitle("JUEGO PACLAB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);

        menuBar = new JMenuBar();
        menu = new JMenu("Inicio");
        menuBar.add(menu);
        menuItem_resetGame = new JMenuItem("Reiniciar Juego");
        menu.add(menuItem_resetGame);

        fc = new JFileChooser();
        menuItem_loadMap = new JMenuItem("Cargar Mapa");
        menuItem_loadMap.addActionListener(this);
        menu.add(menuItem_loadMap);

        menu = new JMenu("Ayuda");
     
        menuBar.add(menu);
        menuItem_about = new JMenuItem("Acerca del juego");
        menu.add(menuItem_about);
        setJMenuBar(menuBar);
        addKeyListener(new AL());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem_loadMap) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String path = file.getAbsolutePath();
                updateFrame(path);
            }
        }
    }

    public void updateFrame(String path) {
        P = new LabPanel(this);
        P.updatePanel(path);
        this.add(P);
        setSize(Global.panelWidth, Global.panelHeight + 50);
    }
    
    //mapProcessing.
    public class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            P.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            P.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        //Frame
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.updateFrame("");
    }
}
