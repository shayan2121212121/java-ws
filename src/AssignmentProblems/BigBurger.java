package AssignmentProblems;

public class BigBurger {
    //calculate the wait time in line for a customer and return max
    public static int maxWait(int[] arrival, int[] service){
       
        int[] servTime = new int[arrival.length];
        servTime[0]=arrival[0]+service[0];

        int maxWait = 0;
        for(int i=1; i< arrival.length;i++){
            if(servTime[i-1]>arrival[i]){
                int wait = servTime[i-1]-arrival[i];
                maxWait=Math.max(maxWait, wait);
                servTime[i] = servTime[i-1]+service[i];
            } else {
                servTime[i] = arrival[i] + service[i];
            }
        }
        return maxWait;
    }

    public static void main(String[] args) {
        int[] arrival = {2,10,11};
        int[] service = {3,4,3};
        int result = maxWait(arrival, service);
        System.out.println("Network delay time: " + result); // Output: 2
    }

}
