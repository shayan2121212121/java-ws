package Practice.PracticeAlgo;

public class PartitionEqualSubsetSum {

    public boolean canPartitian(int[] nums){
        int totalSum = 0;
        for(int i : nums){
            totalSum = totalSum + i;
        }
        //odd check
        if(totalSum%2 !=0) return false;
        
        //target part sum
        int target = totalSum/2;
        boolean[] dp = new boolean[target+1];
        dp[0]=true;

        for(int num: nums){
            for(int j=target; j>=num;j--){
                if(dp[j-num]){
                    dp[j]=true;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum tm = new PartitionEqualSubsetSum();

        System.err.println(tm.canPartitian(new int[]{2,4,1,5}));

    }

}
