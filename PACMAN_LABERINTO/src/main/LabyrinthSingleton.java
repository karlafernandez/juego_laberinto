package main;

import java.util.Random;

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
    
}