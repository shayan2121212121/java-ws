package AssignmentProblems;

import java.util.ArrayList;
import java.util.List;

public class FibonacciDiv2 {

    
    public int find(int N){
        int noOfSteos = 0;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        boolean addElement = true;
        List<Integer> fibseries = new ArrayList<>();

        while(addElement == true) {
            int lastVal = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = lastVal;
            fibseries.add(dp[1]);
            if(dp[1]> N){
                addElement = false;
            }
        }
        int lastTerm = fibseries.get(fibseries.size()-1);
        int secondLastTerm = fibseries.get(fibseries.size()-2);
        noOfSteos = Math.min((lastTerm - N), (N - secondLastTerm));
        return noOfSteos;
    }

}
