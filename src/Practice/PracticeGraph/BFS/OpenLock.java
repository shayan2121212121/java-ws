package Practice.PracticeGraph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OpenLock {

    public static int bfs(String[] deadEnds, String target){
        int count = 0;
        Set<String> deads = new HashSet<>(Arrays.asList(deadEnds));

        //start point will be "0000" 
        //create queue
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        //add root
        queue.add("0000");
        visited.add("0000");

        while(!queue.isEmpty()){
            int queueSize = queue.size();

            for(int i = 0; i<queueSize; i++){
                //get current obj
                String currCombination = queue.remove();

                //check if target
                if(currCombination.equals(target)){
                    return count;
                }

                List<String> neighbors = getNeighbors2(currCombination);
                for(String neighbor: neighbors){
                    //add in set and queue if not visited and not in deadEnds
                    if(!visited.contains(neighbor) && !deads.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            count++;
        }


        return -1;//not possible to open the lock
    }

    public static List<String> getNeighbors2(String currCombination){//e.g. "0000"
        List<String> neighbors = new ArrayList<>();

        char[] chars = currCombination.toCharArray();

        for(int i = 0; i<4;i++){
            char oldChar = chars[i];

            //turn ith up convert to string add to neighbors
            int upInt = oldChar - '0';//convert char to int
            if(upInt == 9){//check if 9 make it 0
                upInt = 0;
            } else {//else increase int by 1
                upInt= upInt+ 1;
            }
            char[] upChars = chars.clone();//clone original chars
            upChars[i] = (char)(upInt+'0');//change current char in clone to updated int char-note upint is changed to char using +'0'
            neighbors.add(new String(upChars));//type cast the clone char array to string and add in list

            //turn ith down convert to string add to neighbors
            int downCharint = oldChar - '0';//
            if(downCharint == 0){
                downCharint = 9;
            } else {
                downCharint=downCharint-1;
            }
            char[] downChars = chars.clone();
            downChars[i] = (char)(downCharint+'0');
            neighbors.add(new String(downChars));
        }
        return neighbors;
    }

    private static List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = node.toCharArray();

        for (int i = 0; i < 4; i++) {
            char c = chars[i];

            // Turn wheel up
            chars[i] = (char)((c - '0' + 1) % 10 + '0');
            neighbors.add(new String(chars));

            // Turn wheel down
            chars[i] = (char)((c - '0' + 9) % 10 + '0'); // (c - 1 + 10) % 10
            neighbors.add(new String(chars));

            // Reset character
            chars[i] = c;
        }

        return neighbors;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        int moves = bfs(deadends, target);
        System.out.println("Minimum moves to open the lock: " + moves);
    }

}
