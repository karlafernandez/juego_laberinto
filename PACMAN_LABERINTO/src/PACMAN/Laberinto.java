package PACMAN;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Laberinto {
    DecimalFormatSymbols simbolos;
    DecimalFormat formateador;
    
    int inicio;
    
    int FILAS=13;
    int COLUMNAS=20;
    int PARED = -2;
    
    int contnodos=-1;
    
    int tempnodo;
    double pesoarista=1;
    
    int lab[][]= {
        {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
        {-2,-1,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-1,-2,-2,-2,-1,-2},
        {-2,-1,-2,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2},
        {-2,-1,-1,-1,-2,-1,-1,-2,-1,-1,-1,-2,-2,-2,-2,-2,-1,-2,-2,-2},
        {-2,-2,-2,-2,-2,-1,-1,-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2,-2,-1,-2,-1,-2,-2,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-1,-1,-2,-1,-1,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-2,-1,-2,-1,-1,-1},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2},
        {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
        {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}
    };
   
    public Laberinto(){
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        formateador = new DecimalFormat("####.#",simbolos);        
    }
    
    public void mostrar_lab(){
        for(int i=0; i<FILAS; ++i){
            for(int j=0; j<COLUMNAS; ++j){
                if(lab[i][j]!=PARED)
                    System.out.print(lab[i][j]+"\t");
                else
                    System.out.print("*\t");
            }
            System.out.print("\n");
        }
    }
    
}