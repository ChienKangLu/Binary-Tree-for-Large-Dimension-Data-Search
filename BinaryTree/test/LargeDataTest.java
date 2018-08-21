
import DataBase.Data;
import DataBase.Database;
import DataBase.Query;
import RealTimeAlgorithm.Algorithm;
import binarytree.BinarySearchTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 1)Generate 1000000 with 10 dimension
 * 2)create binary search tree for every dimension
 * 3)sort query vector form big to small
 * 4)search binary serach tree with accuracy for the biggest dimension of the query
 * 5)the other dimension use linear search
 * 6)judge whether real time or not
 * @author leo
 */

public class LargeDataTest {

    public static void main(String args[]) {
        int dataNumber = 1000000;//1000000
        int dim = 10;
        String dataBaseName = "TEST DATABASE";
        Database database = new Database(dataNumber, dim, dataBaseName);
        //System.out.println(database);

        //(1)construct a binary search tree for every attr
        title("(1)construct a binary search tree for every attr");
        BinarySearchTree<Data> tree[] = new BinarySearchTree[dim];
        for (int i = 0; i < dim; i++) {
            tree[i] = new BinarySearchTree<Data>();
            Data.whichToCompare=i;
            for (int j = 0; j < dataNumber; j++) {
                Data d=database.getData().get(j);
                tree[i].insert(d);
            }
            System.out.println("Create "+i+" attribute binary search tree");
            //tree[i].printTree();
        }
        
        //(2)sort query attr,start search from the biggest one
        title("(2)sort query attr,start search from the biggest one");
        Query query=new Query(dim);
        Integer index[]=sort(query.getAttr());
        System.out.println("After sorted the attr index:");
        for(int now : index){
            System.out.println(now);
        }
        
        
        //(3)search the largest attr by binary tree
        title("(3)search by a sorted attr");
        System.out.println(query);
        double accuracy=0.1;        
        int search_k_number=0;
        int searched_number=0;//the number of data which have been searched
        System.out.println("accuracy:"+accuracy+"\n");
        Data.whichToCompare=index[0];//select which index will be used for comparing        
        ArrayList<Data> similarData=tree[Data.whichToCompare].findSimilar(query,accuracy);
        System.out.println("1 round search from binary tree(attr["+index[0]+"]): "+similarData.size());
        searched_number+=similarData.size();
        search_k_number++;
        
        //(4)search the other attr by linear search
        title("(4)search the other attr by linear search");
        for(int i=1;i<index.length;i++){
            //System.out.println(query);
            ArrayList<Data> similarDataTemp=searchLinear(query,similarData,accuracy,i,index);
            if(similarDataTemp.size()>0){
                similarData=similarDataTemp;
                System.out.println((i+1)+" round search from linear search(attr["+index[i]+"]): "+similarData.size());
                search_k_number++;
                searched_number+=similarData.size();
            }else{
                System.out.println((i+1)+" round search from linear search(attr["+index[i]+"]): "+similarDataTemp.size());
                break;
            }
        }
        //(5)print the final similar data we found
        title("(5)print the final similar data we found");
        for(int i=0;i<similarData.size();i++){
            System.out.println(similarData.get(i));
        }
        
        //(6)check whether realtime
        //ONLY search_k_number and total Number of database dependency with previous stage!!
        title("(6)check whether realtime or not");
        Algorithm algorithm=new Algorithm(accuracy,search_k_number,1000, database, index);
        System.out.println("accuracy: "+accuracy);
        System.out.println("search_k_number: "+search_k_number);
        System.out.println("the number of data which have been searched:"+searched_number);
        double parameters[]=algorithm.checkrealtime(query);
        if(parameters[1]>=parameters[2]){
            System.out.println("This can be searched in realtime");
        }else{
            System.out.println("This can NOT be searched in realtime");
        }
        
        
    }
    /**
     * sort the data from big to small
     * @param data
     * @return sorted index
     */
    public static Integer[] sort(double[] data){
        Integer indexes[] = new Integer[data.length];
        for (int j = 0; j < data.length; j++) {
            indexes[j] = j;
        }
        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                double diff = data[y] - data[x];
                return diff < 0 ? -1 : diff > 0 ? 1 : 0;
            }
        });
        return indexes;
    }
    /**
     * for friendly read
     * @param title 
     */
    public static void title(String title){
        System.out.println("\n"+title+"\n");
    }
    /**
     * Linear search
     * 
     * @param query 
     * @param similarData all data
     * @param accuracy 
     * @param whichToCompare compare with which dimension
     * @param index the query sorted index
     * @return  similar data 
     */
    public static  ArrayList<Data> searchLinear(Data query,ArrayList<Data> similarData,double accuracy,int whichToCompare,Integer[] index){
        Data.whichToCompare=index[whichToCompare];//select which index will be used for comparing  

        ArrayList<Data> newSimilarData=new ArrayList<>();
        for(int i=0;i<similarData.size();i++){
            double differnece=Math.abs(similarData.get(i).differentTo(query));
            if(differnece<=accuracy){
                newSimilarData.add(similarData.get(i));
                //System.out.println(similarData.get(i));
            }
        }
        return newSimilarData;
    }

}
