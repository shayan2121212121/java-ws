package AssignmentProblems;

import java.util.HashMap;
import java.util.Map;

public class ColorfulRabbits {

    public int getMinimum(int[] replies){

        Map<Integer,Integer> rabMap = new HashMap<>();
        for (int num : replies) {
            rabMap.put(num, rabMap.getOrDefault(num, 0) + 1);
        }
        
        int minRabbit = 0;
        for(Map.Entry<Integer,Integer> entry: rabMap.entrySet()){
            if(entry.getValue()==entry.getKey()+1){
                minRabbit+=entry.getValue();
            }
            if(entry.getValue()>entry.getKey()+1){
                int currNumCount = 0;
                currNumCount = (entry.getValue()/(entry.getKey()+1))*(entry.getKey()+1);
                if (entry.getValue()%(entry.getKey()+1)>0){
                    currNumCount = currNumCount + entry.getKey()+1;
                }
                minRabbit =minRabbit + currNumCount;
             }
            if(entry.getValue()<entry.getKey()+1){
                minRabbit= minRabbit+ entry.getKey()+1;
            }
        }

        return minRabbit;
    }

}
