package main;

import com.googlecode.javacv.CanvasFrame;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;
import imageProcessing.MapProcessing;
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
    public int PASILLO = 0;
    public int PARED = 1;
    public int PUERTA = 2;
    public Point inicio;
    public Point fin;
    public int lab[][];
    private MapProcessing mapProcessing;

    private LabyrinthSingleton() {
        mapProcessing = new MapProcessing();
    }

    /* Static 'instance' method */
    public static LabyrinthSingleton getInstance() {
        if (singleton == null) {
            singleton = new LabyrinthSingleton();
        }
        return singleton;
    }

    private int getPixSize(IplImage img_bin) {
        ByteBuffer buffer = img_bin.getByteBuffer();
        int value = 0, firstval = -1, i;
        for (i = 0; i < img_bin.height(); i++) {
            int index = i * img_bin.widthStep() + i * img_bin.nChannels();
            value = buffer.get(index) & 0xFF;

            if (firstval == -1) {
                firstval = value;
            } else if (firstval != value) {
                break;
            }
        }

        //int pix = i + 1;
        int pix = 10;
        Global.pixSize = 10;
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
    
    public void buildMap(String path) {
        System.out.println(path);
        IplImage im = mapProcessing.getProcessedImage(path);
        buildLabyrinth(im);
        //mostrar_lab();
    }

    private void buildLabyrinth(IplImage img_bin) {
        FILAS = img_bin.height();
        COLUMNAS = img_bin.width();

        Global.pixSize = 1;
        Global.panelWidth = COLUMNAS;
        Global.panelHeight = FILAS;
        lab = new int[FILAS][COLUMNAS];

        ByteBuffer buffer = img_bin.getByteBuffer();
        int value = 0;
        for (int y = 0; y < img_bin.height(); y++) {
            for (int x = 0; x < img_bin.width(); x++) {
                int index = y * img_bin.widthStep() + x * img_bin.nChannels();
                value = buffer.get(index) & 0xFF;
                if (value > 0) {
                    lab[y][x] = PARED;
                } else {
                    lab[y][x] = PASILLO;
                }
            }
        }
    }
}
