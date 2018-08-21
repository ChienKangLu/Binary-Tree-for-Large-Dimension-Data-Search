/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.util.ArrayList;

/**
 * use this database to store all data
 * @author leo
 */
public class Database {
    private ArrayList<Data> data;//store all data
    private int number;//total number of data in this database
    private String name;//database name(for read)
    /**
     * 
     * @param number number of data
     * @param dimension dimension of data
     * @param name this database name
     */
    public Database(int number,int dimension,String name){
        this.data=new ArrayList<>();
        this.name=name;
        this.number=number;
        initialDatabase(dimension);
    }
    /**
     * initial every data
     * @param dimension 
     */
    private void initialDatabase(int dimension) {
        for(int i=0;i<number;i++){
            Data new_data=new Data(dimension);
            this.data.add(new_data);
        }
    }
    public ArrayList<Data> getData() {
        return data;
    }
    public int getNumber() {
        return number;
    }
    
    /**
     * Use this method to check whether the automatically generated data is normal distribution or not
     * @param attr which dimension(row data)
     */
    public void check(int attr){
        int bell[] = new int[10];
        for(int i=0;i<this.number;i++){
            double value=data.get(i).getAttr()[attr];
            double t = -2;
            for (int x = 0; x < 10; x++, t += 0.5) {
                if (value < t) {
                    bell[x]++;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int x = bell[i]; x > 0; x--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String s="";
        s+="name:"+name+"\n";
        s+="number:"+number+"\n";
        for(int i=0;i<number;i++){
            s+=this.data.get(i)+"\n";
        }
        return s;
    }
    
    
}
