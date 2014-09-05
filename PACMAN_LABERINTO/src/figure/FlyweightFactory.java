/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package figure;

/**
 *
 * @author rgap
 */
public class FlyweightFactory {
    
    /**
    * Pool
    */
    
    private static WallImp WALL;
    private static GateImp GATE;
    private static Pacman PACMAN;
    
    /**
     * getFlyweight
     * @return 
     */
    
    public static FigureImp getWall() {
        if (WALL == null) {
            WALL = new WallImp();
        }
        return WALL;
    }
    
    public static FigureImp getGate() {
        if (GATE == null) {
            GATE = new GateImp();
        }
        return GATE;
    }
    
    public static Pacman getPacman() {
        if (PACMAN == null) {
            PACMAN = new PacmanImp();
        }
        return PACMAN;
    }
}
