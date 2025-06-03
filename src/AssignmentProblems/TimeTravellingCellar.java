package AssignmentProblems;

public class TimeTravellingCellar {

    public int determineProfit(int[] profit, int[] decay){

        int maxProfit = 0;

        for (int p =0; p< profit.length; p++){
            for(int d = 0; d< decay.length; d++){
                if(p!=d){
                    int currentProfit = profit[p]-decay[d];
                    maxProfit = Math.max(maxProfit, currentProfit);
                }
            }
        }
        return maxProfit;
    }

}
