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
public class realTimeSearch {
    public realTimeSearch(){
    }
    /**
     * 
     * @param k
     * @param delta
     * @param dataNumber
     * @param thresholdNumber
     * @return the probability of real time search
     */
    public double estimate(int k,double delta, double dataNumber, double thresholdNumber){
        double Pth=thresholdNumber/dataNumber;
        System.out.println(Pth);
        System.out.println("**"+k*(2*Math.log(2*delta)-Math.log(2*Math.PI)));
        return k*(2*Math.log(2*delta)-Math.log(2*Math.PI))-Math.log(Pth);
    }
}
