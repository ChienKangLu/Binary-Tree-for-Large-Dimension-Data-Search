/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gammadistribution;

/**
 *
 * @author leo
 */
public class Point2D {
    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x+","+y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
    
}
