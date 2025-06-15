package Practice.PracticeAlgo;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;

        // Check validity
        if (Math.abs(target) > sum || (target + sum) % 2 != 0) return 0;

        int P = (target + sum) / 2;
        if (P < 0) return 0;  // Safety check

        int[] dp = new int[P + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = P; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[P];
    }

    public static void main(String[] args) {
        TargetSum solver = new TargetSum();
        
        System.out.println(solver.findTargetSumWays(new int[]{1,1,1,1,1}, 3)); // Output: 5
        System.out.println(solver.findTargetSumWays(new int[]{1}, 1));         // Output: 1
        System.out.println(solver.findTargetSumWays(new int[]{100}, -200));    // Output: 0 (no crash!)
    }
}


