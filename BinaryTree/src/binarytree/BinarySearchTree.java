/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import java.util.ArrayList;

/**
 * AnyType must implements Comparable and Difference
 * 
 * @author leo
 */
public class BinarySearchTree <AnyType extends Comparable<? super AnyType> & Difference<? super AnyType>>{
        private BinaryNode<AnyType> root;//tree's root

        /**
         * initial BinarySearchTree
         */
        public BinarySearchTree(){
            this.root=null;
        }
        /**
         * remove this tree
         */
        public void makeEmpty(){
            this.root=null;
        }
        
        public boolean isEmpty(){
            return this.root==null;
        }
        /**
         * judge whether x is contain in this tree
         * @param x we wnat to find
         * @return 
         */
        public boolean contains(AnyType x){
            return contains(x,this.root);
        }
        /**
         * for query function!!!
         * 
         * find the similar data of x with the accuracy
         * @param x the data we want
         * @param accuracy the difference between x and similar one
         * @return  list of all similar data
         */
        public ArrayList<AnyType> findSimilar(AnyType x,double accuracy){
            ArrayList<AnyType> similarData=new ArrayList<>();
            findSimilar(x,this.root,accuracy,similarData);
            return similarData;
        }
        /**
         * Use to find the min value of binarySearchTree
         * if the tree is empty, throw UnderflowException and show "this is an empty tree"
         * 
         * 
         * @return min value
         * @throws UnderflowException 
         */
        public AnyType findMin() throws UnderflowException{
            if(isEmpty()){
                throw new UnderflowException();
            }
            return findMin(this.root).element;
        }
        /**
         * Use to find the max value of binarySearchTree
         * if the tree is empty, throw UnderflowException and show "this is an empty tree"
         * 
         * 
         * @return max value
         * @throws UnderflowException 
         */
        public AnyType findMax() throws UnderflowException{
            if(isEmpty()){
                throw new UnderflowException();
            }
            return findMax(this.root).element;
        }
        public void insert(AnyType x){
            root=insert(x,this.root);
        }
        public void remove(AnyType x){
            root=remove(x,this.root);
        }
        public void printTree(){
            printTree(this.root,0);
        }
        
        /**
         * Use recursive way to judge whether the x is contain in t's subtree or not
         * if x is larger than t, find t's right subtree
         * if x is smaller than t, find t's left subtree
         * 
         * 
         * @param x the thing we want to find
         * @param t subtree's node
         * @return true if contain , false if not contain
         */
        private boolean contains(AnyType x,BinaryNode<AnyType> t){
            if(t==null){
                return false;
            }
            int compareResult=x.compareTo(t.element);
            if(compareResult<0)
                return contains(x,t.left);
            else if(compareResult>0)
                return contains(x,t.right);
            else
                return true;
        }
        /**
         * for query function!!!
         * 
         * find the similar data of x with the accuracy
         * 
         * @param x the data we want
         * @param t the subtree or root 
         * @param accuracy the difference between x and similar one
         * @param similarData the list is to store all similardata which will return to main(like bundle)
         */
        private void findSimilar(AnyType x,BinaryNode<AnyType> t,double accuracy,ArrayList<AnyType> similarData){
            if(t!=null){
                judgeDiffer(x,t,accuracy,similarData);
                if(t.left!=null){
                    findSimilar(x,t.left,accuracy,similarData);
                }
                if(t.right!=null){
                    findSimilar(x,t.right,accuracy,similarData);
                }
            }else{
                return;
            }
        }
        /**
         * for query function!!!
         * 
         * judge the two data whether similar or not
         * 
         * @param x the data we want
         * @param t the subtree or root 
         * @param accuracy the difference between x and similar one
         * @param similarData the list is to store all similardata which will return to main(like bundle)
         */
        private void judgeDiffer(AnyType x,BinaryNode<AnyType> t,double accuracy,ArrayList<AnyType> similarData){
            double differnece=Math.abs(x.differentTo(t.element));//calculate the difference
            //System.out.println(differnece);
            if(differnece<=accuracy){
                similarData.add(t.element);
                //System.out.println(t.element);
            }
        }
        /**
         * Use recursive way to find min
         * 
         * @param t subtree's node
         * @return min node
         */
        private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
            if(t==null){
                return null;
            }else if(t.left==null){
                return t;
            }
            return findMin(t.left);
        }
        /**
         * Use while loop way to find max
         * 
         * @param t subtree's node
         * @return max node
         */
        private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
            if(t!=null)
                while(t.right!=null)
                    t=t.right;
            return t;
        }
        /**
         * Use recursive way to insert,
         * every recursion will return the subtree root 
         * except the ending point one which will return a new Node
         * 
         * if find duplicate,let it go,do not save
         * 
         * @param x the value we want to insert
         * @param t tree's subset,first is root
         * @return 
         */
        private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
            /*
                ending point,find the place to insert new value
            */
             if(t==null){
                 return new BinaryNode<AnyType>(x,null,null);
             }
             int compareResult=x.compareTo(t.element);
             if(compareResult<0){
                 t.left=insert(x,t.left);
             }else if(compareResult>0){
                 t.right=insert(x,t.right);
             }else{
                 //find the duplicate value,I did not do anything now,maybe can use extra structure to save it(e.g. the number of appear time)
             }
             return t;//now subtree's root
             
        }
        /**
         * remove x from tree
         * @param x the value we want to remove
         * @param t subtree's root
         * @return 
         */
        private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
            if(t==null)
                return t;//x is not contain in this tree.
            int compareResult=x.compareTo(t.element);
            if(compareResult<0){
                 t.left=remove(x,t.left);
            }else if(compareResult>0){
                 t.right=remove(x,t.right);
            }else if(t.left!=null &&t.right!=null){
                /**
                 * If the x has two child node,we have to find a node to replace its position.
                 * For meets the constraint of binary search tree,we will choose the smallest one in the right subtree of x.
                 * then remove the smallest one in right subtree of x
                 * 
                 */
                t.element = findMin(t.right).element;
                t.right = remove(t.element,t.right);
            }
            else
                /**
                 * We found x,then return the remaining node to x's original parent node
                 */
                t = (t.left!=null)?t.left:t.right;
            return t;
        }
        /**
         * print tree 
         * 
         * root
         *      left
         *      right
         * 
         * @param t root
         * @param tabNumber the number of tab
         */
        private void printTree(BinaryNode<AnyType> t,int tabNumber){
            if(t!=null){
                for(int i=0;i<tabNumber;i++){
                    System.out.print("      ");
                }
                System.out.println(t.element);
                if(t.left!=null){
                    printTree(t.left,tabNumber+1);
                }
                if(t.right!=null){
                    printTree(t.right,tabNumber+1);
                }
            }else{
                return;
            }
        }
        
}
