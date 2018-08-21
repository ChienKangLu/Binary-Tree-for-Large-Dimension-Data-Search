/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 * every node in binary search tree
 * @author leo
 */
public class BinaryNode<AnyType> {
    AnyType element;//the data store in node
    BinaryNode<AnyType> left;//this node's left node
    BinaryNode<AnyType> right;//this node's right node

    /**
     * 
     * @param element the data store in node
     * @param left this node's left node(initial can set to null)
     * @param right this node's right node(initial can set to null)
     */
    public BinaryNode(AnyType element,BinaryNode<AnyType> left,BinaryNode<AnyType> right) {
        this.element=element;
        this.left=left;
        this.right=right;
    }
    
    
    
    
}
