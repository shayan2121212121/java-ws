package AssignmentProblems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ErdosNumber {

    /*
     * Input {"ERDOS A", "A B", "B AA C"}
     * returns: {"A 1", "AA 3", "B 2", "C 3", "ERDOS 0" }

     */

    public static String[] calculateNumbers(String[] publications){

        Set<String> nodeSet = new HashSet<>();
        Map<String, List<String>> nodeMap = new HashMap<>();
        for(String s: publications){
            String[] splitString = s.split(" ");
            for(int i = 0; i< splitString.length;i++){//authors loop in each string
                nodeSet.add(splitString[i]);//add each author to set
                if(splitString.length>1){//more than one author in splitString
                    for(int j =0; j<splitString.length;j++){//loop for other authors in splitString
                        if(i!=j){
                            if(!nodeMap.containsKey(splitString[i])){//ith author not present in map
                                nodeMap.put(splitString[i], new ArrayList<>());//add in map with empy list
                            }
                            nodeMap.get(splitString[i]).add(splitString[j]);//add neighbor to the list in map
                        }
                        
                    }
                }
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add("ERDOS");
        visited.add("ERDOS");
        int count = 1;//level 1 erdos number
        Map<String,Integer> erdosNumMap = new HashMap<>();
        erdosNumMap.put("ERDOS", 0);//root value

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                //currNode
                String currNode = queue.remove();
                //no target have to travel all

                //neighbors
                List<String> neighbors = nodeMap.getOrDefault(currNode, new ArrayList<>());
                for(String neighbor: neighbors){
                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                        erdosNumMap.put(neighbor, count);
                    }
                }
            }
            count++;
        }

        List<String> resList = new ArrayList<>();
        erdosNumMap.forEach((k,v)->{
            StringBuilder sb = new StringBuilder();
            sb.append(k);
            sb.append(" ");
            sb.append(Integer.toString(v));
            resList.add(sb.toString());
        });
        //check for elements that are not in visited set but are in nodeSet
        for(String author: nodeSet){
            if(!visited.contains(author)){
                StringBuilder sb = new StringBuilder();
                sb.append(author);
                resList.add(sb.toString());
            }
        }
        String[] result = resList.toArray(new String[0]);
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        String[] s = {"ERDOS B", "A B C", "B A E", "D F"};
        String[] result = calculateNumbers(s);
        System.out.println("Network delay time: " + result); // Output: 2
    }

}
