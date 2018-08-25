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
public class Integral {

    private Point2D[] data;
    private int size;
    public static final String RECTANGLE_METHOD = "RECTANGLE_METHOD";
    public static final String MIDDLE_POINT_RULE = "MIDDLE_POINT_RULE";
    private static String USE_RECTANGLE_METHOD = "";
    GammaDistribution gammadistribution;
    /**
     * 
     * @param data data point 
     * @param gammadistribution used to generate the middlepoint 
     */
    public Integral(Point2D[] data, GammaDistribution gammadistribution) {
        this.data = data;
        this.size = data.length;
        this.gammadistribution = gammadistribution;
    }
    /**
     * 
     * @param method choose which method will be used on RECTANGLE_METHOD
     */
    public void choose_RECTANGLE_METHOD(String method) {
        USE_RECTANGLE_METHOD = method;
    }

    public double getIntegralValue(String method,double lower_limit,boolean unbound) {//integral from 0 to size
        double integral = 0;
        switch (method) {
            case RECTANGLE_METHOD:
                integral = rectangle_method(USE_RECTANGLE_METHOD,lower_limit,unbound);
        }
        return integral;
    }

    /**
     * calculate the middle point's y (probability generate from gammadistribution)
     * @param method
     * @param lower_limit 
     * @param upper_limit 
     * @param unbound if true just sum all point use method else if false use lower_limit and upper_limit 
     * @return integral
     */
    private double rectangle_method(String method,double lower_limit,boolean unbound) {
        double sum = 0;
        switch (method) {
            case MIDDLE_POINT_RULE:
                for (int i = 0; i <= size - 2; i++) {
                    double x1 = data[i + 1].getX();
                    double x2 = data[i].getX();
                    if(x1>=lower_limit || unbound){
                        sum += (x1 - x2) * middlePoint(x1, x2);
                    }
                }
                break;

        }
        return sum;
    }
    
    private double middlePoint(double x1, double x2) {
        double middlepoint = (x1 + x2) / 2;
        return gammadistribution.getProbability(middlepoint);
    }


}
