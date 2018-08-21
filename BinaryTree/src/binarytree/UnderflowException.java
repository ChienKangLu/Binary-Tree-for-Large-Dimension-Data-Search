/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 * Exception of empty tree
 * @author leo
 */
public class UnderflowException extends Exception{
    public UnderflowException(){
        super("this is an empty tree");
    }
}
