/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 * user may input a query
 * @author leo
 */
public class Query extends Data{
    
    /**
     * generated by normal distribution
     * @param dimension 
     */
    public Query(int dimension) {
        super(dimension);
    }

    @Override
    public String toString() {
        return "query:"+super.toString();
    }
    
    
}
