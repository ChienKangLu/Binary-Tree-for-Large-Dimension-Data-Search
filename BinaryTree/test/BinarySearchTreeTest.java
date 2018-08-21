
import DataBase.Data;
import binarytree.BinarySearchTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leo
 */
public class BinarySearchTreeTest {
    public static void main(String args[]) {
        BinarySearchTree binaryST=new BinarySearchTree();
        
        Data a=new Data(2,new double[]{7,2});
        Data b=new Data(2,new double[]{9,6});
        Data c=new Data(2,new double[]{10,3});
        Data d=new Data(2,new double[]{1,4});
        Data e=new Data(2,new double[]{4,13});
        Data f=new Data(2,new double[]{8,5});
        Data.whichToCompare=1;
        binaryST.insert(a);
        binaryST.insert(b);
        binaryST.insert(c);
        binaryST.insert(d);
        binaryST.insert(e);
        binaryST.insert(f);
       // System.out.println(BinarySearchTree.total);
//        binaryST.insert(10);
//        binaryST.insert(4);
//        binaryST.insert(5);
//        binaryST.insert(3);
//        binaryST.insert(11);
//        binaryST.insert(7);
//        binaryST.insert(8);
        binaryST.printTree();
    }
}
