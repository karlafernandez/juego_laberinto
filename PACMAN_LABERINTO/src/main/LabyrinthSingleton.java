package main;

import com.googlecode.javacv.CanvasFrame;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;
import java.awt.Point;
import java.nio.ByteBuffer;
import java.util.Random;
import javax.swing.JFrame;

/*
 * Singleton
 */
public class LabyrinthSingleton {

    private static LabyrinthSingleton singleton = null;

    public int FILAS = 50;
    public int COLUMNAS = 50;
    public int PARED = 1;
    public int PUERTA = 2;
    public int PASILLO = 0;
    public Point inicio; 
    public Point fin; 
    public int lab[][] = new int[FILAS][COLUMNAS];

    private LabyrinthSingleton() {
        //rand_Lab();
        matriz();
    }

    /* Static 'instance' method */
    public static LabyrinthSingleton getInstance() {
        if (singleton == null) {
            singleton = new LabyrinthSingleton();
        }
        return singleton;
    }
    
    private int getPixSize(IplImage img_bin)
    {
        ByteBuffer buffer = img_bin.getByteBuffer();
        int value = 0, firstval = -1, i;
        for (i = 0; i < img_bin.height(); i ++) {
            int index = i * img_bin.widthStep() + i * img_bin.nChannels();
            value = buffer.get(index) & 0xFF;
            
            if(firstval == -1)
                firstval = value;
            else if (firstval != value)
                break;
        }
        
        //int pix = i + 1;
        int pix = 10;
        Global.pixSize=10;
        return pix;
    }

    public void mostrar_lab() {
        for (int i = 0; i < FILAS; ++i) {
            for (int j = 0; j < COLUMNAS; ++j) {
                if (lab[i][j] != PARED) {
                    System.out.print(lab[i][j] + "\t");
                } else {
                    System.out.print("*\t");
                }
            }
            System.out.print("\n");
        }
    }

    public void rand_Lab() {
        Random rand = new Random();

        for (int i = 0; i < FILAS; ++i) {
            for (int j = 0; j < COLUMNAS; ++j) {
                lab[i][j] = rand.nextInt(2) + 0;
            }
        }
    }
    

    
    public void matriz() {
        String currentDir = System.getProperty("user.dir");
        String fileName = currentDir+"/recursos/mapa6.png";
        IplImage img_rgb = cvLoadImage(fileName, 1);
        if (img_rgb == null) {
            System.out.println("Couldn't load source image.");
            return;
        }
        CanvasFrame canvas_rgb = new CanvasFrame("Source");
        canvas_rgb.showImage(img_rgb);
        canvas_rgb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //////////////////////////////
        IplImage img_grayscale = cvCreateImage(cvGetSize(img_rgb), IPL_DEPTH_8U, 1);
        cvCvtColor(img_rgb, img_grayscale, CV_BGR2GRAY);

        CanvasFrame canvas_grays = new CanvasFrame("Gray");
        canvas_grays.showImage(img_grayscale);
        canvas_grays.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //////////////////////////////
        IplImage img_scaled = cvCreateImage(cvSize(500, 500), IPL_DEPTH_8U, 1);
        cvResize(img_grayscale, img_scaled, CV_INTER_LINEAR);

        CanvasFrame canvas_scaled = new CanvasFrame("Bin");
        canvas_scaled.showImage(img_scaled);
        canvas_scaled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////////////////////////
        IplImage img_bin = cvCreateImage(cvGetSize(img_scaled), IPL_DEPTH_8U, 1);
        cvThreshold(img_scaled, img_bin, 127, 255, CV_THRESH_BINARY);

        CanvasFrame canvas_bin = new CanvasFrame("Bin");
        canvas_bin.showImage(img_bin);
        canvas_bin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////////////////////////
        ByteBuffer buffer = img_bin.getByteBuffer();
        int value = 0;
        int countwhites;
        int pixSize=getPixSize(img_bin);
        for (int y = 0; y < img_bin.height(); y += pixSize) {
            for (int x = 0; x < img_bin.width(); x += pixSize) {
                countwhites = 0;
                for(int i = 0; i < pixSize; i++) {
                    for(int j = 0; j < pixSize; j++) {
                        int index = (y + i) * img_bin.widthStep() + (x + j) * img_bin.nChannels();
                        value = buffer.get(index) & 0xFF;

                        if (value > 0) // white - wall
                            countwhites += 1;
                    }
                }
                
                if(countwhites > 50)
                    lab[y/10][x/10] = PARED;
                else if (countwhites==50)
                    lab[y/10][x/10] = PUERTA;
                    else
                    lab[y/10][x/10] = PASILLO;
            }
        }
    }
    
    

}
