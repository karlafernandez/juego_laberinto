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

    private static int colorToRGB(int alpha, int red, int red0, int red1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int[] imageHistogram(BufferedImage original) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
    
    
    private static BufferedImage toGray(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                // Return back to original format
                newPixel = colorToRGB(alpha, red, red, red);

                // Write pixels into image
                lum.setRGB(i, j, newPixel);

            }
        }
        return lum;
    }
    
     // Get binary treshold using Otsu's method
    private static int otsuTreshold(BufferedImage original) {

        int[] histogram = imageHistogram(original);
        int total = original.getHeight() * original.getWidth();

        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;

            if(wF == 0) break;

            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
    
    private static BufferedImage binarize(BufferedImage original) {
        int red;
        int newPixel;
        int threshold = otsuTreshold(original);

        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                // Get pixels
                red = new Color(original.getRGB(i, j)).getRed();
                int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel); 
            }
        }
        return binarized;
    }
    
}
