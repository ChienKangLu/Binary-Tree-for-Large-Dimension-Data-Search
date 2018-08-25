/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gammadistribution;

import java.applet.Applet;

/**
 *
 * @author leo
 */
public class GammaApplet extends Applet{
    public GammaDistribution createGammaDistribution(double alpha,double lambda,int size){
        return new GammaDistribution(alpha,lambda,size);
    }
    public Integral createIntegral(Point2D[] points,GammaDistribution gammaDistribution){
        return new Integral(points,gammaDistribution);
    }
    public realTimeSearch createRealTimeSearch(){
        return new realTimeSearch();
    }
}
