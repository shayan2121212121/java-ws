package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PracticeTree {

    private int value;
    private List<PracticeTree> children;

    public PracticeTree(int value){
        this.value = value;
        this.children = new ArrayList<>();
    }

    public int getValue(){
        return this.value;
    }

    public List<PracticeTree> getChildren(){
        return this.children;
    }

    public PracticeTree addChild(int value){
        PracticeTree childTree = new PracticeTree(value);
        children.add(childTree);
        return childTree;
    }

    public PracticeTree bfs(int value, PracticeTree root){
        Queue<PracticeTree> queue = new ArrayDeque<>();
        queue.add(root); //add root

        while(!queue.isEmpty()){
            //remove first node from queue
            PracticeTree currNode = queue.remove();

            //check if value is found and return node if found
            if(currNode.getValue()==value){
                return currNode;
            } else{
                //if value not found add all childrent to queue
                queue.addAll(currNode.getChildren());
            }

            

        }
        //if not found return null
        return null;

    }
    

}
