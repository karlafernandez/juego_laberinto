/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageProcessing;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;
import javax.swing.JFrame;

/**
 *
 * @author pisque
 */
public class MapProcessing {

    private String currentDir;
    private String fileName;
    private boolean showWindows;

    public MapProcessing() {
        currentDir = System.getProperty("user.dir");
        showWindows = false;
    }

    public IplImage getProcessedImage(String filePath) {
        System.out.println(filePath);
        fileName = currentDir + "/recursos/mapa6.png";

        opencv_core.IplImage img_rgb = cvLoadImage(fileName, 1);
        if (img_rgb == null) {
            System.out.println("Couldn't load source image.");
            return null;
        }
        if (showWindows) {
            CanvasFrame canvas_rgb = new CanvasFrame("Source");
            canvas_rgb.showImage(img_rgb);
            canvas_rgb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        //////////////////////////////
        opencv_core.IplImage img_grayscale = cvCreateImage(cvGetSize(img_rgb), IPL_DEPTH_8U, 1);
        cvCvtColor(img_rgb, img_grayscale, CV_BGR2GRAY);

        if (showWindows) {
            CanvasFrame canvas_grays = new CanvasFrame("Gray");
            canvas_grays.showImage(img_grayscale);
            canvas_grays.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        //////////////////////////////
        opencv_core.IplImage img_bin = cvCreateImage(cvGetSize(img_grayscale), IPL_DEPTH_8U, 1);
        cvThreshold(img_grayscale, img_bin, 127, 255, CV_THRESH_BINARY);

        if (showWindows) {
            CanvasFrame canvas_bin = new CanvasFrame("Bin");
            canvas_bin.showImage(img_bin);
            canvas_bin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        return img_bin;
    }
}
