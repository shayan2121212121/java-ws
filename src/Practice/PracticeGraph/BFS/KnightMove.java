package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class KnightMove {

    /*
     * Given an infinite chess board
     * starting position (0, 0) and a target position (x, y)
     * find the minimum number of knight moves to reach the target.
     */
    //practice
    public static int bfsPractice(int x, int y){
        //knight can move in 8 final positions, consider from 0,0
        int[][] directions = new int[][]{
            {-2,1},//two up then right
            {-2,-1},//two up then left
            {2,1},//two down then right
            {2,-1},//two down then left
            {1,2},//two right one down
            {-1,2},//two right one up
            {1,-2},//two left one down
            {-1,2}//two left one up
        };
        //queue 
        Queue<int[]> queue = new ArrayDeque<>();
        Set<int[]> visited = new HashSet<>();

        queue.add(new int[]{0,0});//root
        visited.add(new int[]{0,0});

        int count =0;

        while(!queue.isEmpty()){
            //need level count for min no of moves so for loop is needed
            int size = queue.size();
            for(int i=0;i<size;i++){
                //curr node
                int[] currNode = queue.remove();

                //check estination
                if(currNode[0]==x && currNode[1]==y)return count;

                //movement
                for(int[] dir: directions){
                    int ni = currNode[0]+dir[0];
                    int nj = currNode[1]+dir[1];
                    //boundary
                    if(ni>=0 && nj >=0){
                       // if(!visited.contains(new int[]{ni,nj})){
                            queue.add(new int[]{ni,nj});
//                            visited.add(new int[]{ni,nj});
                        //}
                    }
                }
            }
            count++;
        }
        return -1;

    }
    public static int getMinMoves(int x, int y){
        //knight is horse-it can move 2 square in all four directions and 
        //for each of these four directions it can move two more directions, right or left
        //Therefore from (0,0) possible neighbors will be (2,1) and (1,2) 
        //we then check if they are not in visited set we add them to queue and continue bfs 
        //note each node is dependent on two values i,j so queue should contain a List of integer that will have two values

        //Create queue and set
        Queue<int[]> queue = new ArrayDeque<>();
        Set<int[]> visited = new HashSet<>();

        //add root
        int[] root = new int[]{0,0};
        queue.add(root);
        visited.add(root);

        //move count variable
        int count =0;

        while(!queue.isEmpty()){
            //get size to use in for loop as we need to maintain level count
            int size = queue.size();

            for(int i =0; i<size; i++){
                //get current node
                int[] currNode = queue.remove();

                //check if target
                if(currNode[0]==x && currNode[1]==y){
                    return count;
                }

                //get neighbors
                List<int[]> neighbors = new ArrayList<>();
                neighbors = getNeighbors(currNode[0], currNode[1]);
                for(int[] neighbor: neighbors){
                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            count++;

        }
        return -1;//should never reach
    }

    public static List<int[]> getNeighbors (int i, int j){
        //return object
        List<int[]> result = new ArrayList<>();

        //Initial right move - always possible because infinite board
        int[] rMove = new int[]{i,j+2};
        if(i>0){//check if one up after right move possible
            int[] rMoveUp = new int[]{rMove[0]-1,rMove[1]};
            result.add(rMoveUp);
        }
        int[] rMoveDown = new int[]{rMove[0]+1, rMove[1]};//down always possible because infinite board
        result.add(rMoveDown);

        //Initial left Move
        if(j>1){//check if left has 2 spaces from current
            int[] lMove = new int[]{i,j-2};

            if(i>0){//check if one up after left move possible
                int[] lMoveUp = new int[]{lMove[0]-1,lMove[1]};
                result.add(lMoveUp);
            }
            int[] lMoveDown = new int[]{lMove[0]+1, lMove[1]};//down always possible because infinite board
            result.add(lMoveDown);
        }

        //Initial down - always possible because infinite board
        int[] dMove = new int[]{i+2,j};
        if(j>0){//check if one left after down move possible
            int[] dMoveLeft = new int[]{dMove[0],dMove[1]-1};
            result.add(dMoveLeft);
        }
        int[] dMoveRight = new int[]{dMove[0], dMove[1]+1};//right always possible because infinite board
        result.add(dMoveRight);

        //initial up - possible if i>=2
        if(i>1){
            int[] upMove = new int[]{i-2,j};
            if(j>0){//check if left move possiblr
                int[] upMoveLeft = new int[]{upMove[0],upMove[1]-1};
                result.add(upMoveLeft);
            }
            int[] upMoveRight = new int[]{upMove[0],upMove[1]+1};
            result.add(upMoveRight);

        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(bfsPractice(5, 5)); // Output: 4
        //System.out.println(bfsPractice(2, 1)); // Output: 1
    }

}
