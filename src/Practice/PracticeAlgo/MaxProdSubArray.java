package Practice.PracticeAlgo;

public class MaxProdSubArray {

    public int maxProduct(int[] nums){
        int minProd = 1;
        int maxProd = 1;
        int res = nums[0];

        for(int i =0; i<nums.length;i++){
            int tempMax = maxProd*nums[i];
            maxProd = Math.max(nums[i], Math.max(maxProd*nums[i], minProd*nums[i]));
            minProd = Math.min(nums[i], Math.min(tempMax*nums[i], minProd*nums[i]));

            res = Math.max(res, Math.max(maxProd, minProd));
        }


        return res;

    }

    public static void main(String[] args) {
        MaxProdSubArray tm = new MaxProdSubArray();

        System.err.println(tm.maxProduct(new int[]{2,3,-2,-4,5}));

    }

}
