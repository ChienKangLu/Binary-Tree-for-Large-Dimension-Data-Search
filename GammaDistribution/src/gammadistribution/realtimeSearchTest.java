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
public class realtimeSearchTest {
    
    public static void main(String args[]) {
 
        int size = 1000;//split(to simulate differential)
        double convergeThrd=0.0001;
        //double delta=0.5;
        int dataNumber=1000000000;
        //int thresholdNumber=90;
        int test_K[]={1,100};////the number of independent standard normal random variable(min,max)
        System.out.println("delta"+","+"k"+","+"thresholdNumber"+","+"total_probability");
        for(double delta=0.1;delta<=1;delta+=0.1){
            for(int k=test_K[0];k<=test_K[1];k++){
                for(int thresholdNumber=1;thresholdNumber<=dataNumber;thresholdNumber++){
                    //System.out.println("k:"+k);
                    double alpha = 0.5 * k; 
                    double lambda = 0.5;
                    GammaDistribution gammaDistribution = new GammaDistribution(alpha,lambda,size);
                    gammaDistribution.generateGammaDistribution(convergeThrd);
                    Point2D[] points=gammaDistribution.getpoints();


                    //calculate lower_limit
                    realTimeSearch realTimeSearch=new realTimeSearch();


                    double lower_limit=realTimeSearch.estimate(k, delta, dataNumber, thresholdNumber);
                    //System.out.println("lower_limit"+lower_limit);
                    //double upper_limit=0;
                    boolean unbound=true;
                    Integral integral=new Integral(points,gammaDistribution);
                    integral.choose_RECTANGLE_METHOD(Integral.MIDDLE_POINT_RULE);
                    double total_probability=integral.getIntegralValue(Integral.RECTANGLE_METHOD,lower_limit,unbound);
                    //System.out.println("intergal value(should be 1): "+total_probability);
                    System.out.println(delta+","+k+","+thresholdNumber+","+total_probability);
                }
            }                                                                                                                                           
        }
        /*
        int size = 1000;//split(to simulate differential)
        double convergeThrd=0.0001;
        double delta=0.5;
        int dataNumber=100;
        int thresholdNumber=90;
        int test_K[]={1,100};////the number of independent standard normal random variable(min,max)
        
        for(int k=test_K[0];k<=test_K[1];k++){
            System.out.println("k:"+k);
            double alpha = 0.5 * k; 
            double lambda = 0.5;
            GammaDistribution gammaDistribution = new GammaDistribution(alpha,lambda,size);
            gammaDistribution.generateGammaDistribution(convergeThrd);
            Point2D[] points=gammaDistribution.getpoints();


            //calculate lower_limit
            realTimeSearch realTimeSearch=new realTimeSearch();


            double lower_limit=realTimeSearch.estimate(k, delta, dataNumber, thresholdNumber);
            System.out.println("lower_limit"+lower_limit);
            //double upper_limit=0;
            boolean unbound=true;
            Integral integral=new Integral(points,gammaDistribution);
            integral.choose_RECTANGLE_METHOD(Integral.MIDDLE_POINT_RULE);
            double total_probability=integral.getIntegralValue(Integral.RECTANGLE_METHOD,lower_limit,unbound);
            System.out.println("intergal value(should be 1): "+total_probability);
        }*/

    }
}
