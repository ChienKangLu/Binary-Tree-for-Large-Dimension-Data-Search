/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import binarytree.Difference;
import java.util.Random;

/**
 * 
 * @author leo
 */
public class Data implements Comparable<Data>,Difference<Data>{
    private int dimension;//dimension of data
    private double attr[];//every dimention's value
    final Random rd =new Random();
    /**
     * compare with deifferent dimension!
     */
    public static int whichToCompare=0;
    
    /**
     * 
     * @param dimension the data'dimension
     */
    public Data(int dimension){
        this.dimension=dimension;
        this.attr=new double[dimension];
        initialData();
    }
    /**
     * for test only,create non-arbitray data from aray
     * @param dimension 
     * @param array 
     */
    public Data(int dimension,double[]array){
        this.dimension=dimension;
        this.attr=array;
    }
    /**
     * create data with normal distribution
     */
    private void initialData() {
        for(int i=0;i<this.dimension;i++){
            attr[i]=rd.nextGaussian();
        }
    }
    
    public double[] getAttr() {
        return attr;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        String s="";
        s+="[";
        for(int i=0;i<this.dimension;i++){
            if(i!=0)
                s+=",";
            //s+=attr[i];
            s+=String.format("%-10.3f", attr[i]);
        }
        s+="]";
        return s;
    }
//    @Override
//    public String toString() {
//        String s="";
//        for(int i=0;i<this.dimension;i++){
//            if(i!=0)
//                s+=",";
//            s+=attr[i];
//        }
//        return s;
//    }


    /**
     * compare with other data
     * @param t the data we want to compare with
     * @return -1:smaller than t,1:bigger than t,0:equal to t
     */
    @Override
    public int compareTo(Data t) {
        if(this.attr[whichToCompare]-t.attr[whichToCompare]>0){
            return 1;
        }else if(this.attr[whichToCompare]-t.attr[whichToCompare]<0){
            return -1;
        }else{
            return 0;
        }
    }
    
    /**
     * calculate the difference with t
     * @param t the data we want to compare with
     * @return difference
     */
    @Override
    public double differentTo(Data t) {
        return this.attr[whichToCompare]-t.attr[whichToCompare];
    }
    
    
}
