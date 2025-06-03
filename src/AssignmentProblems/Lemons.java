package AssignmentProblems;

public class Lemons {

        public int maxSum(int[] arr) {
            int inputlen = arr.length;
          
            int[] dp = new int[inputlen + 1];
    
            // Base cases
            dp[0] = 0;
            dp[1] = arr[0];
    
            for (int i = 2; i <= inputlen; i++) {
                dp[i] = Math.max(arr[i - 1] + dp[i - 2], dp[i - 1]);
            }
            return dp[inputlen];
        }

    

}
