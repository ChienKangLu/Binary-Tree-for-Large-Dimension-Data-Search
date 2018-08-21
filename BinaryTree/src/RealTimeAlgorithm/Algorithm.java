/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RealTimeAlgorithm;

import DataBase.Database;
import DataBase.Query;
import java.util.Arrays;
import java.util.Comparator;

/**
 * this algorithm for judge a query can be finished in real time or not
 * @author leo
 */
public class Algorithm {
    double delta; // 0~2
    int k;//usedDimension //1~max
    int Sth;//restrict number of data//1~max
    double Pth;//restrict ratio
    Database db;
    Integer indexes[];//this a n array for saving the query's order
    
    /**
     * 
     * @param delta the accuracy
     * @param k dimension we use
     * @param Sth searching number of data limit
     * @param db 
     * @param indexes 
     */
    public Algorithm(double delta, int k, int Sth,Database db,Integer indexes[]) {
        this.delta = delta;
        this.k = k;
        this.Sth = Sth;
        this.db=db;
        this.Pth= (double)this.Sth/db.getNumber();
        this.indexes=indexes;
    }
    /**
     * check the query can be finish in real time or not
     * @param query
     * @return 
     */
    public double[] checkrealtime(Query query){
        //sort query big to small
        double queryData[]=query.getAttr();
        
        //prvious k dimensions
        double querylength=vectorsquare(queryData,this.k,indexes);
        
        double unkownNameYet=this.k*(2*Math.log(2*this.delta)+2*Math.log(2*Math.PI))-Math.log(this.Pth);
        System.out.println("Pth: "+this.Pth);
        System.out.println("querylength: "+querylength);
        System.out.println("unkownNameYet: "+unkownNameYet);
        
        double data[]={this.Pth,querylength,unkownNameYet};
        
        return data;
        
    }
    /**
     * calculate the vector's sum of square 
     * @param data this vector
     * @param k dimension we use 
     * @param index the vector's order
     * @return sum of square 
     */
    public double vectorsquare(double[] data,int k,Integer[] index){
        double value=0;
        for(int i=0;i<k;i++){
            value+=Math.pow(data[index[i]], 2);
        }
        return value;
    }
    
}
