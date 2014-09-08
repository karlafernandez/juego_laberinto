package main;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import imageProcessing.MapProcessing;
import java.awt.Point;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlElementDecl;

/*
 * Singleton
 */
public class LabyrinthSingleton {

    private class puntos {

        public Point punto;
        public int pasillos = 0;
    }
    private static LabyrinthSingleton singleton = null;
    public int FILAS = 50;
    public int COLUMNAS = 50;
    public int PASILLO = 0;
    public int PARED = 1;
    public int PUERTA_SALTO = 2;
    public int PUERTA_BOMBA = 3;
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

    private List<puntos> Accesos() {
        List<puntos> Puertas = new ArrayList<puntos>();
        // recorriendo lengh +10 parte superior
        int value = 0;
        for (int i = 0; i < lab.length - 1; i++) {
            if (lab[i][Global.pixSize] == PASILLO) {
                value++;
            } else {
                if (value > 10) {
                    puntos pt = new puntos();
                    pt.pasillos = value;
                    pt.punto = new Point(Global.pixSize, i - value + 2);
                    Puertas.add(pt);

                }
                value = 0;
            }
        }
        // recorriendo en y
        value = 0;
        for (int i = 0; i < lab[0].length - 1; i++) {
            if (lab[Global.pixSize][i] == PASILLO) {
                value++;
            } else {
                if (value > 12) {
                    puntos pt = new puntos();
                    pt.pasillos = value;
                    pt.punto = new Point(i - value + 2, Global.pixSize);
                    Puertas.add(pt);

                }
                value = 0;
            }
        }
        // recorrido en  x al final
        value = 0;
        for (int i = 4; i < lab.length - 1; i++) {
            if (lab[lab.length - 5][i] == PASILLO) {
                value++;
            } else {
                if (value > 12) {
                    puntos pt = new puntos();
                    pt.pasillos = value;
                    pt.punto = new Point(i - value + 2, lab.length - 5);
                    Puertas.add(pt);
                }
                value = 0;
            }
        }
        return Puertas;
    }

    private void calcularpuntos() {
        List<puntos> puertas = Accesos();

        if (puertas.size() >= 0) {
            Global.inicio = new Point(puertas.get(0).punto.x, puertas.get(0).punto.y);
            // colocar el final al centro del pacillo
            Global.fin = new Point(puertas.get(1).punto.x + Global.pacmanPixSize, puertas.get(1).punto.y - Global.pacmanPixSize / 2);
            System.out.print(Global.fin.x + "-" + Global.fin.y);
        }
    }

    private int getPixSize(IplImage img_bin) {
        ByteBuffer buffer = img_bin.getByteBuffer();
        int value = 0, firstval = -1, i;
        for (i = 1; i < img_bin.height(); i++) {
            int index = i * img_bin.widthStep() + i * img_bin.nChannels();
            value = buffer.get(index) & 0xFF;

            if (firstval == -1) {
                firstval = value;
            } else if (firstval != value) {
                break;
            }
        }

        int pix = i + 1;
        //int pix = 10;
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
        setGates();
        //mostrar_lab();
    }

    public void setGates() {
        int numGates = 5;
        ArrayList<Point> paredes = new ArrayList<Point>();
        for (int i = 0; i < FILAS; ++i) {
            for (int j = 0; j < COLUMNAS; ++j) {
                if (lab[i][j] == PARED) {
                    paredes.add(new Point(i, j));
                }
            }
        }

        int limit = Global.pacmanPixSize * 3;
        int maximum = paredes.size() - 1;
        int minimum = 0;
        int range = maximum - minimum + 1;
        Random rn = new Random();
        int step[] = {-1, -1, -1, 1, 1, 1, 1, -1};

        for (int k = 0; k < numGates; k++) {
            int randPos = rn.nextInt(range) + minimum;
            Point current = paredes.get(randPos);

            lab[current.y][current.x] = PUERTA_BOMBA;

            for (int p = 1; p <= limit / 3 + 1; ++p) {
                int c_pasillo = 0;

                int i = current.y + p * step[0];
                for (int j = current.x + p * step[1]; i >= 0 && i < Global.panelHeight
                        && j >= 0 && j < Global.panelWidth && j < current.x + p * step[3]; ++j) {
                    System.out.println(i + " - " + Global.panelHeight);
                    if (lab[i][j] == PASILLO) {
                        c_pasillo++;
                    } else {
                        lab[i][j] = PUERTA_BOMBA;
                    }
                }
                if (c_pasillo == limit) {
                    break;
                }
                //////////////
                c_pasillo = 0;

                i = current.x + p * step[3];
                for (int j = current.y + p * step[2]; i >= 0 && i < Global.panelWidth
                        && j >= 0 && j < Global.panelHeight && j < current.y + p * step[4]; ++j) {
                    if (lab[j][i] == PASILLO) {
                        c_pasillo++;
                    } else {
                        lab[j][i] = PUERTA_BOMBA;
                    }
                }
                if (c_pasillo == limit) {
                    break;
                }
                //////////////
                c_pasillo = 0;

                i = current.y + p * step[4];
                for (int j = current.x + p * step[7]; i >= 0 && i < Global.panelHeight
                        && j >= 0 && j < Global.panelWidth && j < current.x + p * step[5]; ++j) {
                    if (lab[i][j] == PASILLO) {
                        c_pasillo++;
                    } else {
                        lab[i][j] = PUERTA_BOMBA;
                    }
                }
                if (c_pasillo == limit) {
                    break;
                }

                //////////////
                c_pasillo = 0;

                i = current.x + p * step[7];
                for (int j = current.y + p * step[0]; i >= 0 && i < Global.panelWidth && j >= 0 && j < Global.panelHeight && j < current.y + p * step[6]; ++j) {
                    if (lab[j][i] == PASILLO) {
                        c_pasillo++;
                    } else {
                        lab[j][i] = PUERTA_BOMBA;
                    }
                }
                if (c_pasillo == limit) {
                    break;
                }

            }
        }
    }

    private void buildLabyrinth(IplImage img_bin) {
        FILAS = img_bin.height();
        COLUMNAS = img_bin.width();
        Global.pacmanPixSize = getPixSize(img_bin) / 2 + 1;
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
        calcularpuntos();
    }
}
