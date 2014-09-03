package main;
import com.googlecode.javacv.CanvasFrame;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGet2D;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;
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
    
    public int lab[][] = new int[FILAS][COLUMNAS];
    
    private LabyrinthSingleton(){
        rand_Lab();
    }
    
     /* Static 'instance' method */
   public static LabyrinthSingleton getInstance() {
      if(singleton == null) {
         singleton = new LabyrinthSingleton();
      }
      return singleton;
   }
    
    public void mostrar_lab() {
        for(int i=0; i < FILAS; ++i){
            for(int j=0; j < COLUMNAS; ++j){
                if(lab[i][j]!=PARED)
                    System.out.print(lab[i][j]+"\t");
                else
                    System.out.print("*\t");
            }
            System.out.print("\n");
        }
    }
    
    public void rand_Lab() {
        Random rand = new Random();
        
        for(int i=0; i < FILAS; ++i){
            for(int j=0; j < COLUMNAS; ++j){
                lab[i][j] = rand.nextInt(2) + 0;
            }
        }
    }
    
    
    public void escalagris(String[] args) {
        
        String fileName = "C:\\javacv-bin\\samples\\Shapes2.jpg";
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
        IplImage img_bin = cvCreateImage(cvGetSize(img_rgb), IPL_DEPTH_8U, 1);
        cvThreshold(img_grayscale, img_bin, 127, 255, CV_THRESH_BINARY);
        
        CanvasFrame canvas_bin = new CanvasFrame("Bin");
        canvas_bin.showImage(img_bin);
        canvas_bin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////////////////////////
        
        IplImage img_scaled = cvCreateImage(cvSize(500, 500), IPL_DEPTH_8U, 1);        
        cvResize(img_bin, img_scaled, CV_INTER_LINEAR);
        
        CanvasFrame canvas_scaled = new CanvasFrame("Bin");
        canvas_scaled.showImage(img_scaled);
        canvas_scaled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // img_scaled
        
        int val;
        for (int j = 0; j < img_scaled.height(); j++) {
            for (int k = 0; k < img_scaled.width(); k++) {
                val = (int) cvGet2D(img_scaled, j, k).getVal(0);
                
            }
        }
    }
    
}