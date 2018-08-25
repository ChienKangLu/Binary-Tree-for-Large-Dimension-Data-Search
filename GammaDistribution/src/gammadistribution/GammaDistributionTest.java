package gammadistribution;

/**
 *
 * @author leo
 */
public class GammaDistributionTest {

    public static void main(String args[]) {
        
        int k = 4;//the number of independent standard normal random variable
        double alpha = 0.5 * k; 
        double lambda = 0.5;
        int size =1000;//split(to simulate differential)
        double convergeThrd=0.0001;
        
        GammaDistribution gammaDistribution = new GammaDistribution(alpha,lambda,size);
        gammaDistribution.generateGammaDistribution(convergeThrd);
        Point2D[] points=gammaDistribution.getpoints();
        
        
        System.out.println("SHOW ALL POINTS start");
        for(int i=0;i<size;i++){
            System.out.println(points[i]);
        }
        System.out.println("SHOW ALL POINTS end\n");
        
        
        System.out.println("mean: "+gammaDistribution.getMean());
        System.out.println("variance: "+gammaDistribution.getVariance());
        //integral
        double lower_limit=950;
        //double upper_limit=0;
        boolean unbound=true;
        Integral integral=new Integral(points,gammaDistribution);
        integral.choose_RECTANGLE_METHOD(Integral.MIDDLE_POINT_RULE);
        double total_probability=integral.getIntegralValue(Integral.RECTANGLE_METHOD,lower_limit,unbound);
        System.out.println("intergal value(should be 1): "+total_probability);
        
    }
}
