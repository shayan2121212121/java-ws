package AssignmentProblems;

import java.util.Arrays;

public class FillTheBoar {
    public int eaten(int stomach, int[] muffins, int delay) {

        Arrays.sort(muffins);
        int n = muffins.length;
        int[] revMuffins = new int[n];

        for(int i = 0; i<n; i++){
            revMuffins[n-1-i] = muffins[i];
        }
        int fedWeight = 0;
        int fedCount = 0;

        for(int i = 0; i<n; i++){
            if(fedWeight >= stomach){
                break;
            }
            fedWeight = fedWeight+revMuffins[i];
            fedCount++; 
        }

        if(delay>0 && fedCount<n){
            int[] remainingMuffins = Arrays.copyOfRange(revMuffins, fedCount, n);
            if(delay<=remainingMuffins.length){
                for(int i = 0; i<delay;i++){
                    fedWeight = fedWeight+remainingMuffins[i];
                    fedCount++;
                }
            } else {
                for(int i = 0; i<remainingMuffins.length;i++){
                    fedWeight = fedWeight+remainingMuffins[i];
                    fedCount++;
                }
            }
        }

        return fedWeight;
    }

    public static int eaten2(int stomach, int[] muffins, int delay) {
        int n = muffins.length;
        Arrays.sort(muffins); 

        int[] dp = new int[n + 1]; 
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int m : muffins) {
            for (int i = n - 1; i >= 0; i--) {
                if (dp[i] != -1) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[i] + m);
                }
            }
        }

        int maxSum = 0;
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= stomach) {
                int extra = Math.min(delay, n - i); 
                int total = dp[i];

                for (int j = 0; j < extra; j++) {
                    total += muffins[n - 1 - j]; 
                }
                maxSum = Math.max(maxSum, total);
            }
        }

        
        if (dp[n] < stomach) return dp[n];

        return maxSum;
    }
}
